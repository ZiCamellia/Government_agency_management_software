package sample.oop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerTT implements Initializable {
    @FXML
    TableView<NhanVien> tableView ;
    @FXML
    TableColumn<NhanVien, Integer> idColumn ;
    @FXML
    TableColumn<NhanVien, String> tenColumn;
    @FXML
    TableColumn<NhanVien, String> msnvColumn ;
    @FXML
    TableColumn<NhanVien, String> nsColumn ;
    @FXML
    TableColumn<NhanVien, String> thoiGianVaoLamColumn ;
    @FXML
    TableColumn<NhanVien, String> loaiNVColumn ;
    @FXML
    TableColumn<NhanVien, Integer> luongColumn;

    @FXML
    TextField txtId, txtTen, txtMSNV, txtLuong;
    @FXML
    DatePicker datePicker, dateNS;
    @FXML
    Button btnthem;
    @FXML
    TextField txtKeyword;
    @FXML
    ComboBox<String> cbLoaiNV ;
    NhanVien nhanVien;


    ObservableList<NhanVien> nhanVienList = FXCollections.observableArrayList();


    public void them(ActionEvent event){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(Integer.parseInt(txtId.getText()));
        nhanVien.setTen(txtTen.getText());
        nhanVien.setMsnv(txtMSNV.getText());
        nhanVien.setNamSinh(dateNS.getEditor().getText());
        nhanVien.setLoaiNhanVien(String.valueOf(cbLoaiNV.getSelectionModel().getSelectedItem()));
        nhanVien.setThoiGianVaoLam(datePicker.getEditor().getText());
        nhanVien.setLuong(Float.valueOf((txtLuong.getText())));
        nhanVienList.add(nhanVien);

        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();

        String loaiNV = cbLoaiNV.getSelectionModel().getSelectedItem();
        String id = txtId.getText();
        String ten = txtTen.getText();
        String msnv = txtMSNV.getText();
        String ns = dateNS.getEditor().getText();
        String thoiGianVaoLam = datePicker.getEditor().getText();
        String luongCoBan = txtLuong.getText();
        String luong = luongCoBan;
        String heSoLuong = "0";
        String phuCap = "0";
        String soNgayLam = "0";
        String tienCongTheoNgay = "0";
        if(loaiNV.isEmpty() || ten.isEmpty() || msnv.isEmpty() || ns.isEmpty() || thoiGianVaoLam.isEmpty() || luong.isEmpty()){
            AlertType alertAlertType;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            alert.show();
        }
        else{
            try{
                String sql = "insert into nhanvien(id, ten, msnv, namSinh, thoiGianVaoLam, loaiNV, luong) values('"+id+"','"+ten+"', '"+msnv+"', '"+ns+"', '"+thoiGianVaoLam+"', '"+loaiNV+"', '"+luong+"')";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                if(cbLoaiNV.getSelectionModel().getSelectedItem().equals("Biên chế")){
                    String sql2 = "insert into nhanvienbienche(id, ten, msnv, namSinh, thoiGianVaoLam, luong,luongCoBan, heSoLuong,phuCap) values('"+id+"','"+ten+"', '"+msnv+"', '"+ns+"', '"+thoiGianVaoLam+"','"+luongCoBan+"', '"+luongCoBan+"', '"+heSoLuong+"','"+phuCap+"')";
                    Statement st2 = conn.createStatement();
                    st2.executeUpdate(sql2);
                }
                else if(cbLoaiNV.getSelectionModel().getSelectedItem().equals("Hợp đồng")){
                    String sql3 = "insert into nhanvienhopdong(id,ten,msnv,namSinh,thoiGianVaoLam,luong,soNgayLam,tienCongTheoNgay) values ('"+id+"','"+ten+"', '"+msnv+"', '"+ns+"', '"+thoiGianVaoLam+"', '"+luong+"', '"+soNgayLam+"', '"+tienCongTheoNgay+"')";
                    Statement st3 = conn.createStatement();
                    st3.executeUpdate(sql3);
                }



            }catch (SQLException e){
                e.printStackTrace();
            }
        }




    }
    public void capNhat(){

        NhanVien nhanVien = new NhanVien();

        nhanVien = tableView.getSelectionModel().getSelectedItem();
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();

        String id = txtId.getText();
        String loaiNV = cbLoaiNV.getSelectionModel().getSelectedItem();
        String ten = txtTen.getText();
        String msnv = txtMSNV.getText();
        String ns = dateNS.getEditor().getText();
        String thoiGianVaoLam = datePicker.getEditor().getText();
        String luongCoBan = txtLuong.getText();
        String luong = luongCoBan;
        String heSoLuong = "0";
        String phuCap = "0";
        String soNgayLam = "0";
        String tienCongTheoNgay = "0";
        try{
            String sql = "UPDATE `nhanvien` SET `id` = '"+id+"' ,`ten`='"+ten+"',`msnv`='"+msnv+"',`namSinh`='"+ns+"',`thoiGianVaoLam`='"+thoiGianVaoLam+"',`loaiNV`='"+loaiNV+"',`luong`='"+luong+"' WHERE `id` = '"+id+"'";

            Statement st = conn.createStatement();
            st.executeUpdate(sql);

            if(cbLoaiNV.getSelectionModel().getSelectedItem().equals("Biên chế")){
                String sql2 = "UPDATE `nhanvienbienche` SET `id`='"+id+"',`ten`='"+ten+"',`msnv`='"+msnv+"',`namSinh`='"+ns+"',`thoiGianVaoLam`='"+thoiGianVaoLam+"',`luong`='"+luongCoBan+"',`luongCoBan`='"+luong+"',`heSoLuong`='"+heSoLuong+"',`phuCap`='"+phuCap+"' WHERE `id` = '"+id+"'";
                Statement st2 = conn.createStatement();
                st2.executeUpdate(sql2);
            }
            else if(cbLoaiNV.getSelectionModel().getSelectedItem().equals("Hợp đồng")){
                String sql2 = "UPDATE `nhanvienhopdong` SET `id`='"+id+"',`ten`='"+ten+"',`msnv`='"+msnv+"',`namSinh`='"+ns+"',`thoiGianVaoLam`='"+thoiGianVaoLam+"',`luong`='"+luongCoBan+"',`soNgayLam`='"+soNgayLam+"',`tienCongTheoNgay`='"+tienCongTheoNgay+"' WHERE `id` = '"+id+"'";
                Statement st2 = conn.createStatement();
                st2.executeUpdate(sql2);
            }

            AlertType alertAlertType;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Cập nhật thành công!");
            alert.show();
            nhanVienList.remove(nhanVien);
            nhanVien.setId(Integer.parseInt(txtId.getText()));
            nhanVien.setTen(txtTen.getText());
            nhanVien.setMsnv(txtMSNV.getText());
            nhanVien.setNamSinh(dateNS.getEditor().getText());
            nhanVien.setLoaiNhanVien(String.valueOf(cbLoaiNV.getSelectionModel().getSelectedItem()));
            nhanVien.setThoiGianVaoLam(datePicker.getEditor().getText());
            nhanVien.setLuong(Float.valueOf((txtLuong.getText())));
            nhanVienList.add(nhanVien);

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    public void reset(){
        txtTen.setText("");
        txtMSNV.setText("");
        dateNS.getEditor().setText("");
        cbLoaiNV.setValue("");
        datePicker.getEditor().setText("");
        txtLuong.setText("");



    }
    public void xoa(){
        NhanVien nhanVien = new NhanVien();
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();
        try{
            nhanVien = tableView.getSelectionModel().getSelectedItem();

            String sql = "delete from nhanvien where id = '"+nhanVien.getId()+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            nhanVienList.remove(nhanVien);
            if(cbLoaiNV.getSelectionModel().getSelectedItem().equals("Biên chế")){
                String sql2 = "delete from nhanvienbienche where id = '"+nhanVien.getId()+"'";
                Statement st2 = conn.createStatement();
                st2.executeUpdate(sql2);
            }
            else if(cbLoaiNV.getSelectionModel().getSelectedItem().equals("Hợp đồng")){
                String sql3 = "delete from nhanvienhopdong where id = '"+nhanVien.getId()+"'";
                Statement st3 = conn.createStatement();
                st3.executeUpdate(sql3);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void tinhLuong(ActionEvent event) throws  Exception{

        if(cbLoaiNV.getSelectionModel().getSelectedItem().equals("Biên chế")){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("tinhLuongBienChe.fxml"));
            Parent tinhLuongView = loader.load();
            Scene scene = new Scene(tinhLuongView);
            stage.setTitle("Tính lương");
            nhanVien = tableView.getSelectionModel().getSelectedItem();
            ControllerTinhLuongBienChe controllerTinhLuongBienChe =loader.<ControllerTinhLuongBienChe>getController();
            controllerTinhLuongBienChe.setNhanVien(nhanVien);
            stage.setScene(scene);


        }
        else{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("tinhLuongHopDong.fxml"));
            Parent tinhLuongView2 = loader.load();
            Scene scene = new Scene(tinhLuongView2);
            stage.setTitle("Tính lương");
            nhanVien = tableView.getSelectionModel().getSelectedItem();
            ControllerHopDong controllerHopDong = loader.<ControllerHopDong>getController();
            controllerHopDong.setNhanVien(nhanVien);
            stage.setScene(scene);
        }


    }
    public void chon(){
        new NhanVien();
        NhanVien nhanVien = tableView.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(nhanVien.getId()));
        txtTen.setText(nhanVien.getTen());
        txtMSNV.setText(nhanVien.getMsnv());
        dateNS.getEditor().setText(nhanVien.getNamSinh());
        cbLoaiNV.setValue(nhanVien.getLoaiNhanVien());
        datePicker.getEditor().setText(nhanVien.getThoiGianVaoLam());
        txtLuong.setText(String.valueOf(nhanVien.getLuong()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();
        try{
            String sql = "select * from nhanVien";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                nhanVienList.add(new NhanVien(rs.getInt("id"), rs.getString("ten"), rs.getString("msnv"), rs.getString("namSinh"), rs.getString("loaiNV"),  rs.getString("thoiGianVaoLam"),rs.getFloat("luong")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("ten"));
        msnvColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("msnv"));
        nsColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("namSinh"));
        thoiGianVaoLamColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("thoiGianVaoLam"));
        loaiNVColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("loaiNhanVien"));
        luongColumn.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("luong"));

        tableView.setItems(nhanVienList);

        FilteredList<NhanVien> filteredData = new FilteredList<>(nhanVienList, b -> true);
        txtKeyword.textProperty().addListener((observableValue,oldValue, newValue) ->{
            filteredData.setPredicate(nhanVien -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String timKiem = newValue.toLowerCase();
                if(nhanVien.getTen().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVien.getMsnv().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVien.getLoaiNhanVien().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVien.getNamSinh().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVien.getThoiGianVaoLam().toString().indexOf(timKiem) > -1){
                    return true;
                }
                else{
                    return false;
                }


            });
        });
        SortedList<NhanVien> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);

        cbLoaiNV.getItems().add("Biên chế");
        cbLoaiNV.getItems().add("Hợp đồng");

       // btnthem.setDisable(true);


    }
}
