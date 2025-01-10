package com.example.playerstatsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class PlayerswiththemaximumheightofaclubController {
    PlayerStats360 Player360;
    List<Player> PlayerList;
    @FXML
    private Button button;
    @FXML
    private TextField enterClubname;
    @FXML
    private ListView<String> listview;
    @FXML
    private ImageView ClubImage;
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
        Player360 = player360;
        PlayerList=playerList;
        Manager=manager;
    }

    public void initialize() {
        enterClubname.setOnAction(event -> {
            ClickButton();
            button.requestFocus();
        });
        listview.setOnMouseClicked(event -> {
            String PlayerName = listview.getSelectionModel().getSelectedItem();
                try {
                    RequiredTools requiredTools=new RequiredTools(PlayerList);
                    Player player =requiredTools.ByName(PlayerName);
                    if(player!=null) {
                        SelectedfromList(player);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        });
    }

    private void SelectedfromList(Player player) throws IOException {
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

    public void ClickButton()  {
        button.requestFocus();
        listview.getItems().clear();
        RequiredTools requiredTools =new RequiredTools(PlayerList);
        List<Player> players= requiredTools.PlayerwiththeMaximumHeight(enterClubname.getText());
        if(players.isEmpty()){
            listview.getItems().add("No Players Found");
            ClubImage.setVisible(false);
            enterClubname.clear();
        }
        else {
            ClubImage.setVisible(true);
            String path=imagepath+enterClubname.getText().toUpperCase()+".png";
            Image ClubImage=new Image(path);
            this.ClubImage.setImage(ClubImage);
            for (Player player : players) {
                listview.getItems().add(player.getName());
            }
            enterClubname.setText(enterClubname.getText().toUpperCase());
        }
    }

    public void Playerswiththemaximumsalaryofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumsalaryofaclub(Manager);
        }
        else{
            Player360.gotoPlayerswiththemaximumsalaryofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Playerswiththemaximumheightofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoSearchClubs(Manager);
        }
        else{
            Player360.gotoSearchClubsforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Playerswiththemaximumageofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoPlayerswiththemaximumageofaclub(Manager);
        }
        else{
            Player360.gotoPlayerswiththemaximumageofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void Totalyearlysalaryofaclub() throws IOException {
        if(!Manager) {
            Player360.gotoTotalyearlysalaryofaclub(Manager);
        }
        else{
            Player360.gotoTotalyearlysalaryofaclubforManager(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);

        }
    }

    public void BacktoMainMenu() throws Exception {
        if(!Manager) {
            Player360.gotoguest(Manager);
        }
        else {
            Player360.gotoMyclubHomePage(MyPlayers,BuyPlayers,Clubname,socket,oos,Manager);
        }
    }
}
