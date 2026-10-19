/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainjavapoe;

 import com.mycompany.messages.Messages;

import java.util.Scanner;

/**
 *
 * @author ntoko
 */
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
        
        System.out.println("==========================");
        System.out.println("\n---Login---");
        System.out.println("==========================");
        
        
        System.out.print("Enter username: ");
        String logUsername = myInput.next();
        System.out.print("Enter password: ");
        String logPassword = myInput.next();

        //Line 74 checks whether username & password are correct, recalls whether the login wprked and 
        //diplay appropriate login message
        boolean isLoggedIn = user.loginUser(logUsername, logPassword);
        System.out.println(user.returnLoginStatus(isLoggedIn));
        
        //The user can only message after loggin successfully
        if(isLoggedIn) {
            //The feature below loads messages from the previous times used (messages.json)
            
            
            boolean running =true;
            
            while (running) {
                
                System.out.println("\n==========================");
                System.out.println("                 QUICKCHAT");
                System.out.println("============================");
                
                System.out.println("\n---Menu---");
                System.out.println("1) Write a message");
                System.out.println("2) View sent mesages");
                System.out.println("3) View stored messages");
                System.out.println("4) View total messages sent");
                System.out.println("5) Quit");
                
                System.out.println("Choose an option: ");
                
                String userChoice = myInput.next();
                myInput.nextLine();
                
                // The switch statement below structures and conducts the action of the user interms of the gesture they want to take
                // Switch statements are known for handling option related cases
                switch (userChoice) {
                    
                    case "1":
                        
                //=======================================================
                // WRITE A MESSAGE
                //=======================================================
                   
                        String recipient;
                        
                        System.out.print("Enter recipient cellphone number: ");
                        
                        recipient = myInput.nextLine().trim();
                        
                        while (!messages.isValidRecipient(recipient)) {
                            
                            System.out.println("Invalid cellphone.");
                            
                            System.out.println("The number must start with +27 "
                            + "and contain 9 digits after it.");
                            
                            System.out.print("Re-enter recipient cellphone number");
                            
                            recipient = myInput.nextLine().trim();
                            
                        }
                        
                        //Enter message content
                        String messageText;
                        
                        System.out.print("Enter your message");
                        messageText = myInput.nextLine();
                        
                        while (messageText.trim().isEmpty() || messageText.length() > 250) {
                            
                            if (messageText.trim().isEmpty()) {
                                
                                System.out.print("The message cannot be empty.");
                                
                            }else {
                                
                                System.out.println("The message must exceed" + "250 character");
                            }
                            
                            System.out.print("Enter your message again: ");
                            
                            messageText = myInput.nextLine();
                      
                            }
                        // Generate a unique ID
                        String messageId = Messages.createUniqueMessageId();
                        
                        
                        //Assign a message number
                        //This first message number is 0
                        int messageNum = 0;
                        
                        //Create the message object
                        Messages newMessage = new Messages (messageId, messageNum, recipient, messageText,user.getUserName());
                        }
                
                //Validate the generated ID
                if (!newMessage.checkMessageId()){
                    
                    System.out.println("Message ID validation failed");
                    
                    break;
                    
                }
                
                //Diplay message details
                System.out.println("\n----Message Details ---");
                
                System.out.println("Message ID: " + newMessage.getMessageId());
                
                System.out.println("Message Hash: " + newMessage.getMessageHash
                      
                System.out.println(newMessage.checkRecipient());
                
                //let the user choose what to do
                System.out.println(newMessage.checkRecipient());
                
                  break;
                  
                  case "2":
                  
                  //Display messages sent this session
                  
                Messages displayMessages =
                            new Messages(
                                    "0000000000",
                                    0,
                                    "+27000000000",
                                    "Display",
                                    user.getUserName()
                            );

                    System.out.println(
                            displayMessages.printMessages());

                    break;
                    
                    case "3":

                    // Display messages stored in JSON
                    System.out.println(
                            Messages.printStoredMessages());

                    break;

                case "4":

                    // Display total sent messages
                    Messages countMessages =
                            new Messages(
                                    "0000000000",
                                    0,
                                    "+27000000000",
                                    "Count",
                                    user.getUserName()
                            );

                    System.out.println(System.out.println(
                            "Total messages sent this session: "
                            + countMessages.returnTotalMessages());

                    break;

                case "5":

                    running = false;

                    System.out.println(
                            "Thank you for using QuickChat!");

                    break;

                default:

                    System.out.println(
                            "Invalid option. Please select "
                            + "a number between 1 and 5.");

                    break;
            }
        }

        myInput.close();
    }
            }
        
      
                }
            }
        
    
