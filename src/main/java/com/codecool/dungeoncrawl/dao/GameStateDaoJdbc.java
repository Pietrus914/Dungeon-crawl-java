package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO  game_state (save_name, current_map, saved_at) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getSaveName());
            statement.setString(2, state.getCurrentMap());
            statement.setTimestamp(3, state.getSavedAt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException();
        }
    }

    @Override
    public void update(GameState state) {

    }

    @Override
    public GameState get(int id) {
        String sql = "SELECT save_name, current_map, saved_at FROM game_state WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            GameState gameState = new GameState(resultSet.getString(1), resultSet.getTimestamp(2), resultSet.getString(3));
            gameState.setId(id);
            return gameState;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GameState> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<GameState> result = new ArrayList<>();
            while (resultSet.next()) {
                GameState gameState = new GameState(resultSet.getString("current_map"), resultSet.getTimestamp("saved_at"),
                        resultSet.getString("save_name"));
                gameState.setId(resultSet.getInt(1));
                result.add(gameState);

            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all resultSet", e);
        }
    }


    @Override
    public List<String> getAllSavedNames(){
        String sql = "SELECT save_name  FROM game_state";

        List<String> savedNames = new ArrayList<>();


        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    savedNames.add(rs.getString("save_name"));
                }
                return savedNames;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}