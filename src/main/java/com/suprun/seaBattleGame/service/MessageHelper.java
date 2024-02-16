package com.suprun.seaBattleGame.service;

import com.suprun.seaBattleGame.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * {@code MessageHelper} class is used to display messages in ATM monitor
 *
 * @author Philip Suprun
 */
public class MessageHelper {
    private static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger LOGGER = LogManager.getLogger();

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws ServiceException {
        String line = ContentGame.LINE;
        try {
            line = bufferReader.readLine();
        } catch (IOException e) {
            LOGGER.error("Exception in MessageHelper.readString() method find ", e);
            throw new ServiceException(e);
        }

        return line;
    }

    public static Operation askOperation() {
        boolean exit = false;
        while (!exit) {
            try {
                writeMessage(ContentGame.MESSAGE);
                int operationType = Integer.parseInt(readString());
                exit = true;
                return Operation.getAllowableOperationByOrdinal(operationType);
            } catch (NumberFormatException | ServiceException e) {
                LOGGER.error("Exception in MessageHelper.askOperation() method find ", e);
                MessageHelper.writeMessage(ContentGame.UNKNOWN_OPERATION_MESSAGE);
            }
        }
        return askOperation();
    }
}
