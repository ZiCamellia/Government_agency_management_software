package sample.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerDangKi {
    @FXML
    TextField txtEmail, txtUsername;
    @FXML
    PasswordField txtPassword, txtRepassword;

    public void dangKi(){
        DatabaseConnecction databaseConnecction = new DatabaseConnecction();
        Connection conn = databaseConnecction.getConnection();
        String EM , UN, PW, RPW;
        EM = txtEmail.getText();
        UN = txtUsername.getText();
        PW = txtPassword.getText();
        RPW = txtRepassword.getText();

        if(EM.equals("") || UN.equals("") || PW.equals("") || RPW.equals("")){
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            alert.show();
            System.out.println("loi");
        }
        else if(!PW.equals(RPW)){
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Mật khẩu chưa đúng!");
            alert.show();
        }
        else{
            try{
                String sql = "insert into dangnhap(tenDN, matKhau, email) values('"+UN+"' , '"+PW+"','"+EM+"')" ;
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                Alert.AlertType alertAlertType;
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Đăng kí tài khoản thành công");
                alert.setContentText("Đăng kí thành công!!");
                alert.show();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }



    }
    public void Reset(){
        txtEmail.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtRepassword.setText("");

    }
    public void dangNhap(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleLogin.fxml"));
        Parent dangNhapView = loader.load();
        Scene scene = new Scene(dangNhapView);
        stage.setScene(scene);
    }

}
