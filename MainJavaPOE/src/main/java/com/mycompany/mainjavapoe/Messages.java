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

//The Messages class stores the details of ONE message and also keeps
// the lists of messages that were sent and stored.
public class Messages {
 
    //=====================================================
    // VARIABLES
    //=====================================================
 
    
    // These variables are private so other classes cannot change them directly.
    // Other classes can only read them by using the getters below.
    // Details of one message
    private String messageId;      // random ten-digit number
    private int messageNum;        // number of messages sent so far (first one is 0)
    private String recipientCell;  // recipient's cell number
    private String message;        // the message text (max 250 characters)
    private String messageHash;    // e.g. 00:0:HITONIGHT
 
    
    // These lists are static, which means ALL messages share the same lists
    // (they do not belong to one message only).
    // Lists shared by all messages (static)
    private static ArrayList<Messages> sentMessages = new ArrayList<>(); //messages sent in the running of message.java
    private static ArrayList<Messages> storedMessages = new ArrayList<>();// messages saved for later
    private static ArrayList<String> usedIds = new ArrayList<>();   // stops duplicate IDs
 
    //=====================================================
    // CONSTRUCTORS
    //=====================================================
 
    // Used by the program: the ID and number are made automatically
     // This constructor is used by the main program.
    // It only needs the recipient and the text, because it makes the
    // ID, the number and the hash by itself.
    public Messages(String recipientCell, String message) {
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageId = createMessageId();          // random ten-digit ID
        this.messageNum = sentMessages.size();       // 0 for the first message, then 1, 2...
        this.messageHash = createMessageHash();      // needs the ID and number above
    }
 
    // Used by the unit tests: the ID and number are given, so the hash can be tested
    // This constructor is only used by the unit tests.
    // The ID and number are given to it, so I can test that the hash is correct
    // (a random ID would give a different hash every time).
    public Messages(String messageId, int messageNum, String recipientCell, String message) {
        this.messageId = messageId;
        this.messageNum = messageNum;
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageHash = createMessageHash();
    }
 
    //=====================================================
    // GETTERS
    //=====================================================
 
    // A getter just returns the value of a private variable so
    // other classes (like main and MessageStorage) can read it.
 
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
    
 
    // createMessageId: makes a random ten-digit number that has not been used before.
    // It is private because only this class needs to use it (the constructor calls it).
 
    //=====================================================
    // MESSAGE ID
    //=====================================================
 
    // Creates a random ten-digit number that has not been used before
    private String createMessageId() {
 
        String newId;
        
           // do...while runs at least once, then repeats if the ID was already used
        do {
            
            
            // Math.random() gives a number between 0 and 1, so I multiply it
            // to get a number between 0 and 9999999999
            long number = (long) (Math.random() * 10000000000L);
 
            // %010d adds zeros at the front so the ID always has ten digits
            newId = String.format("%010d", number);
 
        } while (usedIds.contains(newId));   // try again if the ID was already used
 
        // Remember this ID so it is never used again
        usedIds.add(newId);
 
        return newId;
    }
 
    // Checks that the message ID is not more than ten characters
    public boolean checkMessageID() {
        return messageId.length() <= 10;
    }
 
    // messageIdGenerated: returns the sentence "Message ID generated: " followed by the ID.
    // The brief asked for this sentence, and the unit test checks it.
    public String messageIdGenerated() {
        return "Message ID generated: " + messageId;
    }
 
    //=====================================================
    // RECIPIENT CELL NUMBER
    //=====================================================
 
  // isValidRecipient: returns true if the number starts with +27 and is
    // 12 characters long (+27 and then 9 digits).
    // It is static so main can use it without creating a message first
    public static boolean isValidRecipient(String cell) {
        return cell.startsWith("+27") && cell.length() == 12;
    }
 
    
    // checkRecipientCell: uses isValidRecipient and returns a sentence
    // telling the user if the number is correct or not.
    public String checkRecipientCell() {
 
        if (isValidRecipient(recipientCell)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                    + "an international code. Please correct the number and try again.";
        }
    }
 
    //=====================================================
    // MESSAGE LENGTH
    //=====================================================
 
