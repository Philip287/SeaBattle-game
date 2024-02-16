package com.suprun.seaBattleGame.service;

import com.suprun.seaBattleGame.entity.Display;
import com.suprun.seaBattleGame.entity.Player;
import com.suprun.seaBattleGame.entity.RandomIntelligence;

import java.io.IOException;

public class Game {
    Player playerOne;
    RandomIntelligence playerTwo;

    public Game(Player playerOne, RandomIntelligence playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void startGame() throws IOException, InterruptedException {
        Display display = new Display();
        while (true) {
            display.clearScreen();
            display.displayMap(playerOne.getMap());
            display.displayRadar(playerOne.getRadar());
            if (playerOne.shoot()) {
            MessageHelper.writeMessage(ContentGame.MESSAGE_KEEP_GOING);
                continue;
            }

            while (true) {
                if (playerTwo.shoot()) {
                    MessageHelper.writeMessage(ContentGame.MESSAGE_SHIP_UNDER_FIRE);
                } else {
                    break;
                }
            }
        }
    }
}
