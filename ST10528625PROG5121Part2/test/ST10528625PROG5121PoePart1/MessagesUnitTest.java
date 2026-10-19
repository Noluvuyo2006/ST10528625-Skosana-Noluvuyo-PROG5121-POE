/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ST10528625PROG5121PoePart1;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class MessagesUnitTest {
    
    public MessagesUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     */
    @Test
    public void testGenerateMessageID() {
        Messages message = new Messages(
                "", 0, "+27718693002", "Hello there");

        String ID = message.generateMessageID();

        assertNotNull(ID);
        assertEquals(10, ID.length());
        assertTrue(ID.matches("[0-9]{10}"));
    }

    
    @Test
    public void testCheckMessageID() {
        Messages validMessage = new Messages(
                "1234567890", 0, "+27718693002", "Hello there");

        Messages invalidMessage = new Messages(
                "12345678901", 0, "+27718693002", "Hello there");

        assertTrue(validMessage.checkMessageID());
        assertFalse(invalidMessage.checkMessageID());
    }



    /**
     * Test of checkRecipientCell method, of class Messages.
     */
    
    @Test
    public void testCheckRecipientCellData1() {
        // Test data 1: Valid recipient cell number
        Messages message1 = new Messages(
            "1234567890",
            0,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
            );

        assertTrue(message1.checkRecipientCell());
        }

    @Test
    public void testCheckRecipientCellData2() {
        // Test data 2: Invalid recipient cell number
        Messages message2 = new Messages(
            "9876543210",
            1,
            "08575975889",
            "Hi Keegan, did you recieve the payment?"
            );

        assertFalse(message2.checkRecipientCell());
        }


    /**
    * Test of createMessageHash method, of class Messages.
    */
    @Test
    public void testCreateMessageHashData1() {

        String[] IDs = {"0012345678", "1234567890", "9876543210"};
        int[] numbers = {0, 1, 2};
        String[] recipients = {"+27718693002", "+27718693002", "08575975889"};
        String[] messages = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you recieve the payment?"
        };
        String[] expected = {"00:0:HITONIGHT", "12:1:HITONIGHT", "98:2:HIPAYMENT"};

        for (int i = 0; i < IDs.length; i++) {
            Messages message = new Messages(IDs[i], numbers[i], recipients[i], messages[i]);
            assertEquals(expected[i], message.createMessageHash());
        }
    }


    /**
     * Test of SentMessage method, of class Messages.
     */
    @Test
    public void testSentMessageSend() {
        Messages message = new Messages(
                "0012345678", 1, "+27612345678", "Hi tonight");

        String result = message.SentMessage(new Scanner("1\n"));

        assertEquals("Message successfully sent", result);
    }

    @Test
    public void testSentMessageDisregard() {
        Messages message = new Messages(
                "0012345679", 2, "+27823456789", "Hello there");

        String result = message.SentMessage(new Scanner("2\n1\n"));

        assertEquals("Message disregarded", result);
    }

    @Test
    public void testSentMessageStore() {
        Messages message = new Messages(
                "0012345680", 3, "+27698765432", "Good night");

        String result = message.SentMessage(new Scanner("3\n"));

        assertEquals("Message successfully stored", result);
    }

    /**
     * Test of printMessages method, of class Messages.
     */
    @Test
    public void testPrintMessages() {
        Messages message = new Messages(
                "0012345681", 1, "+27612345678", "Hello there");

        assertNotNull(message.printMessages());
    }

    /**
     * Test of returnTotalMessages method, of class Messages.
     */
    @Test
    public void testReturnTotalMessages() {
        Messages message = new Messages(
                "0012345682", 1, "+27612345678", "Hello there");

        assertTrue(message.returnTotalMessages() >= 0);
    }


    /**
     * Test of storeMessages method, of class Messages.
     */
    @Test
    public void testStoreMessages() {
        Messages.storeMessages();
    }
    
}
