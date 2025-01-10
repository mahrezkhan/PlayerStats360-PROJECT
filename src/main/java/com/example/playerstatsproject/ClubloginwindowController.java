package com.example.playerstatsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClubloginwindowController {
    PlayerStats360 Player360;
//    List<Player> MyPlayers;
//    List<Player> BuyPlayers;
    List<Password> PasswordList;
    boolean Manager;
    @FXML
    private SplitMenuButton Clubname;
    @FXML
    private MenuItem Chennai;
    @FXML
    private MenuItem Delhi;
    @FXML
    private MenuItem Gujarat;
    @FXML
    private MenuItem Kolkata;
    @FXML
    private MenuItem Lucknow;
    @FXML
    private MenuItem Mumbai;
    @FXML
    private MenuItem Punjab;
    @FXML
    private MenuItem Rajasthan;
    @FXML
    private MenuItem Royal;
    @FXML
    private MenuItem Sunrisers;
    @FXML
    private Label incorrect;
    @FXML
    private PasswordField password;
    @FXML
    private TextField visiblepassword;
    @FXML
    private CheckBox CheckBoxid;
    @FXML
    private Button submit;
    public void initialize() {
        password.setOnAction(event -> {
            try {
                clicksubmit((new ActionEvent()));
                submit.requestFocus();
            } catch (Exception e) {

            }
        });
        visiblepassword.setOnAction(event -> {
            try {
                clicksubmit(new ActionEvent());
                submit.requestFocus();
            } catch (Exception e) {

            }
        });
        visiblepassword.textProperty().bindBidirectional(password.textProperty());
        visiblepassword.setVisible(false);
    }
    @FXML
    void CheckBox(ActionEvent event) {
        if (CheckBoxid.isSelected()) {
            visiblepassword.setVisible(true);
            password.setVisible(false);

        } else {
            visiblepassword.setVisible(false);
            password.setVisible(true);
        }

    }

    public void SetAllList(PlayerStats360 player360, List<Password> passwordList,boolean manager) {
        Player360 = player360;
        PasswordList=passwordList;
        Manager=manager;
    }

    @FXML
    void ClickChennai(ActionEvent event) {
        Clubname.setText(Chennai.getText());
        Clubname.setStyle("-fx-prompt-text-fill: red;");
    }

    @FXML
    void ClickDelhi(ActionEvent event) {
        Clubname.setText(Delhi.getText());
    }

    @FXML
    void ClickGujarat(ActionEvent event) {
        Clubname.setText(Gujarat.getText());
    }

    @FXML
    void ClickKolkata(ActionEvent event) {
        Clubname.setText(Kolkata.getText());
    }

    @FXML
    void ClickLucknow(ActionEvent event) {
        Clubname.setText(Lucknow.getText());
    }

    @FXML
    void ClickMumbai(ActionEvent event) {
        Clubname.setText(Mumbai.getText());
    }

    @FXML
    void ClickPunjab(ActionEvent event) {
        Clubname.setText(Punjab.getText());
    }

    @FXML
    void ClickRajasthan(ActionEvent event) {
        Clubname.setText(Rajasthan.getText());
    }

    @FXML
    void ClickRoyal(ActionEvent event) {
        Clubname.setText(Royal.getText());
    }

    @FXML
    void ClickSunrisers(ActionEvent event) {
        Clubname.setText(Sunrisers.getText());
    }
    @FXML
    void clicksubmit(ActionEvent event){
        Password p=new Password("p","p");
        for(Password pass:PasswordList)
        {
            if(pass.Clubname.equalsIgnoreCase(Clubname.getText()))
            {
                p=pass;
                break;
            }
        }
        if(!(Clubname.getText().equalsIgnoreCase("Club Name")) && p.password.equals(password.getText()))
        {
            List<Player> MyPlayers;
            List<Player> BuyPlayers;
            try {
                Socket socket = new Socket("127.0.0.1", 55555);
                System.out.println("Client Connected");
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                oos.writeObject(Clubname.getText());
                MyPlayers=(List<Player>) ois.readObject();
                oos.writeObject(Clubname.getText());
                BuyPlayers=(List<Player>) ois.readObject();
                for(Player pl:MyPlayers)
                {
                    System.out.println(pl);
                }for(Player pl:BuyPlayers)
                {
                    System.out.println(pl);
                }
                new ReaderThread(ois, MyPlayers, BuyPlayers, Clubname.getText());
                Player360.gotoMyclubHomePage(MyPlayers,BuyPlayers,Clubname.getText(), socket, oos,Manager);
            }catch(Exception e)
            {
                incorrect.setText("Server Unavailable");
            }
        }
        else
        {
            if(Clubname.getText().equalsIgnoreCase("Club Name"))
            {
                incorrect.setText("Select Club!");
            }
            else {
                incorrect.setText("Wrong Password");
            }

        }
    }

    public void ClickBack(ActionEvent actionEvent) throws Exception {
        Player360.GuestOManager();
    }
}
