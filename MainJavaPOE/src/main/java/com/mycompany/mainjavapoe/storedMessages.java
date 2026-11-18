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
public class storedMessages {
    
    public static ArrayList<String> sentMessagees = new ArrayList<>();
    public static ArrayList<String> disregarededMessages = new ArrayList<>();
   //Declaration of Variable
   public static ArrayList<String> sentMessages = new ArrayList<>();
   public static ArrayList<String> disregardMessages = new ArrayList<>();
   public static ArrayList<String> storedMessages = new ArrayList<>();
   public static ArrayList<String> messageHashes = new ArrayList<>();
   public static ArrayList<String> messageIDs = new ArrayList<>();
   public static ArrayList<String> senders = new ArrayList<>();
   public static ArrayList<String> recipeints = new ArrayList<>();
   
   private String sender;
   
   public StoredMessages(String sender) {
       this sender = sender;
   }
   
    public String displaySendersAndRecipients() {
        if (storedMessages.size() == 0) {
            return "No stored messages.";
            
        }
        
        String output = "";
        
        for (int i = 0; i < storedMessages.size(); i++) {
            output = output + "\nSender: " + sender + "\nRecipient: " + recipient.get(i)
                    + "\n-----------------------------------------";
        }
        
        return output;
    }
    
    public String displayLongestMessage() {
        
        if (storedMessages.size() == 0) {
            return "No stored message.";
        }
        
        int longestPosition = 0;
        
        for(int i = 1; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).length() > storedMessages.get(longestPostion).length()) {
                longestPosition = i;
                
            }
        }
        return "Longet stored message: " + storedMessages.get(longestPosition);
        
    }
    
    public String searchByMessageId(String id) {
        
        for (int i = 0; i < messageIds.size(); i++) {
            if (messageIds.get(i).equals(id)) {
                return "Recipient: " + recipients.get(i) + "\nMessage: " + storedMessages.get(i);
                
            }
        }
        
        return "Message ID not found.";
    }
   
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
    
    public String deleteByHash(String hash) {
        
        for (int i = 0; i < messageHashes.size(); i++) {
            
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                
                String deleted = storedMessages.get(i);
                
                messageIds.remove(i);
                messageNums.remove(i);
                recipients.remove(i);
                storedMessages.remove(i);
                messagesHashes.remove(i);
                
                MessageStorage.saveStoredLists();
                
                return "Message \"" + deleted +"\" successfully deleted.";
                
            }
        }
        return "Message hash not found.";
    }
    public String displayReport() {
        
        if (storedMessages.size() == 0) {
            return "No stored messages.";
        }
        
        String output = "=========Stored messages report======";
        
        for (int i = 0; i < storedMessages.size(); i++) {
            output = output + "nMessage ID: " + messageIds.get(i)
                    + "\nMessage Hash: " + messagesHashes.get(i)
                    +"\nSender: " + sender
                    +"\nRecipient: " + recipients.get(i)
                    +"\nMessage: " + storedMessages.get(i)
                    + "\n----------------------";            
        }
        return output;
    }
    
    public static void resetLists() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIds.clear();
        messageNums.clear();
        recipients.clear();
        
    }
    
    public void showMenu (Scanner input) {
        
        MessageStorage.loadMessages();
        
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
                    System.out.prinln(displaySenderAndRecipients());
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
                    System.out.println(deletByHash(hash));
                    break;
                    
                case "f":
                    System.out.println(displayReport());
                    break;
                    
                case "g":
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid option. Please choose a to g.");
            }
            
        }
    }
       
}
