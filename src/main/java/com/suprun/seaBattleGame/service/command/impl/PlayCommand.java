package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.entity.Player;
import com.suprun.seaBattleGame.entity.RandomIntelligence;
import com.suprun.seaBattleGame.entity.SeaMap;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.service.Game;
import com.suprun.seaBattleGame.service.GameService;
import com.suprun.seaBattleGame.service.MessageHelper;
import com.suprun.seaBattleGame.service.command.Command;

import java.io.IOException;

public class PlayCommand implements Command {
    private Command singInCommand;

    public PlayCommand() {
        singInCommand = new SingInCommand();
    }

    @Override
    public void execute() throws ServiceException {
        if (GameService.player == null) {
            singInCommand.execute();
        }else {
            SeaMap mapOne = new SeaMap();
            SeaMap mapTwo = new SeaMap();

            Player playerOne = new Player(GameService.player.getName(), mapOne, mapTwo);
            RandomIntelligence playerTwo = new RandomIntelligence(mapTwo, mapOne);

            Game gameSession = new Game(playerOne, playerTwo);
            try {
                gameSession.startGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            MessageHelper.writeMessage(ContentGame.PLAYER_MESSAGE);
        }
    }
}
