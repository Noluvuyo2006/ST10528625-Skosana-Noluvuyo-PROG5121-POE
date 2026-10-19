package ST10528625PROG5121PoePart1;

import java.util.regex.Pattern;

public class login {
    //Declare the user's login details
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String cellPhoneNumber;
    
    private boolean loggedIn;

    // Constructor
    public login(String username, String password,
                 String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loggedIn = false;
    }

    //Create a variable that will store the login details was successful or not
    private boolean loginSuccessful;

    //Create a Login class with methods to ensure my application mmeets good cooding standards and testable code
    public login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    //Create a method to check if the username meets the requirements
    //The username must contain an underscore and must be 5 or less characters
    public boolean checkUserName() {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    //Create a method to check if the password meets the password complexity requirements
    //The password must contain an capital letter, a number, a special character and must be 8 or more characters
    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapitalLetter = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        return hasCapitalLetter && hasNumber && hasSpecialCharacter;
    }

    //Create a method to check if the cell phone number meets the requirements
    //The cell phone mumber must be the correct length and contains the international country code
    public boolean checkCellPhoneNumber() {

        if (cellPhoneNumber == null) {
            return false;
        }

        String regex = "^\\+27[0-9]{9}$";

        return Pattern.matches(regex, cellPhoneNumber);
    }

    //Create a mathod that will return the necessary registration message
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more less than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number is not correctly formatted or does not contain an international code, please correct the number and try again.";
        }

        return "User is registered successfully";
    }

    //Create a method that will verify the user's login details entered match the login details stored when the user registers
    public boolean loginUser(String enteredUserName, String enteredPassword) {

        loginSuccessful = username !=null
                && password !=null
                && username.equals(enteredUserName)
                && password.equals(enteredPassword);

        loggedIn = loginSuccessful;
        return loginSuccessful;
    }

    //Create a method that will return successful login or failed login message
    public String returnLoginStatus() {

        if (loginSuccessful) {
            return "Welcome to QuickChat";
        }

        return "Username or password is incorrect, please try again.";
    }
}