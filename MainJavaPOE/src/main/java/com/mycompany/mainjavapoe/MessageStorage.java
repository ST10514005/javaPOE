/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainjavapoe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ntoko
 */

//MessageStorage only saves messages to the message.json file,
// the message class does not need any JSon code
public class MessageStorage {
    
    private static final String FILE_NAME= "message.json";
    
    //Writes the list of messages to the JSON file
    public static void saveMessages(ArrayList<Messages> messages) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
 
            writer.write("[\n");
 
            for (int i = 0; i < messages.size(); i++) {
 
                Messages current = messages.get(i);
 
                writer.write("  {\n");
                writer.write("    \"messageId\": \"" + clean(current.getMessageId()) + "\",\n");
                writer.write("    \"messageNum\": " + current.getMessageNum() + ",\n");
                writer.write("    \"recipientCell\": \"" + clean(current.getRecipientCell()) + "\",\n");
                writer.write("    \"message\": \"" + clean(current.getMessage()) + "\",\n");
                writer.write("    \"messageHash\": \"" + clean(current.getMessageHash()) + "\"\n");
                
                
                // No comma after the last message
                if (i < messages.size() - 1) {
                    writer.write("  },\n");
                } else {
                    writer.write("  }\n");
                }
            }
 
            writer.write("]\n");
            
            writer.write("]\n");
 
        } catch (IOException exception) {
            System.out.println("Error saving messages: " + exception.getMessage());
        }
    }
 
    // Puts a backslash before \ and " so the text is valid inside JSON
    private static String clean(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
        
    }
    

