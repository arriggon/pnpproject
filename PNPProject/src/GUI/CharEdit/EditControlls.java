package GUI.CharEdit;

import GUI.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
