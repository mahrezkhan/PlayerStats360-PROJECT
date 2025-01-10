package com.example.playerstatsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

public class PlayerProfileforManagerController {
    ObjectOutputStream oos;
    Socket socket;
    Player player;
    List<Player> MyPlayers;
    List<Player> BuyPlayers;
    String Clubname;
    boolean Manager;
    @FXML
    private Button BuySell;
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


    public void setplayer(Player player,List<Player>playerList,Socket socket,ObjectOutputStream oos,List<Player> playerListforBuyPlayers,String Clubname)
    {
        this.player =player;
        MyPlayers =playerList;
        this.socket=socket;
        this.oos=oos;
        BuyPlayers =playerListforBuyPlayers;
        this.Clubname=Clubname;
    }

    public void action()
    {
        BuySell.setText("SELL");
        for(Player pll: BuyPlayers)
        {
            if(pll.getName().equalsIgnoreCase(player.getName()))
            {
                BuySell.setText("WITHDRAW");
            }
        }

        jerseynumber.setText(String.valueOf(player.getJerseyNumber()));
        height.setText(String.valueOf(player.getHeight()));
        age.setText(String.valueOf(player.getAge()));
        weeklysalary.setText(String.valueOf(player.getWeeklySalary()));
        name.setText(player.getName().toUpperCase());
        String k= player.getPosition();
        String pos=k.substring(0,3);
        position.setText(pos.toUpperCase());
        StringTokenizer token=new StringTokenizer(player.getName());
        StringTokenizer token1=new StringTokenizer(player.getCountry());
        String s=token.nextToken().toLowerCase();
        if(s.equals("mohammed"))
        {
            s=s+token.nextToken();
        }
        String t=token1.nextToken().toLowerCase();

        String im=imagepath+s+".png";
        String in=imagepath+t+".jpg";
        String path=imagepath+player.getClub().toUpperCase()+".png";
        Image myimage=new Image(im);
        Image countryimage=new Image(in);
        Image clubImage=new Image(path);
        image.setImage(myimage);
        country.setImage(countryimage);
        ClubImage.setImage(clubImage);
    }
    @FXML
    public void ClickSell(ActionEvent event) throws Exception {
        if(BuySell.getText().equalsIgnoreCase("sell")) {
            BuySell.setText("WITHDRAW");
            Player player1=new Player(player.getName(),player.getCountry(),player.getAge(),player.getHeight(),player.getClub(),player.getPosition(),player.getJerseyNumber(),player.getWeeklySalary());
            player1.setStatus("SELL");
            oos.writeObject(player1);
        }
        else
        {
            Player player1=new Player(player.getName(),player.getCountry(),player.getAge(),player.getHeight(),player.getClub(),player.getPosition(),player.getJerseyNumber(),player.getWeeklySalary());
            player1.setStatus("WITHDRAW");
            BuySell.setText("SELL");
            oos.writeObject(player1);
        }
    }

}
