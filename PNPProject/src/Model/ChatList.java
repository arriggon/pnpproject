package Model;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Creates a chat-list in which the chat-messages are stored.
 */
public class ChatList {

    /**
     * Creates a ObservableList, which holds the chat-messages
     */
    private ObservableList<ChatUnit> chatUnits;

    /**
     * This method is the constructor for the chat-list and sycronizes the chat-list with the observable-list given as parameter.
     * @param listToBind List which the chat-list is syncronized with
     */
    public ChatList(ObservableList<ChatUnit> listToBind) {
        chatUnits = FXCollections.synchronizedObservableList(FXCollections.observableList(new ArrayList<ChatUnit>()));
}

    /**
     * Adds a chatunit to the chat-list holding the chat messages
     * @param u Chatunit which is added to the chat-list
     */
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
    }

    /**
     * Removes a chatunit out of the chat-list
     * @param u Chatunit which is removed from the chat-list
     */
    public void removeChatUnit(ChatUnit u) {
        chatUnits.remove(u);
    }

    /**
     * Get-Method for the chat-list
     * @return Returns the chat-list of the type ObservableList[ChatUnit]
     */
    public ObservableList<ChatUnit> getList() {return chatUnits;}
}
