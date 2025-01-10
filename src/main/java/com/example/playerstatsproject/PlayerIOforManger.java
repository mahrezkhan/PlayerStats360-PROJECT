package com.example.playerstatsproject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;


public class PlayerIOforManger {
    private static final String INPUT_FILE_NAME = "C:\\PlayerStatsProject\\src\\main\\java\\com\\example\\playerstatsproject\\BuyPlayers.txt";
    private static final String OUTPUT_FILE_NAME = "C:\\PlayerStatsProject\\src\\main\\java\\com\\example\\playerstatsproject\\BuyPlayers.txt";

     public static void READ(List<Player> PlayerList) throws Exception{
        BufferedReader reading = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        
        while (true) {
            String line = reading.readLine();
            if (line == null)
                break;

            String[] token = line.split(",");
            if(token[6].equals(""))
            {
                token[6]="-1";
            }
             PlayerList.add(new Player(token[0], token[1], Integer.parseInt(token[2]),
                     Double.parseDouble(token[3]), token[4], token[5],
                     Integer.parseInt(token[6]), Long.parseLong(token[7])));
                     
        }
        
        reading.close();
        
    }
     public static void WRITE(List<Player> PlayerList) throws Exception{
        
        BufferedWriter writing = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        
        for (int i = 0; i < PlayerList.size(); i++) {
            Player p = PlayerList.get(i);
            if(p.getJerseyNumber()==-1)
            {
                writing.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+",,"+p.getWeeklySalary());
            }
            else
            {
                writing.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getJerseyNumber()+","+p.getWeeklySalary());
            }
            writing.write(System.lineSeparator());
        }
        
        writing.close();
    }
}