package GUI;

import TestServer.TestChat;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HostWindow {
    public static void display(String title){
        Stage primaryStage = new Stage();
        TextField server_name = new TextField();
        server_name.setPromptText("Server-Name");
        TextField usrname = new TextField();
        usrname.setPromptText("User-Name");
        TextField password_field = new TextField();
        password_field.setPromptText("Password");
        //TextField ip_field = new TextField();
        //ip_field.setPromptText("IP");
        TextField port_field = new TextField();
        port_field.setPromptText("Port");
        Button connect_btn = new Button("Start");
        Button cancel_btn = new Button("Cancel");
        connect_btn.setOnAction(e -> {
            GameWindow gm = new GameWindow();
            gm.display("Title", true, true);
            ExecutorService ser = Executors.newCachedThreadPool();
            TestChat c = new TestChat(gm);
            ser.submit(c);
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
        layout.getChildren().addAll(server_name,usrname,password_field,port_field);
        bottom.getChildren().addAll(connect_btn, cancel_btn);
        main.setCenter(layout);
        main.setBottom(bottom);
        Scene sc = new Scene(main);
        primaryStage.setScene(sc);
        primaryStage.showAndWait();
    }
}
