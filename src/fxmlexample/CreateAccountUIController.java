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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class CreateAccountUIController implements Initializable {
    
    @FXML private Button createAccountButton;
    @FXML private Button goBack;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordField1;
    @FXML private TextField emaildField;
    @FXML private Label userStatus;
    @FXML private ChoiceBox<String> userChoice;
    @FXML private DatePicker birthDate;
    
    private String passwordValue;
    private String password2Value;
    private boolean passwordsMatch = false;
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] options = {"Doctor", "Patient"};
        ObservableList<String> list = FXCollections.observableArrayList(options);
        userChoice.setItems(list);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
           this.updatePasswordStatus(newValue);
           this.passwordValue = newValue;
        });
     
    }     
    
    public void handleCreateAccount(){
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String uName =  usernameField.getText();
        String email = emaildField.getText();
        String password = passwordField.getText();
        String password2 = passwordField1.getText();
        String userType = userChoice.getValue();
        LocalDate dateofbirth  =birthDate.getValue();
        //System.out.println("date of Birth:"+ dateofbirth.toString());
    
       checkUserInput();
        
       //access user in DB
       Boolean userExist=accessUserInDB( uName);
       if (userExist){
            userStatus.setText("username has existed, please input again");
            return;
        }
         
         //insert user data in DB
         User user= new User(fName,lName,uName,email,password ,userType);
         insertUserInDB(user);
        
         userStatus.setText("Account has saved in DB");  
    }
    
    public void checkUserInput(){
        
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String uName =  usernameField.getText();
        String email = emaildField.getText();
        String password = passwordField.getText();
        String password2 = passwordField1.getText();
        String userType = userChoice.getValue();
        LocalDate dateofbirth  =birthDate.getValue();
        //System.out.println("date of Birth:"+ dateofbirth.toString());
    
       
      if(fName.length()==0){
            userStatus.setText("First name can not be null.");
            return;
        }
        
       if(lName.length()==0){
            userStatus.setText("Last name can not be null.");
             return;
        }
        
       if(uName.length()==0){
            userStatus.setText("user name can not be null.");
             return;
        }
        
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        if(!matcher.find()){
              userStatus.setText("Wrong email format.");
              return;
          }
         
        if(password.length()==0){
            userStatus.setText("Password can not be null.");
            return;
        }
        
        if(!password.equals(password2)){
             passwordsMatch = false;
             userStatus.setText("Passwords do not match.");
             return;
          } 
        else{
             userStatus.setText(" ");
        }
        
        if (userType == null){
            userStatus.setText("User type can not be null.");
            return;
        }
        
        if (birthDate.getValue() == null){
         userStatus.setText("Birth of date can not be null.");
            return;
       }
        
    }
    
    public Boolean accessUserInDB(String uName){
         //connect DataBase
         Connection conn =Sqlite.getConnection();
         Sqlite_User sqlite_user= new Sqlite_User(); 
        // if not exists user table, create it
         sqlite_user.createTable(conn);
         
         Boolean userExist= sqlite_user.selectTableByUsername(conn, uName);
         return userExist;
    }
    public void insertUserInDB(User user){
      
          //connect DataBase
         Connection conn =Sqlite.getConnection();
         Sqlite_User sqlite_user= new Sqlite_User();
         
         //sqlite_user.dropTable(conn);
        // if not exists user table, create it
         sqlite_user.createTable(conn);
         sqlite_user.insertTable(conn, user);
         
           //access user data from DB
         ArrayList<User> users = new ArrayList<>();
         users = sqlite_user.selectTable(conn);
         for ( int i=0 ; i <users.size();i++){
             System.out.println( "The infromation of user"+(i+1) +";" );
             System.out.println( "FirstName: " + users.get(i).getFirstName() );
             System.out.println( "LastName: " + users.get(i).getLastName() );
             System.out.println( "Username: " + users.get(i).getUsername() );
             System.out.println( "emailaddress: " +  users.get(i).getEmailaddress() );
             System.out.println( "Password: " +users.get(i).getPassword() );
             System.out.println( "UserType: " +users.get(i).getType() );
             System.out.println();
         }
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
    
    /**
     * Takes the user inputted password from CreateAccountUI and
     * determines the strength of the password based on their input
     * @param password 
     */
    public void updatePasswordStatus(String password) {

        if(password.length() == 0) {
            userStatus.setText("");
        }
        else if(password.length() < 5) {
            userStatus.setText("WEAK PASSWORD");
        }
        else if(password.length() < 9 && password.length() >= 5) {
            userStatus.setText("MODERATE PASSWORD");
        }
        else {
             userStatus.setText("STRONG PASSWORD");
        }   
    }
}
