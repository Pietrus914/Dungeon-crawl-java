package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.BaseModel;
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

    private PlayerDao playerDao;
    private ItemDao itemDao;

    private PlayerModel model;
    private ItemModel itemModel;
    List<ItemModel> floorItemModels;

    private String Json;

    public void setUp(Player player, String saveName, List<Item> items){
        preparePlayerModel(player, saveName);
        prepareFloorItemModels(items, model.getId());
        getItemsJson();
    }

    public void preparePlayerModel(Player player, String saveName){
        model = new PlayerModel(player, saveName);

    }

    public ItemModel prepareItemModel(Item item, int playerId){
        return new ItemModel(item,playerId);
    }

    public void prepareFloorItemModels(List<Item> items, int playerId){
        floorItemModels = new ArrayList<>();
        floorItemModels = items.stream()
                .map(item -> prepareItemModel(item, playerId ))
                .collect(Collectors.toList());
    }




    public void getItemsJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedGson = gson.toJson(floorItemModels);
        serializedGson += gson.toJson(model);
        System.out.println(serializedGson);

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("gameData.json"));
            writer.write(serializedGson);
            writer.close();
        } catch (IOException e) {
            System.out.println("writing failed");
        }

    }





    public void makeGson(ItemModel baseModel) {
        String serializedGson = new Gson().toJson(baseModel);
//        System.out.println(serializedGson);

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("gameData.json"));
            writer.write(serializedGson);
            writer.close();
        } catch (IOException e) {
            System.out.println("writing failed");
        }
    }

}
