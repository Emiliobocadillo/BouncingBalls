package com.example.bouncingballs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncyBallsApp extends Application {


    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 600;

    private BouncyBall ball1 = new BouncyBall(10, Color.RED, true, true, 5);
    private BouncyBall ball2 = new BouncyBall(10, Color.GREEN, false, true, 5);

    Thread t1 = new Thread(ball1) {
        @Override
        public void run(){
            System.out.println("Thread 1 Working");
            // if the left boolean is true then add -5 to the x coordinate of the ball, else add 5
            ball1.setTranslateX(ball1.getTranslateX() + (ball1.goesLeft ? -50 : 50));
            // if the up boolean is true then add -5 to the y coordinate of the ball, else add 5
            ball1.setTranslateY(ball1.getTranslateY() + (ball1.goesUp ? -50 : 50));

            // collision detection sides
            // ball.getTranslateX() gets the center of the ball
            if (ball1.getTranslateX() - ball1.getRadius() == 0)
                ball1.goesLeft = false;
            else if (ball1.getTranslateX() + ball1.getRadius() == APP_WIDTH)
                ball1.goesLeft = true;


            // collision detection with top and bottom
            if (ball1.getTranslateY() - ball1.getRadius() == 0)
                ball1.goesUp = false;
            else if (ball1.getTranslateY() + ball1.getRadius() == APP_HEIGHT)
                ball1.goesUp = true;
        }
    };

    Thread t2 = new Thread(ball2) {
        @Override
        public void run(){
            System.out.println("Thread 2 Working");
            // if the left boolean is true then add -5 to the x coordinate of the ball, else add 5
            ball2.setTranslateX(ball2.getTranslateX() + (ball2.goesLeft ? -5 : 5));
            // if the up boolean is true then add -5 to the y coordinate of the ball, else add 5
            ball2.setTranslateY(ball2.getTranslateY() + (ball2.goesUp ? -5 : 5));

            // collision detection sides
            // ball.getTranslateX() gets the center of the ball
            if (ball2.getTranslateX() - ball2.getRadius() == 0)
                ball2.goesLeft = false;
            else if (ball2.getTranslateX() + ball2.getRadius() == APP_WIDTH)
                ball2.goesLeft = true;


            // collision detection with top and bottom
            if (ball2.getTranslateY() - ball2.getRadius() == 0)
                ball2.goesUp = false;
            else if (ball2.getTranslateY() + ball2.getRadius() == APP_HEIGHT)
                ball2.goesUp = true;
        }
    };


    // takes care of animating the game
    private Timeline timeline = new Timeline();
    // Whether the game is running or not
    private boolean running = true;



    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(APP_WIDTH, APP_HEIGHT);

        KeyFrame frame = new KeyFrame(Duration.seconds(1), event -> {
            if (!running){
                return;
            }


            t1.start();
            t2.start();
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        root.getChildren().addAll(ball1, ball2);
        return root;
    }

    private void startGame(){

        //private BouncyBall ball1 = new BouncyBall(10, Color.RED, true, true);
        //private BouncyBall ball2 = new BouncyBall(10, Color.GREEN, false, true);
        ball1.setFill(Color.BLUE);
        ball1.setTranslateX(100);
        ball1.setTranslateY(APP_HEIGHT / 2);
        ball2.setTranslateX(700);
        ball2.setTranslateY(APP_HEIGHT / 2);
        timeline.play();
        running = true;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        stage.setTitle("Bouncing Balls");
        stage.setScene(scene);
        stage.show();
        startGame();

    }
    public static void main(String[] args){
        launch(args);
    }

}
