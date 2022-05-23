
package sample.oop;

public class NhanVienHopDong extends NhanVien{
    private float soNgayLam;
    private float tienCongTheoNgay;

    public NhanVienHopDong(Integer id, String ten, String msnv, String namSinh, String thoiGianVaoLam, Float luong, float soNgayLam, float tienCongTheoNgay) {
        super(id, ten, msnv, namSinh, thoiGianVaoLam, luong);
        this.soNgayLam = soNgayLam;
        this.tienCongTheoNgay = tienCongTheoNgay;
    }
    public NhanVienHopDong(){

    }

    public float getSoNgayLam() {
        return soNgayLam;
    }

    public void setSoNgayLam(float soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public float getTienCongTheoNgay() {
        return tienCongTheoNgay;
    }

    public void setTienCongTheoNgay(float tienCongTheoNgay) {
        this.tienCongTheoNgay = tienCongTheoNgay;
    }
}
