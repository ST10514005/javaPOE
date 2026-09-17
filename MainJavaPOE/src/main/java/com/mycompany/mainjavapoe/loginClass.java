/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainjavapoe;

import java.util.Scanner;
 
/**
 *
 * @author ntoko
 */
public class loginClass {
    
        public static void loginClass() {
             //Declarations
        String name;
        String surname;
        String userName;
        String logUsername;
        String passWord;
        String logPassword;
        String cellNum;
        
            
            Scanner myInput = new Scanner(System.in);
            
            System.out.print("Enter username");
            logUsername = myInput.nextLine();
            
            System.out.print("Enter password");
            logPassword = myInput.nextLine();
            
            //Comparing the Strings
            if (logUsername.equals(MainJavaPOE.checkUserName(userName)) && logPassword.equals(MainJavaPOE.checkPasswordComplexity(passWord))) {
                
                System.out.println("Login successful!");
        } else {
                System.out.print("Login is unsuccessful");
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
