package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.App;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.service.MessageHelper;
import com.suprun.seaBattleGame.service.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ExitCommand} a class that implements the function of exiting the game application.
 *
 * @author Philip Suprun
 */
public class ExitCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public ExitCommand() {

    }
    @Override
    public void execute() throws ServiceException {

        boolean work = true;
        MessageHelper.writeMessage(ContentGame.QUESTION_EXIT_MESSAGE);
        do {
            String answer = MessageHelper.readString();
            if (answer.equals(ContentGame.CHOICE_1)) {
                work = false;
                App.exit = true;
            } else if (answer.equals(ContentGame.CHOICE_2)) {
                App.exit = false;
                work = false;
            } else {
                MessageHelper.writeMessage(ContentGame.WRONG_MESSAGE);
                LOGGER.error("Incorrect input: " + answer);
            }
        } while (work);
    }

}
