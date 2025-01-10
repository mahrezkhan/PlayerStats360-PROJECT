package com.example.playerstatsproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.StringTokenizer;

public class PlayerProfileController {
    List<Player> PlayerList;
    @FXML
    private Label age;
    @FXML
    private ImageView ClubImage;
    @FXML
    private ImageView country;
    @FXML
    private Label height;
    @FXML
    private ImageView image;
    @FXML
    private Label jerseynumber;
    @FXML
    private Label name;
    @FXML
    private Label position;
    @FXML
    private Label weeklysalary;
    String imagepath="file:/C:/PlayerStatsProject/src/main/resources/images/";


    public void SetPlayerList(List<Player> playerList) {
        PlayerList=playerList;
    }

    void action(Player player)
    {
        if(player==null)
        {
            jerseynumber.setText("N/A");
            height.setText("N/A");
            age.setText("N/A");
            weeklysalary.setText("N/A");
            name.setText("NOT FOUND");
            position.setText("N/A");
            String s="Unavailable";
            String im=imagepath+s+".png";
            Image myimage=new Image(im);
            image.setImage(myimage);
            ClubImage.setImage(myimage);
            country.setImage(myimage);
            ClubImage.setVisible(false);
            country.setVisible(false);
        }
        else {
            RequiredTools requiredTools = new RequiredTools(PlayerList);
            jerseynumber.setText(String.valueOf(player.getJerseyNumber()));
            height.setText(String.valueOf(player.getHeight()));
            age.setText(String.valueOf(player.getAge()));
            double salary = player.getWeeklySalary();
            String salaryinString = String.valueOf(player.getWeeklySalary());
            if (requiredTools.ConvertToMillion(player.getWeeklySalary())) {
                salary = salary / 1000000.0;
                salaryinString = String.format("%.2f", salary);
                salaryinString = salaryinString + "M";

            } else if (requiredTools.ConvertToThousand(player.getWeeklySalary())) {
                salary = salary / 1000.0;
                salaryinString = String.format("%.2f", salary);
                salaryinString = salaryinString + "K";
            }
            weeklysalary.setText(salaryinString);
            name.setText(player.getName().toUpperCase());
            String pos = player.getPosition();
            if (pos.length() >= 3) {
                pos = pos.substring(0, 3);
            }
            position.setText(pos.toUpperCase());
            StringTokenizer token = new StringTokenizer(player.getName());
            StringTokenizer token1 = new StringTokenizer(player.getCountry());
            String s = token.nextToken().toLowerCase();
            if (s.equals("mohammed")) {
                s = s + token.nextToken();
            }
            String t = token1.nextToken().toLowerCase();
            String im = imagepath + s + ".png";
            String in = imagepath + t + ".jpg";
            String path = imagepath + player.getClub().toUpperCase() + ".png";
            Image myimage = new Image(im);
            Image countryimage = new Image(in);
            Image clubImage = new Image(path);
            image.setImage(myimage);
            country.setImage(countryimage);
            ClubImage.setImage(clubImage);
        }
    }
}
