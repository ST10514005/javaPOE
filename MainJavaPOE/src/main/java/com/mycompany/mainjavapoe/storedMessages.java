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

//StoredMessages purpose:This class manages the messages that were saved (stored) by the chat app.
//This class enables the user view, search, delete and report on those stored messages through a
// text menu (showMenu)
 //Reference: W3schools-https://www.w3schools.com/js/tryit.asp?filename=tryjs_class_method //


public class StoredMessages {
    
//Overall approach consists of creating a message object, each piece of 
//informtion about a message is kept in it's own ArrayList, and the same index
//(position) in every list refers to the same message.
    
//Messages that were sent
    public static ArrayList<String> sentMessages = new ArrayList<>();
    
//Messages the user chose not to send (discarded).
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    
//The actual text of every stored message. Index(i) = message(i)
   public static ArrayList<String> storedMessages = new ArrayList<>();
   
//Used to find and delete a message (see deleteByHash)
   public static ArrayList<String> messageHashes = new ArrayList<>();
   
//Used to look a message up (see searchByMesessageId)
   public static ArrayList<String> messageIds = new ArrayList<>();
 
//The message number (order it was created) for each stored message.
   public static ArrayList<String> messageNums = new ArrayList<>();
   
//The recipient's cell number for each stored message at the same Index.
   public static ArrayList<String> recipients = new ArrayList<>();
   
//The logged-in user who sent the messages, it belongs to one StoredMessages object
   private String sender;
   
//Constructor: creates a StorageMessages object for a given sender.
//"this.sender = sender" copies the paramter into the field
//(this. is needed because both have the same name)
   public StoredMessages(String sender) {
       this.sender = sender;
   }
   
//The method below loops through the messages by index based of a string listing the sender and recipient of every stored stored message
    public String displaySendersAndRecipients() {
       
        if (storedMessages.size() == 0) {
            return "No stored messages.";
        }
        
        String output = "";
        
        for (int i = 0; i < storedMessages.size(); i++) {
            output = output + "\nSender: " + sender + "\nRecipient: " + recipients.get(i)
                    + "\n-----------------------------------------";
        }
        
        return output;
    }

//Approach: Assume the first message (index 0) is the longest, then compare every
//other message to the current longest. If one is longer, keep in mind it's position instead
    public String displayLongestMessage() {
        
        if (storedMessages.isEmpty()) {
            return "No stored message.";
        }
        
        int longestPosition = 0;
        
        for(int i = 1; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).length() > storedMessages.get(longestPosition).length()) {
                longestPosition = i;
                
            }
        }
        return "Longest stored message: " + storedMessages.get(longestPosition);
        
    }
    
//A linear search below was used to check each ID in order. When a match is found at 
//position i, use that SAME index i to get the recipient and message from the other
//lists
    public String searchByMessageId(String id) {
        
        for (int i = 0; i < messageIds.size(); i++) {
            if (messageIds.get(i).equals(id)) {
                return "Recipient: " + recipients.get(i) + "\nMessage: " + storedMessages.get(i);
                
            }
        }
        
        return "Message ID not found.";
    }
   
//The method below finds all messages sent to a given recipient cell number
// We keep looping and build up one String containing every match
    public String searchByRecipient (String cell) {
        
        String output = "";
        
        for (int i = 0; i < recipients.size(); i++) {
            
            if (recipients.get(i).equals(cell)) {
                output = output + "\nMessage: " + storedMessages.get(i);
                
            }
        }
        
        if (output.equals("")) {
            return "No messages found for that recipient.";
        }
        
        return output;
    }
    
 //The method below deletes a message using its hash by finding the index of the message
// whose hash matches, then removes the item at that index from every parallel list so the lists stay lined
// up. Finally the lists are saved so the deletion is permanent.

 
    public String deleteByHash(String hash) {
        
        for (int i = 0; i < messageHashes.size(); i++) {
            
            System.out.println("Typed: [" + hash + "] Stored: [" + messageHashes.get(i) + "]");
            
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                
                String deleted = storedMessages.get(i);
                
                messageIds.remove(i);
                messageNums.remove(i);
                recipients.remove(i);
                storedMessages.remove(i);
                messageHashes.remove(i);
                
                MessageStorage.saveStoredLists();
                
                return "Message \"" + deleted +"\" successfully deleted.";
                
            }
        }
        return "Message hash not found";
    }
    
//The method below builds a full report through the same looping feature showing all details listed
    public String displayReport() {
        
        if (storedMessages.size() == 0) {
            return "No stored messages.";
        }
        
        String output = "=========Stored messages report======";
        
        for (int i = 0; i < storedMessages.size(); i++) {
            output = output + "\nMessage ID: " + messageIds.get(i)
                    + "\nMessage Hash: " + messageHashes.get(i)
                    +"\nSender: " + sender
                    +"\nRecipient: " + recipients.get(i)
                    +"\nMessage: " + storedMessages.get(i)
                    + "\n----------------------";            
        }
        return output;
    }

//The method below empties every list, it is static so it can be called without
//without creating an object: StoredMessages.restLists().
//Typically used before reloading data or between unit tests so old data doesn't affect
// new results.
    public static void resetLists() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIds.clear();
        messageNums.clear();
        recipients.clear();
        
    }
    
//Method shows the "Stored Messages" menu and keeps running until the user chooses
// 0ption g.
    public void showMenu (Scanner input) {
        
        MessageStorage.loadMessages();

    // A flag that controls the loop. Setting it to false ends the menu.
        boolean running = true;
        
       
        while (running) {
            
            System.out.println("\n---Stored Messages---");
            System.out.println("a) Display the sender and recipient of all stored messages");
            System.out.println("b) Display the longest stored message");
            System.out.println("c) Search for a message ID");
            System.out.println("d) Search for all messages to a recipient");
            System.out.println("e) Delete a message using the message hash");
            System.out.println("f) Display a report of all stored messages");
            System.out.println("g) Back to main menu");
            System.out.print("Choose an option: ");
            
            String choice = input.nextLine().trim().toLowerCase();
            
            switch (choice) {
                
                case"a":
                    System.out.println(displaySendersAndRecipients());
                    break;
                    
                case"b":
                    System.out.println(displayLongestMessage());
                    break;
                    
                case"c":
                    System.out.print("Enter the message ID: ");
                    String id = input.nextLine().trim();
                    System.out.println(searchByMessageId(id));
                    break;
                    
                case "d":
                    System.out.print("Enter the recipient cell number: ");
                    String cell = input.nextLine().trim();
                    System.out.println(searchByRecipient(cell));
                    break;
                    
                case "e":
                    System.out.print("Enter the message hash: ");
                    String hash = input.nextLine().trim();
                    System.out.println(deleteByHash(hash));
                    break;
                    
                case "f":
                    System.out.println(displayReport());
                    break;
                    
                case "g":
                    //Stops the while loop, which returns to the main menu
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid option. Please choose a to g.");
            }
            
        }
}
       
}
