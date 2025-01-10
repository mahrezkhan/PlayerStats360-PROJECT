package com.example.playerstatsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.util.List;

public class MyClubHomePageController {
    PlayerStats360 Player360;
    List<Player> MyPlayers;
    ObjectOutputStream oos;
    Socket socket;
    List<Player> BuyPlayers;
    String Clubname;
    boolean Manager;
    @FXML
    private ImageView ClubImage;
    @FXML
    private Label ClubWorth;
    String imagepath="file:/C:/PlayerStatsProject/src/main/resources/images/";


    public void SetAllList(PlayerStats360 player360, List<Player> playerList, String clubname, Socket socket, ObjectOutputStream oos, List<Player> playerListforBuyPlayers,boolean manager) {
        Player360 = player360;
        MyPlayers =playerList;
        Clubname=clubname;
        this.socket=socket;
        this.oos=oos;
        BuyPlayers =playerListforBuyPlayers;
        Manager=manager;
    }

    @FXML
    void Clickmyplayers(ActionEvent event) throws Exception {
        Player360.gotoMyclub(Clubname, MyPlayers,BuyPlayers,socket,oos,Manager);
    }

    public void ClickSearchPlayers(ActionEvent actionEvent) throws IOException {
        Player360.gotoSearchPlayersforManager(MyPlayers,BuyPlayers,Clubname, socket, oos,Manager);
    }

    public void ClickSearchClubs(ActionEvent actionEvent) throws IOException {
        Player360.gotoSearchClubsforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
    }

    @FXML
    void Clickbuyplayers(ActionEvent event) throws Exception {
        Player360.gotoBuyplayers(Clubname,MyPlayers,BuyPlayers,socket,oos,Manager);
    }
    @FXML
    void clicksaveandexit(ActionEvent event) throws Exception {
        socket.close();
        Player360.GuestOManager();
    }

    public void action()
    {
        RequiredTools requiredTools =new RequiredTools(MyPlayers);
        long  max= requiredTools.TotalYearlySalary(Clubname);
        String path=imagepath+Clubname.toUpperCase()+".png";
        Image ClubImage=new Image(path);
        this.ClubImage.setImage(ClubImage);
        double salary = max;
        String salaryinString = String.valueOf(max);
        if (requiredTools.ConvertToBillion(max)) {
            salary = salary / 1000000000.0;
            salaryinString = String.format("%.2f", salary);
            salaryinString = salaryinString + "B";
        } else if (requiredTools.ConvertToMillion(max)) {
            salary = salary / 1000000.0;
            salaryinString = String.format("%.2f", salary);
            salaryinString = salaryinString + "M";

        } else if (requiredTools.ConvertToThousand(max)) {
            salary = salary / 1000.0;
            salaryinString = String.format("%.2f", salary);
            salaryinString = salaryinString + "K";
        }
        ClubWorth.setText("YEARLY EXPENSE : " + salaryinString);

    }
}
