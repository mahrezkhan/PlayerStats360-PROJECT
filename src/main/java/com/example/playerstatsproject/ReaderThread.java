package com.example.playerstatsproject;

import java.io.ObjectInputStream;
import java.util.List;

public class ReaderThread implements Runnable {
    String Clubname;
    ObjectInputStream ois;
    List<Player> MyPlayers;
    List<Player> BuyPlayers;


    ReaderThread(ObjectInputStream ois, List<Player> MyPlayers, List<Player> BuyPlayers, String Clubname)
    {
        this.ois=ois;
        new Thread(this).start();
        this.BuyPlayers = BuyPlayers;
        this.MyPlayers = MyPlayers;
        this.Clubname=Clubname;
    }
    public void run()
    {
        while(true)
        {
            try {
                Player player=(Player)ois.readObject();
                System.out.println(player);
                if(player.getStatus().equalsIgnoreCase("SELL"))
                {
                    System.out.println("SELL IN READER  "+player);
                    BuyPlayers.add(player);
                }
                else if(player.getStatus().equalsIgnoreCase("WITHDRAW"))
                {
                    BuyPlayers.remove(player);
                }
                else if(player.getStatus().equalsIgnoreCase("BUY"))
                {
                    BuyPlayers.remove(player);
                    MyPlayers.remove(player);
                    if(player.getBuyer().equalsIgnoreCase(Clubname))
                    {
                        player.setClub(player.getBuyer());
                        MyPlayers.add(player);
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
    }

}
