package com.example.bouncingballs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BouncyBallsWindow extends Application {

    private enum UserAction{
        NONE, GO_UP, GO_DOWN
    }

    private enum UserAction2{
        NONE, GO_UP, GO_DOWN
    }


    private UserAction action = UserAction.NONE;
    private UserAction2 action2 = UserAction2.NONE;


    // takes care of animating the game
    private Timeline timeline = new Timeline();
    // Whether the game is running or not
    private boolean running = true;


    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 600;
    private static final int BAT_W = 20;
    private static final int BAT_H = 100;

    private Label lbl1 = new Label("Ball 1");
    private Label lbl2 = new Label("Ball 2");
    private Label lbl3 = new Label("Ball 3");

    private BouncyBall ball1 = new BouncyBall(20, Color.RED, true, true, 4);
    private BouncyBall ball2 = new BouncyBall(20, Color.BLUE, true, true, 4);
    private BouncyBall ball3 = new BouncyBall(20, Color.GREEN, true, true, 4);

    private Rectangle sealing = new Rectangle();
    private Rectangle floor = new Rectangle();
    private Rectangle left = new Rectangle();
    private Rectangle right = new Rectangle();
    private Rectangle player1 = new Rectangle(BAT_W, BAT_H);
    private Rectangle player2 = new Rectangle(BAT_W, BAT_H);

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    private Label scoreLabel = new Label("PLAYER ONE SCORE: " + scorePlayer1 + "\t\tPLAYER TWO SCORE: " + scorePlayer2);

    private void follow(Label label, BouncyBall ball) {

            double tx = ball.getTranslateX();
            double ty = ball.getTranslateY();
            label.setTranslateX(tx);
            label.setTranslateY(ty);
    }



    Thread t1 = new Thread(ball1) {
        @Override
        public void run(){

            while(true){

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                follow(lbl1, ball1);
                //System.out.println("Thread 1 Working");
                // if the left boolean is true then add -5 to the x coordinate of the ball, else add 5
                ball1.setTranslateX(ball1.getTranslateX() + (ball1.goesLeft ? -ball1.speed : ball1.speed));
                // if the up boolean is true then add -5 to the y coordinate of the ball, else add 5
                ball1.setTranslateY(ball1.getTranslateY() + (ball1.goesUp ? -ball1.speed : ball1.speed));


                // collision with sealing
                if (BouncyBall.checkCollision(ball1,sealing)){
                    ball1.goesUp = false;
                }

                // collision with floor
                if (BouncyBall.checkCollision(ball1,floor)){
                    ball1.goesUp = true;
                }

                // collision with left
                if (BouncyBall.checkCollision(ball1, left)){
                    scorePlayer2+=1;
                    ball1.goesLeft = false;
                }

                // collision with right
                if (BouncyBall.checkCollision(ball1, right)){
                    scorePlayer1+=1;
                    ball1.goesLeft = true;
                }

                // Ball on ball collision
                if (BouncyBall.checkCollision(ball1,ball2)){
                    ball1.setFill(BouncyBall.changeColor());
                    ball2.setFill(BouncyBall.changeColor());

                    ball1.goesLeft = !ball1.goesLeft;
                    ball1.goesUp = !ball1.goesUp;

                    ball2.goesLeft = !ball2.goesLeft;
                    ball2.goesUp = !ball2.goesUp;
                    System.out.println("BALLS TOUCHED EACH OTHER");
                }

                if (BouncyBall.checkCollision(ball1,ball3)){
                    ball1.setFill(BouncyBall.changeColor());
                    ball3.setFill(BouncyBall.changeColor());

                    ball1.goesLeft = !ball1.goesLeft;
                    ball1.goesUp = !ball1.goesUp;

                    ball3.goesLeft = !ball3.goesLeft;
                    ball3.goesUp = !ball3.goesUp;
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
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    follow(lbl2, ball2);

                System.out.println("Thread 2 Working");
                // if the left boolean is true then add -5 to the x coordinate of the ball, else add 5
                ball2.setTranslateX(ball2.getTranslateX() + (ball2.goesLeft ? -ball2.speed : ball2.speed));
                // if the up boolean is true then add -5 to the y coordinate of the ball, else add 5
                ball2.setTranslateY(ball2.getTranslateY() + (ball2.goesUp ? -ball2.speed : ball2.speed));


                // collision with sealing
                if (BouncyBall.checkCollision(ball2,sealing)){
                    ball2.goesUp = false;
                }

                // collision with floor
                if (BouncyBall.checkCollision(ball2,floor)){
                    ball2.goesUp = true;
                }

                // collision with left
                if (BouncyBall.checkCollision(ball2, left)){
                    scorePlayer2+=1;
                    ball2.goesLeft = false;
                }

                // collision with right
                if (BouncyBall.checkCollision(ball2, right)){
                    scorePlayer1+=1;
                    ball2.goesLeft = true;
                }

                if (BouncyBall.checkCollision(ball2,ball3)){
                    ball2.setFill(BouncyBall.changeColor());
                    ball3.setFill(BouncyBall.changeColor());

                    ball2.goesLeft = !ball2.goesLeft;
                    ball2.goesUp = !ball2.goesUp;

                    ball3.goesLeft = !ball3.goesLeft;
                    ball3.goesUp = !ball3.goesUp;
                    System.out.println("BALLS TOUCHED EACH OTHER");
                }
            }
        }
    };

    Thread t3 = new Thread(ball3) {
        @Override
        public void run(){

            while(true) {

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                follow(lbl3, ball3);
                System.out.println("Thread 3 Working");
                // if the left boolean is true then add -5 to the x coordinate of the ball, else add 5
                ball3.setTranslateX(ball3.getTranslateX() + (ball3.goesLeft ? -ball3.speed : ball3.speed));
                // if the up boolean is true then add -5 to the y coordinate of the ball, else add 5
                ball3.setTranslateY(ball3.getTranslateY() + (ball3.goesUp ? -ball3.speed : ball3.speed));


                // collision with sealing
                if (BouncyBall.checkCollision(ball3,sealing)){
                    ball3.goesUp = false;
                }

                // collision with floor
                if (BouncyBall.checkCollision(ball3,floor)){
                    ball3.goesUp = true;
                }

                // collision with left
                if (BouncyBall.checkCollision(ball3, left)){
                    scorePlayer2+=1;
                    ball3.goesLeft = false;
                }

                // collision with right
                if (BouncyBall.checkCollision(ball3, right)){
                    scorePlayer1+=1;
                    ball3.goesLeft = true;
                }
            }
        }
    };

    // HERE HERE HERE HERE HERE HERE

    private Parent createContent(){
        Pane root = new Pane();

        root.setPrefSize(APP_WIDTH, APP_HEIGHT);

        player1.setTranslateX(0);
        player1.setTranslateY(APP_HEIGHT / 2);
        player1.setFill(Color.BLUE);

        player2.setTranslateX(APP_WIDTH-BAT_W);
        player2.setTranslateY(APP_HEIGHT / 2);
        player2.setFill(Color.RED);



        // runs every 0.016 or roughly 60fps
        KeyFrame frame = new KeyFrame(Duration.seconds(0.016), event -> {
            if (!running){
                return;
            }

            switch(action){
                case GO_UP:
                    if(player1.getTranslateY() - 5 >= 0)
                        player1.setTranslateY(player1.getTranslateY() - 5);
                    break;
                case GO_DOWN:
                    if(player1.getTranslateY() + BAT_H + 5 <= APP_HEIGHT)
                        player1.setTranslateY(player1.getTranslateY() + 5);
                    break;
                case NONE:
                    break;
            }

            switch(action2){
                case GO_UP:
                    if(player2.getTranslateY() - 5 >= 0)
                        player2.setTranslateY(player2.getTranslateY() - 5);
                    break;
                case GO_DOWN:
                    if(player2.getTranslateY() + BAT_H + 5 <= APP_HEIGHT)
                        player2.setTranslateY(player2.getTranslateY() + 5);
                    break;
                case NONE:
                    break;
            }

            scoreLabel.setText("PLAYER ONE SCORE: " + scorePlayer1 + "\t\tPLAYER TWO SCORE: " + scorePlayer2);
            stopGame();



            // player 1 collision detection
            if (BouncyBall.checkCollision(ball1,player1)){
                ball1.goesLeft = false;
            }

            if (BouncyBall.checkCollision(ball2, player1)){
                ball2.goesLeft = false;
            }

            if (BouncyBall.checkCollision(ball3, player1)){
                ball3.goesLeft = false;
            }

            // player 2 collision detection
            if (BouncyBall.checkCollision(ball1,player2)){
                ball1.goesLeft = true;
            }

            if (BouncyBall.checkCollision(ball2, player2)){
                ball2.goesLeft = true;
            }

            if (BouncyBall.checkCollision(ball3, player2)){
                ball3.goesLeft = true;
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        root.getChildren().addAll(ball1, ball2, ball3, sealing, floor, left, right, player1, player2, scoreLabel, lbl1, lbl2, lbl3);
        return root;
    }


    private void stopGame(){
        if (scorePlayer1 >= 3 || scorePlayer2 >= 3){
            timeline.stop();
            running = false;

        }
    }
    private static BouncyBall closestBall(BouncyBall ball1, BouncyBall ball2){
        if (ball1.getTranslateX() >= ball2.getTranslateX()){
            return ball1;
        } else{
            return ball2;
        }
    }


    private void startGame(){

        ball1.setTranslateX(300);
        ball1.setTranslateY(APP_HEIGHT / 2);
        ball1.goesUp = true;
        ball1.goesLeft = true;
        follow(lbl1, ball1);

        ball2.setTranslateX(600);
        ball2.setTranslateY(APP_HEIGHT / 2);
        ball2.goesUp = true;
        ball2.goesLeft = false;
        follow(lbl2, ball2);

        ball3.setTranslateX(100);
        ball3.setTranslateY(APP_HEIGHT / 4);
        ball3.goesUp = false;
        ball3.goesLeft = false;
        follow(lbl3, ball3);


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

        scoreLabel.setTranslateX(270);
        scoreLabel.setTranslateY(100);




        timeline.play();
        running = true;


        t1.start();
        t2.start();
        t3.start();

    }


    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(createContent());

        // What happens when keys are pressed for players
        scene.setOnKeyPressed(event ->{
            switch (event.getCode()){
                case W:
                    action = UserAction.GO_UP;
                    break;
                case S:
                    action = UserAction.GO_DOWN;
                    break;
            }

            switch (event.getCode()){
                case UP:
                    action2 = UserAction2.GO_UP;
                    break;
                case DOWN:
                    action2 = UserAction2.GO_DOWN;
                    break;
            }
        });
        // What happens when keys are released for players
        scene.setOnKeyReleased(event ->{
            switch (event.getCode()){
                case W:
                    action = UserAction.NONE;
                    break;
                case S:
                    action = UserAction.NONE;
                    break;
            }
            switch (event.getCode()){
                case UP:
                    action2 = UserAction2.NONE;
                    break;
                case DOWN:
                    action2 = UserAction2.NONE;
                    break;
            }
        });


        stage.setTitle("Bouncing Balls");
        //scene.setFill(Color.BLUE);
        stage.setScene(scene);
        stage.show();
        startGame();

    }
    public static void main(String[] args){
        launch(args);
    }
}
