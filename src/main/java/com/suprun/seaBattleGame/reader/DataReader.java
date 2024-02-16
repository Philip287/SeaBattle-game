package com.suprun.seaBattleGame.reader;

import com.suprun.seaBattleGame.entity.DataPlayers;
import com.suprun.seaBattleGame.exception.ServiceException;

import java.io.IOException;

public interface DataReader {
    DataPlayers readFile() throws ServiceException;

    boolean saveFile(String content) throws ServiceException;
}
