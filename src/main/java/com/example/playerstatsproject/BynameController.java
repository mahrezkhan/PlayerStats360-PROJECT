package com.example.playerstatsproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class BynameController {
    PlayerStats360 Player360;
    List<Player> PlayerList;
    @FXML
    private Button button;
    @FXML
    private TextField entername;
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

    public void initialize() {
        entername.setOnAction(event -> {
            try {
                ClickButton();
                button.requestFocus();
            } catch (IOException e) {

            }
        });
    }

    public void ClickButton() throws IOException {
        RequiredTools requiredTools =new RequiredTools(PlayerList);
        Player player = requiredTools.ByName(entername.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerProfile.fxml"));
        Scene scene = new Scene(loader.load());
        PlayerProfileController playerProfileController = loader.getController();
        playerProfileController.action(player);
        playerProfileController.SetPlayerList(PlayerList);
        Stage secondaryStage = new Stage();
        if(player==null) {
            secondaryStage.setTitle("Unavailable");
        }
        else {
            secondaryStage.setTitle(player.getName());
        }
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    public void ClickByPlayerName() throws IOException {
        if(!Manager) {
            Player360.gotoSearchPlayers(Manager);
        }
        else{
            Player360.gotoSearchPlayersforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
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
        else {
            Player360.gotoSearchPlayersbyPositionforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void ClickBySalaryRange() throws IOException {
        if(!Manager) {
            Player360.gotoSearchPlayersbySalaryRange(Manager);
        }
        else {
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
        else{
            Player360.gotoMyclubHomePage(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }
}
