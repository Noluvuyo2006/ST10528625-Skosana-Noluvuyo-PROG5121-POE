package ST10528625PROG5121PoePart1;

import java.util.ArrayList;
import java.util.Scanner;

public class StoredMessages {

    // ==========================================
    // ARRAY-BASED STORED MESSAGE INFORMATION
    // ==========================================

    private String[] messageID;
    private String[] messageSender;
    private String[] messageRecipient;
    private String[] messageText;
    private String[] messageHash;

    private int messageCount;

    // ==========================================
    // ARRAYLIST LINK TO MESSAGES CLASS
    // ==========================================

    private static final ArrayList<Messages> storedMessages =
            new ArrayList<>();

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public StoredMessages(
            String[] messageID,
            String[] messageSender,
            String[] messageRecipient,
            String[] messageText,
            String[] messageHash,
            int messageCount) {

        this.messageID = messageID == null ? new String[0] : messageID;
        this.messageSender = messageSender == null ? new String[0] : messageSender;
        this.messageRecipient = messageRecipient == null ? new String[0] : messageRecipient;
        this.messageText = messageText == null ? new String[0] : messageText;
        this.messageHash = messageHash == null ? new String[0] : messageHash;

        int maximumCount = Math.min(
                Math.min(this.messageID.length, this.messageSender.length),
                Math.min(
                        this.messageRecipient.length,
                        Math.min(this.messageText.length, this.messageHash.length)
                )
        );

        this.messageCount = Math.max(0, Math.min(messageCount, maximumCount));
    }

    // ==========================================
    // METHODS LINKED TO MESSAGES CLASS
    // ==========================================

    // Add a message object from Messages.java
    public static void addStoredMessage(Messages message) {

        if (message != null) {
            storedMessages.add(message);
        }
    }

    // Return all stored message objects
    public static ArrayList<Messages> getStoredMessages() {
        return new ArrayList<>(storedMessages);
    }

