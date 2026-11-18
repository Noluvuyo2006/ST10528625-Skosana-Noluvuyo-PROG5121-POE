package ST10528625PROG5121PoePart1;

import org.junit.Test;
import static org.junit.Assert.*;

public class NoRegclassTest {

    // ==========================================
    // USERNAME VALIDATION TESTS
    // ==========================================

    @Test
    public void testValidUsername() {
        assertTrue(NoRegclass.checkUsername("Nolu_"));
    }

    @Test
    public void testUsernameWithoutUnderscore() {
        assertFalse(NoRegclass.checkUsername("Nolu1"));
    }

    @Test
    public void testUsernameTooLong() {
        assertFalse(NoRegclass.checkUsername("Noluvuyo_"));
    }

    @Test
    public void testEmptyUsername() {
        assertFalse(NoRegclass.checkUsername(""));
    }

    @Test
    public void testNullUsername() {
        assertFalse(NoRegclass.checkUsername(null));
    }

    // ==========================================
    // PASSWORD VALIDATION TESTS
    // ==========================================

    @Test
    public void testValidPassword() {
        assertTrue(NoRegclass.checkPassword("@Nolu2006"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(NoRegclass.checkPassword("Nol@20"));
    }

    @Test
    public void testPasswordWithoutCapital() {
        assertFalse(NoRegclass.checkPassword("nolu@2006"));
    }

    @Test
    public void testPasswordWithoutNumber() {
        assertFalse(NoRegclass.checkPassword("Nolu@password"));
    }

    @Test
    public void testPasswordWithoutSpecialCharacter() {
        assertFalse(NoRegclass.checkPassword("Nolu2006"));
    }

    @Test
    public void testEmptyPassword() {
        assertFalse(NoRegclass.checkPassword(""));
    }

    @Test
    public void testNullPassword() {
        assertFalse(NoRegclass.checkPassword(null));
    }

    // ==========================================
    // CELL PHONE VALIDATION TESTS
    // ==========================================

    @Test
    public void testValidCellPhoneNumber() {
        assertTrue(NoRegclass.checkCellPhoneNumber("+27717709273"));
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        assertFalse(NoRegclass.checkCellPhoneNumber("0717709273"));
    }

    @Test
    public void testCellPhoneNumberWithoutCountryCode() {
        assertFalse(NoRegclass.checkCellPhoneNumber("27717709273"));
    }

    @Test
    public void testCellPhoneNumberTooShort() {
        assertFalse(NoRegclass.checkCellPhoneNumber("+277177092"));
    }

    @Test
    public void testCellPhoneNumberTooLong() {
        assertFalse(NoRegclass.checkCellPhoneNumber("+277177092731"));
    }

    @Test
    public void testCellPhoneNumberWithLetters() {
        assertFalse(NoRegclass.checkCellPhoneNumber("+27717ABC273"));
    }

    @Test
    public void testEmptyCellPhoneNumber() {
        assertFalse(NoRegclass.checkCellPhoneNumber(""));
    }

    @Test
    public void testNullCellPhoneNumber() {
        assertFalse(NoRegclass.checkCellPhoneNumber(null));
    }

    // ==========================================
    // LOGIN CLASS TESTS
    // ==========================================

    private login createValidUser() {
        return new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "@Nolu2006",
                "+27717709273"
        );
    }

    @Test
    public void testLoginUsernameValid() {
        login user = createValidUser();
        assertTrue(user.checkUserName());
    }

    @Test
    public void testLoginUsernameInvalid() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu1",
                "@Nolu2006",
                "+27717709273"
        );

