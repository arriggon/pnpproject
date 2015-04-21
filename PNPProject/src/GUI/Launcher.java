package GUI;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.Exception;
import java.lang.Override;

public class Launcher extends Application{
    //Elements
    private Button join_btn;

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(200);
        primaryStage.setHeight(500);
        BorderPane main = new BorderPane();
        VBox menu = new VBox(10);




        primaryStage.show();
    }
}