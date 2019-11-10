/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import SQLiteDB.Sqlite;
import SQLiteDB.Sqlite_User;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class LoginController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button createAccountButton;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void LoginCheck(ActionEvent event){
          
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean check = false;
        Connection conn =Sqlite.getConnection();
        Sqlite_User sqlite_user= new Sqlite_User();
        User user =sqlite_user.loginCheck(conn, username, password);
        if (user == null ){
           errorLabel.setText("Invalid credentials, please try again or create a account");  
        }
        else {
            errorLabel.setText("correct username and password");  
             System.out.println(user.getType());
             Dashboard(user);
            
        }
    }
      
    @FXML
    public void CreateAccount() {
        
        Stage mainStage = (Stage) createAccountButton.getScene().getWindow();
        Parent root;
        Scene scene;
        try {
            root = FXMLLoader.load(getClass().getResource("createAccountUI.fxml"));
            scene = new Scene(root);
            mainStage.setTitle("Create Account");
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Dashboard(User user){
        Stage mainStage = (Stage) loginButton.getScene().getWindow();
        Parent root;
        Scene scene;
        try {
            if(user.getType().equalsIgnoreCase("patient")){
               root = FXMLLoader.load(getClass().getResource("patientUI.fxml"));
            }
            else{
               root = FXMLLoader.load(getClass().getResource("DoctorUI.fxml"));
            }
            scene = new Scene(root);
            mainStage.setTitle("Dashoard");
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
