/**
 * Sample Skeleton for 'main.fxml' Controller Class
 */

package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Clinet.Client;
import Model.ChatList;
import Model.ChatUnit;
import Model.User;
import Model.UserList;
import Server.Server;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.script.Bindings;


public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chat_list"
    private ListView<User> chat_list; // Value injected by FXMLLoader

    @FXML // fx:id="send_text_TF"
    private TextField send_text_TF; // Value injected by FXMLLoader

    @FXML
    private ListView<ChatUnit> chat_history; // Value injected by FXMLLoader

    @FXML // fx:id="button_B"
    private Button button_B; // Value injected by FXMLLoader

    @FXML
    private MenuItem host_mi;

    @FXML
    private MenuItem join_mi;

    @FXML
    private MenuItem stop_mi;

    private UserList userList;
    private ChatList chatList;
    private Server server;
    private Client client;




    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chat_list != null : "fx:id=\"chat_list\" was not injected: check your FXML file 'main.fxml'.";
        assert send_text_TF != null : "fx:id=\"send_text_TF\" was not injected: check your FXML file 'main.fxml'.";
        assert button_B != null : "fx:id=\"button_B\" was not injected: check your FXML file 'main.fxml'.";
        assert chat_history != null : "fx:id=\"chat_history\" was not injected: check your FXML file 'main.fxml'.";
        userList = new UserList();
        javafx.beans.binding.Bindings.bindContentBidirectional(userList.getList(), chat_list.getItems());
        chatList = new ChatList(null);

        javafx.beans.binding.Bindings.bindContentBidirectional(chatList.getList(), chat_history.getItems());


        try {
            server = new Server(userList, chatList);
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
            System.exit(0);

        }


        host_mi.setOnAction(e -> {
            server.start();
        });

        join_mi.setOnAction(e -> {
            clientJoinInit();
        });


        button_B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userList.addUser(new User(send_text_TF.getText()));
            }
        });





    }



    public void clientJoinInit() {
        TextInputDialog d = new TextInputDialog();
        d.setTitle("Username");
        d.setHeaderText("Enter username");
        d.setContentText("Username");
        Optional<String> op = d.showAndWait();
        if(op.get() != "") {
            d.setTitle("IP");
            d.setHeaderText("Please enter the Server IP");
            d.setContentText("Server IP");
            d.getEditor().setText("");
            Optional<String> op1 = d.showAndWait();
            if(op1.get() != "") {
                System.out.println("Username: "+op.get()+"\nServer IP: "+op1.get());
                d.setTitle("Port");
                d.setHeaderText("Please enter Port");
                d.setContentText("Port: ");
                d.getEditor().setText("");
                Optional<String> s = d.showAndWait();
                if(op1.get() != "") {
                    String username = op.get();
                    String severIp = op1.get();
                    int port = Integer.parseInt(s.get());
                    client = new Client(chatList, userList, username, severIp, port);
                    client.start();
                    button_B.setOnAction( e -> {
                        client.send(send_text_TF.getText());
                    });
                }
            }
        }
    }

    public void closeAll() {
        if(client != null) {
            client.cancel();
        }

        if(server != null) {
            server.cancel();
        }
    }
}