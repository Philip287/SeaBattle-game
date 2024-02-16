package com.suprun.seaBattleGame;

import com.suprun.seaBattleGame.entity.Player;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.service.MessageHelper;
import com.suprun.seaBattleGame.service.Operation;
import com.suprun.seaBattleGame.service.command.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger LOGGER = LogManager.getLogger();
    public static boolean exit = false;
    public static Player player;

    public static void main(String[] args) {
        try {
            do {
                Operation operation = MessageHelper.askOperation();
                CommandExecutor.execution(operation);
                if (operation == Operation.EXIT) {
                    CommandExecutor.execution(Operation.EXIT);
                }
            } while (!exit);
        } catch (ServiceException e) {
            player = null;
            LOGGER.error("Exception in App.execution(operation) method find ", e);
        }
        player = null;
        MessageHelper.writeMessage(ContentGame.GOODBYE_MESSAGE);
    }

}
