package com.example.bouncingballs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncyBallsWindow extends Application {

    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 600;

    private BouncyBall ball1 = new BouncyBall(20, Color.RED, true, true);
    private BouncyBall ball2 = new BouncyBall(20, Color.GREEN, false, true);
    private Rectangle sealing = new Rectangle();
    private Rectangle floor = new Rectangle();
    private Rectangle left = new Rectangle();
    private Rectangle right = new Rectangle();





    Thread t1 = new Thread(ball1) {
        @Override
        public void run(){

            while(true){

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1 Working");
                // if the left boolean is true then add -5 to the x coordinate of the ball, else add 5
                ball1.setTranslateX(ball1.getTranslateX() + (ball1.goesLeft ? -5 : 5));
                // if the up boolean is true then add -5 to the y coordinate of the ball, else add 5
                ball1.setTranslateY(ball1.getTranslateY() + (ball1.goesUp ? -5 : 5));

                // collision with sealing
                if (ball1.getBoundsInParent().intersects(sealing.getBoundsInParent())){
                    ball1.goesUp = false;
                }

                // collision with floor
                if (ball1.getBoundsInParent().intersects(floor.getBoundsInParent())){
                    ball1.goesUp = true;
                }

                // collision with left
                if (ball1.getBoundsInParent().intersects(left.getBoundsInParent())){
                    ball1.goesLeft = false;
                }

                // collision with left
                if (ball1.getBoundsInParent().intersects(right.getBoundsInParent())){
                    ball1.goesLeft = true;
                }

                // Ball on ball collision
                if (ball1.getBoundsInParent().intersects(ball2.getBoundsInParent())){
                    ball1.setFill(BouncyBall.changeColor());
                    ball2.setFill(BouncyBall.changeColor());

                    ball1.goesLeft = !ball1.goesLeft;
                    ball1.goesUp = !ball1.goesUp;

                    ball2.goesLeft = !ball2.goesLeft;
                    ball2.goesUp = !ball2.goesUp;
                    System.out.println("BALLS TOUCHED EACH OTHER");
                }
            }

        }
    };

    Thread t2 = new Thread(ball2) {
        @Override
        public void run(){

            while(true) {

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                System.out.println("Thread 2 Working");
                // if the left boolean is true then add -5 to the x coordinate of the ball, else add 5
                ball2.setTranslateX(ball2.getTranslateX() + (ball2.goesLeft ? -5 : 5));
                // if the up boolean is true then add -5 to the y coordinate of the ball, else add 5
                ball2.setTranslateY(ball2.getTranslateY() + (ball2.goesUp ? -5 : 5));


                // collision with sealing
                if (ball2.getBoundsInParent().intersects(sealing.getBoundsInParent())){
                    ball2.goesUp = false;
                }

                // collision with floor
                if (ball2.getBoundsInParent().intersects(floor.getBoundsInParent())){
                    ball2.goesUp = true;
                }

                // collision with left
                if (ball2.getBoundsInParent().intersects(left.getBoundsInParent())){
                    ball2.goesLeft = false;
                }

                // collision with left
                if (ball2.getBoundsInParent().intersects(right.getBoundsInParent())){
                    ball2.goesLeft = true;
                }
            }
        }
    };


    // takes care of animating the game
    private Timeline timeline = new Timeline();
    // Whether the game is running or not
    private boolean running = true;


    private void startGame(){

        //private BouncyBall ball1 = new BouncyBall(10, Color.RED, true, true);
        //private BouncyBall ball2 = new BouncyBall(10, Color.GREEN, false, true);
        ball1.setFill(Color.BLUE);
        ball1.setTranslateX(100);
        ball1.setTranslateY(APP_HEIGHT / 2);
        ball1.goesUp = true;
        ball1.goesLeft = true;
        ball2.setTranslateX(700);
        ball2.setTranslateY(APP_HEIGHT / 2);
        ball2.goesUp = true;
        ball2.goesLeft = false;

        sealing.setTranslateX(0);
        sealing.setTranslateY(0);
        sealing.setWidth(APP_WIDTH);
        sealing.setHeight(5);
        sealing.setFill(Color.BLACK);

        floor.setTranslateX(0);
        floor.setTranslateY(APP_HEIGHT-5);
        floor.setWidth(APP_WIDTH);
        floor.setHeight(5);
        floor.setFill(Color.BLACK);

        left.setTranslateX(0);
        left.setTranslateY(0);
        left.setWidth(5);
        left.setHeight(APP_HEIGHT);
        left.setFill(Color.BLACK);

        right.setTranslateX(APP_WIDTH-5);
        right.setTranslateY(0);
        right.setWidth(5);
        right.setHeight(APP_HEIGHT);
        right.setFill(Color.BLACK);

        t1.start();
        t2.start();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(APP_WIDTH, APP_HEIGHT);
        root.getChildren().addAll(ball1, ball2, sealing, floor, left, right);
        Scene scene = new Scene(root);
        stage.setTitle("Bouncing Balls");
        stage.setScene(scene);
        stage.show();
        startGame();

    }
    public static void main(String[] args){
        launch(args);
    }
}
