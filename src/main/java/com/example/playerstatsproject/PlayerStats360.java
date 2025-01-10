package com.example.playerstatsproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.Manifest;

public class PlayerStats360 extends Application {
    Stage stage;
    List<Player> PlayerList = new ArrayList<>();
    List<Password> passwordList = new ArrayList<>();
    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        PlayerIO.READ(PlayerList);
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("madebyMHK.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        madebyMHKcontroller madebyMHKcontroller = fxmlLoader.getController();
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
        madebyMHKcontroller.start(this,PlayerList);
    }public void GuestOManager() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("guestOmanager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        GuestOmanagerController guestOmanagerController = fxmlLoader.getController();
        guestOmanagerController.setPlayer360(this,PlayerList);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoguest(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        HomePageController homePageController = fxmlLoader.getController();
        homePageController.setPlayer360(this,PlayerList,Manager);
        homePageController.showPlayer();
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoclubloginwindow(boolean Manager) throws Exception {
        PlayerIOforlogin.READ(passwordList);
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Clubloginwindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        ClubloginwindowController clubloginwindowController = fxmlLoader.getController();
        clubloginwindowController.SetAllList(this,passwordList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }public void gotoMyclubHomePage(List<Player> MyPlayers,List<Player> BuyPlayers,String Clubname, Socket socket, ObjectOutputStream oos,boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("MyClubHomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        MyClubHomePageController myClubHomePageController = fxmlLoader.getController();
        myClubHomePageController.SetAllList(this,MyPlayers,Clubname,socket,oos,BuyPlayers,Manager);
        myClubHomePageController.action();
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoMyclub(String Clubname,List<Player> Myplayers,List<Player> BuyPlayers, Socket socket, ObjectOutputStream oos,boolean Manager) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Myclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        MyclubController myclubController = fxmlLoader.getController();
        myclubController.setPlayer360(this,Myplayers,Clubname,BuyPlayers,socket,oos,Manager);
        myclubController.yes();
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
        }
        public void gotoBuyplayers(String Clubname,List<Player>MyPlayers,List<Player> BuyPlayers, Socket socket, ObjectOutputStream oos,boolean Manager) throws Exception {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Buyplayer.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 854, 480);

            BuyplayerControler buyplayerControler = fxmlLoader.getController();
            buyplayerControler.setPlayer360(this, MyPlayers, Clubname, socket, oos, BuyPlayers,Manager);
            buyplayerControler.yes();

            stage.setTitle("PlayerStats360");
            stage.setScene(scene);
            stage.show();
        }
    public void gotoSearchPlayers(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("SearchPlayer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        SearchPlayerController searchPlayerController = fxmlLoader.getController();
        searchPlayerController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }public void gotoSearchPlayersforManager(List<Player> MyPlayers,List<Player> BuyPlayers,String Clubname,Socket socket,ObjectOutputStream oos,boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("SearchPlayer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        SearchPlayerController searchPlayerController = fxmlLoader.getController();
        searchPlayerController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        searchPlayerController.SetAll(MyPlayers,BuyPlayers,Clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoSearchPlayersbyName(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("byname.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        BynameController bynameControllereController = fxmlLoader.getController();
        bynameControllereController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();

    }
    public void gotoSearchPlayersbyNameforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("byname.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        BynameController bynameControllereController = fxmlLoader.getController();
        bynameControllereController.SetPlayer360nPlayerList(this,PlayerList,manager);
        bynameControllereController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }

    public void gotoSearchPlayersbyClubnCountry(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("byClubnCountry.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        ByClubnCountryController byClubnCountryController = fxmlLoader.getController();
        byClubnCountryController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoSearchPlayersbyClubnCountryforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("byClubnCountry.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        ByClubnCountryController byClubnCountryController = fxmlLoader.getController();
        byClubnCountryController.SetPlayer360nPlayerList(this,PlayerList,manager);
        byClubnCountryController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoSearchPlayersbySalaryRange(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("bySalaryRange.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        BySalaryRangeController bySalaryRangeController = fxmlLoader.getController();
        bySalaryRangeController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoSearchPlayersbySalaryRangeforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("bySalaryRange.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        BySalaryRangeController bySalaryRangeController = fxmlLoader.getController();
        bySalaryRangeController.SetPlayer360nPlayerList(this,PlayerList,manager);
        bySalaryRangeController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoSearchPlayersbyPosition(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("byPosition.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        ByPositionController byPositionController = fxmlLoader.getController();
        byPositionController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoSearchPlayersbyPositionforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("byPosition.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        ByPositionController byPositionController = fxmlLoader.getController();
        byPositionController.SetPlayer360nPlayerList(this,PlayerList,manager);
        byPositionController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoCountrywisePlayerCount(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("CountrywisePlayerCount.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        CountrywisePlayerCountController countrywisePlayerCountController= fxmlLoader.getController();
        countrywisePlayerCountController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        countrywisePlayerCountController.ClickButton();
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoCountrywisePlayerCountforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("CountrywisePlayerCount.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        CountrywisePlayerCountController countrywisePlayerCountController= fxmlLoader.getController();
        countrywisePlayerCountController.SetPlayer360nPlayerList(this,PlayerList,manager);
        countrywisePlayerCountController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        countrywisePlayerCountController.ClickButton();
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoSearchClubs(boolean Manager) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("SearchClubs.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 854, 480);
            SearchClubsController searchClubsController = fxmlLoader.getController();
            searchClubsController.SetPlayer360nPlayerList(this,PlayerList,Manager);
            stage.setTitle("PlayerStats360");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println("hi");
        }

    }
    public void gotoSearchClubsforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("SearchClubs.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        SearchClubsController searchClubsController = fxmlLoader.getController();
        searchClubsController.SetPlayer360nPlayerList(this,PlayerList,manager);
        searchClubsController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoPlayerswiththemaximumsalaryofaclub(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Playerswiththemaximumsalaryofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        PlayerswiththemaximumsalaryofaclubControllerr playerswiththemaximumsalaryofaclubControllerr = fxmlLoader.getController();
        playerswiththemaximumsalaryofaclubControllerr.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoPlayerswiththemaximumsalaryofaclubforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Playerswiththemaximumsalaryofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        PlayerswiththemaximumsalaryofaclubControllerr playerswiththemaximumsalaryofaclubControllerr = fxmlLoader.getController();
        playerswiththemaximumsalaryofaclubControllerr.SetPlayer360nPlayerList(this,PlayerList,manager);
        playerswiththemaximumsalaryofaclubControllerr.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoPlayerswiththemaximumageofaclub(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Playerswiththemaximumageofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        PlayerswiththemaximumageofaclubController playerswiththemaximumageofaclubController = fxmlLoader.getController();
        playerswiththemaximumageofaclubController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoPlayerswiththemaximumageofaclubforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Playerswiththemaximumageofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        PlayerswiththemaximumageofaclubController playerswiththemaximumageofaclubController = fxmlLoader.getController();
        playerswiththemaximumageofaclubController.SetPlayer360nPlayerList(this,PlayerList,manager);
        playerswiththemaximumageofaclubController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoPlayerswiththemaximumheightofaclub(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Playerswiththemaximumheightofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        PlayerswiththemaximumheightofaclubController playerswiththemaximumheightofaclubController = fxmlLoader.getController();
        playerswiththemaximumheightofaclubController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoPlayerswiththemaximumheightofaclubforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Playerswiththemaximumheightofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        PlayerswiththemaximumheightofaclubController playerswiththemaximumheightofaclubController = fxmlLoader.getController();
        playerswiththemaximumheightofaclubController.SetPlayer360nPlayerList(this,PlayerList,manager);
        playerswiththemaximumheightofaclubController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoTotalyearlysalaryofaclub(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Totalyearlysalaryofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        TotalyearlysalaryofaclubController totalyearlysalaryofaclubController = fxmlLoader.getController();
        totalyearlysalaryofaclubController.SetPlayer360nPlayerList(this,PlayerList,Manager);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoTotalyearlysalaryofaclubforManager(List<Player> myPlayers, List<Player> buyPlayers, String clubname, Socket socket, ObjectOutputStream oos, boolean manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("Totalyearlysalaryofaclub.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        TotalyearlysalaryofaclubController totalyearlysalaryofaclubController = fxmlLoader.getController();
        totalyearlysalaryofaclubController.SetPlayer360nPlayerList(this,PlayerList,manager);
        totalyearlysalaryofaclubController.SetAll(myPlayers,buyPlayers,clubname,socket,oos);
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoAddPlayers(boolean Manager) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerStats360.class.getResource("AddPlayers.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 854, 480);
        AddPlayersController addPlayersController = fxmlLoader.getController();
        addPlayersController.setPlayer360(this,PlayerList,Manager);
        addPlayersController.showPlayer();
        stage.setTitle("PlayerStats360");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws Exception {


        launch();
    }
}