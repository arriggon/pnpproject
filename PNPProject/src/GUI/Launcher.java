package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.Exception;
import java.lang.Override;

public class Launcher extends Application{
    //Elements
    private Button join_btn;
    private Button host_btn;
    private Button settings_btn;
    private Button quit_btn;

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Stage and Layouts
        primaryStage.setWidth(225);
        primaryStage.setHeight(250);
        primaryStage.setTitle("PNPProject");
        BorderPane main = new BorderPane();
        VBox menu = new VBox(10);

        //Initialization
        join_btn = new Button("Join a game");
        host_btn = new Button("Host a game");
        settings_btn = new Button("Settings");
        quit_btn = new Button("Quit the game");

        //Actions
        join_btn.setOnAction(e -> {
            JoinWindow.display("Join a game");
        });
        host_btn.setOnAction(e -> {
            HostWindow.display("Host a game");
        });
        settings_btn.setOnAction(e -> {
            //To be integrated
        });
        quit_btn.setOnAction(e -> {
            //OptionPane.display();
            primaryStage.close();
        });

        menu.getChildren().addAll(join_btn, host_btn, settings_btn, quit_btn);
        menu.setAlignment(Pos.CENTER);
        main.setCenter(menu);
        Scene sc = new Scene(main);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}