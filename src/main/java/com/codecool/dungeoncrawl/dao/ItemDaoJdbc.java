package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ItemDaoJdbc implements ItemDao {
    private DataSource dataSource;
    private PlayerDao playerDao;

    public ItemDaoJdbc(DataSource dataSource, PlayerDao playerDao){
        this.dataSource = dataSource;
        this.playerDao = playerDao;
    }

    @Override
    public void add(ItemModel item) {
        String sql = "INSERT INTO items (player_id, map_number, item_name, message, x, y, points, inventory) VALUES (?,?,?,?,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,item.getPlayerId());
            statement.setInt(2, item.getMapNumber());
            statement.setString(3,item.getName());
            statement.setString(4,item.getMessage());
            statement.setInt(5,item.getX());
            statement.setInt(6,item.getY());
            statement.setInt(7,item.getPoints());
            statement.setBoolean(8,item.isInInventory());
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(ItemModel item) {

    }

    @Override
    public ItemModel get(int id) {
        String sql = "SELECT id,player_id, map_number, item_name, message, x, y, points  FROM items WHERE id = ?";
        ItemModel itemModel = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,id);


            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    itemModel = new ItemModel(rs.getInt("player_id"),
                                                rs.getInt("map_number"),
                                                rs.getString("item_name"),
                                                rs.getString("message"),
                                                rs.getInt("x"),
                                                rs.getInt("y"),
                                                rs.getInt("points"),
                                                rs.getBoolean("inventory"));
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
