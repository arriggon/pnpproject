package GUI.CharEdit;

import GUI.Controller;
import Model.Character.*;
import Model.Character.Character;
import Model.ChatList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by RAIDER on 16.05.2015.
 */
public class CharEdit {

    private Stage charWindow;
    private EditControlls editControlls;
    private Controller c;

    public CharEdit(Controller c){
        this.c = c;
        this.charWindow = new Stage(StageStyle.UTILITY);
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("charEdit.fxml"));
        try {
            Parent root = loader.load();
            Object controlls = loader.getController();
            if(controlls instanceof EditControlls) {
                 editControlls = (EditControlls)controlls;
                editControlls.setChatEdit(this);
            }


            Scene sc = new Scene(root);
            this.charWindow.setResizable(false);
            this.charWindow.setTitle("Character Editor");
            this.charWindow.setScene(sc);

            this.charWindow.show();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Stage getStage() {
        return charWindow;
    }

    public EditControlls getEditControlls() {
        return editControlls;
    }
}
