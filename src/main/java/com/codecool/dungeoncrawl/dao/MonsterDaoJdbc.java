package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonstersModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MonsterDaoJdbc implements MonstersDao {
    private DataSource dataSource;

    public MonsterDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(MonstersModel monstersModel) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "INSERT INTO monsters (id, monster_name, hp, x, y, player_id, map_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, monstersModel.getId());
            statement.setString(2, monstersModel.getMonsterName());
            statement.setInt(3, monstersModel.getHp());
            statement.setInt(4, monstersModel.getX());
            statement.setInt(5, monstersModel.getY());
            statement.setInt(6, monstersModel.getPlayer().getId());
            statement.setInt(7, monstersModel.getMapNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void update(MonstersModel monstersModel) {

    }

    @Override
    public MonstersModel get(int id) {
        return null;
    }

    @Override
    public List<MonstersModel> getAll() {
        return null;
    }
}
