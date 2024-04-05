/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Khanh
 */
public class KhachHang {

    private int MaKH, SoLanDat;
    private String TenKH, SDT;

    public KhachHang(String tenkh, String sdt) {

        this.TenKH = tenkh;
        this.SDT = sdt;
       
    }

    public KhachHang(int MaKH, String TenKH, String SDT, int SoLanDat) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.SoLanDat = SoLanDat;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public int getSoLanDat() {
        return SoLanDat;
    }

    public void setSoLanDat(int SoLanDat) {
        this.SoLanDat = SoLanDat;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

}
