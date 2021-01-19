package com.codecool.dungeoncrawl.gui;

import javafx.scene.text.Text;

public class StatusLine extends Text {
    private static int counter = 0;

    public StatusLine(String message){
        this.setText(message);
        this.setWrappingWidth(190);
    }


    public void setMessage(String message) {
        if (counter < 7){
            this.setText(message);
        } else {
            this.setText("");

        }

    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        StatusLine.counter = counter;
    }
}
