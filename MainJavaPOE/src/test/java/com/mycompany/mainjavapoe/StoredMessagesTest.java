package com.mycompany.mainjavapoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the StoredMessages class (Part 3).
 * The test data comes from the assignment brief (Test Data Messages 1 to 5).
 *
 * @author ntoko
 */
public class StoredMessagesTest {

    // The object we are testing
    StoredMessages instance;

    // ---------- TEST DATA FROM THE BRIEF ----------
    // Message 1
    String id1 = "0000000001";
    String recipient1 = "+27834557896";
    String message1 = "Did you get the cake?";
    String hash1 = "00:0:DIDCAKE";

    // Message 2
    String id2 = "0000000002";
    String recipient2 = "+27838884567";
    String message2 = "Where are you? You are late! I have asked you to be on time.";
    String hash2 = "00:1:WHERETIME";

    // Message 3
    String id3 = "0000000003";
    String recipient3 = "+27834484567";
    String message3 = "Yohoooo, I am at your gate.";
    String hash3 = "00:2:YOHOOOOGATE";

    // Message 4 (Developer entry)
    String id4 = "0838884567";
    String recipient4 = "0838884567";
    String message4 = "It is dinner time!";
    String hash4 = "08:3:ITTIME";

    // Message 5
    String id5 = "0000000005";
    String recipient5 = "+27838884567";
    String message5 = "Ok, I am leaving without you.";
    String hash5 = "00:4:OKYOU";

    /**
     * Runs before every test. It clears the lists and then fills them
     * with the 5 test messages so every test starts the same way.
     */
    @BeforeEach
    public void setUp() {
        StoredMessages.resetLists();
        instance = new StoredMessages("Developer");

        // Flags: message 1 = Sent, 2 = Stored, 3 = Disregard, 4 = Sent, 5 = Stored
        StoredMessages.sentMessages.add(message1);
        StoredMessages.sentMessages.add(message4);
        StoredMessages.disregardedMessages.add(message3);

        // The methods in StoredMessages read from these lists,
        // so the five messages are added here (same position in every list).
        addToLists("0", id1, recipient1, message1, hash1);
        addToLists("1", id2, recipient2, message2, hash2);
        addToLists("2", id3, recipient3, message3, hash3);
        addToLists("3", id4, recipient4, message4, hash4);
        addToLists("4", id5, recipient5, message5, hash5);
    }

    // Small helper so we do not repeat five add lines for every message
    private void addToLists(String num, String id, String recipient, String message, String hash) {
        StoredMessages.messageNums.add(num);
        StoredMessages.messageIds.add(id);
        StoredMessages.recipients.add(recipient);
        StoredMessages.storedMessages.add(message);
        StoredMessages.messageHashes.add(hash);
    }

    @AfterEach
    public void tearDown() {
        StoredMessages.resetLists();
    }

    /**
     * Test: Sent Messages array correctly populated.
     * The Sent Messages array must contain message 1 and message 4.
     */
    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals(2, StoredMessages.sentMessages.size());
        assertEquals("Did you get the cake?", StoredMessages.sentMessages.get(0));
        assertEquals("It is dinner time!", StoredMessages.sentMessages.get(1));
    }

    /**
     * Test: Display the longest message (message 1 to 4).
     * Expected: "Where are you? You are late! I have asked you to be on time."
     */
    @Test
    public void testDisplayLongestMessage() {
        // The text in front of the message must match the text in your displayLongestMessage method
        String expResult = "Longest stored message: Where are you? You are late! I have asked you to be on time.";
        String result = instance.displayLongestMessage();

        assertEquals(expResult, result);
    }

    /**
     * Test: Search for message ID (message 4).
     * Test data: 0838884567
     * Expected: "It is dinner time!"
     */
    @Test
    public void testSearchByMessageId() {
        String expResult = "Recipient: 0838884567\nMessage: It is dinner time!";
        String result = instance.searchByMessageId("0838884567");

        assertEquals(expResult, result);
    }

    /**
     * Test: Search all the messages sent or stored for a particular recipient.
     * Test data: +27838884567
     * Expected: message 2 and message 5
     */
    @Test
    public void testSearchByRecipient() {
        String expResult = "\nMessage: Where are you? You are late! I have asked you to be on time."
                + "\nMessage: Ok, I am leaving without you.";
        String result = instance.searchByRecipient("+27838884567");

        assertEquals(expResult, result);
    }

    /**
     * Test: Delete a message using a message hash (Test Message 2).
     * Expected: the message is successfully deleted.
     */
    @Test
    public void testDeleteByHash() {
        String expResult = "Message \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        String result = instance.deleteByHash(hash2);

        assertEquals(expResult, result);

        // The message must really be gone from the lists
        assertEquals(4, StoredMessages.storedMessages.size());
        assertFalse(StoredMessages.storedMessages.contains(message2));
    }

    /**
     * Test: Display Report.
     * The report must show the message hash, recipient and message.
     */
    @Test
    public void testDisplayReport() {
        String result = instance.displayReport();

        // Message 1
        assertTrue(result.contains("Message Hash: 00:0:DIDCAKE"));
        assertTrue(result.contains("Recipient: +27834557896"));
        assertTrue(result.contains("Message: Did you get the cake?"));

        // Message 4
        assertTrue(result.contains("Message Hash: 08:3:ITTIME"));
        assertTrue(result.contains("Recipient: 0838884567"));
        assertTrue(result.contains("Message: It is dinner time!"));
    }
}