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
        String regUserName;
        String logPassWord;
        String regPassWord;
        
    Scanner myInput = new Scanner(System.in);
    
    System.out.println("Please enter your username");
    logUserName = myInput.next();
    
    System.out.println("Enter your password");
    logPassWord = myInput.next();
    
    
    checkUserName(String userName);
     checkPasswordComplexity(String passWord);
    
   
    }
    
    public static boolean validateUsername() {
            //Declarations
        String logUserName;
        String regUserName;
        while(true){
         
    if(regUserName.equals(logUserName)) {
        System.out.println("Username matches");
        return true;
        
    }
    else {
        System.out.println("Username incorrect,please try again");
        return false;
    }
    
    }
    
    }
    
    public static boolean validatePassword () {
        //Declarations
        String regPassWord;
        String logPassWord;
           while(true){
         
    if(regPassWord.equals(logPassWord)) {
        System.out.println("Password matches");
        return true;
        
    }
    else {
        System.out.println("Password incorrect,please try again");
        return false;
    }
        
    }
    }
}

