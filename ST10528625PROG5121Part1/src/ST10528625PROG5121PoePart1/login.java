package ST10528625PROG5121PoePart1;

import java.util.regex.Pattern;

public class login {

    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String cellPhoneNumber;

    private boolean loginSuccessful;

    public login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

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

    public boolean checkCellPhoneNumber() {

        if (cellPhoneNumber == null) {
            return false;
        }

        String regex = "^\\+27[0-9]{9}$";

        return Pattern.matches(regex, cellPhoneNumber);
    }

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

    public boolean loginUser(String enteredUserName, String enteredPassword) {

        loginSuccessful = username.equals(enteredUserName)
                && password.equals(enteredPassword);

        return loginSuccessful;
    }

    public String returnLoginStatus() {

        if (loginSuccessful) {
            return "Welcome " + firstName + " " + lastName
                    + ", it is great to see you!";
        }

        return "Username or password is incorrect, please try again.";
    }
}