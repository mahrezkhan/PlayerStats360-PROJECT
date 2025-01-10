package com.example.playerstatsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BuyplayerControler {
    PlayerStats360 Player360;
    List<Player> MyPlayers;
    ObjectOutputStream oos;
    Socket socket;
    List<Player> BuyPlayers;
    String Clubname;
    boolean Manager;
    @FXML
    private ListView<String> listview;

    public void setPlayer360(PlayerStats360 player360, List<Player> playerList,String s,Socket socket,ObjectOutputStream oos,List<Player> PlayerListforBuyPlayers,boolean manager) {
        this.Player360 = player360;
        MyPlayers =playerList;
        Clubname=s;
        this.socket=socket;
        this.oos=oos;
        this.BuyPlayers =PlayerListforBuyPlayers;
        Manager=manager;
    }

    public void initialize() {
        listview.setOnMouseClicked(event -> {
            String PlayerName = listview.getSelectionModel().getSelectedItem();
                System.out.println("Selected Playe: " + PlayerName);
                try {
                    System.out.println("Selected Player: " + PlayerName);
                    List<Player>playerList=new ArrayList<>();
                    PlayerIO.READ(playerList);
                    RequiredTools requiredTools=new RequiredTools(BuyPlayers);
                    Player player=requiredTools.ByName(PlayerName);
                    //if(player!=null) {
                        SelectedfromList(player);
                    //}
                    //else {
                        //System.out.println(player);
                    //}
                } catch (Exception e) {
                    System.out.println(e);
                }
        });
    }

    private void SelectedfromList(Player player) throws IOException {
        if(player==null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerProfile.fxml"));
            Scene scene = new Scene(loader.load());
            PlayerProfileController playerProfileController = loader.getController();
            playerProfileController.action(player);
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Unavailable");
            secondaryStage.setScene(scene);
            secondaryStage.show();
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerProfileforManagerBuyPlayer.fxml"));
            Scene scene = new Scene(loader.load());
            PlayerProfileforManagerBuyPlayerController playerProfileforManagerBuyPlayerController = loader.getController();
            playerProfileforManagerBuyPlayerController.setplayer(player, MyPlayers, socket, oos, BuyPlayers, Clubname);
            playerProfileforManagerBuyPlayerController.action();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(player.getName());
            secondaryStage.setScene(scene);
            secondaryStage.setOnCloseRequest(event -> {
                try {
                    Player360.gotoBuyplayers(Clubname, MyPlayers, BuyPlayers, socket, oos, Manager);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            secondaryStage.show();
        }
    }
    public void yes() {
        if(BuyPlayers.isEmpty()) {
            listview.getItems().add("NO PLAYER AVAILABLE NOW");
        }
        else {
            for (Player player : BuyPlayers) {
                listview.getItems().add(player.getName());
            }
        }
    }

    public void Clickmyplayers(ActionEvent actionEvent) throws Exception {
        Player360.gotoMyclub(Clubname, MyPlayers, BuyPlayers,socket,oos,Manager);

    }

    public void ClickSearchPlayers(ActionEvent actionEvent) throws IOException {
        Player360.gotoSearchPlayersforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

    }

    public void ClickSearchClubs(ActionEvent actionEvent) throws IOException {
        Player360.gotoSearchClubsforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
    }

    public void Clickbuyplayers(ActionEvent actionEvent) throws IOException {
        Player360.gotoMyclubHomePage(MyPlayers, BuyPlayers,Clubname,socket,oos,Manager);
    }

    public void clicksaveandexit(ActionEvent actionEvent) throws Exception {
        socket.close();
        Player360.GuestOManager();
    }
}