   // isValidMessageLength: returns true if the message is 250 characters or fewer.
    // The main class uses this one in its loop when it asks for the message.
    public static boolean isValidMessageLength(String text) {
        return text.length() <= 250;
    }
    
    // checkMessageLength: returns a sentence about the length.
    // If the message is too long it also tells the user by how many characters.
    
    public static String checkMessageLength(String text) {
 
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int tooMany = text.length() - 250;   // how many characters over the limit
            return "Message exceeds 250 characters by " + tooMany
                    + "; please reduce the size.";
        }
    }
 
    //=====================================================
    // MESSAGE HASH
    //=====================================================
 
    
    // createMessageHash: builds the hash from the first two digits of the ID,
    // the message number, and the first and last word of the message (all capitals).
    public String createMessageHash() {
 
        // Split the message into words( a space separates the words)
        String[] words = message.trim().split(" ");
 
        // Remove punctuation such as , or ? so "Today?" becomes "today"
        // Take the first and last word, and remove punctuation such as , or ?
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
 
        // The first two characters of the ID
        String firstTwoDigits = messageId.substring(0, 2);
 
       
        // Join everything together with colons
        String hash = firstTwoDigits + ":" + messageNum + ":" + firstWord + lastWord;
        
        //// Change the whole hash to capital letters
        return hash.toUpperCase();
    }
 
    //=====================================================
    // SEND, STORE OR DELETE THE MESSAGE
    //=====================================================
 
    // SentMessage: asks the user what to do with the message and does it.
    // It needs the Scanner from main so that the program only uses one Scanner.
    public String SentMessage(Scanner input) {
 
        // Keep asking until the user chooses a valid option
        while (true) {
 
            System.out.println("\nWhat would you like to do with this message?");
            System.out.println("1) Send Message");
            System.out.println("2) Store Message to send later");
            System.out.println("0) Press 0 to delete the message");
            System.out.print("Choose an option: ");
 
            String choice = input.nextLine().trim();
 
             // The switch decides what happens for each choice
            switch (choice) {
 
                case "1":
                     // Send: add this message to the list of sent messages
                    sentMessages.add(this);
                    return "Message successfully sent.";
 
                case "2":
                    storeMessage();
                    // Store: save the message for later (storeMessage does the work)
                    return "Message successfully stored.";
 
                case "0":
                     // Delete: the message is not added to any list
                    return "Press 0 to delete the message.";
 
                default:
                      // Wrong choice: print an error and the loop asks again
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
 
    //=====================================================
    // STORE THE MESSAGE (JSON)
    //=====================================================
 
    
    // storeMessage: adds the message to the stored list and then asks the
    // MessageStorage class to save the whole list in message.json.
    // The JSON code is in MessageStorage so this class stays simple.
    public void storeMessage() {
        storedMessages.add(this);
        MessageStorage.saveMessages(storedMessages);
    }
 
    //=====================================================
    // PRINT AND COUNT SENT MESSAGES
    //=====================================================
 
    
    // printMessages: returns one piece of text that has every message sent
    // while the program was running (ID, hash, recipient and the message).
    public static String printMessages() {
 
        // If nothing was sent yet, say so
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
 
        String output = "";
 
         // Go through the sent messages one by one and add their details
        for (Messages current : sentMessages) {
            output = output + "\nMessage ID: " + current.messageId
                    + "\nMessage Hash: " + current.messageHash
                    + "\nRecipient: " + current.recipientCell
                    + "\nMessage: " + current.message
                    + "\n--------------------------";
        }
 
        // returnTotalMessages: returns how many messages have been sent.
    // That is the same as the size of the sent list.
        return output;
    }
 
   
    
    // returnTotalMessages: returns how many messages have been sent.
    // That is the same as the size of the sent list.
    public static int returnTotalMessages() {
        return sentMessages.size();
    }
 
    
    // resetMessages: empties the three lists so every unit test starts fresh
    // (the lists are static, so they would keep old messages otherwise).
 
    public static void resetMessages() {
        sentMessages.clear();
        storedMessages.clear();
        usedIds.clear();
    }
}
 