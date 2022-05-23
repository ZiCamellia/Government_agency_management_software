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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Stack;

public class ControllerTinhLuongBienChe  implements Initializable {
    @FXML
    TableView<NhanVienBienChe> tableView ;
    @FXML
    TableColumn<NhanVienBienChe, Integer> idColumn ;
    @FXML
    TableColumn<NhanVienBienChe, String> tenColumn;
    @FXML
    TableColumn<NhanVienBienChe, String> msnvColumn ;
    @FXML
    TableColumn<NhanVienBienChe, String> nsColumn ;
    @FXML
    TableColumn<NhanVienBienChe, String> thoiGianVaoLamColumn ;
    @FXML
    TableColumn<NhanVienBienChe, Float> luongColumn;
    @FXML
    TableColumn<NhanVienBienChe, Float> luongCoBanColumn ;
    @FXML
    TableColumn<NhanVienBienChe, Float> heSoLuong ;
    @FXML
    TableColumn<NhanVienBienChe, Float>phuCapColumn;
    @FXML
    TextField txtId ,txtName, txtMsnv, txtLuongCoBan, txtHeSoLuong, txtPhuCap, txtLuongCuaBan;
    @FXML
    TextField txtTimKiem;
    NhanVien nhanVien;
    NhanVienBienChe nhanVienBienChe;
    ObservableList<NhanVienBienChe> nhanVienBienCheList = FXCollections.observableArrayList();
    public void setNhanVien(NhanVien nhanVien){
        txtId.setText(String.valueOf(nhanVien.getId()));
        txtName.setText(nhanVien.getTen());
        txtMsnv.setText(nhanVien.getMsnv());

    }
    public void tinhLuong(){

        String LCB = txtLuongCoBan.getText();
        String HSL = txtHeSoLuong.getText();
        String PC = txtPhuCap.getText();
        if(LCB.isEmpty() || HSL.isEmpty() || PC.isEmpty()){
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            alert.show();
        }
        float luongCoBan = Float.parseFloat((LCB));
        float heSoLuong = Float.parseFloat((HSL));
        float phuCap = Float.parseFloat((PC));

        float luongCuaBan = luongCoBan*heSoLuong + phuCap;

       txtLuongCuaBan.setText(String.valueOf(luongCuaBan));



    }
    public void Luu(){
        NhanVienBienChe nhanVienBienChe = new NhanVienBienChe();
        nhanVienBienChe = tableView.getSelectionModel().getSelectedItem();
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();

        try{
            String sql = "update  nhanvien set id = '"+txtId.getText()+"', luong = '"+txtLuongCuaBan.getText()+"' where id = '"+txtId.getText()+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            String sql2 = "update nhanvienbienche set id = '"+txtId.getText()+"' ,luong = '"+txtLuongCuaBan.getText()+"', luongCoBan = '"+txtLuongCoBan.getText()+"', heSoLuong = '"+txtHeSoLuong.getText()+"', phuCap = '"+txtPhuCap.getText()+"' where id = '"+txtId.getText()+"'";
            Statement st2 = conn.createStatement();
            st2.executeUpdate(sql2);
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Lưu thành công");
            alert.setContentText("Cập nhật thành công!");
            alert.show();

            nhanVienBienCheList.remove(nhanVienBienChe);
            nhanVienBienChe.setId(Integer.valueOf(txtId.getText()));
            nhanVienBienChe.setTen(txtName.getText());
            nhanVienBienChe.setMsnv(txtMsnv.getText());
            nhanVienBienChe.setLuong(Float.valueOf((txtLuongCuaBan.getText())));
            nhanVienBienChe.setLuongCoBan(Float.parseFloat(txtLuongCoBan.getText()));
            nhanVienBienChe.setHeSoLuong(txtHeSoLuong.getText());
            nhanVienBienChe.setPhuCap(Float.parseFloat(txtPhuCap.getText()));
            nhanVienBienCheList.add(nhanVienBienChe);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void BackToTable(ActionEvent event)  throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleTT.fxml"));
        Parent tableView = loader.load();
        Scene scene = new Scene(tableView);
        stage.setScene(scene);
    }
    public void chon(){
        new NhanVienBienChe();
        NhanVienBienChe nhanVienBienChe = tableView.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(nhanVienBienChe.getId()));
        txtName.setText(nhanVienBienChe.getTen());
        txtMsnv.setText(nhanVienBienChe.getMsnv());
        txtLuongCoBan.setText(String.valueOf(nhanVienBienChe.getLuong()));
        txtHeSoLuong.setText(String.valueOf(nhanVienBienChe.getHeSoLuong()));
        txtPhuCap.setText(String.valueOf(nhanVienBienChe.getPhuCap()));
        txtLuongCuaBan.setText(String.valueOf(nhanVienBienChe.getLuong()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();
        try{
            String sql = "select * from nhanVienBienChe";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                nhanVienBienCheList.add(new NhanVienBienChe(rs.getInt("id"), rs.getString("ten"), rs.getString("msnv"),rs.getString("namSinh"), rs.getString("thoiGianVaoLam"), rs.getInt("luong"), rs.getString("heSoLuong"), rs.getFloat("luongCoBan"), rs.getFloat("phuCap")));

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe, Integer>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe, String>("ten"));
        msnvColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe, String>("msnv"));
        nsColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe, String>("namSinh"));
        thoiGianVaoLamColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe, String>("thoiGianVaoLam"));
        luongColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe, Float>("luong"));
        luongCoBanColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe,Float>("luongCoBan"));
        heSoLuong.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe,Float>("heSoLuong"));
        phuCapColumn.setCellValueFactory(new PropertyValueFactory<NhanVienBienChe, Float>("phuCap"));

        tableView.setItems(nhanVienBienCheList);

        FilteredList<NhanVienBienChe> filteredData = new FilteredList<>(nhanVienBienCheList, b -> true);
        txtTimKiem.textProperty().addListener((observableValue,oldValue, newValue) ->{
            filteredData.setPredicate(nhanVienBienChe -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String timKiem = newValue.toLowerCase();
                if(nhanVienBienChe.getTen().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVienBienChe.getMsnv().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }

                else if(nhanVienBienChe.getNamSinh().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVienBienChe.getThoiGianVaoLam().toString().indexOf(timKiem) > -1){
                    return true;
                }
                else{
                    return false;
                }


            });
        });
        SortedList<NhanVienBienChe> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
        txtId.setDisable(true);
        txtLuongCuaBan.setDisable(true);
        txtName.setDisable(true);
        txtMsnv.setDisable(true);
    }
}
