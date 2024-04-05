/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Khanh
 */
public class San {
    private int MaSan;
    private String TenSan, LoaiSan;
    private float GiaSan;

    public San(String tensan, String loaisan, float giasan) {

        this.TenSan = tensan;
        this.LoaiSan = loaisan;
        this.GiaSan = giasan;
    }

    public San(int MaSan, String TenSan, String LoaiSan, float GiaSan) {
        this.MaSan = MaSan;
        this.TenSan = TenSan;
        this.LoaiSan = LoaiSan;
        this.GiaSan = GiaSan;
    }

    public int getMaSan() {
        return MaSan;
    }

    public void setMaSan(int MaSan) {
        this.MaSan = MaSan;
    }

    public String getTenSan() {
        return TenSan;
    }

    public void setTenSan(String TenSan) {
        this.TenSan = TenSan;
    }

    public String getLoaiSan() {
        return LoaiSan;
    }

    public void setLoaiSan(String LoaiSan) {
        this.LoaiSan = LoaiSan;
    }

    public float getGiaSan() {
        return GiaSan;
    }

    public void setGiaSan(float GiaSan) {
        this.GiaSan = GiaSan;
    }
    
}
