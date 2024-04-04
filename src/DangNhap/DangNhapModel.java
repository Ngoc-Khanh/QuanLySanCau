package DangNhap;

public class DangNhapModel {
    private String userName;
    private String passWord;
    
    public DangNhapModel() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public boolean kiemTra(String userName, String passWord) {
        return "admin".equals(userName) && "1".equals(passWord);
    }

    public class index {

        public void setVisible(boolean b) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
        }
    }
}
