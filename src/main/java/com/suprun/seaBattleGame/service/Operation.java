package com.suprun.seaBattleGame.service;

import com.suprun.seaBattleGame.entity.User;
import com.suprun.seaBattleGame.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Operation} enum is used to choose type of operation
 *
 * @author Philip Suprun
 */
public enum Operation {
    SING_IN,   //to do sing in game
    INFO,    //to display information about user (for register user)
    REGISTER,//to register new user
    PLAY,//to play in game (for register user)
    EXIT,    //to exit
    INFO_ABOUT_USERS, // display information about register users (for register admin).
    CHANGE_USER_PROFILE; // to block or unblock register users (for register admin).
    private static final Logger LOGGER = LogManager.getLogger();

    public static Operation getAllowableOperationByOrdinal(Integer i) throws ServiceException {
        if (GameService.player != null) {
            switch (i) {
                case 1:
                    return Operation.INFO;
                case 2:
                    return Operation.SING_IN;
                case 3:
                    return Operation.REGISTER;
                case 4:
                    return Operation.PLAY;
                case 5:
                    return Operation.EXIT;
                case 6:
                    if (GameService.player.getRole() == User.UserRole.ADMIN) {
                        return Operation.INFO_ABOUT_USERS;
                    } else {
                        throw new ServiceException(ContentGame.MESSAGE);
                    }
                case 7:
                    if (GameService.player.getRole() == User.UserRole.ADMIN) {
                        return Operation.CHANGE_USER_PROFILE;
                    } else {
                        throw new ServiceException(ContentGame.MESSAGE);
                    }
                default:
                    LOGGER.info(ContentGame.MESSAGE);
                    throw new ServiceException(ContentGame.MESSAGE);
            }
        } else {
            return Operation.SING_IN;
        }
    }

}

