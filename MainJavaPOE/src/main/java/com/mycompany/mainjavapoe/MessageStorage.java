/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainjavapoe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author ntoko
 */


// THIS IS THE CONTENT OF JSON FILE BELOW

//MessageStorage only saves messages to the message.json file,
// the message class does not need any JSon code
 //Reference: W3schools-https://www.w3schools.com/js/tryit.asp?filename=tryjs_class_method //

public class MessageStorage {
   
    // saveMessages: writes the whole list of messages to the JSON file.
    // It is static so Messages can call it without making a MessageStorage object.
    private static final String FILE_NAME= "message.json";
    
    // saveEnabled: a switch for saving to the file.
    // It is true when the program runs normally.
    // The unit tests set it to false so they do not overwrite message.json.
    public static boolean saveEnabled = true;
    
    //Writes the list of messages to the JSON file
    public static void saveMessages(ArrayList<Messages> messages) {
        
       // try-with-resources closes the file by itself when it is done 
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
 
              // JSON starts with [ because it is a list of messages
            writer.write("[\n");
 
             // Write each message as one { ... } block
            for (int i = 0; i < messages.size(); i++) {
 
                Messages current = messages.get(i);
 
                writer.write("  {\n");
                writer.write("    \"messageId\": \"" + clean(current.getMessageId()) + "\",\n");
                writer.write("    \"messageNum\": " + current.getMessageNum() + ",\n");
                writer.write("    \"recipientCell\": \"" + clean(current.getRecipientCell()) + "\",\n");
                writer.write("    \"message\": \"" + clean(current.getMessage()) + "\",\n");
                writer.write("    \"messageHash\": \"" + clean(current.getMessageHash()) + "\"\n");
                
                
                 // JSON needs a comma between messages, but not after the last one
                if (i < messages.size() - 1) {
                    writer.write("  },\n");
                } else {
                    writer.write("  }\n");
                }
            }
 
            // Close the list
            writer.write("]\n");
            
            
 
        } catch (IOException exception) {
            // If the file cannot be written, show the problem instead of crashing
            System.out.println("Error saving messages: " + exception.getMessage());
        }
    }
 
    //===========================================
    // NEW FOR PART 3
    
    //saveStoredLists : writes the stored lists in StoredMesssages to message.json
    // It does the same job as saveMessages, but the details come from the
    // lists of text instead of Messages objects.
    public static void saveStoredLists() {
        
        // If saving is switched off (for example during unit tests), do nothing
        if (!saveEnabled) {
            return;
        }
        
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("[\n");
            for (int i = 0; i < StoredMessages.messageIds.size(); i++) {
                writer.write("  {\n");
                writer.write("    \"messageId\": \"" + clean(StoredMessages.messageIds.get(i)) + "\",\n");
                writer.write("    \"messageNum\": " + StoredMessages.messageNums.get(i) + ",\n");
                writer.write("    \"recipientCell\": \"" + clean(StoredMessages.recipients.get(i)) + "\",\n");
                writer.write("    \"message\": \"" + clean(StoredMessages.storedMessages.get(i)) + "\",\n");
                writer.write("    \"messageHash\": \"" + clean(StoredMessages.messageHashes.get(i)) + "\"\n");
                if (i < StoredMessages.messageIds.size() - 1) {
                    writer.write("  },\n");
                } else {
                    writer.write("  }\n");
                }
            }
            writer.write("]\n");
        } catch (IOException exception) {
            System.out.println("Error saving messages: " + exception.getMessage());
        }
    }

    public static void loadMessages() {
        StoredMessages.messageIds.clear();
        StoredMessages.messageNums.clear();
        StoredMessages.recipients.clear();
        StoredMessages.storedMessages.clear();
        StoredMessages.messageHashes.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.startsWith("\"messageId\"")) {
                    StoredMessages.messageIds.add(getValue(line));
                } else if (line.startsWith("\"messageNum\"")) {
                    StoredMessages.messageNums.add(getValue(line));
                } else if (line.startsWith("\"recipientCell\"")) {
                    StoredMessages.recipients.add(getValue(line));
                } else if (line.startsWith("\"message\"")) {
                    StoredMessages.storedMessages.add(getValue(line));
                } else if (line.startsWith("\"messageHash\"")) {
                    StoredMessages.messageHashes.add(getValue(line));
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException exception) {
            // No file yet, so there is nothing to load
        } catch (IOException exception) {
            System.out.println("Error reading messages: " + exception.getMessage());
        }
    }

    private static String getValue(String line) {
        String value = line.substring(line.indexOf(":") + 1).trim();
        if (value.endsWith(",")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        return value.replace("\\\"", "\"").replace("\\\\", "\\");
    }
    
    // clean: puts a backslash before \ and " so the text is valid inside JSON.
    // Without this, a quote in a message would break the JSON file.
    private static String clean(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
        
}
