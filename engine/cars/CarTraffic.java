package engine.cars;

import game.sprites.CarSprite;
import engine.graphics.Sprite;

import java.awt.image.BufferedImage;

/**
 * Created by Leo on 08-Oct-15.
 */
public class CarTraffic {
    private CarSprite sprite;
    private boolean valid = false;
    private double velocity;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public CarTraffic(BufferedImage image, double length, double width)
    {
        this.sprite = new CarSprite(image, length, width);
        this.velocity = 0;
    }

    private CarTraffic(CarSprite sprite, double velocity) {
        this.sprite = sprite;
        this.velocity = velocity;
    }

    public CarSprite getSprite() {
        return sprite;
    }

    public BufferedImage getImage()
    {
        return sprite.getImage();
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight()
    {
        return sprite.getHeight();
    }

    public int getStartX() {
        return sprite.getStartX();
    }

    public int getStartY() {
        return sprite.getStartY();
    }

    public int getEndX() {
        return sprite.getEndX();
    }

    public int getEndY() {
        return sprite.getEndY();
    }

    public void setStartX(int x) {
        sprite.setStartX(x);
    }

    public void setStartY(int y) {
        sprite.setStartY(y);
    }

    public void moveX(int dx) {
        sprite.moveX(dx);
    }

    public void moveY(int dy) {
        sprite.moveY(dy);
    }

    public static boolean areOverlapping(CarTraffic a, CarTraffic b) {
        return (Sprite.areOverlapping(a.getSprite(), b.getSprite()));
    }

    public static boolean areAtSafeDistanceY(CarTraffic a, CarTraffic b, double safeDistanceY) {
        return (Sprite.areAtSafeDistanceY(a.getSprite(), b.getSprite(), safeDistanceY));
    }

    public static CarTraffic findUpper(CarTraffic a, CarTraffic b)
    {
        if (Sprite.findUpper(a.getSprite(), b.getSprite()) == a.getSprite())
            return a;

        else if (Sprite.findUpper(a.getSprite(), b.getSprite()) == b.getSprite())
            return b;

        else
            return null;
    }

    public static CarTraffic findLower(CarTraffic a, CarTraffic b)
    {
        if (Sprite.findLower(a.getSprite(), b.getSprite()) == a.getSprite())
            return a;

        else if (Sprite.findLower(a.getSprite(), b.getSprite()) == b.getSprite())
            return b;

        else
            return null;
    }


    public CarTraffic clone() {
        return new CarTraffic(sprite.clone(), velocity);
    }
}
