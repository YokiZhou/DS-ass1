/*COMP90015 Assignment1  
  Shengqi Zhou 893295 */
package dict.client;

import com.fasterxml.jackson.core.JsonProcessingException;

import dict.server.Word;

import static dict.server.Word.jsonToWord;
import static dict.server.Word.wordToJson;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Client {

    // IP and port
    private static String ip = "localhost";
    private static int port = 2019;
    
    //link with GUI
    public String find(String key) throws Exception {

        Word word = new Word(key);
        word.setInstruction("find");

        return feedback(word);
    }

    public String add(String key,String val) throws Exception {

        Word word = new Word(key,val);
        word.setInstruction("add");

        return feedback(word);
    }

    public String remove(String key) throws Exception {

        Word word = new Word(key);
        word.setInstruction("remove");

        return feedback(word);
    }

    public String feedback(Word word) throws IOException {
        Word message;
        String outputStr = wordToJson(word);

        try (Socket socket = new Socket(ip, port);) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            output.writeUTF(outputStr);
            output.flush();

            while (true) {
                if (input.available() > 0) {
                    message = jsonToWord(input.readUTF());
                    System.out.println(message);
                    break;
                }
            }
        }
        return message.getMsg()==null ? message.getValue(): message.getMsg();
        
    }
}
