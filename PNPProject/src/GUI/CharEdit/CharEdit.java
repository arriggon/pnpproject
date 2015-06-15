package GUI.CharEdit;

import GUI.Controller;
import Model.Character.*;
import Model.Character.Character;
import Model.ChatList;
import Model.ServerUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * This is the GUI opened when the user clicked on the 'Character Creator' button
 */
public class CharEdit {

    private Stage charWindow;
    private EditControlls editControlls;
    private Controller c;

    public CharEdit(){
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



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Stage getStage() {
        return charWindow;
    }

    public void setCharacterForServerUser(ServerUser u) {
        editControlls.setCharacterForServerUser(u);
    }

    public EditControlls getEditControlls() {
        return editControlls;
    }
}
