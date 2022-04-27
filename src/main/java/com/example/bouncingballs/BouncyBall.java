package com.example.bouncingballs;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class BouncyBall extends Circle implements Runnable {
    public boolean goesLeft;
    public boolean goesUp;
    public int speed;


    public BouncyBall(double ballRadius, Paint color, boolean goesLeft, boolean goesUp, int speed) {
        super(ballRadius, color);
        this.goesLeft = goesLeft;
        this.goesUp = goesUp;
        this.speed = speed;
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

    public static boolean checkCollision (Shape shape1, Shape shape2){

        if (shape1.getBoundsInParent().intersects(shape2.getBoundsInParent())){
            return true;
        } else{
            return false;
        }
    }




}
