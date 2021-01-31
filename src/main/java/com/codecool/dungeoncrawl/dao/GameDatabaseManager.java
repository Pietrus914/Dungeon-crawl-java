package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.MonstersModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private MonstersDao monstersDao;
    private PlayerModel model;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        monstersDao = new MonsterDaoJdbc(dataSource);
    }

    public void savePlayer(Player player, String saveName) {
        model = new PlayerModel(player, saveName);
        playerDao.add(model);
    }

    public void savaGameState(String currentMap) {
        GameState gameState = new GameState(currentMap, new Timestamp(System.currentTimeMillis()), model);
        gameStateDao.add(gameState);
    }

    public void saveMonsters(Actor actor, int mapNumber) {
        MonstersModel monstersModel = new MonstersModel(actor, mapNumber, model);
        monstersModel.setId(actor.getId());
        monstersDao.add(monstersModel);

    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();


        // update for your data base
        String serverName = System.getenv("LOCAL_HOST");
        int portNumber = 5432;
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setServerName(serverName);
        dataSource.setPortNumber(portNumber);
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
