import DangNhap.DangNhapController;
import DangNhap.DangNhapModel;
import DangNhap.DangNhapView;

public class Main {
    public static void main(String[] args) {
        DangNhapModel model = new DangNhapModel();
        DangNhapView view = new DangNhapView();
        DangNhapController controller = new DangNhapController(view, model);

        view.setVisible(true);    
    }
}