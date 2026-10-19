package ST10528625PROG5121PoePart1;

import ST10528625PROG5121PoePart1.login;
import java.util.Scanner;
import java.util.regex.Pattern;

public class NoRegclass {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //Varibales
        String firstName;
        String lastName;
        String username;
        String password;
        String cellPhoneNumber;

        //Prompt the user to enter their first name
        System.out.println("Enter your first name");
        firstName = input.nextLine();

        //Prompt the user to enter their last name
        System.out.println("Enter your surname");
        lastName = input.nextLine();

        //Prompt the user to enter their username
        while (true) {
            System.out.println("Enter your username");
            username = input.nextLine();

            if (checkUsername(username)) {
                System.out.println("Username successfully captured");
                break;
            } else {
                System.out.println("Invalid username");
                System.out.println(
                    "Username must contain an underscore (_) and be "
                    + "no more than 5 characters long.\n"
                );
            }
        }

        //Prompt the user to enter their password
        while (true) {
            System.out.println("Enter your password");
            password = input.nextLine();

            if (checkPassword(password)) {
                System.out.println("Password successfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted.");
                System.out.println("Password must:");
                System.out.println("- Be at least 8 characters long");
                System.out.println("- Contain at least one capital letter");
                System.out.println("- Contain at least one number");
                System.out.println("- Contain at least one special character\n");
            }
        }

        //Prompt the user to enter their cell phone number
        while (true) {
            System.out.println(
                "Enter your cell phone number "
                + "(Example: +27821234567)"
            );

            cellPhoneNumber = input.nextLine();

            if (checkCellPhoneNumber(cellPhoneNumber)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number is incorrectly formatted.");
                System.out.println(
                    "Please enter a valid South African "
                    + "international number, for example +27821234567.\n"
                );
            }
        }

        // Create a Login object using the registered details
        login user = new login(
        firstName,
        lastName,
        username,
        password,
        cellPhoneNumber
        );

        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        if (registrationMessage.equals("User is registered successfully")) {
            Messages.startLogin(user);
        }

        // Display registration message from the Login class
        System.out.println("\n" + user.registerUser());

        // Ask the user to log in
        System.out.println("\nPlease log in.");

        System.out.println("Enter your username:");
        String enteredUserName = input.nextLine();

        System.out.println("Enter your password:");
        String enteredPassword = input.nextLine();

        // Check the login details
        user.loginUser(enteredUserName, enteredPassword);

        // Display login status
        System.out.println(user.returnLoginStatus());

        input.close();
    }

    // Check password complexity
    public static boolean checkPassword(String password) {

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
    
    // Check username requirements
    public static boolean checkUsername(String username) {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    // Check cellphone number requirements
    public static boolean checkCellPhoneNumber(String cellPhoneNumber) {

        String regex = "^\\+27[0-9]{9}$";

        return cellPhoneNumber != null
                && Pattern.matches(regex, cellPhoneNumber);
    }
}