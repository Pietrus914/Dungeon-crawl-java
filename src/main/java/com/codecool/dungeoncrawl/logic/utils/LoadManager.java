package com.codecool.dungeoncrawl.logic.utils;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.GameJsonManager;
import com.codecool.dungeoncrawl.logic.GameWorld;

import java.io.File;

public class LoadManager {

    private File file;
    private String gameSaveName;

    private GameJsonManager jsonManager;
    private GameDatabaseManager dbManager;

    private GameWorld gameWorld;

    public LoadManager(GameDatabaseManager dbManager, GameJsonManager jsonManager,
                       GameWorld gameWorld){
        this.file = null;
        this.gameSaveName = null;
        this.jsonManager = jsonManager;
        this.dbManager = dbManager;
        this.gameWorld = gameWorld;

    }


    public void chooseOption(){
        if (file != null){
            jsonManager.importProject(file);
            gameWorld.importWorld(GameWorldFactory.importGame(jsonManager.getGameState(),
                    jsonManager.getItemModels(), jsonManager.getMonsterModels(),
                    jsonManager.getPlayerModel()));


        } else if (this.gameSaveName != null){

        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setGameSaveName(String gameSaveName) {
        this.gameSaveName = gameSaveName;
    }
}

