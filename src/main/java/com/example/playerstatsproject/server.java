package com.example.playerstatsproject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {
    static List<Player>Buyplayerlist=new ArrayList<>();
    static List<Player>Playerlist=new ArrayList<>();

    public static void main(String[] args) throws Exception {
        PlayerIOforManger.READ(Buyplayerlist);
        PlayerIO.READ(Playerlist);
        ServerSocket serverSocket =new ServerSocket(55555);
        System.out.println("Server Started");
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connected client");
            new serve(socket,Buyplayerlist,Playerlist);
        }
    }
}
class serve implements Runnable {
    static Socket socket;
    static List<ObjectOutputStream> clientlist = new ArrayList<>();
    static List<Player> Buyplayerlist;
    static List<Player> PlayerList;

    serve(Socket socket1, List<Player> buyplayerlist, List<Player> playerlist) {
        socket = socket1;
        new Thread(this).start();
        Buyplayerlist = buyplayerlist;
        PlayerList = playerlist;
    }

    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            synchronized (clientlist) {
                clientlist.add(oos);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            List<Player> MyPlayer = new ArrayList<>();
            String ClubName = (String) ois.readObject();
            for (Player player : PlayerList) {
                if (player.getClub().equalsIgnoreCase(ClubName)) {
                    MyPlayer.add(player);
                }
            }
            oos.writeObject(MyPlayer);
            ClubName = (String) ois.readObject();
            System.out.println(ClubName);
            oos.writeObject(Buyplayerlist);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                Player player = (Player) ois.readObject();
                if (player.getStatus().equalsIgnoreCase("SELL")) {
                    Buyplayerlist.add(player);
                    PlayerIOforManger.WRITE(Buyplayerlist);
                    PassPlayer(player);
                } else if (player.getStatus().equalsIgnoreCase("WITHDRAW") || player.getStatus().equalsIgnoreCase("BUY")) {
                    Buyplayerlist.remove(player);
                    PlayerIOforManger.WRITE(Buyplayerlist);
                    if (player.getStatus().equalsIgnoreCase("BUY")) {
                        PlayerList.remove(player);
                        String Club = player.getClub();
                        player.setClub(player.getBuyer());
                        PlayerList.add(player);
                        PlayerIO.WRITE(PlayerList);
                        player.setClub(Club);
                        PassPlayer(player);
                        player.setClub(player.getBuyer());
                    }
                    else{
                        PassPlayer(player);
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
                clientlist.remove(oos);
                break;
            }

        }
    }

    void PassPlayer(Player player) throws IOException {
        for (ObjectOutputStream cl : clientlist) {
            cl.writeObject(player);
        }
    }
}
