package GUI.CharEdit;

import Model.ChatList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by RAIDER on 16.05.2015.
 */
public class CharEdit {

    private Stage charWindow;

    public CharEdit(){
        this.charWindow = new Stage(StageStyle.UTILITY);
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("charEdit.fxml"));
        try {
            Parent root = loader.load();
            Object controlls = loader.getController();
            if(controlls instanceof EditControlls) {
                EditControlls editControlls = (EditControlls)controlls;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
