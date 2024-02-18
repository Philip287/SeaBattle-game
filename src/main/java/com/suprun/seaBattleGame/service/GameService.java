package com.suprun.seaBattleGame.service;

import com.suprun.seaBattleGame.entity.User;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.command.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameService {
    private static final Logger LOGGER = LogManager.getLogger();
    public static boolean exit = false;
    public static User player;

    public static void startGame() {

        try {
            Operation operation = MessageHelper.askOperation();
            do {
                CommandExecutor.execution(operation);
                if (player != null) {
                    if (player.getRole() == User.UserRole.ADMIN) {
                        operation = MessageHelper.askOperationForAdmin();
                    } else {
                        operation = MessageHelper.askOperation();
                    }
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
