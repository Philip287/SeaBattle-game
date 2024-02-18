package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.entity.Player;
import com.suprun.seaBattleGame.entity.User;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.service.GameService;
import com.suprun.seaBattleGame.service.MessageHelper;
import com.suprun.seaBattleGame.service.command.Command;

/**
 * {@code InfoCommand} class that implements the account status view function.
 *
 * @author Philip Suprun
 */
public class InfoCommand implements Command {
    private SingInCommand singInCommand;

    public InfoCommand() {
        singInCommand = new SingInCommand();
    }

    @Override
    public void execute() throws ServiceException {
        if (GameService.player == null) {
            singInCommand.execute();
        } else {
            printUserInformation(GameService.player);
        }
    }

    public void printUserInformation(User user) {
        MessageHelper.writeMessage(ContentGame.MESSAGE_HEADER);
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_NAME + user.getName());
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_AGE + user.getAge());
        MessageHelper.writeMessage(ContentGame.MESSAGE_VICTORIES_COUNT + user.getVictoriesCount());
        MessageHelper.writeMessage(ContentGame.MESSAGE_LESIONS_COUNT + user.getLesionsCount());
        MessageHelper.writeMessage("\n");
    }
}
