package com.codecool.dungeoncrawl.gui;

import javafx.scene.text.Text;

public class StatusLine extends Text {
//    private String message;
    private static int counter = 0;

    public StatusLine(String message){
        this.setText(message);

    }



//    public String getMessage() {
//        return message;
//    }

    public void setMessage(String message) {
        if (counter < 3){
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
