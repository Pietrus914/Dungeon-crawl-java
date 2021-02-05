package com.codecool.dungeoncrawl.logic.utils;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.GameJsonManager;
import com.codecool.dungeoncrawl.gui.GameMenu;
import com.codecool.dungeoncrawl.gui.SavePopUp;
import com.codecool.dungeoncrawl.gui.StartPopUp;
import com.codecool.dungeoncrawl.logic.GameWorld;

import java.io.File;

public class LoadManager {

    private File file;
    private File savingFile;
    private String gameSaveName;

    private GameJsonManager jsonManager;
    private GameDatabaseManager dbManager;


    private GameWorld gameWorld;

    public LoadManager(GameDatabaseManager dbManager, GameJsonManager jsonManager,
                        GameWorld gameWorld){
        this.file = null;
        this.savingFile = null;
        this.gameSaveName = null;
        this.jsonManager = jsonManager;
        this.dbManager = dbManager;
        this.gameWorld = gameWorld;

    }


    public void chooseLoadOption(){
        if (file != null){
            jsonManager.importProject(file);
            gameWorld.importWorld(GameWorldFactory.importGame(jsonManager.getGameState(),
                    jsonManager.getItemModels(), jsonManager.getMonsterModels(),
                    jsonManager.getPlayerModel()));
            this.file = null;
        } else if (this.gameSaveName != null){

        } else {
            StartPopUp.display();
            gameWorld.getCurrentMap().getPlayer().setName(StartPopUp.getPlayerName());

        }
    }

    public void chooseSaveOption(){
        if (savingFile != null){
            jsonManager.saveToProjectFile(savingFile);
            savingFile = null;
        } else if (gameSaveName != null){
            dbManager.saveGameState(String.format("map%s", gameWorld.getCurrentMap().getMapNumber()), SavePopUp.getPlayerName(), gameWorld.getCurrentMap().getPlayer());
            dbManager.savePlayer(gameWorld.getCurrentMap().getPlayer());
            dbManager.saveItems(gameWorld.getItemList(), gameWorld.getCurrentMap().getPlayer().getId());
            dbManager.saveMonsters(gameWorld.getMonsterList(), gameWorld.getCurrentMap().getPlayer().getId());
            gameSaveName = null;
        }

    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setGameSaveName(String gameSaveName) {
        this.gameSaveName = gameSaveName;
    }

    public void setSavingFile(File savingFile) {
        this.savingFile = savingFile;
    }

}

