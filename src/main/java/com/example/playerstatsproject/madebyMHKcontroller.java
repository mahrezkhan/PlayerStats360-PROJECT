package com.example.playerstatsproject;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class madebyMHKcontroller {
    PlayerStats360 player360;
    List<Player> PlayerList=new ArrayList<>();
    public PlayerStats360 getPlayer360() {
        return player360;
    }
    public void start(PlayerStats360 playerStats360, List<Player> playerList) throws Exception {
        this.player360 = player360;
        PlayerList=playerList;
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {
            try {
                playerStats360.GuestOManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}
