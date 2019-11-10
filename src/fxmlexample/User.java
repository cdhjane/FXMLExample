/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

public  class User {

    private String firstName;
    private String lastName;
    private String username;
    private String emailaddress;
    private String password;
    private String type; // the user type can MedicalAdministrator, patient, doctor or nurse
    
   /**
     * This is the constructor for the User class
     * @param firstName Set first name for the user
     * @param lastName  Set last name for the user
     * @param username  set username for the user
     * @param emailaddress set emailaddress for the user 
     * @param  password set  password for the user
     * @param  type set type for the user
   */
   public User(String firstName, String lastName, String username, String emailaddress, String password, String type  ){
          this.firstName =firstName;
          this.lastName = lastName;
          this.username = username;
          this.emailaddress = emailaddress;
          this.password = password;
          this.type = type;
    }
   
    
    /**
     * Returns the first name of User
     * @return   A string representing the firstName of a user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of User
     * @param firstName Sets the first name of User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of User
     * @return A string representing the last name of User
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of User
     * @param lastName Sets the last name of User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    /**
     * Returns the username of User
     * @return A string representing the username of User
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the type of User
     * @param username Sets the type of User
     */
    public void setUsername(String userName) {
        this.username =username;
    }
    
    
    /**
     * Returns the password of User
     * @return A string representing the password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of User
     * @param userName Sets the password of User
     */
    public void setPassword(String userName) {
        this.username =username;
    }
    
    
       /**
     * Returns the fullname of User
     * @return A string representing the fullname of User
     */
     public String getFullName(){
        
        return firstName+ " " + lastName;
    }
     
     /**
     * Returns the type of User
     * @return A string representing the type of User
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of User
     * @param type Sets the type of User
     */
    public void setType(String type) {
        this.type = type;
    }

      /**
     * Returns the address for Patient
     * @return the address for Patient
     */
    public String getEmailaddress() {
        return emailaddress;
    }

    /**
     * Sets the emailaddress for Patient
     * @param emailaddress Sets the address for Patient
     */
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
}