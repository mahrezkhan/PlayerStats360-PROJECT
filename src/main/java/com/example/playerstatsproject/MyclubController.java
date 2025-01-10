package com.example.playerstatsproject;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class MyclubController {
    PlayerStats360 Player360;
    List<Player> MyPlayers;
    ObjectOutputStream oos;
    Socket socket;
    String Clubname;
    List<Player> BuyPlayers;
    public PlayerStats360 getPlayer360() {
        return Player360;
    }
    boolean Manager;


    public void setPlayer360(PlayerStats360 player360, List<Player> playerList,String s,List<Player>playerListforBuyPlayers,Socket socket,ObjectOutputStream oos,boolean manager) {
        this.Player360 = player360;
        MyPlayers =playerList;
        Clubname=s;
        BuyPlayers =playerListforBuyPlayers;
        this.socket=socket;
        this.oos=oos;
        Manager=manager;
    }
    @FXML
    private ListView<String> listview;
    public void initialize() {
        listview.setOnMouseClicked(event -> {
            String PlayerName = listview.getSelectionModel().getSelectedItem();
                RequiredTools requiredTools=new RequiredTools(MyPlayers);
                Player player=requiredTools.ByName(PlayerName);
                System.out.println("Selected Player: " + player);
                try {
                    if(player!=null) {
                        SelectedList(player);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        });
    }

    private void SelectedList(Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerProfileforManager.fxml"));
        Scene scene = new Scene(loader.load());
        PlayerProfileforManagerController playerProfileforManagerController = loader.getController();
        playerProfileforManagerController.setplayer(player, MyPlayers,socket,oos, BuyPlayers,Clubname);
        playerProfileforManagerController.action();
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle(player.getName());
        secondaryStage.setScene(scene);
        secondaryStage.setOnCloseRequest(event -> {
            try {
                Player360.gotoMyclub(Clubname, MyPlayers,BuyPlayers,socket,oos,Manager); // Restart reloading or any other necessary action
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        secondaryStage.show();
    }
    public void yes() {
        for (Player player : MyPlayers) {
            listview.getItems().add(player.getName());
        }
    }

    public void Clickmyplayers(ActionEvent actionEvent) throws IOException {
        Player360.gotoMyclubHomePage(MyPlayers, BuyPlayers,Clubname,socket,oos,Manager);
    }

    public void ClickSearchPlayers(ActionEvent actionEvent) throws IOException {
        Player360.gotoSearchPlayersforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
    }

    public void ClickSearchClubs(ActionEvent actionEvent) throws IOException {
        Player360.gotoSearchClubsforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
    }

    public void Clickbuyplayers(ActionEvent actionEvent) throws Exception {
        Player360.gotoBuyplayers(Clubname, MyPlayers,BuyPlayers,socket,oos,Manager);
    }

    public void clicksaveandexit(ActionEvent actionEvent) throws Exception {
        socket.close();
        Player360.GuestOManager();
    }


}
