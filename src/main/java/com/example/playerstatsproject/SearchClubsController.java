package com.example.playerstatsproject;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SearchClubsController {
    PlayerStats360 Player360;
    List<Player> PlayerList;
    boolean Manager;
    List<Player> MyPlayers;
    List<Player> BuyPlayers;
    String Clubname;
    Socket socket;
    ObjectOutputStream oos;

    public void SetAll(List<Player> MyPlayers,List<Player> BuyPlayers,String Clubname,Socket socket,ObjectOutputStream oos)
    {
        this.MyPlayers=MyPlayers;
        this.BuyPlayers=BuyPlayers;
        this.Clubname=Clubname;
        this.socket=socket;
        this.oos=oos;
    }

    public void SetPlayer360nPlayerList(PlayerStats360 player360, List<Player> playerList,boolean manager)
    {
        try {
            Player360 = player360;
            PlayerList = playerList;
            Manager = manager;
        }catch (Exception e)
        {
            System.out.println("ki");
        }
    }

    public void Playerswiththemaximumsalaryofaclub(ActionEvent actionEvent) throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumsalaryofaclub(Manager);
        }
        else{
            Player360.gotoPlayerswiththemaximumsalaryofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Playerswiththemaximumheightofaclub(ActionEvent actionEvent) throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumheightofaclub(Manager);
        }
        else{
            Player360.gotoPlayerswiththemaximumheightofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Playerswiththemaximumageofaclub(ActionEvent actionEvent) throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumageofaclub(Manager);
        }
        else{
            Player360.gotoPlayerswiththemaximumageofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Totalyearlysalaryofaclub(ActionEvent actionEvent) throws IOException {
        if(!Manager) {
            Player360.gotoTotalyearlysalaryofaclub(Manager);
        }
        else {
            Player360.gotoTotalyearlysalaryofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void BacktoMainMenu(ActionEvent actionEvent) throws Exception {
        if(!Manager) {
            Player360.gotoguest(Manager);
        }
        else{
            Player360.gotoMyclubHomePage(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }
}
