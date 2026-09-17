/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainjavapoe;


/**
 *
 * @author ntoko
 */
public class loginClass {
    //Declarations
    String name;
    String pName;
    String surname;
    String gSurname;
    String userName;
    String passWord;
    String cellNum;

    public loginClass() {
    }

    public loginClass(String name, String surname, String userName, String passWord, String cellNum) {
        this.name = name;
        this.pName = name;
        this.surname = surname;
        this.gSurname = surname;
        this.userName = userName;
        this.passWord = passWord;
        this.cellNum = cellNum;
    }

    public static boolean checkUserName(String userName) {
        if (userName.length() <= 5 && userName.contains("_")) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted");
            return false;
        }
    }

    public static boolean checkPasswordComplexity(String passWord) {
        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < passWord.length(); i++) {
            char character = passWord.charAt(i);

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
        if (passWord.length() >= 8 && hasUppercase && hasNumber && hasSpecial) {
            System.out.println("Password successfully captured");
            return true;
        } else {
            System.out.println("Password is not correctly formatted");
            System.out.println("Please ensure that the password contains at least eight characters, a capital letter, a number and a special character");
            return false;
        }
    }

    public static boolean checkCellPhoneNumber(String cellNum) {
        if (cellNum.startsWith("+27") && cellNum.length() == 12) {
            System.out.println("Cell phone number is successfully added");
            return true;
        } else {
            System.out.println("Cellphone number is incorrect or does not contain international code");
            System.out.println("Please ensure that the number starts with +27 and contains 9 digits after +27");
            return false;
        }
    }

    public String registerUser(String userName, String passWord, String cellNum) {
        if (!checkUserName(userName)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(passWord)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNum)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        this.userName = userName;
        this.passWord = passWord;
        this.cellNum = cellNum;

        return "Welcome " + pName + ", " + gSurname + " it is great to see you.";
    }

    public boolean loginUser(String logUsername, String logPassword) {
        return logUsername.equals(userName) && logPassword.equals(passWord);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            // True
            return "Welcome " + pName + ", " + gSurname + " it is great to see you again.";
        } else {
            //If login is not successful
            return "Username or password, incorrect , please try again.";
        }
    }
}