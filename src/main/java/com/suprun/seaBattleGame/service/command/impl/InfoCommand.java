package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.App;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.ContentGame;
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
        if (App.player == null) {
            singInCommand.execute();
        } else {
            MessageHelper.writeMessage(ContentGame.MESSAGE_HEADER);
            MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_NAME + App.player.getName());
            MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_AGE + App.player.getAge());
            MessageHelper.writeMessage(ContentGame.MESSAGE_VICTORIES_COUNT + App.player.getVictoriesCount());
            MessageHelper.writeMessage(ContentGame.MESSAGE_LESIONS_COUNT + App.player.getLesionsCount());
        }
    }
}
