package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.entity.User;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.reader.DataReader;
import com.suprun.seaBattleGame.reader.impl.DataReaderImpl;
import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.service.GameService;
import com.suprun.seaBattleGame.service.MessageHelper;
import com.suprun.seaBattleGame.service.command.Command;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InfoUsersCommand implements Command {
    private DataReader reader;

    public InfoUsersCommand() {
        reader = new DataReaderImpl();
    }

    @Override
    public void execute() throws ServiceException {
        if (GameService.player.getRole() == User.UserRole.ADMIN) {
            Map<String, User> playersList = reader.readFile().getPlayersList();
            playersList.forEach((s, user) -> printUserInformation(user));
        } else {
            MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
        }
    }

    public void printUserInformation(User user) {
        MessageHelper.writeMessage(ContentGame.MESSAGE_INFORMATION);
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_NAME + user.getName());
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_AGE + user.getAge());
        MessageHelper.writeMessage(ContentGame.MESSAGE_VICTORIES_COUNT + user.getVictoriesCount());
        MessageHelper.writeMessage(ContentGame.MESSAGE_LESIONS_COUNT + user.getLesionsCount());
        showUnblockTime(user);
        MessageHelper.writeMessage(ContentGame.MESSAGE_IS_ACTIVE + user.getIsActive());
        MessageHelper.writeMessage(ContentGame.MESSAGE_ROLE + user.getRole());
        MessageHelper.writeMessage("\n");
    }

    private void showUnblockTime(User user) {
        Calendar calendar = new GregorianCalendar();
        Long now = calendar.getTimeInMillis();
        if (user.getBlockTime() > 0) {
            Long remainingTimeToUnlocking = (user.getBlockTime() + ContentGame.DAY_IN_MILLISECONDS) - now;
            MessageHelper.writeMessage(ContentGame.INFO_UNBLOCK_PLAYER_MESSAGE +
                    TimeUnit.MILLISECONDS.toMinutes(remainingTimeToUnlocking) / 60 + ContentGame.HOURS_MESSAGE);
        } else {
            MessageHelper.writeMessage(ContentGame.INFO_NOT_BLOCK_PLAYER_MESSAGE);
        }
    }
}
