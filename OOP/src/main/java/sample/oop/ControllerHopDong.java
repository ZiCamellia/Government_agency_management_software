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

public class ControllerHopDong implements Initializable {
    @FXML
    TextField txtId, txtName, txtMsnv, txtTienCongMotNgay, txtSoNgayLam, txtLuong;
    @FXML
    TableView<NhanVienHopDong> tableView ;
    @FXML
    TableColumn<NhanVienHopDong, Integer> idColumn ;
    @FXML
    TableColumn<NhanVienHopDong, String> tenColumn;
    @FXML
    TableColumn<NhanVienHopDong, String> msnvColumn ;
    @FXML
    TableColumn<NhanVienHopDong, String> nsColumn ;
    @FXML
    TableColumn<NhanVienHopDong, String> thoiGianVaoLamColumn ;
    @FXML
    TableColumn<NhanVienHopDong, Float> luongColumn;
    @FXML
    TableColumn<NhanVienHopDong, Float> tienCongMotNgayColumn ;
    @FXML
    TableColumn<NhanVienHopDong, Float> soNgayLamColumn ;
    @FXML
    TextField txtTimKiem;

    NhanVienHopDong nhanVienHopDong ;
    NhanVien nhanVien;
    ObservableList<NhanVienHopDong> nhanVienHopDongList = FXCollections.observableArrayList();

    public void setNhanVien( NhanVien nhanVien){
        txtId.setText(String.valueOf(nhanVien.getId()));
        txtName.setText(nhanVien.getTen());
        txtMsnv.setText(nhanVien.getMsnv());
    }

    public void tinhLuong(){
        String TCMN = txtTienCongMotNgay.getText();
        String SNL = txtSoNgayLam.getText();
        if(TCMN.isEmpty() || SNL.isEmpty()){
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            alert.show();
        }
        float tienCongMotNgay = Float.parseFloat((TCMN));
        float soNgayLam = Float.parseFloat((SNL));
        float luong = tienCongMotNgay*soNgayLam;

        txtLuong.setText(String.valueOf(luong));

    }
    public void Luu(){
        NhanVienHopDong nhanVienHopDong = new NhanVienHopDong();
        nhanVienHopDong = tableView.getSelectionModel().getSelectedItem();
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();

        try{
            String sql = "update nhanvien set id = '"+ txtId.getText()+"', luong = '"+txtLuong.getText()+"' where id = '"+ txtId.getText()+"'";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            String sql2 = "update nhanvienhopdong set id = '"+txtId.getText()+"' ,luong = '"+txtLuong.getText()+"', soNgayLam = '"+txtSoNgayLam.getText()+"', tienCongTheoNgay = '"+txtTienCongMotNgay.getText()+"' where id = '"+txtId.getText()+"'";
            Statement st2 = conn.createStatement();
            st2.executeUpdate(sql2);
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lưu thành công");
            alert.setContentText("Cập nhật thành công!");
            alert.show();
            nhanVienHopDongList.remove(nhanVienHopDong);
            nhanVienHopDong.setId(Integer.valueOf(txtId.getText()));
            nhanVienHopDong.setTen(txtName.getText());
            nhanVienHopDong.setMsnv(txtMsnv.getText());
            nhanVienHopDong.setLuong(Float.valueOf((txtLuong.getText())));
            nhanVienHopDong.setSoNgayLam(Float.parseFloat(txtSoNgayLam.getText()));
            nhanVienHopDong.setTienCongTheoNgay(Float.parseFloat(txtTienCongMotNgay.getText()));
            nhanVienHopDongList.add(nhanVienHopDong);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void backToTable(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource() ).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleTT.fxml"));
        Parent tableView = loader.load();
        Scene scene = new Scene(tableView);
        stage.setScene(scene);
    }
    public void chon(){
        new NhanVienHopDong();
        NhanVienHopDong nhanVienHopDong = tableView.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(nhanVienHopDong.getId()));
        txtName.setText(nhanVienHopDong.getTen());
        txtMsnv.setText(nhanVienHopDong.getMsnv());
        txtTienCongMotNgay.setText(String.valueOf(nhanVienHopDong.getTienCongTheoNgay()));
        txtSoNgayLam.setText(String.valueOf(nhanVienHopDong.getSoNgayLam()));
        txtLuong.setText(String.valueOf(nhanVienHopDong.getLuong()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();
        try{
            String sql = "select * from nhanvienhopdong ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                nhanVienHopDongList.add(new NhanVienHopDong(rs.getInt("id"), rs.getString("ten"), rs.getString("msnv"), rs.getString("namSinh"), rs.getString("thoiGianVaoLam"), rs.getFloat("luong"), rs.getFloat("soNgayLam"), rs.getFloat("tienCongTheoNgay")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong, Integer>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong, String>("ten"));
        msnvColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong, String>("msnv"));
        nsColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong, String>("namSinh"));
        thoiGianVaoLamColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong, String>("thoiGianVaoLam"));
        luongColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong, Float>("luong"));
        tienCongMotNgayColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong,Float>("tienCongTheoNgay"));
        soNgayLamColumn.setCellValueFactory(new PropertyValueFactory<NhanVienHopDong,Float>("soNgayLam"));
        tableView.setItems(nhanVienHopDongList);

        FilteredList<NhanVienHopDong> filteredData = new FilteredList<>(nhanVienHopDongList, b -> true);
        txtTimKiem.textProperty().addListener((observableValue,oldValue, newValue) ->{
            filteredData.setPredicate(nhanVienHopDong -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String timKiem = newValue.toLowerCase();
                if(nhanVienHopDong.getTen().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVienHopDong.getMsnv().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }

                else if(nhanVienHopDong.getNamSinh().toLowerCase().indexOf(timKiem) > -1){
                    return true;
                }
                else if(nhanVienHopDong.getThoiGianVaoLam().toString().indexOf(timKiem) > -1){
                    return true;
                }
                else{
                    return false;
                }


            });
        });
        SortedList<NhanVienHopDong> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtMsnv.setDisable(true);
        txtLuong.setDisable(true);
    }
}
