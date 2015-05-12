package Clinet.Service;

import Model.ChatUnit;
import Server.Server;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by Alexander on 12.05.2015.
 */
public class DataRetriever extends Service<ChatUnit> {


    @Override
    protected Task<ChatUnit> createTask() {
        return null;
    }
}
