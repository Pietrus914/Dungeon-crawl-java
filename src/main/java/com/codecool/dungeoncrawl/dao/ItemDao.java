package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.List;

public interface ItemDao {

    void add(ItemModel item);
    void update(ItemModel item , int gameStateId);
    ItemModel get(int id);
    List<ItemModel> getAll();
    List<ItemModel> getAllForInventory();
    List<ItemModel> getAllOnFloor();

}