        assertFalse(user.checkUserName());
    }

    @Test
    public void testLoginUsernameNull() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                null,
                "@Nolu2006",
                "+27717709273"
        );

        assertFalse(user.checkUserName());
    }

    @Test
    public void testLoginPasswordValid() {
        login user = createValidUser();
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginPasswordTooShort() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "Nol@20",
                "+27717709273"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginPasswordWithoutCapital() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "nolu@2006",
                "+27717709273"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginPasswordWithoutNumber() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "Nolu@password",
                "+27717709273"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginPasswordWithoutSpecialCharacter() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "Nolu2006",
                "+27717709273"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginPasswordNull() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                null,
                "+27717709273"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginCellPhoneValid() {
        login user = createValidUser();
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testLoginCellPhoneInvalid() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "@Nolu2006",
                "0717709273"
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testLoginCellPhoneNull() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "@Nolu2006",
                null
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    // ==========================================
    // REGISTRATION TESTS
    // ==========================================

    @Test
    public void testSuccessfulRegistration() {
        login user = createValidUser();

        assertEquals(
                "User is registered successfully",
                user.registerUser()
        );
    }

    @Test
    public void testRegistrationWithInvalidUsername() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu1",
                "@Nolu2006",
                "+27717709273"
        );

        assertEquals(
                "Username is not correctly formatted, please ensure that your username contains an underscore and is no more less than five characters in length.",
                user.registerUser()
        );
    }

    @Test
    public void testRegistrationWithInvalidPassword() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "nolu2006",
                "+27717709273"
        );

        assertEquals(
                "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                user.registerUser()
        );
    }

    @Test
    public void testRegistrationWithInvalidCellPhone() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                "@Nolu2006",
                "0717709273"
        );

        assertEquals(
                "Cell phone number is not correctly formatted or does not contain an international code, please correct the number and try again.",
                user.registerUser()
        );
    }

    // ==========================================
    // LOGIN AUTHENTICATION TESTS
    // ==========================================

    @Test
    public void testSuccessfulLogin() {
        login user = createValidUser();

        assertTrue(user.loginUser("Nolu_", "@Nolu2006"));

        assertEquals(
                "Welcome to QuickChat",
                user.returnLoginStatus()
        );
    }

    @Test
    public void testFailedLoginWrongUsername() {
        login user = createValidUser();

        assertFalse(user.loginUser("Wrong_", "@Nolu2006"));

        assertEquals(
                "Username or password is incorrect, please try again.",
                user.returnLoginStatus()
        );
    }

    @Test
    public void testFailedLoginWrongPassword() {
        login user = createValidUser();

        assertFalse(user.loginUser("Nolu_", "Wrong123!"));

        assertEquals(
                "Username or password is incorrect, please try again.",
                user.returnLoginStatus()
        );
    }

    @Test
    public void testFailedLoginBothIncorrect() {
        login user = createValidUser();

        assertFalse(user.loginUser("Wrong_", "Wrong123!"));
    }

    @Test
    public void testLoginWithNullUsername() {
        login user = createValidUser();

        assertFalse(user.loginUser(null, "@Nolu2006"));
    }

    @Test
    public void testLoginWithNullPassword() {
        login user = createValidUser();

        assertFalse(user.loginUser("Nolu_", null));
    }

    @Test
    public void testLoginWithNullStoredUsername() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                null,
                "@Nolu2006",
                "+27717709273"
        );

        assertFalse(user.loginUser("Nolu_", "@Nolu2006"));
    }

    @Test
    public void testLoginWithNullStoredPassword() {
        login user = new login(
                "Noluvuyo",
                "Skosana",
                "Nolu_",
                null,
                "+27717709273"
        );

        assertFalse(user.loginUser("Nolu_", "@Nolu2006"));
    }

    @Test
    public void testLoginStatusBeforeLogin() {
        login user = createValidUser();

        assertEquals(
                "Username or password is incorrect, please try again.",
                user.returnLoginStatus()
        );
    }

    // ==========================================
    // FOUR-PARAMETER CONSTRUCTOR TEST
    // ==========================================

    @Test
    public void testFourParameterConstructor() {
        login user = new login(
                "Nolu_",
                "@Nolu2006",
                "Noluvuyo",
                "Skosana"
        );

        assertEquals("Nolu_", user.username);
        assertEquals("@Nolu2006", user.password);
        assertEquals("Noluvuyo", user.firstName);
        assertEquals("Skosana", user.lastName);
        assertFalse(user.checkCellPhoneNumber());
    }
}
