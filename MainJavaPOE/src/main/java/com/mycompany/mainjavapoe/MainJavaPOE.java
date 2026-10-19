/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainjavapoe;

 

import java.util.Scanner;

/**
 *
 * @author ntoko
 */

//Main class: registration, login and the QuickChat Menu.
public class MainJavaPOE {

    //The method below prompts the user for details and stores them
    public static void main(String[] args) {
        
        Scanner myInput = new Scanner(System.in);
        
    //===============================================================
    //REGISTRATION
    //===============================================================

        System.out.print("Enter name: ");
        String name = myInput.next();

        System.out.print("Enter surname: ");
        String surname = myInput.next();

        //validation  for userName
        //Only the rules of the username are printed here
        System.out.print("Create a username: ");
        String userName = myInput.next();
        while (!loginClass.checkUserName(userName)) {
            System.out.println("Username must:");
            System.out.println("Be no more than 5 characters long");
            System.out.println("Contain an underscore");
            System.out.print("Re-enter username: ");
            userName = myInput.next();
        }

        //validation for password
        System.out.print("Create a password: ");
        String passWord = myInput.next();
        while (!loginClass.checkPasswordComplexity(passWord)) {
            System.out.print("Re-enter password: ");
            passWord = myInput.next();
        }


        //validation for phone numbers
        System.out.print("Enter cell phone number: ");
        String cellNum = myInput.next();
        while (!loginClass.checkCellPhoneNumber(cellNum)) {
            System.out.print("Re-enter cell phone number: ");
            cellNum = myInput.next();
        }
    
        //Below is the login phase where login details are needed which is username & password
        //Line 66 is basically linking the login class with the variable inputs
        loginClass user = new loginClass(name, surname, userName, passWord, cellNum);
        
        //=====================================================
        //LOGIN
        //=====================================================
        
        System.out.println("==========================");
        System.out.println("\n---Login---");
        System.out.println("==========================");
        
        
        System.out.print("Enter username: ");
        String logUsername = myInput.next();
        System.out.print("Enter password: ");
        String logPassword = myInput.next();
        myInput.nextLine().trim();

        //Line 74 checks whether username & password are correct, recalls whether the login wprked and 
        //diplay appropriate login message
        
        // Check if the username and password are correct and show the message
        boolean isLoggedIn = user.loginUser(logUsername, logPassword);
        System.out.println(user.returnLoginStatus(isLoggedIn));
        
        
       //=========================================================================
       //QUICKCHAT
       //==========================================================================
       
        //The user can only message after loggin successfully
        if(isLoggedIn) {
        
            System.out.println("\nWelcome to QuickChat.");
            
            //The user decides how many messages they want to enter
            System.out.print("How many messages do you wish to enter? ");
            
            while(!myInput.hasNextInt()) {
                System.out.print("Please enter a number: ");
                myInput.next();
            } 
            int numberOfMessages = myInput.nextInt();
            myInput.nextLine();
            
                //how many messages have been entered so far
                int messagesEntered = 0;
                boolean running = true;
      
            // The menu repeats until user chooses Quit
            while (running) {
               
                System.out.println("\n---Menu---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recentlty sent mesages");
                System.out.println("3) Quit");
                System.out.println("Choose an option: ");
                
                String userChoice = myInput.nextLine().trim();
               
                // The switch statement below structures and conducts the action of the user interms of the gesture they want to take
                // Switch statements are known for handling option related cases
                switch (userChoice) {
                    
                    case "1":
                        
            //Stop the user from entering more than the set number
                        if (messagesEntered >= numberOfMessages) {
                            System.out.println("You have already enterd all" + numberOfMessages + " of your meassages.");
                            
                            break;
                        }
                        
                        //Keep going until the set number of messages is entered
                  
                       while (messagesEntered < numberOfMessages) {
                          System.out.println("\n---- Message " + (messagesEntered + 1) + " of " + numberOfMessages + "---");
                       
                        
                // Recipient cell number
                       
                    System.out.print("Enter recipient cell number: ");
                    String recipient = myInput.nextLine().trim();
                    
                    while(!Messages.isValidRecipient(recipient)) {
                        System.out.println("Cell phone number is incorrectly formatted" + "or does not contain an international code.");
                        System.out.println("It must star with +27 followed by 9 digits.");
                        System.out.print("Re-enter recipient cell number:");
                        recipient = myInput.nextLine().trim();
                    }
                    
                    // Message text (not empty and not more than 250 characters)
                            System.out.print("Enter your message: ");
                            String messageText = myInput.nextLine();
 
                            while (messageText.trim().isEmpty()
                                    || !Messages.isValidMessageLength(messageText)) {
 
                                if (messageText.trim().isEmpty()) {
                                    System.out.println("The message cannot be empty.");
                                } else {
                                    System.out.println("Please enter a message of less than 250 characters.");
                                }
 
                                System.out.print("Enter your message again: ");
                                messageText = myInput.nextLine();
                            }
                    //Create the message (the ID, number and hash are made auatomatically)
                            
                            Messages newMessages = new Messages(recipient, messageText);
                            
                   // Check the message ID
                            if(!newMessages.checkMessageID()) {
                                System.out.println("Message ID must be no more than ten characters.");
                                break;
                            }
                            // Show the message details
                            System.out.println("\n--- Message Details ---");
                            System.out.println("Message ID: " + newMessages.getMessageId());
                            System.out.println("Message Hash: " + newMessages.getMessageHash());
                            System.out.println(newMessages.checkRecipientCell());
 
                            // Send, store or delete the message
                            System.out.println(newMessages.SentMessage(myInput));
 
                            messagesEntered++;
                       }
                            break;
                            
                    case "2":
                        System.out.println("Coming Soon");
                        break;
                        
                    case "3":
                        running = false;
                        System.out.println("Thank you for using QuickChat.");
                        break;
                        
                    default:
                        System.out.println("Invalid option. Please choose 1, 2 or 3.");
                        
                        break;
                            
                            }
                                
                        }
                    }
                  myInput.close();
 
                    }
                } 
              
        
    
