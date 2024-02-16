package com.suprun.seaBattleGame.util;

import com.suprun.seaBattleGame.entity.DataPlayers;
import com.suprun.seaBattleGame.exception.ServiceException;

/**
 * {@code JsonParserUtil} interface provides functionality for parse JSON data.
 *
 * @author Philip Suprun
 */
public interface JsonParserUtil {
    /**
     * @param jsonPlayers containing json string Players object
     * @return object class DataCard
     */
    DataPlayers parseFromJson(String jsonPlayers) throws ServiceException;

    /**
     * @param dataPlayers containing DataPlayers object
     * @return json String from that object
     */
    String parseToJson(DataPlayers dataPlayers) throws ServiceException;
}
