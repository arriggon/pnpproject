package GUIOld;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OptionPane {
    private static boolean y;

    public static boolean display(String title, String message){
        y = false;
        Stage window = new Stage();
        window.setTitle(title);
        window.setHeight(100);
        window.setWidth(200);
        Button by = new Button("Yes");
        Button bn = new Button("No");
        by.setOnAction(e -> {
            y = true;
            window.close();
        });
        bn.setOnAction(e -> {
            y = false;
            window.close();
        });
        Label l1 = new Label(message);
        BorderPane main = new BorderPane();
        HBox buttons = new HBox(10);
        main.setCenter(l1);
        buttons.getChildren().addAll(by, bn);
        main.setBottom(buttons);
        buttons.setAlignment(Pos.CENTER);
        Scene sc = new Scene(main);
        window.setScene(sc);
        window.showAndWait();
        return y;
    }
}
