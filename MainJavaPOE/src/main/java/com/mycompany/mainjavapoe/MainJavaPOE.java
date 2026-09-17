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

    public static void main(String[] args) {
         Scanner myInput = new Scanner(System.in);
        
        //Declarations
        String name;
        String surname;
        String userName;
        String logUsername;
        String passWord;
        String logPassword;
        String cellNum;
        char character;
          
        System.out.print("Enter name :" + "");
        name = myInput.next();
        
        System.out.print("Enter surname :" + "");
        surname = myInput.next();
        
        System.out.print("Enter cell phone number :" + "");
        cellNum = myInput.next();
        
        System.out.print("Create a username :" + "");
        userName = myInput.next();
 
        System.out.print("Create a password :" + " ");
        passWord = myInput.next();
   
        //Validation for registration (username)
        while (!(userName.length() <= 5 && userName.contains(""))) {
      
                  System.out.println("Username incorrectly informatted");
                  System.out.println("Username must: ");
                  System.out.println("Be no more 5 characters long");
                  System.out.println("Contain an underscore");
                  System.out.println("Re-enter username: ");
                  userName = myInput.next();
        }
                  System.out.println("Username successfully captured");
                  
                  //Validation for registration (password)
                  while (!(checkPasswordComplexity(passWord))) {
              
              
                  System.out.println("Password is not correctly formatted");
                  System.out.println("Password must: ");
                  System.out.println("Be at least 8 characters long");
                  System.out.println("Contain at least one capital letter");
                  System.out.println("Contain atleast one number");
                  System.out.println("Contain at least one special character");
              
              System.out.print("Re-enter password: ");
              passWord = myInput.next();
                  
                  }
                  loginClass user = new loginClass(name, surname, userName, passWord, cellNum);
                  
                  // Login Validation
                  System.out.println("/n---Login---");
                  System.out.println("Enter username");
                  logUsername = myInput.next();
                  System.out.println("Enter passWord");
                  logPassword = myInput.next();
                  
                  
          
          System.out.println("Password successfully captured");
           
          //Validation for cell phone number on Registration
          while (!(checkCellPhoneNumber(cellNum))){
              System.out.println("Cellphone number is incorrectly formatted");
              System.out.println("Cell phone number must contain international country code");
              
              System.out.println("Re-enter cell phone number");
              cellNum = myInput.nextLine();
          }
                  
    }
    public static boolean checkUserName(String userName)  {
        
     
          if(userName.length() <= 5 && userName.contains("_")) {
              System.out.println("Username successfully captured"); 
              
          } else {
              System.out.println("Username is not correctly formatted");
          }
          return true;
      }
    
      
    
        
     public static boolean checkPasswordComplexity(String passWord) {
   
            boolean hasUppercase = false;
            boolean hasNumber = false;
            boolean hasSpecial = false;
            
            for (int i = 0; i < passWord.length(); i++) {
                
                 char character;
                 
                character = passWord.charAt(i);
                
                if (Character.isUpperCase(character)) {
                    hasUppercase = true;
                } 
                
                if (Character.isDigit(character)) {
                    hasNumber = true;
                }
                
                if (!Character.isLetterOrDigit(character)) {
                    hasSpecial = true;
                }
                }
            if (passWord.length() >=8 && hasUppercase && hasNumber && hasSpecial) {
                System.out.println("Password successfully captured");
                return true;
            }
            else {
                System.out.println("Password is not correctly formatted");
                System.out.println ("Please ensure that the password contains at least eight characters, a capital letter, a number and a special character");
                return false;
            }
     }
      public static boolean checkCellPhoneNumber(String cellNum) {
             
            while (true) {
      
                String countryCode = "+27";
                boolean hasCountryCode = false;
                
                if (cellNum.startsWith("+27") && cellNum.length() == 12) {
                    System.out.println("Cell phone number is successfully added");
                    return true;
                    
                } 
                else {
                    System.out.println("Cellphone number is incorrect or does not contain international code");
                    System.out.println("Please ensure that the number starts with +27 and contains 9 digits after +27");
                    return false;
                }
            } 
            }
      
      
  }

     
