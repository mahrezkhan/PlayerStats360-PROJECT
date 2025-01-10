package com.example.playerstatsproject;

import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestOmanagerController {
    PlayerStats360 player360;
    List<Player> PlayerList=new ArrayList<>();
    public PlayerStats360 getPlayer360() {
        return player360;
    }

    public void setPlayer360(PlayerStats360 player360, List<Player> playerList) {
        this.player360 = player360;
        PlayerList=playerList;
    }
    @FXML
    void clickguest() throws IOException {
        player360.gotoguest(false);
    }

    @FXML
    void clickmanager() throws Exception {
        player360.gotoclubloginwindow(true);
    }

    @FXML
    void ClcikExit() {
        player360.stage.close();
    }

}
