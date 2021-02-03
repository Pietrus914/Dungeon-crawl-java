package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameJsonManager {

    private GameState gameState;
    private PlayerModel playerModel;
    private List<ItemModel> itemModels;

    private GameStateDaoJson gameDao;


    public GameJsonManager(String currentMap, String saveName, Player player, List<Item> items ){
        this.gameState = prepareGameState(currentMap, saveName);
        this.playerModel = preparePlayerModel(player);
        this.itemModels = prepareItemModels(items);

        this.gameDao = new GameStateDaoJson();
    }


    public void saveGame(){

//        gameDao.add();
    }



    private GameState prepareGameState(String currentMap, String saveName){
        GameState gameState = new GameState(currentMap, new Timestamp(System.currentTimeMillis()), saveName);
        return gameState;
    }

    private PlayerModel preparePlayerModel(Player player){
        return new PlayerModel(player);
    }

    private ItemModel prepareItemModel(Item item){
        return new ItemModel(item, 0);
    }

    private List<ItemModel> prepareItemModels(List<Item> items){
        itemModels = new ArrayList<>();
        itemModels = items.stream()
                .map(item -> prepareItemModel(item))
                .collect(Collectors.toList());
        return itemModels;
    }


    private String serialize(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedGson = gson.toJson(this);
        System.out.println(serializedGson);

        return serializedGson;
    }

    private void deserialize(String serializedString){
        GameJsonManager deserializedOutput = new Gson().fromJson(serializedString, GameJsonManager.class);
        System.out.println(deserializedOutput.toString());
    }






    public void saveToProjectFile(){
        String serializedJson = serialize();
        try {
            writeToJsonFile(serializedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Now deserialization....");
        getDeserializedModels();
    }

    private void writeToJsonFile(String jsonString) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("gameData.json"));
        writer.write(jsonString);
        writer.close();
    }

    private String getFromJsonFile() throws IOException {
        FileReader reader = new FileReader("gameData.json");
        StringBuilder content = new StringBuilder();
        int nextChar;
        while ((nextChar = reader.read()) != -1) {
            content.append((char) nextChar);
        }
        return String.valueOf(content);
    }


    private void getDeserializedModels(){
        try {
            String serializedString = getFromJsonFile();
            deserialize(serializedString);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }



    @Override
    public String toString(){
        String fields = this.playerModel.getPlayerName() + " " +
                this.playerModel.getArmor() + " " +
                this.playerModel.getStrength() +" " +
                this.playerModel.getX() +" "
//                this.playerModel.getGameStateId() +"\n "

                ;

        String item = "";

        for (ItemModel im : itemModels){
            item += im.getName() + " " + im.getMapNumber() + " " + im.getX() + " " + im.isInInventory() + "\n";
        }

        return fields + item;
    }



}
