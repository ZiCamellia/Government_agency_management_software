package sample.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ControllerLogin {
    @FXML
    TextField txtUserName, txtPassword;
    public  static String userName, passWord, email;

    public void dangNhap(ActionEvent event) throws Exception{
        String UN, PW;
        UN = txtUserName.getText();
        PW = txtPassword.getText();
        if(UN.equals("")){
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Vui lòng nhập tên đăng nhập");
            alert.show();
        }
        else if(PW.equals("")){
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Vui lòng nhập mật khẩu");
            alert.show();
        }
        if(UN.equals("") && PW.equals("")){
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Invalidation");
            alert.show();
        }
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();
        try{
            String sql = "select tenDN,matKhau, email from dangnhap where tenDN = '"+UN+"' and matKhau ='"+PW+"' ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                userName = rs.getString("tenDN");
                passWord = rs.getString("matKhau");
                email = rs.getString("email");
                if((userName.equals(rs.getString("tenDN")) && passWord.equals(rs.getString("matKhau")))  ){
                    Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("sampleTT.fxml"));
                    Parent tableView = loader.load();
                    stage.setTitle("Thông tin nhân viên");
                    Scene scene = new Scene(tableView);
                    stage.setScene(scene);

                }

            }
            else{
                Alert.AlertType alertAlertType;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Sai tên tài khoản hoặc mật khẩu!");
                alert.show();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void Reset(){
        txtUserName.setText("");
        txtPassword.setText("");
    }
    public void Back(ActionEvent event) throws Exception{
        Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleReady.fxml"));
        Parent tableView = loader.load();
        stage.setTitle("Thông tin nhân viên");
        Scene scene = new Scene(tableView);
        stage.setScene(scene);
    }


}
