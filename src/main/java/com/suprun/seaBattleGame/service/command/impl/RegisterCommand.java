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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.suprun.seaBattleGame.entity.User.builder;

public class RegisterCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private SaveResult saveResult;
    private Validator passwordValidator;
    private DataReader reader;

    public RegisterCommand() {
        saveResult = new SaveResult();
        passwordValidator = PasswordValidator.getInstance();
        reader = new DataReaderImpl();
    }

    @Override
    public void execute() throws ServiceException {
        User tempPlayer = playerCreateVerification();
        GameService.player = tempPlayer;
    }

    private User playerCreateVerification() throws ServiceException {
        boolean flag = false;
        User tempPlayer = null;
        while (!flag) {
            MessageHelper.writeMessage(ContentGame.PLAYER_FOR_REGISTR_NAME_MESSAGE);
            String namePlayer = MessageHelper.readString().trim();
            int age = 0;
            boolean readInteger = false;
            do {
                MessageHelper.writeMessage(ContentGame.PLAYER_AGE_MESSAGE);
                try {
                    age = Integer.parseInt(MessageHelper.readString().trim());
                    readInteger = true;
                } catch (NumberFormatException e) {
                    MessageHelper.writeMessage(ContentGame.PLAYER_AGE_ERROR_MESSAGE);
                }
            } while (readInteger == false);

            boolean checkPassword = false;
            String password = null;
            while (!checkPassword) {
                MessageHelper.writeMessage(ContentGame.PASSWORD_FOR_REGISTER_CODE_MESSAGE);
                password = MessageHelper.readString().trim();
                checkPassword = passwordCheck(password);
            }
            Map<String, User> playersList = reader.readFile().getPlayersList();
            if (namePlayer != null) {
                User checkPlayer = playersList.get(namePlayer);
                if (checkPlayer != null) {
                    flag = false;
                    MessageHelper.writeMessage(ContentGame.PLAYER_IS_EXISTS);
                }
                flag = true;

                tempPlayer = builder()
                        .setName(namePlayer)
                        .setAge(age)
                        .setPassword(password)
                        .setLesionsCount(0)
                        .setVictoriesCount(0)
                        .setActive(true)
                        .serRole(User.UserRole.USER)
                        .build();

                if (saveResult.saveResultOperation(tempPlayer)) {
                    MessageHelper.writeMessage(tempPlayer.getName() + ContentGame.GOOD_REGISTER_MESSAGE);
                }
            } else {
                flag = false;
                MessageHelper.writeMessage(ContentGame.PLAYER_NAME_MESSAGE);
            }
        }
        return tempPlayer;
    }

    public boolean passwordCheck(String password) {
        boolean flag = false;
        if (passwordValidator.validate(password)) {
            flag = true;
        } else {
            MessageHelper.writeMessage(ContentGame.WRONG_PASSWORD_MESSAGE);
        }
        return flag;
    }
}
