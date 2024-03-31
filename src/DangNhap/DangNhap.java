/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DangNhap;

/**
 *
 * @author Krug
 */
public class DangNhap {
    private String userName;
    private String passWord;
    
    public DangNhap() { }

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
        return "admin".equals(userName) && "admin".equals(passWord);
    }
}
