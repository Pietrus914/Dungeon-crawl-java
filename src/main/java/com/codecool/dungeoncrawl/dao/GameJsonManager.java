package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameJsonManager {

    transient private Player player;

    private PlayerModel playerModel;
    List<ItemModel> itemModels;


    public GameJsonManager(Player player){
        this.player = player;
    }


    public void setUp(String saveName, List<Item> items){
        preparePlayerModel(player, saveName);
        prepareFloorItemModels(items, playerModel.getId());
        String serializedJson = getItemsJson();
        try {
            writeToJsonFile(serializedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void preparePlayerModel(Player player, String saveName){
        playerModel = new PlayerModel(player, saveName);

    }

    public ItemModel prepareItemModel(Item item, int playerId, Player player){
        return new ItemModel(item,playerId, player);
    }

    public void prepareFloorItemModels(List<Item> items, int playerId){
        itemModels = new ArrayList<>();
        itemModels = items.stream()
                .map(item -> prepareItemModel(item, playerId,player ))
                .collect(Collectors.toList());
    }




    public String getItemsJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedGson = gson.toJson(this);

        System.out.println(serializedGson);
        return serializedGson;


    }

    private void writeToJsonFile(String jsonString) throws IOException {

            BufferedWriter writer = new BufferedWriter(new FileWriter("gameData.json"));
            writer.write(jsonString);
            writer.close();


    }


}
