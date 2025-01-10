package com.example.playerstatsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class AddPlayersController {
    PlayerStats360 Player360;
    List<Player> PlayerList;
    @FXML
    private ListView<String> listview;
    @FXML
    private TextField Age;
    @FXML
    private TextField Club;
    @FXML
    private TextField Country;
    @FXML
    private TextField Height;
    @FXML
    private TextField JerseyNumber;
    @FXML
    private TextField Name;
    @FXML
    private TextField Position;
    @FXML
    private TextField WeeklySalary;
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

    public void NameTextField(MouseEvent actionEvent) {
        Name.setPromptText("Name");
        Name.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void AgeTextField(MouseEvent mouseEvent) {
        Age.setPromptText("Age");
        Age.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void HeightTextField(MouseEvent mouseEvent) {
        Height.setPromptText("Height");
        Height.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void ClubTextField(MouseEvent mouseEvent) {
        Club.setPromptText("Club");
        Club.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void CountryTextField(MouseEvent mouseEvent) {
        Country.setPromptText("Country");
        Country.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void PositionTextField(MouseEvent mouseEvent) {
        Position.setPromptText("Position");
        Position.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void JerseyNumberTextField(MouseEvent mouseEvent) {
        JerseyNumber.setPromptText("Jersey Number");
        JerseyNumber.setStyle("-fx-prompt-text-fill: grey;");
    }

    public void WeeklyTextField(MouseEvent mouseEvent) {
        WeeklySalary.setPromptText("WeeklySalary");
        WeeklySalary.setStyle("-fx-prompt-text-fill: grey;");
    }


        @FXML
        void ClickButton(ActionEvent event) throws Exception {

            RequiredTools requiredTools =new RequiredTools(PlayerList);
            if(Name.getText().isEmpty())
            {
                Name.setPromptText("Name!");
                Name.setStyle("-fx-prompt-text-fill: red;");
            }

            if(Age.getText().isEmpty())
            {
                Age.setPromptText("Age!");
                Age.setStyle("-fx-prompt-text-fill: red;");
            }

            if(Club.getText().isEmpty())
            {
                Club.setPromptText("Club!");
                Club.setStyle("-fx-prompt-text-fill: red;");
            }

            if(Country.getText().isEmpty())
            {
                Country.setPromptText("Country!");
                Country.setStyle("-fx-prompt-text-fill: red;");
            }

            if(Position.getText().isEmpty())
            {
                Position.setPromptText("Position!");
                Position.setStyle("-fx-prompt-text-fill: red;");
            }

            if(Height.getText().isEmpty())
            {
                Height.setPromptText("Height!");
                Height.setStyle("-fx-prompt-text-fill: red;");
            }

            if(JerseyNumber.getText().isEmpty())
            {
                JerseyNumber.setPromptText("Jersey Number!");
                JerseyNumber.setStyle("-fx-prompt-text-fill: red;");
            }

            if(WeeklySalary.getText().isEmpty())
            {
                WeeklySalary.setPromptText("WeeklySalary!");
                WeeklySalary.setStyle("-fx-prompt-text-fill: red;");
            }

            Player NewPlayer=null;
            try {
                NewPlayer = requiredTools.addPlayer(Name.getText(), Country.getText(), Integer.parseInt(Age.getText()), Double.parseDouble(Height.getText()), Club.getText(), Position.getText(), Integer.parseInt(JerseyNumber.getText()), Long.parseLong(WeeklySalary.getText()));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerProfile.fxml"));
                Scene scene = new Scene(loader.load());
                PlayerProfileController playerProfileController = loader.getController();
                playerProfileController.SetPlayerList(PlayerList);
                Stage secondaryStage = new Stage();
                if (NewPlayer==null) {
                    playerProfileController.action(requiredTools.ByName(Name.getText()));
                }
                else {
                    playerProfileController.action(NewPlayer);
                    PlayerList.add(NewPlayer);
                    Player360.gotoAddPlayers(Manager);
                }

                    secondaryStage.setTitle(requiredTools.ByName(Name.getText()).getName());
                    secondaryStage.setScene(scene);
                    secondaryStage.show();

            }catch (Exception e)
            {
                System.out.println(e+"ji");
            }
            Name.clear();
        }

    public void initialize() {
        listview.setOnMouseClicked(event -> {
            String PlayerName = listview.getSelectionModel().getSelectedItem();
            RequiredTools requiredTools=new RequiredTools(PlayerList);
            Player player =requiredTools.ByName(PlayerName);
            try {
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

    public void ClickAddPlayer() throws Exception {
        Player360.gotoguest(Manager);
    }

    public void ClickBack() throws Exception {
            Player360.GuestOManager();
    }


}
