package GUI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameWindow {
    private boolean isAdmin;
    private boolean isGM;
    private TextArea chat;
    private TextField input;
    private Button send_btn;
    private ListView<String> users;
    private ScrollPane scrolly;
    private MenuBar menu;
    private Menu files;
    private Menu gm;
    private Menu admin;
    //files
    private MenuItem connect;
    private MenuItem disconnect;
    private MenuItem settings;
    private MenuItem exit;
    //gm
    private MenuItem fiever;
    private MenuItem character_editor;
    /**
     * Still needs further implementation
     * **/
    //admin
    private MenuItem save;
    private MenuItem load;
    /**
     * Still needs further implementation
     * **/

    private Stage window;
    private Scene scene;
    private BorderPane main_layout;
    private HBox input_layout;

    public void display(String title, boolean isAdmin, boolean isGM){
        //Init
        window = new Stage();
        main_layout = new BorderPane();
        input_layout = new HBox();
        chat = new TextArea();
        input = new TextField();
        send_btn = new Button("Send");
        users = new ListView<>();
        scrolly = new ScrollPane(users);
        menu = new MenuBar();
        connect = new MenuItem("Connect");
        disconnect = new MenuItem("Disconnect");
        settings = new MenuItem("Settings");
        exit = new MenuItem("Exit");
        fiever = new MenuItem("Fiever (Fight Viewer)");
        character_editor = new MenuItem("Character Editor");
        save = new MenuItem("Save");
        load = new MenuItem("Load");
        files = new Menu("Files");
        gm = new Menu("Game Master");
        admin = new Menu("Admin");
        this.isAdmin = isAdmin;
        this.isGM = isGM;

        //Add functionality
        window.setTitle(title);
        input.setPrefWidth(400);
        chat.setEditable(false);
        /*
        chat.setText("Hello World!");
        users.getItems().addAll("Moritz","Alex","Ai-Mei");
        */
        files.getItems().addAll(connect,disconnect,settings,exit);
        gm.getItems().addAll(fiever,character_editor);
        admin.getItems().addAll(save,load);
        if(isAdmin && !isGM) {
            menu.getMenus().addAll(files,admin);
        }else if(isGM && !isAdmin){
            menu.getMenus().addAll(files, gm);
        }else if(isGM && isAdmin){
            menu.getMenus().addAll(files, gm, admin);
        }else if(!isGM && !isAdmin){
            menu.getMenus().add(files);
        }

        //Add to layouts
        input_layout.getChildren().addAll(input, send_btn);
        main_layout.setCenter(chat);
        main_layout.setRight(scrolly);
        main_layout.setBottom(input_layout);
        main_layout.setTop(menu);

        //Scene and Stage
        scene = new Scene(main_layout);
        window.setScene(scene);
        window.show();
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public boolean getAdmin(){
        return this.isAdmin;
    }

    public void setGM(boolean isGM){
        this.isGM = isGM;
    }

    public boolean getGM(){
        return this.isGM;
    }
}
