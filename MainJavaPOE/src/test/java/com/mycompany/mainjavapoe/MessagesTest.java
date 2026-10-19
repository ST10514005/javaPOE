/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainjavapoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
/**
 * Unit tests for the Messages class, using the test data from the brief.
 *
 * @author ntoko
 */
public class MessagesTest {
 
    //=====================================================
    // TEST DATA
    //=====================================================
 
    // Test Data for Message 1 (the user selects Send)
    private final String recipient1 = "+27718693002";
    private final String message1 = "Hi Mike, can you join us for dinner tonight?";
 
    // Test Data for Message 2 (the user selects Discard)
    private final String recipient2 = "08575975889";
    private final String message2 = "Hi Keegan, did you receive the payment?";
 
    // Runs before every test so the lists always start empty
    @BeforeEach
    public void setUp() {
        Messages.resetMessages();
    }
 
    //=====================================================
    // 1. MESSAGE LENGTH (not more than 250 characters)
    //=====================================================
 
    @Test
    public void testMessageLengthSuccess() {
        assertEquals("Message ready to send.", Messages.checkMessageLength(message1));
        assertEquals("Message ready to send.", Messages.checkMessageLength(message2));
    }
 
    @Test
    public void testMessageLengthFailure() {
 
        // Build a message that is 260 characters long (10 too many)
        String longMessage = "";
        for (int i = 0; i < 260; i++) {
            longMessage = longMessage + "a";
        }
 
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                Messages.checkMessageLength(longMessage));
    }
 
    //=====================================================
    // 2. RECIPIENT NUMBER
    //=====================================================
 
    @Test
    public void testRecipientNumberSuccess() {
        Messages msg = new Messages(recipient1, message1);
 
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }
 
    @Test
    public void testRecipientNumberFailure() {
        Messages msg = new Messages(recipient2, message2);
 
        assertEquals("Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.",
                msg.checkRecipientCell());
    }
 
    //=====================================================
    // 3. MESSAGE HASH
    //=====================================================
 
    // Test Case 1: the hash must be 00:0:HITONIGHT
    @Test
    public void testMessageHashTestCase1() {
        Messages msg = new Messages("0012345678", 0, recipient1, message1);
 
        assertEquals("00:0:HITONIGHT", msg.getMessageHash());
    }
 
    // Tests the remainder of the hashes in a loop
    @Test
    public void testMessageHashesInLoop() {
 
        String[] ids = {"0012345678", "0087654321"};
        int[] numbers = {0, 1};
        String[] recipients = {recipient1, recipient2};
        String[] messages = {message1, message2};
        String[] expectedHashes = {"00:0:HITONIGHT", "00:1:HIPAYMENT"};
 
        for (int i = 0; i < messages.length; i++) {
 
            Messages msg = new Messages(ids[i], numbers[i], recipients[i], messages[i]);
 
            assertEquals(expectedHashes[i], msg.getMessageHash());
        }
    }
 
    //=====================================================
    // 4. MESSAGE ID
    //=====================================================
 
    @Test
    public void testMessageIdCreated() {
        Messages msg = new Messages(recipient1, message1);
 
        // "Message ID generated: <Message ID>"
        assertEquals("Message ID generated: " + msg.getMessageId(), msg.messageIdGenerated());
 
        // The ID must be ten digits and no more than ten characters
        assertEquals(10, msg.getMessageId().length());
        assertTrue(msg.checkMessageID());
    }
 
    //=====================================================
    // 5. MESSAGE SENT (send, disregard, store)
    //=====================================================
 
    // 1) User selected 'Send Message'
    @Test
    public void testSendMessage() {
        Messages msg = new Messages(recipient1, message1);
 
        // The Scanner pretends the user typed 1 and pressed Enter
        Scanner input = new Scanner("1\n");
 
        assertEquals("Message successfully sent.", msg.SentMessage(input));
    }
 
    // 2) User selected 'Disregard Message'
    @Test
    public void testDisregardMessage() {
        Messages msg = new Messages(recipient2, message2);
 
        Scanner input = new Scanner("0\n");
 
        assertEquals("Press 0 to delete the message.", msg.SentMessage(input));
    }
 
    // 3) User selected 'Store Message' (this also creates message.json)
    @Test
    public void testStoreMessage() {
        Messages msg = new Messages(recipient1, message1);
 
        Scanner input = new Scanner("2\n");
 
        assertEquals("Message successfully stored.", msg.SentMessage(input));
    }
 
    //=====================================================
    // 6. RETURN TOTAL NUMBER SENT
    //=====================================================
 
    @Test
    public void testReturnTotalMessages() {
 
        // Message 1: Send, so the total becomes 1
        Messages first = new Messages(recipient1, message1);
        first.SentMessage(new Scanner("1\n"));
        assertEquals(1, Messages.returnTotalMessages());
 
        // Message 2: Discard, so the total stays 1
        Messages second = new Messages(recipient2, message2);
        second.SentMessage(new Scanner("0\n"));
        assertEquals(1, Messages.returnTotalMessages());
    }
}