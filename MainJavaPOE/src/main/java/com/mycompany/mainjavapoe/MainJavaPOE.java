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

        System.out.print("Enter name: ");
        String name = myInput.next();

        System.out.print("Enter surname: ");
        String surname = myInput.next();

        System.out.print("Create a username: ");
        String userName = myInput.next();
        while (!loginClass.checkUserName(userName)) {
            System.out.println("Username incorrectly formatted");
            System.out.println("Username must:");
            System.out.println("Be no more than 5 characters long");
            System.out.println("Contain an underscore");
            System.out.print("Re-enter username: ");
            userName = myInput.next();
        }
        System.out.println("Username successfully captured");

        System.out.print("Create a password: ");
        String passWord = myInput.next();
        while (!loginClass.checkPasswordComplexity(passWord)) {
            System.out.println("Password is not correctly formatted");
            System.out.println("Password must:");
            System.out.println("Be at least 8 characters long");
            System.out.println("Contain at least one capital letter");
            System.out.println("Contain at least one number");
            System.out.println("Contain at least one special character");
            System.out.print("Re-enter password: ");
            passWord = myInput.next();
        }
        System.out.println("Password successfully captured");

        System.out.print("Enter cell phone number: ");
        String cellNum = myInput.next();
        while (!loginClass.checkCellPhoneNumber(cellNum)) {
            System.out.println("Cellphone number is incorrectly formatted");
            System.out.println("Cell phone number must start with +27 and be 12 characters long");
            System.out.print("Re-enter cell phone number: ");
            cellNum = myInput.next();
        }
        System.out.println("Cell phone number successfully captured");

        loginClass user = new loginClass(name, surname, userName, passWord, cellNum);

        System.out.println("\n---Login---");
        System.out.print("Enter username: ");
        String logUsername = myInput.next();
        System.out.print("Enter password: ");
        String logPassword = myInput.next();

        boolean isLoggedIn = user.loginUser(logUsername, logPassword);
        System.out.println(user.returnLoginStatus(isLoggedIn));
    }
}