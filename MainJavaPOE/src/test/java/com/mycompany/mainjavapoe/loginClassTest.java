/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainjavapoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ntoko
 */
public class loginClassTest {

    public loginClassTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkUserName method, of class loginClass.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");

        // Correctly formatted: 5 characters or fewer, contains an underscore
        String validUserName = "kyl_1";
        assertEquals(true, loginClass.checkUserName(validUserName));

        // Incorrectly formatted: too long and no underscore
        String invalidUserName = "kyle!!!!!!!";
        assertEquals(false, loginClass.checkUserName(invalidUserName));

        // Incorrectly formatted: empty string
        String emptyUserName = "";
        assertEquals(false, loginClass.checkUserName(emptyUserName));
    }

    /**
     * Test of checkPasswordComplexity method, of class loginClass.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");

        // Correctly formatted: 8+ chars, uppercase, number, special character
        String validPassword = "Ch&&sec@ke99!";
        assertEquals(true, loginClass.checkPasswordComplexity(validPassword));

        // Incorrectly formatted: no uppercase, no number, no special character
        String invalidPassword = "password";
        assertEquals(false, loginClass.checkPasswordComplexity(invalidPassword));

        // Incorrectly formatted: empty string
        String emptyPassword = "";
        assertEquals(false, loginClass.checkPasswordComplexity(emptyPassword));
    }

    /**
     * Test of checkCellPhoneNumber method, of class loginClass.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");

        // Correctly formatted: starts with +27, 12 characters total
        String validCellNum = "+27838968976";
        assertEquals(true, loginClass.checkCellPhoneNumber(validCellNum));

        // Incorrectly formatted: missing international code
        String invalidCellNum = "08966553";
        assertEquals(false, loginClass.checkCellPhoneNumber(invalidCellNum));

        // Incorrectly formatted: empty string
        String emptyCellNum = "";
        assertEquals(false, loginClass.checkCellPhoneNumber(emptyCellNum));
    }

    /**
     * Test of loginUser method, of class loginClass.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");

        loginClass instance = new loginClass("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");

        // Correct username and password
        assertEquals(true, instance.loginUser("kyl_1", "Ch&&sec@ke99!"));

        // Incorrect username and password
        assertEquals(false, instance.loginUser("kyle!!!!!!!", "password"));
    }

    /**
     * Test of returnLoginStatus method, of class loginClass.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");

        loginClass instance = new loginClass("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");

        // Successful login
        String expectedSuccess = "Welcome Kyle, Smith it is great to see you again.";
        assertEquals(expectedSuccess, instance.returnLoginStatus(true));

        // Failed login
        String expectedFailure = "Username or password, incorrect , please try again.";
        assertEquals(expectedFailure, instance.returnLoginStatus(false));
    }

}