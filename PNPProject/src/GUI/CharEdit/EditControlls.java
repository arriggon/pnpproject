package GUI.CharEdit;

import GUI.Controller;
import Model.Character.*;
import Model.Character.Character;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditControlls implements Initializable{

    @FXML
    private TextField name_TF;

    @FXML
    private TextField age_tf;

    @FXML
    private TextField race_tf;

    @FXML
    private TextField class_tf;

    @FXML
    private TextArea biography_ta;

    @FXML
    private TextField agi_tf;

    @FXML
    private TextField dex_tf;

    @FXML
    private TextField wis_tf;

    @FXML
    private TextField intel_tf;

    @FXML
    private TextField atk_tf;

    @FXML
    private TextField def_tf;

    @FXML
    private TextField sta_tf;

    @FXML
    private TextField hp_tf;

    @FXML
    private Button cancel_bt;

    @FXML
    private Button accept_bt;

    private Controller controller;
    private Model.Character.Character character;
    private CharEdit chatEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        accept_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Character c = createCharacter();
                if(c != null) {
                    character = c;
                    chatEdit.getStage().close();
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText(character.toString());
                    a.show();
                }

            }
        });

        cancel_bt.setOnAction( e -> {
            chatEdit.getStage().close();
        });

    }

    public void setController(Controller ca) {
        if(ca != null) {
            controller = ca;
        }
    }

    public Character createCharacter() {
        try {
            Character c = new Character(name_TF.getText(), Integer.parseInt(age_tf.getText()), race_tf.getText(), class_tf.getText(), biography_ta.getText(), Integer.parseInt(agi_tf.getText()),
                    Integer.parseInt(wis_tf.getText()), Integer.parseInt(hp_tf.getText()), Integer.parseInt(intel_tf.getText()), Integer.parseInt(atk_tf.getText()),
                    Integer.parseInt(def_tf.getText()), Integer.parseInt(sta_tf.getText()), Integer.parseInt(dex_tf.getText()));
            return c;
        } catch (CharacterCreatorException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Oppps, something went wrong");
            a.setContentText(e.getMessage());
            a.showAndWait();
            return null;
        }
    }

    public Character getCharacter() {
        return character;
    }

    public void setChatEdit(CharEdit ce) {
        chatEdit = ce;
    }

    public void fillCharEdit(Character c) {
        name_TF.setText(c.getName());
        name_TF.setEditable(false);

        age_tf.setText(String.valueOf(c.getAge()));
        age_tf.setEditable(false);

        race_tf.setText(c.getRace());
        race_tf.setEditable(false);

        class_tf.setText(c.get_class());
        class_tf.setEditable(false);
    }

}
