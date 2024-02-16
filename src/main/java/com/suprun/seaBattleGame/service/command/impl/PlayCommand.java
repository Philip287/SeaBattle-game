package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.App;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.command.Command;

public class PlayCommand implements Command {
    private SingInCommand singInCommand;

    public PlayCommand() {
        singInCommand = new SingInCommand();
    }

    @Override
    public void execute() throws ServiceException {
        if (App.player == null) {
            singInCommand.execute();
        }
    }
}
