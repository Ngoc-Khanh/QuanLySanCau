package DangNhap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class DangNhapController {
    private DangNhapView view;
    private DangNhapModel model;
    
    public DangNhapController(DangNhapView view, DangNhapModel model) {
        this.view = view;
        this.model = model;
        this.view.addDangNhapListioner(new dangNhapListioner());
        this.view.addThoatListioner(new thoatsListener());
    }
    
    private void openIndexFrm() {
        // Gọi form mới từ package khác
        new MainMenu.MainMenuView().setVisible(true);
    }

    class dangNhapListioner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = view.getUser();
            String pass = view.getPass();
            
            if (model.kiemTra(user, pass)) {
                JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");
                view.dispose();
                openIndexFrm();
            } else {
                view.displaySai("Sai tài khoản hoặc mật khẩu!");
            }
        }         
    }
    
    class thoatsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }    
    }
}