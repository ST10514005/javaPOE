/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainjavapoe;

import java.util.Scanner;

/**
 *
 * @author ntoko
 */
public class MainJavaPOE {

    //The method below prompts the user for details and stores them
    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);

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


        //validation for phone number
        System.out.print("Enter cell phone number: ");
        String cellNum = myInput.next();
        while (!loginClass.checkCellPhoneNumber(cellNum)) {
            System.out.print("Re-enter cell phone number: ");
            cellNum = myInput.next();
        }

        //Below is the login phase where login details are needed which is username & password
        //Line 66 is basically linking the login class with the variable inputs
        loginClass user = new loginClass(name, surname, userName, passWord, cellNum);

        System.out.println("\n---Login---");
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
                System.out.println("\n---Menu---");
                System.out.println("1) Write a message");
                System.out.println("2) View stored mesages");
                System.out.println("3) Quit");
                System.out.println("Choose an option: ");
                String userChoice = myInput.next();
                myInput.nextLine();
                
                // The switch statement below structures and conducts the action of the user interms of the gesture they want to take
                // Switch statements are known for handling option related cases
                switch (userChoice) {
                    case "1":
                        System.out.print("Recipient cell number: ");
                        String recipient = myInput.next();
                        myInput.nextLine();
                        
                        //Silent validator, so no duplicate messages are printed
                       
                            
                    
                }
            }
        }
    }
}