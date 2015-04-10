package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Arriggon on 4/8/2015.
 */
public class OptionDialogue {
    private static Button by;
    private static Button bn;
    private static Label l1;
    private static boolean ret;
    private static double xOffset;
    private static double yOffset;

    public static boolean show(String title, String message){
        Stage s = new Stage();
        s.initStyle(StageStyle.UNDECORATED);
        by = new Button("Yes");
        by.setMaxHeight(25);
        by.setMinHeight(25);
        by.setMaxWidth(48);
        by.setMinWidth(48);
        by.setOnAction(e -> {
            s.close();
            ret = true;
        });
        bn = new Button("No");
        bn.setMaxHeight(25);
        bn.setMinHeight(25);
        bn.setMaxWidth(48);
        bn.setMinWidth(48);
        bn.setOnAction(e ->{
            s.close();
            ret = false;
        });
        l1 = new Label(message);
        l1.setMaxWidth(96);
        l1.setMinWidth(96);
        l1.setMaxHeight(20);
        l1.setMinHeight(20);
        l1.setWrapText(true);
        l1.setAlignment(Pos.CENTER);
        s.setTitle(title);
        BorderPane layout = new BorderPane();
        layout.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        layout.setOnMouseDragged(e -> {
            s.setX(e.getScreenX() - xOffset);
            s.setY(e .getScreenY() - yOffset);
        });
        layout.setCenter(l1);
        HBox h = new HBox(5);
        h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(by,bn);
        layout.setBottom(h);
        Scene sc = new Scene(layout,100,100);
        s.setScene(sc);
        s.showAndWait();
        return ret;
    }
}
