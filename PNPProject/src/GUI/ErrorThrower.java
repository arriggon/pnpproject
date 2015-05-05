package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ErrorThrower {
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private Label message;
    private Button accept;

    public ErrorThrower(ErrorType type){
        window = new Stage();
        layout = new BorderPane();
        message = new Label(type.getMessage()+"\nError-ID: "+type.getId());
        window.setTitle(type.getName());
        accept = new Button("Okay");
        accept.setOnAction(e -> {
            window.close();
        });
        layout.setBottom(accept);
        layout.setCenter(message);
        scene = new Scene(layout,400,200);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void throwError(ErrorType type){
        ErrorThrower e = new ErrorThrower(type);
    }
}
