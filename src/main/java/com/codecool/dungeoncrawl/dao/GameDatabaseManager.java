package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class GameDatabaseManager {

    private PlayerDao playerDao;
    private ItemDao itemDao;


    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        itemDao = new ItemDaoJdbc(dataSource);
    }

    public void savePlayer(Player player, String saveName) {
        PlayerModel model = new PlayerModel(player, saveName);
        playerDao.add(model);
    }

    private ItemModel getItemModel(Item item){
        // TODO : pass the right gameStateId
        ItemModel itemModel = new ItemModel(item, 8888888);
        return itemModel;
    }

    public void saveItems(List<Item> itemList){
        for (Item item : itemList){
            ItemModel itemModel = getItemModel(item);
            itemDao.add(itemModel);
        }
    }

    public void updateItems(List<Item> itemList){
        for (Item item : itemList){
            ItemModel itemModel = getItemModel(item);
            // TODO : pass the right gameStateId
            int gameStateId = 8888888;
            itemDao.update(itemModel, gameStateId);
        }
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
