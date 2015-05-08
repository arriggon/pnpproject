package GUI;

import TestServer.ChatObserver;
import TestServer.InputWatcher;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

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
    private InputWatcher in;        //Meine Addins: Chatter
    private ChatObserver observer; //Observer: Asynchrone Daten:

    public void setIn(InputWatcher i) {     //InputWatcher: Funktioniert, da er vom javaFX Thread aufgerufen wird
        in = i;
        send_btn.setOnAction(e ->
            in.run(input.getText()));

        input.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)){
                //for testing it only appends text to the chat
                in.run(input.getText());
            }
        });
    }

    public ChatObserver getObserver() { //Observer: Mein VErsuch fürs Asyncrone Datenmodell
        if(observer == null) {
            observer = new ChatObserver(this);
            Platform.runLater(observer);
        }
        return observer;
    }





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
        send_btn.setOnAction(e -> {
            //for testing it only appends text to the chat
            chat.appendText("\n"+input.getText());
            input.setText("");
        });
        input.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)){
                //for testing it only appends text to the chat
                chat.appendText("\n"+input.getText());
                input.setText("");
            }
        });

        //Add to layouts
        input_layout.getChildren().addAll(input, send_btn);
        main_layout.setCenter(chat);
        main_layout.setRight(scrolly);
        main_layout.setBottom(input_layout);
        main_layout.setTop(menu);

        //Scene and Stage
        /**
         * For testing purposes:
         * getUserByName(null);
         * **/
        scene = new Scene(main_layout);
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            e.consume();
            if(Launcher.serverF != null) {
                Launcher.serverF.cancel(true);
                Launcher.mainThreads.shutdown();
            }
            window.close();
        });
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

    public void appendChatText(String text){
        chat.appendText("\n" + text);
    }

    public void setChatText(String text){
        chat.setText(text);
    }

    public String getChatText(){
        return chat.getText();
    }

    public void addUser(String user){
        if(user != null && user != "" && user.length() >= 3) {
            users.getItems().add(user);
        }else{
            ErrorThrower.throwError(ErrorType.NAME);
        }
    }

    public void addUserList(ArrayList<String> users){
        for(String key : users){
            if(key != null && key != "" && key.length() >= 3){
                this.users.getItems().add(key);
            }else{
                ErrorThrower.throwError(ErrorType.NAME);
            }
        }
    }

    public String getUserById(int id){
        return users.getItems().get(id);
    }

    public String getUserByName(String name){
        if (name != null && name != "" && name.length() >= 3){
            for(int i = 0; i < users.getItems().toArray().length; i++){
                if(name.equals(users.getItems().toArray()[i].toString())){
                    return users.getItems().get(i);
                }
            }
        }else{
            ErrorThrower.throwError(ErrorType.NAME);
        }
        return null;
    }
}
