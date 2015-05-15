package GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.ObjectInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Object rootA = loader.load();
        Parent root = null;
        if(Parent.class.isInstance(rootA)) {
            root = (Parent) rootA;
        }
        Object o = loader.getController();
        Controller co = null;
        if(o instanceof Controller) {
            co = (Controller) o;

        }
        final Controller c = co;
        primaryStage.setTitle("PNP Project");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if(c != null) {
                    c.closeAll();
                }

                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
