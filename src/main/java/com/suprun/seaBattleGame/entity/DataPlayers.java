package com.suprun.seaBattleGame.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code DataPlayers} class represents a DataPlayers entity.
 *
 * @author Philip Suprun
 */
public class DataPlayers {
    private Map<String, Player> playersList = new HashMap<>();

    private DataPlayers() {

    }

    public static DataPlayersBuilder builder() {
        return new DataPlayers().new DataPlayersBuilder();
    }

    public Map<String, Player> getPlayersList() {
        return playersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataPlayers that = (DataPlayers) o;

        return playersList != null ? playersList.equals(that.playersList) : that.playersList == null;
    }

    @Override
    public int hashCode() {
        return playersList != null ? playersList.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DataPlayers{");
        sb.append("playersList=").append(playersList);
        sb.append('}');
        return sb.toString();
    }

    public class DataPlayersBuilder {

        private DataPlayersBuilder() {

        }

        public DataPlayersBuilder setPlayerList(Map<String, Player> playerList) {
            DataPlayers.this.playersList = playerList;
            return this;
        }

        public DataPlayersBuilder of(DataPlayers dataPlayers) {
            DataPlayers.this.playersList = playersList;
            return this;
        }

        public DataPlayers build() {
            return DataPlayers.this;
        }

    }

    public Player addPlayer(Player player) {
        String name = player.getName();
        return playersList.put(name, player);
    }
}
