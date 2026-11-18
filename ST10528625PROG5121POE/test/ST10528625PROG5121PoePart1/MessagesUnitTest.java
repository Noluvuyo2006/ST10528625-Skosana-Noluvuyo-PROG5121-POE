package ST10528625PROG5121PoePart1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessagesUnitTest {

    @Before
    public void resetMessageData() throws Exception {

        clearStaticList(Messages.class, "sentMessages");
        clearStaticList(Messages.class, "disregardedMessages");
        clearStaticList(StoredMessages.class, "storedMessages");

        Field total = Messages.class.getDeclaredField("totalMessages");
        total.setAccessible(true);
        total.setInt(null, 0);
    }

    private void clearStaticList(Class<?> type, String fieldName)
            throws Exception {

        Field field = type.getDeclaredField(fieldName);
        field.setAccessible(true);

        ArrayList<?> list = (ArrayList<?>) field.get(null);
        list.clear();
    }

    // TEST 1: Generate a valid message ID
    @Test
    public void testGenerateMessageID() {

        Messages message = new Messages(
                "", 0, "+27718693002", "Hello"
        );

        String id = message.generateMessageID();

        assertNotNull(id);
        assertEquals(10, id.length());
        assertTrue(id.matches("[0-9]{10}"));
        assertTrue(message.checkMessageID());
    }

    // TEST 2: Check an invalid message ID
    @Test
    public void testCheckMessageIDInvalid() {

        Messages message = new Messages(
                "12345678901", 0, "+27718693002", "Hello"
        );

        assertFalse(message.checkMessageID());
    }

    // TEST 3: Check a valid recipient number
    @Test
    public void testRecipientValid() {

        Messages message = new Messages(
                "1234567890", 0, "+27718693002", "Hello"
        );

        assertTrue(message.checkRecipientCell());
    }

    // TEST 4: Check an invalid recipient number
    @Test
    public void testRecipientInvalid() {

        Messages message = new Messages(
                "1234567890", 0, "08575975889", "Hello"
        );

        assertFalse(message.checkRecipientCell());
    }

    // TEST 5: Check message hash creation
    @Test
    public void testMessageHash() {

        Messages message = new Messages(
                "0012345678",
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "00:0:HITONIGHT",
                message.createMessageHash()
        );
    }

    // TEST 6: Check message getters
    @Test
    public void testMessageGetters() {

        Messages message = new Messages(
                "1234567890",
                1,
                "+27718693002",
                "Hello there"
        );

        assertEquals("1234567890", message.getMessageID());
        assertEquals("+27718693002", message.getRecipient());
        assertEquals("Hello there", message.getMessage());
        assertEquals("12:1:HELLOTHERE", message.getMessageHash());
        assertEquals("Pending", message.getMessageStatus());
    }

    // TEST 7: Send a message successfully
    @Test
    public void testSendMessage() {

        Messages message = new Messages(
                "1234567890", 1, "+27718693002", "Hello"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage(new Scanner("1\n"))
        );

        assertEquals("Sent", message.getMessageStatus());
        assertEquals(1, message.returnTotalMessages());
    }

    // TEST 8: Disregard a message
    @Test
    public void testDisregardMessage() {

        Messages message = new Messages(
                "1234567890", 1, "+27718693002", "Hello"
        );

        assertEquals(
                "Message disregarded.",
                message.SentMessage(new Scanner("2\n1\n"))
        );

        assertEquals("Disregarded", message.getMessageStatus());
    }

    // TEST 9: Store a message successfully
    @Test
    public void testStoreMessage() {

        Messages message = new Messages(
                "1234567890", 1, "+27718693002", "Hello"
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage(new Scanner("3\n"))
        );

        assertEquals("Stored", message.getMessageStatus());
        assertEquals(1, StoredMessages.getStoredMessageCount());
    }

    // TEST 10: Check an invalid message option
    @Test
    public void testInvalidNumericChoice() {

        Messages message = new Messages(
                "1234567890", 1, "+27718693002", "Hello"
        );

        assertEquals(
                "Invalid choice. Please try again.",
                message.SentMessage(new Scanner("9\n"))
        );
    }

    // TEST 11: Display sent messages
    @Test
    public void testPrintMessages() {

        Messages message = new Messages(
                "1234567890", 1, "+27718693002", "Unique message"
        );

        message.SentMessage(new Scanner("1\n"));

        assertTrue(
                message.printMessages().contains("Unique message")
        );
    }
}
