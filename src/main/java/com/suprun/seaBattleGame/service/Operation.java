package com.suprun.seaBattleGame.service;

import com.suprun.seaBattleGame.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Operation} enum is used to choose type of operation
 *
 * @author Philip Suprun
 */
public enum Operation {
    SING_IN,   //to do
    INFO,    //to
    REGISTER,//to
    PLAY,//to
    EXIT;    //to exit

    private static final Logger LOGGER = LogManager.getLogger();

    public static Operation getAllowableOperationByOrdinal(Integer i) throws ServiceException {
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
            default:
                LOGGER.info(ContentGame.MESSAGE);
                throw new ServiceException(ContentGame.MESSAGE);
        }
    }

}

