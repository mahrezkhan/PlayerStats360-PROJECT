package com.example.playerstatsproject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SearchPlayerController {
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
    public void SetPlayer360nPlayerList(PlayerStats360 player360, List<Player> playerList,boolean manager) {
        this.Player360 = player360;
        PlayerList=playerList;
        Manager=manager;
    }

    public void ClickByPlayerName() throws IOException {
        if(!Manager)
        {
            Player360.gotoSearchPlayersbyName(Manager);
        }
        else{
            Player360.gotoSearchPlayersbyNameforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }

    public void ClickByClubandCountry() throws IOException {
        if(!Manager) {
            Player360.gotoSearchPlayersbyClubnCountry(Manager);
        }
        else {
            Player360.gotoSearchPlayersbyClubnCountryforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }

    public void ClickByPosition() throws IOException {
        if(!Manager) {
            Player360.gotoSearchPlayersbyPosition(Manager);
        }
        else{
            Player360.gotoSearchPlayersbyPositionforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }

    public void ClickBySalaryRange() throws IOException {
        if(!Manager) {
            Player360.gotoSearchPlayersbySalaryRange(Manager);
        }
        else{
            Player360.gotoSearchPlayersbySalaryRangeforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }

    public void ClickCountrywiseplayercount() throws IOException {
        if(!Manager) {
            Player360.gotoCountrywisePlayerCount(Manager);
        }
        else{
            Player360.gotoCountrywisePlayerCountforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }

    public void ClickBacktoMainMenu() throws Exception {
        if(!Manager) {
            Player360.gotoguest(Manager);
        }
        else {
            Player360.gotoMyclubHomePage(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }
}
