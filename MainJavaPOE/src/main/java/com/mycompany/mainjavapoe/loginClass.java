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
        
    public static boolean loginUser() {
        //Declarations of variables 
        String logUserName;
        String userName;
        String regUserName = userName;
        String logPassWord;
        String passWord;
        String regPassWord = passWord;
        
        while(true) {
    Scanner myInput = new Scanner(System.in);
    
    System.out.println("Please enter your username");
    logUserName = myInput.next();
    
    System.out.println("Enter your password");
    logPassWord = myInput.next();
    
    if (validateUsername(logUserName) && validatePassWord(logPassWord)) {
        
        System.out.println("Login successful!");
              return true;
    } else {
        
        System.out.println("Username or passed incorrect.");
        System.out.println("please try again.");
        
    }
   
    }
    }
    
    public static boolean validateUsername(String logUserName) {
        
     
        while(true){
        String regUserName; 
    if(regUserName.equals(logUserName)) {
        System.out.println("Username matches");
        return true;
    }else {
        System.out.println("Username incorrect,please try again");
        return false;
    }
    
    }
    
    }
    
    public static boolean validatePassWord (String logPassWord) {
        //Declarations
        String regPassWord;
        String logPassWord;
           while(true){
         
    if(regPassWord.equals(logPassWord)) {
        System.out.println("Password matches");
        return true;
        
    }else {
        System.out.println("Password incorrect,please try again");
        return false;
    }
        
    }
    }
}

