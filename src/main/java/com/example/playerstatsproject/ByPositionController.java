package com.example.playerstatsproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.print.attribute.standard.MediaName;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ByPositionController {
    PlayerStats360 Player360;
    List<Player> PlayerList;
    @FXML
    private Button button;
    @FXML
    private TextField enterPosition;
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
        this.Player360 = player360;
        PlayerList=playerList;
        Manager=manager;
    }

    public void initialize() {
        enterPosition.setOnAction(event -> {
            ClickButton();
        });
        listview.setOnMouseClicked(event -> {
            String PlayerName = listview.getSelectionModel().getSelectedItem();
                RequiredTools requiredTools=new RequiredTools(PlayerList);
                Player player =requiredTools.ByName(PlayerName);
                try {
                    if(player!=null) {
                        SelectfromList(player);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        });
    }

    private void SelectfromList(Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerProfile.fxml"));
        Scene scene = new Scene(loader.load());
        PlayerProfileController playerProfileController = loader.getController();
        playerProfileController.action(player);
        playerProfileController.SetPlayerList(PlayerList);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle(player.getName());
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    @FXML
    void ClickButton() {
        button.requestFocus();
        listview.getItems().clear();
        RequiredTools requiredTools =new RequiredTools(PlayerList);
        List<Player> players= requiredTools.ByPosition(enterPosition.getText());
        if(players.isEmpty())
        {
            listview.getItems().add("Input must be a Batsman,");
            listview.getItems().add("Bowler or");
            listview.getItems().add("Allrounder");
        }
        else {
            for (Player player : players) {
                listview.getItems().add(player.getName());
            }
        }
            enterPosition.clear();
    }

    public void ClickByPlayerName() throws IOException {
        if(!Manager) {
            Player360.gotoSearchPlayersbyName(Manager);
        }else {
            Player360.gotoSearchPlayersbyNameforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void ClickByClubandCountry() throws IOException {
        if (!Manager) {
            Player360.gotoSearchPlayersbyClubnCountry(Manager);
        } else {
            Player360.gotoSearchPlayersbyClubnCountryforManager(MyPlayers, BuyPlayers, Clubname, socket, oos, Manager);
        }
    }
    public void ClickByPosition() throws IOException {
        if (!Manager) {
            Player360.gotoSearchPlayers(Manager);
        } else {
            Player360.gotoSearchPlayersforManager(MyPlayers, BuyPlayers, Clubname, socket, oos, Manager);

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
        else {
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
