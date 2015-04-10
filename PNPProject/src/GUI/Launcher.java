package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application {
    private Button join_btn;
    private Button host_btn;
    private Button settings_btn;
    private Button quit_btn;
    private TextField user_field;
    private Label user_label;
    private double xOffset;
    private double yOffset;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        BorderPane main = new BorderPane();

        //Main Menu Layout
        VBox v = new VBox(15);
        v.setAlignment(Pos.CENTER);
        //Buttons
        join_btn = new Button("Join a Game");
        join_btn.setMaxSize(100, 20);
        host_btn = new Button("Host a Game");
        host_btn.setMaxSize(100,20);
        settings_btn = new Button("Settings");
        settings_btn.setMaxSize(100,20);
        quit_btn = new Button("Quit");
        quit_btn.setMaxSize(100,20);
        quit_btn.setOnAction(e ->{
            if(new OptionDialogue().show("","Quit?")){
                primaryStage.close();
            }else{
                //do nothing
            }
        });
        //Textfields
        user_field = new TextField();
        user_field.setMaxSize(100,20);
        //Labels
        user_label = new Label("User-Name: ");
        //Adding
        v.getChildren().addAll(user_label, user_field, join_btn, host_btn, settings_btn, quit_btn);

        //Add to main
        main.setCenter(v);

        //Draggable
        main.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        main.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - xOffset);
            primaryStage.setY(e .getScreenY() - yOffset);
        });


        //Create Scene & set layout
        Scene sc = new Scene(main,250,400);

        //Set scene
        primaryStage.setScene(sc);

        //Show Stage
        primaryStage.show();
    }

    public void join(){

    }

    public void host(){

    }

    public void settings(){

    }
}