/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainjavapoe;


import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ntoko
 */
public class Messages {
     //=====================================================
    // VARIABLES
    //=====================================================
 
    // Details of one message
    private String messageId;      // random ten-digit number
    private int messageNum;        // number of messages sent so far (first one is 0)
    private String recipientCell;  // recipient's cell number
    private String message;        // the message text (max 250 characters)
    private String messageHash;    // e.g. 00:0:HITHANKS
 
    // Lists shared by all messages (static)
    private static ArrayList<Messages> sentMessages = new ArrayList<>();
    private static ArrayList<Messages> storedMessages = new ArrayList<>();
    private static ArrayList<String> usedIds = new ArrayList<>();   // stops duplicate IDs
    
    
    //=====================================================
    // CONSTRUCTOR
    //=====================================================
 
    
   
    public Messages(String recipientCell, String message) {
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageId = createMessageId();          // random ten-digit ID
        this.messageNum = sentMessages.size();       // 0 for the first message, then 1, 2...
        this.messageHash = createMessageHash();      // needs the ID and number above
    }
    //=====================================================
    // GETTERS
    //=====================================================
 
    public String getMessageId() {
        return messageId;
    }
 
    public int getMessageNum() {
        return messageNum;
    }
 
    public String getRecipientCell() {
        return recipientCell;
    }
 
    public String getMessage() {
        return message;
    }
 
    public String getMessageHash() {
        return messageHash;
    }
    //=====================================================
    // MESSAGE ID
    //=====================================================
 
    // Creates a random ten-digit number that has not been used before
    private String createMessageId() {
 
        String newId;
 
        do {
            // Random number between 0 and 9999999999
            long number = (long) (Math.random() * 10000000000L);
 
            // %010d adds zeros at the front so the ID always has ten digits
            newId = String.format("%010d", number);
 
        } while (usedIds.contains(newId));   // try again if the ID was already used
 
        usedIds.add(newId);
 
        return newId;
    }
    // Checks that the message ID is not more than ten characters
    public boolean checkMessageID() {
        return messageId.length() <= 10;
    }
 
    //=====================================================
    // RECIPIENT CELL NUMBER
    //=====================================================
 
    // True if the number starts with +27 and is 12 characters long (+27 and 9 digits)
    public static boolean isValidRecipient(String cell) {
        return cell.startsWith("+27") && cell.length() == 12;
    }
 
    // Tells the user if the recipient number is correct
    public String checkRecipientCell() {
 
        if (isValidRecipient(recipientCell)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                    + "an international code, please correct the number and try again.";
        }
    }
    //=====================================================
    // MESSAGE LENGTH
    //=====================================================
 
    // True if the message is not longer than 250 characters
    public static boolean isValidMessageLength(String text) {
        return text.length() <= 250;
    }
    //=====================================================
    // MESSAGE HASH
    //=====================================================
 
    // First two digits of the ID : message number : FIRSTWORDLASTWORD (all capitals)
    public String createMessageHash() {
 
        // Split the message into words
        String[] words = message.trim().split(" ");
 
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
 
        String firstTwoDigits = messageId.substring(0, 2);
 
        String hash = firstTwoDigits + ":" + messageNum + ":" + firstWord + lastWord;
 
        return hash.toUpperCase();
    }
    //=====================================================
    // SEND, STORE OR DELETE THE MESSAGE
    //=====================================================
 
    public String SentMessage(Scanner input) {
 
        // Keep asking until the user chooses a valid option
        while (true) {
 
            System.out.println("\nWhat would you like to do with this message?");
            System.out.println("1) Send Message");
            System.out.println("2) Store Message to send later");
            System.out.println("0) Press 0 to delete the message");
            System.out.print("Choose an option: ");
 
            String choice = input.nextLine().trim();
 
            switch (choice) {
 
                case "1":
                    sentMessages.add(this);
                    return "Message successfully sent";
 
                case "2":
                    storeMessage();
                    return "Message successfully stored";
 
                case "0":
                    return "Message deleted";
 
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    //=====================================================
    // STORE THE MESSAGE (JSON)
    //=====================================================
 
    // Adds the message to the stored list and saves the list in message.json.
    // The JSON writing is done by the MessageStorage class.
    public void storeMessage() {
        storedMessages.add(this);
        MessageStorage.saveMessages(storedMessages);
    }
    //=====================================================
    // PRINT AND COUNT SENT MESSAGES
    //=====================================================
 
    // Returns all the messages sent while the program is running
    public static String printMessages() {
 
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
 
        String output = "";
 
        for (Messages current : sentMessages) {
            output = output + "\nMessage ID: " + current.messageId
                    + "\nMessage Hash: " + current.messageHash
                    + "\nRecipient: " + current.recipientCell
                    + "\nMessage: " + current.message
                    + "\n--------------------------";
        }
        
        return output;
    }
 
    // Returns the total number of messages sent
    public static int returnTotalMessages() {
        return sentMessages.size();
    }
 
    
 
 
 
}
