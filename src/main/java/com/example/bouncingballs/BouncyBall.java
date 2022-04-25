package com.example.bouncingballs;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.Random;

public class BouncyBall extends Circle implements Runnable {
    public boolean goesLeft;
    public boolean goesUp;


    public BouncyBall(double ballRadius, Paint color, boolean goesLeft, boolean goesUp) {
        super(ballRadius, color);
        this.goesLeft = goesLeft;
        this.goesUp = goesUp;
    }

    @Override
    public void run() {

    }

    public static Color changeColor(){
        Random random = new Random();
        int r = random.nextInt(255+1);
        int g = random.nextInt(255+1);
        int b = random.nextInt(255+1);
        return Color.rgb(r,g,b);
    }

  /*  @Override
    public void run() {

        for (int i = 0; i <= 5; i++){
            System.out.println("Thread #" + threadNumber + " : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread #" + threadNumber + " is now finished");
    }*/



}
