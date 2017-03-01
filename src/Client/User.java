package Client;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public class User {

    //Data members
//    private Username username;
//    private Password password;
	String username;
	String password;
    private String str_authentication_code;

    //Constructor
    public User() {
//        username = new Username();
//        password = new Password();
//
//        username.setUsername("");
//        password.setPassword("");
    	username = "";
    	password = "";
        str_authentication_code = "-1" ; //initialize to -1 for debugging purposes.
    }
    
    @Override
    public String toString() {
    	return "Username: " + getUsername() + "\npassword: " + getPassword() + "\nauthentication: " + getStr_authentication_code() + "\n";
    }

    //Getters
    /*
     * Goes into the Username class and changes the string there
     */
    public String getUsername() {
    	return username;
//        return username.getUsername();
    }

    /*
     * Goes into the Username class and changes the string there
     */
    public void setUsername(String username) {
    	this.username = username;
//        this.username.setUsername(username);
    }

    public String getStr_authentication_code() {
        return str_authentication_code;
    }

    //Setters
    /*
     * Goes into the Password class and grabs the string version of the password.
     */
    public String getPassword() {
    	return password;
//        return password.getPassword();
    }

    /*
     * Goes into the Password class and changes the string there
     */
    public void setPassword(String password) {
    	this.password = password;
//        this.password.setPassword(password);
    }

    public void setStr_authentication_code(String str_authentication_code) {
        this.str_authentication_code = str_authentication_code;
    }
}
