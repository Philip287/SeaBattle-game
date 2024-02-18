package com.suprun.seaBattleGame.service.command.impl;

import com.suprun.seaBattleGame.entity.User;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.reader.DataReader;
import com.suprun.seaBattleGame.reader.impl.DataReaderImpl;
import com.suprun.seaBattleGame.service.*;
import com.suprun.seaBattleGame.service.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.suprun.seaBattleGame.entity.User.builder;

public class ChangeProfileUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private DataReader reader;
    private SaveResult saveResult;
    private User tempUser;

    public ChangeProfileUserCommand() {
        reader = new DataReaderImpl();
        saveResult = new SaveResult();
        tempUser = new User();
    }

    @Override
    public void execute() throws ServiceException {
        if (GameService.player.getRole() == User.UserRole.ADMIN) {
            Map<String, User> playersList = reader.readFile().getPlayersList();
            playersList.forEach((s, user) -> printUserInformation(user));
            boolean work = true;
            do {
                MessageHelper.writeMessage(ContentGame.MESSAGE_CHOOSE_USER_FOR_CHANGE);
                String answer = MessageHelper.readString();
                if (answer != null) {
                    tempUser = playersList.get(answer);
                    if (tempUser != null) {
                        tempUser = changeUserData();
                        work = false;
                    }
                } else {
                    MessageHelper.writeMessage(ContentGame.WRONG_NAME_PLAYER);
                }
            } while (work);
            saveResult.saveResultOperation(tempUser);
        } else {
            MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
        }
    }

    private User changeUserData() {
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_NAME + tempUser.getName());
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_AGE + tempUser.getAge());

        MessageHelper.writeMessage(ContentGame.MESSAGE_VICTORIES_COUNT + tempUser.getVictoriesCount());
        int newVictoriesCount = getNewVictoriesCount();

        MessageHelper.writeMessage(ContentGame.MESSAGE_LESIONS_COUNT + tempUser.getLesionsCount());
        int newLesionCount = getNewLesionCount();

        MessageHelper.writeMessage(ContentGame.MESSAGE_BLOCK_TIME + tempUser.getBlockTime());
        MessageHelper.writeMessage(ContentGame.MESSAGE_IS_ACTIVE + tempUser.getIsActive());
        changeActive();

        MessageHelper.writeMessage(ContentGame.MESSAGE_ROLE + tempUser.getRole());
        User.UserRole newRole = getNewUserRole();

        User newUser = builder()
                .setName(tempUser.getName())
                .setAge(tempUser.getAge())
                .setPassword(tempUser.getPassword())
                .setLesionsCount(newLesionCount)
                .setVictoriesCount(newVictoriesCount)
                .setBlockTime(tempUser.getBlockTime())
                .setActive(tempUser.getIsActive())
                .serRole(newRole)
                .build();

        return newUser;
    }

    private int getNewVictoriesCount() {
        boolean exit = false;
        int newVictoriesCount = 0;
        while (!exit) {
            try {
                MessageHelper.writeMessage(ContentGame.MESSAGE_CHANGE_VICTORIES_COUNT);
                newVictoriesCount = Integer.parseInt(MessageHelper.readString());
                exit = true;
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error("Exception in MessageHelper.askOperation() method find ", e);
                MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
            }
        }
        return newVictoriesCount;
    }

    private int getNewLesionCount() {
        boolean exit = false;
        int newLesionCount = 0;
        while (!exit) {
            try {
                MessageHelper.writeMessage(ContentGame.MESSAGE_CHANGE_LESION_COUNT);
                newLesionCount = Integer.parseInt(MessageHelper.readString());
                exit = true;
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error("Exception in MessageHelper.askOperation() method find ", e);
                MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
            }
        }
        return newLesionCount;
    }

    private void changeActive() {
        boolean exit = false;
        while (!exit) {
            try {
                MessageHelper.writeMessage(ContentGame.MESSAGE_CHANGE_ACTIVE);
                int operationType = Integer.parseInt(MessageHelper.readString());
                exit = true;
                if (operationType == 1) {
                    Calendar calendar = new GregorianCalendar();
                    tempUser.setActive(true);
                    tempUser.setBlockTime(calendar.getTimeInMillis());
                    exit = true;
                }
                if (operationType == 2) {
                    tempUser.setActive(true);
                    tempUser.setBlockTime(0L);
                    exit = true;
                } else {
                    MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
                }
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error("Exception in MessageHelper.askOperation() method find ", e);
                MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
            }
        }
    }

    private User.UserRole getNewUserRole() {
        User.UserRole role = null;
        boolean exit = false;
        while (!exit) {
            try {
                MessageHelper.writeMessage(ContentGame.MESSAGE_CHANGE_ROLE);
                int operationType = Integer.parseInt(MessageHelper.readString());
                exit = true;
                if (operationType == 1) {
                    role = User.UserRole.ADMIN;
                    exit = true;
                } else if (operationType == 2) {
                    role = User.UserRole.USER;
                    exit = true;
                } else {
                    MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
                }
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error("Exception in MessageHelper.askOperation() method find ", e);
                MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
            }
        }
        return role;
    }

    public void printUserInformation(User user) {
        MessageHelper.writeMessage(ContentGame.MESSAGE_INFORMATION);
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_NAME + user.getName());
        MessageHelper.writeMessage(ContentGame.MESSAGE_PLAYER_AGE + user.getAge());
        MessageHelper.writeMessage(ContentGame.MESSAGE_VICTORIES_COUNT + user.getVictoriesCount());
        MessageHelper.writeMessage(ContentGame.MESSAGE_LESIONS_COUNT + user.getLesionsCount());
        MessageHelper.writeMessage(ContentGame.MESSAGE_BLOCK_TIME);
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
