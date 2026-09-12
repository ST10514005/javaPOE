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
        String cellNum;
        String passWord;
        char character;
          
        
        
        System.out.print("Enter name :" + "");
        name = myInput.next();
        
        System.out.print("Enter surname :" + "");
        surname = myInput.next();
        
        System.out.print("Enter cell phone number :" + "");
        cellNum = myInput.next();
        
        checkCellPhoneNumber(cellNum);
 
        
        System.out.print("Create a username :" + "");
        userName = myInput.next();
        checkUserName(userName); 
         
            System.out.print("Create a password :" + " ");
            passWord = myInput.next();
            checkPasswordComplexity(passWord);
        
    }
    public static boolean checkUserName(String userName) 
    {
        
        while ( true) {
            
            if (userName.length() <= 5 && userName.contains("_")) {
                System.out.println("Username successfully captured");
                return true;
              
            }
            
            else 
            {
                System.out.println("Username is not correctly formatted");
                System.out.println("Please ensure your usename contains an underscore and is no more five characters in length");
                return false;
      
            }
            
        }
        
        }
     public static boolean checkPasswordComplexity(String passWord) {
   
        while (true) {
           
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
            if (hasUppercase && hasNumber && hasSpecial) {
                System.out.println("Password successfully captured");
                return true;
            }
            else {
                System.out.println("Password is not correctly formatted");
                System.out.println ("Please ensure that the password contains at least eight characters, a capital letter, a number and a special character");
                return false;
            }
            }
   
            }
         
    
    
}
