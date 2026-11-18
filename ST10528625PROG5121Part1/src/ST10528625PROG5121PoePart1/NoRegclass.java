package ST10528625PROG5121PoePart1;

import ST10528625PROG5121PoePart1.login;
import java.util.Scanner;
import java.util.regex.Pattern;

public class NoRegclass {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String firstName;
        String lastName;
        String username;
        String password;
        String cellPhoneNumber;

        System.out.println("Enter your first name");
        firstName = input.nextLine();

        System.out.println("Enter your surname");
        lastName = input.nextLine();

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

        login user = new login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );

        System.out.println("\n" + user.registerUser());

        System.out.println("\nPlease log in.");

        System.out.println("Enter your username:");
        String enteredUserName = input.nextLine();

        System.out.println("Enter your password:");
        String enteredPassword = input.nextLine();

        user.loginUser(enteredUserName, enteredPassword);

        System.out.println(user.returnLoginStatus());

        input.close();
    }

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

    public static boolean checkUsername(String username) {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    public static boolean checkCellPhoneNumber(String cellPhoneNumber) {

        String regex = "^\\+27[0-9]{9}$";

        return cellPhoneNumber != null
                && Pattern.matches(regex, cellPhoneNumber);
    }
}