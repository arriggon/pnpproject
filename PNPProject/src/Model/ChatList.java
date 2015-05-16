package Model;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by RAIDER on 08.05.2015.
 */
public class ChatList {

    private ObservableList<ChatUnit> chatUnits;

    public ChatList(ObservableList<ChatUnit> listToBind) {
        chatUnits = FXCollections.synchronizedObservableList(FXCollections.observableList(new ArrayList<ChatUnit>()));
}

    public void addChatUnit(ChatUnit u) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatUnits.add(u);
                if(chatUnits.size() >= 500) {
                    chatUnits.remove(0, chatUnits.size()-501);
                }
            }
        });
        /*chatUnits.add(u);
        if(chatUnits.size() >= 500) {
            chatUnits.remove(0, chatUnits.size()-501);
        }*/
    }

    public void removeChatUnit(ChatUnit u) {
        chatUnits.remove(u);
    }

    public ObservableList<ChatUnit> getList() {return chatUnits;}
}
