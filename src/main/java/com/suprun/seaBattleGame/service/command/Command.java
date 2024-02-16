package com.suprun.seaBattleGame.service.command;

import com.suprun.seaBattleGame.exception.ServiceException;

/**
 * {@code Command} is a functional interface implementing instances of which
 * execution command.
 *
 * @author Philip Suprun
 */
public interface Command {
    void execute() throws ServiceException;
}
