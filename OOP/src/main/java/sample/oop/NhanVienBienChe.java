package sample.oop;

public class NhanVienBienChe extends NhanVien {
    public String heSoLuong;
    public float luongCoBan;
    public float phuCap;

    public NhanVienBienChe(Integer id, String ten, String msnv, String namSinh, String thoiGianVaoLam, float luong, String heSoLuong, float luongCoBan,float phuCap) {
        super(id, ten, msnv, namSinh, thoiGianVaoLam, luong);
        this.heSoLuong = heSoLuong;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
    }
    public NhanVienBienChe(){

    }

    public String getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(String heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public float getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(float luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public float getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(float phuCap) {
        this.phuCap = phuCap;
    }
}
