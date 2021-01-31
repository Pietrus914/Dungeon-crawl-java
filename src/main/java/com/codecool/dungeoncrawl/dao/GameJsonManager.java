package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameJsonManager {

    private PlayerDao playerDao;
    private ItemDao itemDao;

    private PlayerModel model;
    private ItemModel itemModel;

    public void prepareModels(){

    }







    public void makeGson(BaseModel baseModel) {
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
