package com.suprun.seaBattleGame.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.suprun.seaBattleGame.entity.DataPlayers;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.util.JsonParserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonParserUtilImpl implements JsonParserUtil {
    private static JsonParserUtil instance;
    private static Gson gson = new GsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public static JsonParserUtil getInstance() {
        if (instance == null) {
            instance = new JsonParserUtilImpl();
        }
        return instance;
    }

    @Override
    public DataPlayers parseFromJson(String jsonPlayers) throws ServiceException {
        DataPlayers players = null;
        try {
            players = gson.fromJson(jsonPlayers, DataPlayers.class);
        } catch (JsonParseException e) {
            LOGGER.error("Could not parse");
            throw new ServiceException("Could not parse" + e);
        }

        return players;
    }

    @Override
    public String parseToJson(DataPlayers dataPlayers) throws ServiceException {
        String jsonString;
        try {
            jsonString = gson.toJson(dataPlayers, DataPlayers.class);
        } catch (JsonParseException e) {
            LOGGER.error("Could not parse");
            throw new ServiceException("Could not parse" + e);
        }
        return jsonString;
    }
}
