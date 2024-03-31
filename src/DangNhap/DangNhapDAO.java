/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DangNhap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Krug
 */
public class DangNhapDAO {
    private DangNhapFrm view;
    private DangNhap model;
    
    public DangNhapDAO(DangNhapFrm view, DangNhap model) {
        this.view = view;
        this.model = model;
        this.view.addDangNhapListioner(new dangNhapListioner());
        this.view.addThoatListioner(new thoatsListener());
    }
    
    class dangNhapListioner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = view.getUser();
            String pass = view.getPass();
            
            if (model.kiemTra(user, pass)) {
                JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");
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
