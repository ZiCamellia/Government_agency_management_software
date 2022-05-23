package sample.oop;

import java.io.File;
import java.time.LocalDate;

public class NhanVien {
    private Integer id;
    private String ten;
    private String msnv;
    private String namSinh;
    private String loaiNhanVien;
    private String thoiGianVaoLam;
    private Float luong;

    public NhanVien(Integer id, String ten, String msnv, String namSinh, String loaiNhanVien, String thoiGianVaoLam, Float luong) {
        this.id = id;
        this.ten = ten;
        this.msnv = msnv;
        this.namSinh = namSinh;
        this.loaiNhanVien = loaiNhanVien;
        this.thoiGianVaoLam = thoiGianVaoLam;
        this.luong = luong;
    }
    public NhanVien(Integer id, String ten, String msnv, String namSinh,String thoiGianVaoLam, Float luong) {
        this.id = id;
        this.ten = ten;
        this.msnv = msnv;
        this.namSinh = namSinh;
        this.thoiGianVaoLam = thoiGianVaoLam;
        this.luong = luong;
    }
    public NhanVien(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMsnv() {
        return msnv;
    }

    public void setMsnv(String msnv) {
        this.msnv = msnv;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(String loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    public String getThoiGianVaoLam() {
        return thoiGianVaoLam;
    }

    public void setThoiGianVaoLam(String thoiGianVaoLam) {
        this.thoiGianVaoLam = thoiGianVaoLam;
    }

    public Float getLuong() {
        return luong;
    }

    public void setLuong(Float luong) {
        this.luong = luong;
    }
}
