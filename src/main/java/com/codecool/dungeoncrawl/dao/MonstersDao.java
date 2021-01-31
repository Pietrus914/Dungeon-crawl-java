package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonstersModel;

import java.util.List;

public interface MonstersDao {
    void add(MonstersModel monstersModel);
    void update(MonstersModel monstersModel);
    MonstersModel get(int id);
    List<MonstersModel> getAll();
}
