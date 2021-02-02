package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ItemDaoJdbc implements ItemDao {
    private DataSource dataSource;
    private PlayerDao playerDao;

    public ItemDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
//        this.playerDao = playerDao;
    }

    @Override
    public void add(ItemModel item) {
        String sql = "INSERT INTO items (id, item_name, message, x, y, points, " +
                "inventory, game_state_id, map_number) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,item.getId());
            statement.setString(2,item.getName());
            statement.setString(3,item.getMessage());
            statement.setInt(4,item.getX());
            statement.setInt(5,item.getY());
            statement.setInt(6,item.getPoints());
            statement.setBoolean(7, item.isInInventory());
            statement.setInt(8, item.getGameStateId());
            statement.setInt(9, item.getMapNumber());

            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(ItemModel item) {

    }

    @Override
    public ItemModel get(int id) {
        String sql = "SELECT id, item_name, message, x, y, points, " +
                "inventory, game_state_id, map_number  FROM items WHERE id = ?";
        ItemModel itemModel = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,id);


            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    itemModel = new ItemModel(rs.getInt("id"),
                                                rs.getString("item_name"),
                                                rs.getString("message"),
                                                rs.getInt("x"),
                                                rs.getInt("y"),
                                                rs.getInt("points"),
                                                rs.getBoolean("inventory"),
                                                rs.getInt("game_state_id"),
                                                rs.getInt("map_number"));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return itemModel;
    }

    @Override
    public List<ItemModel> getAllForInventory() {
        return null;
    }

    @Override
    public List<ItemModel> getAllOnFloor() {
        return null;
    }
}
