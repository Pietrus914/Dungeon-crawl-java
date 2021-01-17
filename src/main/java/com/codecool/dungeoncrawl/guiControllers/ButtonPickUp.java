package com.codecool.dungeoncrawl.guiControllers;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class ButtonPickUp extends Button {

    public ButtonPickUp(){
        super("Add to inventory");
        this.setTooltip(new Tooltip("Add item to your inventory"));
        this.setOnAction(ignoreEvent -> {
            System.out.println("\n" + "\n" + ">>>>>>>>>>>>>>>>>>>>Button PickUp pressed");
        });

    }

}
