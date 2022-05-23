package sample.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class ControllerReady {


    public void dangNhap(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleLogin.fxml"));
        Parent loginView = loader.load();
        Scene scene = new Scene(loginView);
        stage.setTitle("Đăng nhập");
        stage.setScene(scene);
    }
    public void dangKi(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleDangKi.fxml"));
        Parent dangKiView = loader.load();
        stage.setTitle("Đăng kí");
        Scene scene = new Scene(dangKiView);
        stage.setScene(scene);
    }
}
