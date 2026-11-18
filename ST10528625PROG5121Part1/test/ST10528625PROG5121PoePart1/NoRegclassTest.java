package ST10528625PROG5121PoePart1;

import org.junit.Test;
import static org.junit.Assert.*;
import ST10528625PROG5121PoePart1.login;
import ST10528625PROG5121PoePart1.NoRegclass;

public class NoRegclassTest {

    // Username tests
    @Test
    public void testValidUsername() {
        assertTrue(NoRegclass.checkUsername("Nolu_"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(NoRegclass.checkUsername("Nolu1"));
    }

    @Test
    public void testUsernameTooLong() {
        assertFalse(NoRegclass.checkUsername("Noluvuyo_"));
    }

    // Password tests
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

    // Cell phone tests
    @Test
    public void testValidCellPhoneNumber() {
        assertTrue(NoRegclass.checkCellPhoneNumber("+27717709273"));
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        assertFalse(NoRegclass.checkCellPhoneNumber("0717709273"));
    }

    // Login class tests
    @Test
    public void testLoginUsername() {
        login user = new login(
                "Noluvuyo", "Skosana", "Nolu_",
                "@Nolu2006", "+27717709273"
        );

        assertTrue(user.checkUserName());
    }

    @Test
    public void testLoginPassword() {
        login user = new login(
                "Noluvuyo", "Skosana", "Nolu_",
                "@Nolu2006", "+27717709273"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginCellPhone() {
        login user = new login(
                "Noluvuyo", "Skosana", "Nolu_",
                "@Nolu2006", "+27717709273"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testSuccessfulRegistration() {
        login user = new login(
                "Noluvuyo", "Skosana", "Nolu_",
                "@Nolu2006", "+27717709273"
        );

        assertEquals("User is registered successfully",
                user.registerUser());
    }

    @Test
    public void testSuccessfulLogin() {
        login user = new login(
                "Noluvuyo", "Skosana", "Nolu_",
                "@Nolu2006", "+27717709273"
        );

        assertTrue(user.loginUser("Nolu_", "@Nolu2006"));

        assertEquals(
                "Welcome Noluvuyo Skosana, it is great to see you!",
                user.returnLoginStatus()
        );
    }

    @Test
    public void testFailedLogin() {
        login user = new login(
                "Noluvuyo", "Skosana", "Nolu_",
                "@Nolu2006", "+27717709273"
        );

        assertFalse(user.loginUser("Wrong_", "Wrong123!"));

        assertEquals(
                "Username or password is incorrect, please try again.",
                user.returnLoginStatus()
        );
    }
}