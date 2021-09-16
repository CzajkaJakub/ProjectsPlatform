package com.example.simpleprojectsplatform.MainPlatform;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;


public class DiamondAnimation implements Runnable {

    private RotateTransition rotation;
    private FadeTransition fade;
    private ScaleTransition scale;

    private final ImageView diamondIcon;
    private final ImageView backgroundLight;
    private final double maxX;
    private final double maxY;
    private double currentX;
    private double currentY;
    private final double diamondWidth;
    private final double diamondHeight;
    private final double lightWidth;
    private final double lightHeight;

    private final double arrowsMove = 6;

    public DiamondAnimation(ImageView diamondIcon, ImageView backgroundLight, double maxX, double maxY) {
        this.lightHeight = backgroundLight.getFitHeight();
        this.lightWidth = backgroundLight.getFitWidth();
        this.diamondHeight = diamondIcon.getFitHeight();
        this.diamondWidth = diamondIcon.getFitWidth();
        this.currentX = diamondIcon.getLayoutX();
        this.currentY = diamondIcon.getLayoutY();
        this.backgroundLight = backgroundLight;
        this.diamondIcon = diamondIcon;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public void run() {

        Random random = new Random();
        createAnimations();

        double currentLightX = backgroundLight.getLayoutX();
        double currentLightY = backgroundLight.getLayoutY();

        double lightMoveY = 1;
        double lightMoveX = 2;


        while (true) {
            currentLightY += lightMoveY;
            currentLightX += lightMoveX;
            backgroundLight.setY(currentLightY);
            backgroundLight.setX(currentLightX);
            if (currentLightX >= maxX + lightWidth) {
                currentLightX = random.nextInt((int) maxX);
                currentLightY = 0 - lightHeight;
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createAnimations() {
        createRotation();
        createFade();
        createScale();
    }

    private void createRotation() {
        rotation = new RotateTransition();
        rotation.setNode(diamondIcon);
        rotation.setAutoReverse(false);
        rotation.setInterpolator(Interpolator.LINEAR);
        rotation.setDuration(Duration.seconds(2));
        rotation.setByAngle(360);
    }

    private void createFade() {
        fade = new FadeTransition();
        fade.setNode(diamondIcon);
        fade.setAutoReverse(true);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setDuration(Duration.seconds(3));
    }

    private void createScale() {
        scale = new ScaleTransition();
        scale.setNode(diamondIcon);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setDuration(Duration.seconds(3));
        scale.setAutoReverse(false);
    }

    public void moveUP() {
        if(currentY > 150){
            currentY -= arrowsMove;
            diamondIcon.setLayoutY(currentY);
        }
    }

    public void moveDOWN() {
        if(currentY < maxY - diamondHeight){
            currentY += arrowsMove;
            diamondIcon.setLayoutY(currentY);
        }
    }

    public void moveLEFT() {
        if(currentX > 0){
            currentX -= arrowsMove;
            diamondIcon.setLayoutX(currentX);
        }
    }

    public void moveRIGHT() {
        if(currentX < maxX - diamondWidth){
            currentX += arrowsMove;
            diamondIcon.setLayoutX(currentX);
        }
    }

    public void rotateDiamondZ() {
        rotation.setAxis(Rotate.Z_AXIS);
        rotation.play();
    }

    public void rotateDiamondY() {
        rotation.setAxis(Rotate.Y_AXIS);
        rotation.play();
    }

    public void rotateDiamondX() {
        rotation.setAxis(Rotate.X_AXIS);
        rotation.play();
    }

    public void hideDiamond() {
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }

    public void showDiamond(){
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void makeTheDiamondBig(){
        scale.setByX(2);
        scale.setByY(2);
        scale.play();
    }

    public void makeTheDiamondSmall(){
        scale.setByX(-2);
        scale.setByY(-2);
        scale.play();
    }
}

