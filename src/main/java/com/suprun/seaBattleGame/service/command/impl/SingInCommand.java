package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.entity.User;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.reader.DataReader;
import com.suprun.seaBattleGame.reader.impl.DataReaderImpl;
import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.service.GameService;
import com.suprun.seaBattleGame.service.MessageHelper;
import com.suprun.seaBattleGame.service.SaveResult;
import com.suprun.seaBattleGame.service.command.Command;
import com.suprun.seaBattleGame.validator.Validator;
import com.suprun.seaBattleGame.validator.impl.PasswordValidator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * {@code LoginCommand} class that implements the user authorization function.
 *
 * @author Philip Suprun
 */
public class SingInCommand implements Command {
    private SaveResult saveResult;
    private Validator passwordValidator;
    private DataReader reader;
    private Command registerCommand;


    public SingInCommand() {
        saveResult = new SaveResult();
        passwordValidator = PasswordValidator.getInstance();
        reader = new DataReaderImpl();
        registerCommand = new RegisterCommand();
    }

    @Override
    public void execute() throws ServiceException {
        User tempPlayer = playerVerification();
        if (passwordCheck(tempPlayer) && tempPlayer != null) {
            GameService.player = tempPlayer;
        }

    }

    private User playerVerification() throws ServiceException {
        boolean flag = false;
        User tempPlayer = null;
        while (!flag) {
            MessageHelper.writeMessage(ContentGame.PLAYER_NAME_MESSAGE);
            String namePlayer = MessageHelper.readString().trim();
            Map<String, User> playersList = reader.readFile().getPlayersList();
            if (namePlayer != null) {
                tempPlayer = playersList.get(namePlayer);
                if (tempPlayer != null) {
                    flag = true;
                } else {
                    MessageHelper.writeMessage(ContentGame.PLAYER_NOT_EXIST_MESSAGE);
                    registerCommand.execute();
                }

            } else {
                MessageHelper.writeMessage(ContentGame.WRONG_NAME_PLAYER);
            }
        }
        return tempPlayer;
    }


    public boolean passwordCheck(User tempPlayer) throws ServiceException {
        int count = 0;
        boolean flag = false;
        while (count <= 2 && !flag) {
            if (blockingCheck(tempPlayer)) {
                break;
            }
            MessageHelper.writeMessage(ContentGame.PASSWORD_ASK_CODE_MESSAGE);
            String password = MessageHelper.readString().trim();
            if (passwordValidator.validate(password)) {
                if (tempPlayer.getIsActive()) {
                    if (tempPlayer.getPassword().equals(password)) {
                        flag = true;
                    } else {
                        count += 1;
                        MessageHelper.writeMessage(ContentGame.WRONG_PASSWORD_MESSAGE + (4 - count));
                    }
                } else {
                    GameService.exit = true;
                    break;
                }
            } else {
                count += 1;
                MessageHelper.writeMessage(ContentGame.WRONG_PASSWORD_MESSAGE + (4 - count));
            }
        }
        playerBlock(tempPlayer, count);
        return flag;
    }

    private void playerBlock(User tempPlayer, int count) throws ServiceException {
        if (count == 3) {
            Calendar calendar = new GregorianCalendar();
            Long date = calendar.getTimeInMillis();
            tempPlayer.setBlockTime(date);
            tempPlayer.setActive(false);
            saveResult.saveResultOperation(tempPlayer);
            GameService.player = null;
            MessageHelper.writeMessage(ContentGame.BLOCK_PLAYER_MESSAGE);
        }
    }

    private boolean blockingCheck(User tempPlayer) throws ServiceException {
        if (!tempPlayer.getIsActive()) {
            return playerUnblock(tempPlayer);
        } else {
            return false;
        }
    }

    private boolean playerUnblock(User tempPlayer) throws ServiceException {
        Calendar calendar = new GregorianCalendar();
        Long now = calendar.getTimeInMillis();
        if (now >= (tempPlayer.getBlockTime() + ContentGame.DAY_IN_MILLISECONDS)) {
            tempPlayer.setActive(true);
            tempPlayer.setBlockTime(null);
            saveResult.saveResultOperation(tempPlayer);
            MessageHelper.writeMessage(ContentGame.UNBLOCK_PLAYER_MESSAGE);
            return false;
        } else {
            Long remainingTimeToUnlocking = (tempPlayer.getBlockTime() + ContentGame.DAY_IN_MILLISECONDS) - now;
            MessageHelper.writeMessage(ContentGame.INFO_BLOCK_PLAYER_MESSAGE +
                    TimeUnit.MILLISECONDS.toMinutes(remainingTimeToUnlocking) / 60 + ContentGame.HOURS_MESSAGE);
            GameService.player = null;
            return true;
        }
    }
}
