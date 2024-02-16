package com.suprun.seaBattleGame.service.command;

import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.service.Operation;
import com.suprun.seaBattleGame.service.command.impl.ExitCommand;
import com.suprun.seaBattleGame.service.command.impl.InfoCommand;
import com.suprun.seaBattleGame.service.command.impl.SingInCommand;
import com.suprun.seaBattleGame.service.command.impl.RegisterCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code CommandExecutor} class provides logic for executing command.
 *
 * @author Philip Suprun
 */
public class CommandExecutor {
    private static final Map<Operation, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(Operation.SING_IN, new SingInCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
        commandMap.put(Operation.REGISTER, new RegisterCommand());
        commandMap.put(Operation.PLAY, new RegisterCommand());

    }

    private CommandExecutor() {

    }

    public static void execution(Operation operation) throws ServiceException {
        commandMap.get(operation).execute();
    }

}
