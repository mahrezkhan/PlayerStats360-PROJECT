package com.example.playerstatsproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class HomePageController {
    PlayerStats360 Player360;
    List<Player> PlayerList;
    @FXML
    private ListView<String> listview;
    boolean Manager;
    public void setPlayer360(PlayerStats360 player360, List<Player> playerList,boolean manager) {
        this.Player360 = player360;
        PlayerList=playerList;
        Manager=manager;
    }
    public void showPlayer()
    {
        for(Player p:PlayerList)
        {
            listview.getItems().add(p.getName());
        }
    }
    public void initialize() {
        listview.setOnMouseClicked(event -> {
            String PlayerName = listview.getSelectionModel().getSelectedItem();
                try {
                    RequiredTools requiredTools=new RequiredTools(PlayerList);
                    Player player=requiredTools.ByName(PlayerName);
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


    public void ClickSearchPlayers() throws IOException {
        Player360.gotoSearchPlayers(Manager);
    }

    public void ClickSearchClubs() throws IOException {
        Player360.gotoSearchClubs(Manager);
    }

    public void ClickAddPlayer() throws IOException {
        Player360.gotoAddPlayers(Manager);
    }

    public void ClickBack() throws Exception {
        PlayerIO.WRITE(PlayerList);
        Player360.GuestOManager();
    }
}
