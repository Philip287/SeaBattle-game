package com.suprun.seaBattleGame.service;

import com.suprun.seaBattleGame.entity.DataPlayers;
import com.suprun.seaBattleGame.entity.User;
import com.suprun.seaBattleGame.exception.ServiceException;
import com.suprun.seaBattleGame.reader.DataReader;
import com.suprun.seaBattleGame.reader.impl.DataReaderImpl;
import com.suprun.seaBattleGame.util.JsonParserUtil;
import com.suprun.seaBattleGame.util.impl.JsonParserUtilImpl;

import static com.suprun.seaBattleGame.entity.User.builder;

/**
 * {@code SaveResult} class is used to save information about User
 *
 * @author Philip Suprun
 */
public class SaveResult {
    private JsonParserUtil jsonParserUtil;
    private DataReader reader;

    public SaveResult() {
        jsonParserUtil = new JsonParserUtilImpl();
        reader = new DataReaderImpl();
    }

    public boolean saveResultOperation(User playerToSave) throws ServiceException {
        DataPlayers dataPlayers = reader.readFile();
        User player = builder()
                .setName(playerToSave.getName())
                .setAge(playerToSave.getAge())
                .setPassword(playerToSave.getPassword())
                .setLesionsCount(playerToSave.getLesionsCount())
                .setVictoriesCount(playerToSave.getVictoriesCount())
                .setBlockTime(playerToSave.getBlockTime())
                .setActive(playerToSave.isActive())
                .serRole(playerToSave.getRole())
                .build();
        dataPlayers.getPlayersList().put(player.getName(), player);
        String jsonString = jsonParserUtil.parseToJson(dataPlayers);
        return reader.saveFile(jsonString);

    }
}
