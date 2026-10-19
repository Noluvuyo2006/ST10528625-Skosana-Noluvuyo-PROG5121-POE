/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ST10528625PROG5121PoePart1;

/**
 *
 * @author Student
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Messages {
    //Declare the variables
    private String messageID;
    private int option;
    private String recipient; 
    private String message;
    private String messageHash;
    private String messageStatus;
    
    //Stores messages sent during the program
    private static ArrayList<Messages> sentMessages = new ArrayList<>();
    
    //Stores messages that were disregarded
    private static ArrayList<Messages> disregardedMessages = new ArrayList<>();
    
    //Stores messages saved for later
    private static ArrayList<Messages> storedMessages = new ArrayList<>();
    
    //Total number of messages sent
    private static int totalMessages = 0;
    
    //==============================================
    //CONSTRUCTOR
    //==============================================
    public Messages(
            String messageID,
            int messageNumber,
            String recipient,
            String message)
    {
        this.messageID = messageID;
        this.option = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageStatus = "Pending";
    }
    
    // Login and open QuickChat after successful authentication
    public static void startLogin(login user) {

        Scanner input = new Scanner(System.in);

        boolean loggedIn = false;

        while (!loggedIn) {

            System.out.println("\nPlease log in.");
            System.out.print("Enter username: ");
            String enteredUsername = input.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = input.nextLine();

            //Linking messsages class to login class
            loggedIn = user.loginUser(
                    enteredUsername, enteredPassword);

            //Display the login status
            System.out.println(user.returnLoginStatus());

            if (!loggedIn) {
                System.out.println("Please try again.");
            }
        }

        // Only reached after successful login
        startQuickChat(input);
    }
            

    //Method after a successful login
    public static void startQuickChat(Scanner input) {

        int option = 0;

        System.out.println("Welcome to QuickChat.");

        while (option != 3) {

            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Enter your option: ");
            
            if (!input.hasNextInt()) {
                System.out.println("Please enter a number.");
                input.nextLine();
                continue;
            }

            option = input.nextInt();
            input.nextLine();

            switch (option) {

                case 1:
                    sendMessages(input);
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    storeMessages();
                    System.out.println("Thank you for using QuickChat.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        input.close();
    }
    
    // Send messages
    public static void sendMessages(Scanner input) {

        System.out.print("How many messages do you want to send? ");

        if (!input.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            input.nextLine();
            return;
        }

        int numberOfMessages = input.nextInt();
        input.nextLine();

        if (numberOfMessages <= 0) {
            System.out.println(
                    "The number of messages must be greater than zero.");
            return;
        }

        for (int i = 0; i < numberOfMessages; i++) {

            System.out.println("\nCreating message " + (i + 1));

            String recipient;

            // Validate the South African international number
            while (true) {
                System.out.print(
                        "Enter recipient number (+27 followed by 9 digits): ");
                recipient = input.nextLine().trim();

                if (recipient.matches("\\+27\\d{9}")) {
                    break;
                }

                System.out.println(
                        "Invalid number. Use +27 followed by 9 digits.");
            }

            System.out.print("Enter your message: ");
            String messageText = input.nextLine().trim();

            if (messageText.isEmpty()) {
                System.out.println(
                        "Message cannot be empty. Please try again.");
                i--;
                continue;
            }

            // Generate a message ID of 10 digits
            String messageID = String.format(
                    "%010d",
                    new Random().nextInt(1_000_000_000));

            int messageNumber = totalMessages + 1;

            Messages newMessage = new Messages(
                    messageID, messageNumber, recipient, messageText);

            if (!newMessage.checkMessageID()) {
                System.out.println("Invalid message ID.");
                i--;
                continue;
            }

            newMessage.createMessageHash();

            System.out.println("\nRecipient: " + recipient);
            System.out.println("Message: " + messageText);
            System.out.println("Message Hash: " + newMessage.messageHash);

            System.out.println(newMessage.SentMessage(input));
        }
    }
    
    //Create a method that ensures the message ID meets the requirements
    //The message ID must not be more than 10 characters
    public String createMessageHash() {

        if (messageID == null || messageID.length() < 2
                || message == null || message.trim().isEmpty()) {
            return "";
        }

        // Get the first two characters of the message ID
        String firstTwoNumbers = messageID.substring(0, 2);

        // Split the message into words
        String[] words = message.trim().split("\\s+");

        // Get the first and last words
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "");

        // Build and return the hash
        messageHash = (firstTwoNumbers + ":" + option + ":"
                + firstWord + lastWord).toUpperCase();

        return messageHash;
    }

    
    //Create a method that will allow the user to choose if they want to send, store or disregard the message
    public String SentMessage(Scanner input) {
        System.out.println("\nChoose what you want to do with the message:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to Send Later");
        System.out.print("Enter your choice: ");

        int option = input.nextInt();
        input.nextLine();

        switch (option) {

            case 1:
                messageStatus = "Sent";
                sentMessages.add(this);
                totalMessages++;

                return "Message successfully sent";

            case 2:
                messageStatus = "Disregarded";
                disregardedMessages.add(this);

                System.out.println("Press 0 to delete the message.");
                System.out.print("Enter your choice: ");

                int deleteOption = input.nextInt();
                input.nextLine();

                if (deleteOption == 0) {
                    messageStatus = "Deleted";
                    disregardedMessages.remove(this);
                    return "Message successfully deleted";
                }

                return "Message disregarded";

            case 3:
                messageStatus = "Stored";
                storedMessages.add(this);

                return "Message successfully stored";

            default:
                return "Invalid choice. Please try again.";
        }
    }
        
    //Create a method that will return all the messages sent duirng the program
    public String printMessages() {

        StringBuilder allMessages = new StringBuilder();

        for (Messages msg : sentMessages) {
            allMessages.append(msg.message)
                       .append("\n");
        }

        return allMessages.toString();
    }
    
    //Create a method that will return the total number of messages sent
    public int returnTotalMessages() {
        return totalMessages;
    }

   
  // Save sent messages to a JSON file
    public static void storeMessages() {

        try (FileWriter writer = new FileWriter(
                "src/ST10528625PROG5121PoePart1/messages.json")) {

            writer.write("[\n");

            for (int i = 0; i < sentMessages.size(); i++) {

                Messages msg = sentMessages.get(i);

                writer.write("  {\n");
                writer.write("    \"messageID\": \""
                        + escapeJson(msg.messageID) + "\",\n");
                writer.write("    \"messageNumber\": "
                        + msg.option + ",\n");
                writer.write("    \"recipient\": \""
                        + escapeJson(msg.recipient) + "\",\n");
                writer.write("    \"message\": \""
                        + escapeJson(msg.message) + "\",\n");
                writer.write("    \"messageHash\": \""
                        + escapeJson(msg.messageHash) + "\",\n");
                writer.write("    \"messageStatus\": \""
                        + escapeJson(msg.messageStatus) + "\"\n");
                writer.write("  }");

                if (i < sentMessages.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]\n");

            System.out.println(
                    "Messages successfully stored in JSON file.");

        } catch (IOException e) {
            System.out.println(
                    "Error saving messages: " + e.getMessage());
        }
    }

    // Escape special characters for valid JSON
    private static String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

   public String generateMessageID() {
        StringBuilder id = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }

        messageID = id.toString();

        System.out.println("Message ID generated: " + messageID);

        return messageID;
    }

    
    public boolean checkMessageID() {
        return messageID != null
                && messageID.matches("[0-9]{1,10}");
    }

    public boolean checkRecipientCell() {
        return recipient != null
                && recipient.matches("\\+27[6-8][0-9]{8}");
    }


}
