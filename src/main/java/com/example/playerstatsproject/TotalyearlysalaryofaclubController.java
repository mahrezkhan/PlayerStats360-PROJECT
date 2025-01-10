package com.example.playerstatsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TotalyearlysalaryofaclubController {
    PlayerStats360 Player360;
    List<Player> PlayerList = new ArrayList<>();
    @FXML
    private ImageView ClubImage;
    @FXML
    private Button button;
    @FXML
    private TextField enterClubname;
    @FXML
    private Label maxsalary;
    boolean Manager;
    String imagepath="file:/C:/PlayerStatsProject/src/main/resources/images/";
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
        enterClubname.setOnAction(event -> {
            ClickButton();
        });
    }

    public void ClickButton()  {
        button.requestFocus();
        RequiredTools requiredTools =new RequiredTools(PlayerList);
        long  max= requiredTools.TotalYearlySalary(enterClubname.getText());
        if(max!=0) {
            maxsalary.setVisible(true);
            ClubImage.setVisible(true);
            String path=imagepath+enterClubname.getText().toUpperCase()+".png";
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
            maxsalary.setText("EXPENSE : " + salaryinString);
            enterClubname.setText(enterClubname.getText().toUpperCase());
        }
        else {
            ClubImage.setVisible(false);
            maxsalary.setText("EXPENSE : " + "N/A");
            enterClubname.clear();
        }
    }

    public void Playerswiththemaximumsalaryofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumsalaryofaclub(Manager);
        }
        else {
            Player360.gotoPlayerswiththemaximumsalaryofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Playerswiththemaximumheightofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumheightofaclub(Manager);
        }
        else{
            Player360.gotoPlayerswiththemaximumheightofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Playerswiththemaximumageofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumageofaclub(Manager);
        }
        else {
            Player360.gotoPlayerswiththemaximumageofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Totalyearlysalaryofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoSearchClubs(Manager);
        }
        else
        {
            Player360.gotoSearchClubsforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void BacktoMainMenu() throws Exception {
        if(!Manager) {
            Player360.gotoguest(Manager);
        }
        else{
            Player360.gotoMyclubHomePage(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }
}