    // Display messages stored through Messages.java
    public static void displayStoredMessages() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }

        System.out.println("\n========== STORED MESSAGES ==========");

        for (Messages message : storedMessages) {

            System.out.println("Message ID: " + message.getMessageID());
            System.out.println("Recipient: " + message.getRecipient());
            System.out.println("Message: " + message.getMessage());
            System.out.println("Message Hash: " + message.getMessageHash());
            System.out.println("Message Status: " + message.getMessageStatus());

            System.out.println("-------------------------------------");
        }

        System.out.println("Total Stored Messages: " + storedMessages.size());
    }

    // Return number of messages stored through Messages.java
    public static int getStoredMessageCount() {
        return storedMessages.size();
    }

    // ==========================================
    // OPTION 1: DISPLAY STORED SENDERS AND RECIPIENTS
    // ==========================================

    public void displayStoredSendersAndRecipients() {

        if (messageCount == 0) {
            System.out.println("There are no stored messages.");
            return;
        }

        for (int i = 0; i < messageCount; i++) {

            System.out.println("\nMessage ID: " + messageID[i]);
            System.out.println("Sender: " + messageSender[i]);
            System.out.println("Recipient: " + messageRecipient[i]);
            System.out.println("Message: " + messageText[i]);
            System.out.println("Hash: " + messageHash[i]);
        }
    }

    // ==========================================
    // OPTION 2: DISPLAY LONGEST STORED MESSAGE
    // ==========================================

    public void getLongestStoredMessage() {

        if (messageCount == 0) {
            System.out.println("There are no stored messages.");
            return;
        }

        int longestIndex = 0;

        for (int i = 1; i < messageCount; i++) {

            if (messageText[i] != null
                    && messageText[longestIndex] != null
                    && messageText[i].length()
                    > messageText[longestIndex].length()) {

                longestIndex = i;
            }
        }

        System.out.println("\nLongest Stored Message");
        System.out.println("Message ID: " + messageID[longestIndex]);
        System.out.println("Message: " + messageText[longestIndex]);

        if (messageText[longestIndex] != null) {
            System.out.println(
                    "Length: " + messageText[longestIndex].length()
                    + " characters"
            );
        }
    }

    // ==========================================
    // OPTION 3: SEARCH MESSAGE USING MESSAGE ID
    // ==========================================

    public void searchByMessageID(Scanner input) {

        System.out.print("Enter the message ID: ");
        String searchID = input.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < messageCount; i++) {

            if (messageID[i] != null
                    && messageID[i].equalsIgnoreCase(searchID)) {

                System.out.println("\nMessage found!");
                System.out.println("Message ID: " + messageID[i]);
                System.out.println("Sender: " + messageSender[i]);
                System.out.println("Recipient: " + messageRecipient[i]);
                System.out.println("Message: " + messageText[i]);
                System.out.println("Message Hash: " + messageHash[i]);

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with that ID.");
        }
    }

    // ==========================================
    // OPTION 4: SEARCH MESSAGES USING RECIPIENT
    // ==========================================

    public void searchByRecipient(Scanner input) {

        System.out.print("Enter recipient number: ");
        String searchRecipient = input.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < messageCount; i++) {

            if (messageRecipient[i] != null
                    && messageRecipient[i].equalsIgnoreCase(searchRecipient)) {

                System.out.println("\nMessage found!");
                System.out.println("Message ID: " + messageID[i]);
                System.out.println("Sender: " + messageSender[i]);
                System.out.println("Recipient: " + messageRecipient[i]);
                System.out.println("Message: " + messageText[i]);
                System.out.println("Message Hash: " + messageHash[i]);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // ==========================================
    // OPTION 5: DELETE MESSAGE USING MESSAGE HASH
    // ==========================================

    public void deleteMessageByHash(Scanner input) {

        if (messageCount == 0) {
            System.out.println("There are no stored messages to delete.");
            return;
        }

        System.out.print("Enter the message hash of the message to delete: ");
        String searchHash = input.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < messageCount; i++) {

            if (messageHash[i] != null
                    && messageHash[i].equalsIgnoreCase(searchHash)) {

                System.out.println("\nMessage found!");
                System.out.println("Message ID: " + messageID[i]);
                System.out.println("Recipient: " + messageRecipient[i]);
                System.out.println("Message: " + messageText[i]);

                // Shift remaining elements to the left
                for (int j = i; j < messageCount - 1; j++) {

                    messageID[j] = messageID[j + 1];
                    messageSender[j] = messageSender[j + 1];
                    messageRecipient[j] = messageRecipient[j + 1];
                    messageText[j] = messageText[j + 1];
                    messageHash[j] = messageHash[j + 1];
                }

                // Clear the last position
                messageID[messageCount - 1] = null;
                messageSender[messageCount - 1] = null;
                messageRecipient[messageCount - 1] = null;
                messageText[messageCount - 1] = null;
                messageHash[messageCount - 1] = null;

                messageCount--;

                System.out.println("\nMessage successfully deleted!");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with that message hash.");
        }
    }

    // ==========================================
    // OPTION 6: DISPLAY FULL STORED MESSAGE REPORT
    // ==========================================

    public void displayFullMessageReport() {

        if (messageCount == 0) {
            System.out.println("\nThere are no stored messages available.");
            return;
        }

        System.out.println("\n==========================================");
        System.out.println("       FULL STORED MESSAGE REPORT");
        System.out.println("==========================================");

        for (int i = 0; i < messageCount; i++) {

            System.out.println("\nMessage Number: " + (i + 1));
            System.out.println("------------------------------------------");

            System.out.println("Message ID: " + messageID[i]);
            System.out.println("Sender: " + messageSender[i]);
            System.out.println("Recipient: " + messageRecipient[i]);
            System.out.println("Message: " + messageText[i]);
            System.out.println("Message Hash: " + messageHash[i]);
            System.out.println("Message Status: Stored");

            System.out.println("------------------------------------------");
        }

        System.out.println("\nTotal Stored Messages: " + messageCount);
        System.out.println("==========================================");
    }
}
