package com.example.playerstatsproject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;


public class PlayerIOforlogin {
    private static final String INPUT_FILE_NAME = "C:\\PlayerStatsProject\\src\\main\\java\\com\\example\\playerstatsproject\\Password.txt";

     static void READ(List<Password> passwordList) throws Exception{
        BufferedReader reading = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        
        while (true) {
            String line = reading.readLine();
            if (line == null)
                break;

            String[] token = line.split(",");
            passwordList.add(new Password(token[0],token[1]));
                     
        }
        
        reading.close();
        
    }
}