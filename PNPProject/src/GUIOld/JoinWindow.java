package GUIOld;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JoinWindow{
    public static void display(String title){
        Stage primaryStage = new Stage();
        TextField usrname = new TextField();
        usrname.setPromptText("User-Name");
        TextField password_field = new TextField();
        password_field.setPromptText("Password");
        TextField ip_field = new TextField();
        ip_field.setPromptText("IP");
        TextField port_field = new TextField();
        port_field.setPromptText("Port");
        Button connect_btn = new Button("Connect");
        Button cancel_btn = new Button("Cancel");
        connect_btn.setOnAction(e -> {
            //To be integrated
        });
        cancel_btn.setOnAction(e -> {
            primaryStage.close();
        });
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setTitle(title);
        BorderPane main = new BorderPane();
        VBox layout = new VBox();
        VBox bottom = new VBox();
        layout.getChildren().addAll(usrname,password_field,ip_field,port_field);
        bottom.getChildren().addAll(connect_btn, cancel_btn);
        main.setCenter(layout);
        main.setBottom(bottom);
        Scene sc = new Scene(main);
        primaryStage.setScene(sc);
        primaryStage.showAndWait();
    }
}