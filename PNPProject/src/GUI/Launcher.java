package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.Exception;
import java.lang.Override;

public class Launcher extends Application{
    //Elements
    private Button join_btn;
    private Button host_btn;
    private Button settings_btn;
    private Button quit_btn;
    private double xOffset;
    private double yOffset;
    /**
     * For testing purposes:
     * private GameWindow g = new GameWindow();
    **/

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Stage and Layouts
        primaryStage.setWidth(250);
        primaryStage.setHeight(400);
        primaryStage.setTitle("PNPProject");
        BorderPane main = new BorderPane();
        VBox menu = new VBox(10);
        primaryStage.initStyle(StageStyle.UNDECORATED);


        main.setOnMousePressed(e -> {
            xOffset = primaryStage.getX() - e.getScreenX();
            yOffset = primaryStage.getY() - e.getScreenY();
        });

        main.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() + xOffset);
            primaryStage.setY(e.getScreenY() + yOffset);
        });

        //Initialization
        join_btn = new Button("Join a game");
        host_btn = new Button("Host a game");
        settings_btn = new Button("Settings");
        quit_btn = new Button("Quit the game");
        join_btn.setPrefSize(100, 30);
        host_btn.setPrefSize(100, 30);
        settings_btn.setPrefSize(100, 30);
        quit_btn.setPrefSize(100, 30);
        quit_btn.setStyle("-fx-focus-color: transparent; -fx-background-image: url('button_image.png'); -fx-text-fill: #000000; -fx-font-weight: bold; -fx-background-size: 100%");
        settings_btn.setStyle("-fx-focus-color: transparent; -fx-background-image: url('button_image.png'); -fx-text-fill: #000000; -fx-font-weight: bold; -fx-background-size: 100%");
        host_btn.setStyle("-fx-focus-color: transparent; -fx-background-image: url('button_image.png'); -fx-text-fill: #000000; -fx-font-weight: bold; -fx-background-size: 100%");
        join_btn.setStyle("-fx-focus-color: transparent; -fx-background-image: url('button_image.png'); -fx-text-fill: #000000; -fx-font-weight: bold; -fx-background-size: 100%");

        //Actions
        join_btn.setOnAction(e -> {
            JoinWindow.display("Join a game");
        });
        host_btn.setOnAction(e -> {
            /**
             * For testing purposes:
             * g.display("Game", true, true);
             * **/
            HostWindow.display("Host a game");
        });
        settings_btn.setOnAction(e -> {
            //To be integrated
        });
        quit_btn.setOnAction(e -> {
            if (OptionPane.display("Quit", "Do you really want to quit?")) {
                primaryStage.close();
            } else {
                //do nothing
            }
        });

        primaryStage.setOnCloseRequest(e -> {
            if (OptionPane.display("Quit", "Do you really want to quit?")) {
                primaryStage.close();
            } else {
                //do nothing
            }
        });

        menu.getChildren().addAll(join_btn, host_btn, settings_btn, quit_btn);
        menu.setAlignment(Pos.CENTER);
        main.setCenter(menu);
        Scene sc = new Scene(main);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}