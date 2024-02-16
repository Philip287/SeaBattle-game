package com.suprun.seaBattleGame.reader.impl;

import com.suprun.seaBattleGame.entity.DataPlayers;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.reader.DataReader;
import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.util.JsonParserUtil;
import com.suprun.seaBattleGame.util.impl.JsonParserUtilImpl;
import com.suprun.seaBattleGame.validator.Validator;
import com.suprun.seaBattleGame.validator.impl.FilePathValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code DataReaderImpl} class for read and save class DataPlayers to file data.txt.
 *
 * @author Philip Suprun
 */
public class DataReaderImpl implements DataReader {
    private static JsonParserUtil jsonParserUtil = JsonParserUtilImpl.getInstance();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public DataPlayers readFile() throws ServiceException {
        Validator fileValidator = FilePathValidator.getInstance();
        if (!fileValidator.validate(ContentGame.PATH)) {
            LOGGER.fatal(ContentGame.PATH_ERR);
            throw new ServiceException();
        }

        ArrayList<String> lines;
        Path pathFile = Paths.get(ContentGame.PATH);
        try (Stream<String> lineStream = Files.lines(pathFile)) {
            lines = lineStream.collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            LOGGER.error(ContentGame.READING_FAIL);
            throw new ServiceException(ContentGame.READING_FAIL, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : lines) {
            stringBuilder.append(s).append("\n");
        }
        return jsonParserUtil.parseFromJson(stringBuilder.toString());
    }

    @Override
    public boolean saveFile(String content) throws ServiceException {
        Path pathFile = Paths.get(ContentGame.PATH);
        boolean flag = false;
        try {
            Path path = Files.writeString(pathFile, content);
            if (path != null) {
                flag = true;
            }
        } catch (IOException e) {
            LOGGER.error(ContentGame.READING_FAIL);
            throw new ServiceException(ContentGame.READING_FAIL, e);
        }
        return flag;
    }
}
