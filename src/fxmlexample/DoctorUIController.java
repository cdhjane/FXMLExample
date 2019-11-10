/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class DoctorUIController implements Initializable {
     @FXML private Button goBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void getLoginCntl(){
        Stage mainStage = (Stage) goBack.getScene().getWindow();
        Parent root;
        Scene scene;
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            scene = new Scene(root);
            mainStage.setTitle("Login");
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
