package com.example.playerstatsproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class CountrywisePlayerCountController {
    PlayerStats360 Player360;
    List<Player> PlayerList;
    @FXML
    private Button button;
    @FXML
    private ListView<String> listview;
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
        Player360 = player360;
        PlayerList=playerList;
        Manager=manager;
    }

    @FXML
    void ClickButton() {
        listview.getItems().clear();
        RequiredTools requiredTools = new RequiredTools(PlayerList);
        HashMap<String, Integer> map = requiredTools.ByCountrywisePlayerCount();
        for (var a : map.entrySet()) {
            listview.getItems().add(a.getKey()+" : "+a.getValue());
        }
    }

    public void ClickByPlayerName() throws IOException {
        if(!Manager) {
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
        else{
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
            Player360.gotoSearchPlayers(Manager);
        }
        else{
            Player360.gotoSearchPlayersforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }

    public void ClickBacktoMainMenu() throws Exception {
        if (!Manager) {
            Player360.gotoguest(Manager);
        }
        else{
            Player360.gotoMyclubHomePage(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }
}
