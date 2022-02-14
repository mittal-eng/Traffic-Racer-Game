package engine;

import engine.graphics.Sprite;
import engine.math.Rectangle;

import java.awt.image.BufferedImage;

/**
 * Created by Leo on 05-Nov-15.
 */
public class Coin
{
    protected Sprite coinSprite;
    protected int value;
    protected boolean isDeath = false;
    protected boolean isHealth = false;

    public boolean isDeath()
    {
        return isDeath;
    }

    public boolean isHealth()
    {
        return isHealth;
    }

    public int getValue()
    {
        return this.value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public Coin(Sprite coinSprite)
    {
        this.coinSprite = coinSprite;
    }

    public void setStartY(int y) {
        coinSprite.setStartY(y);
    }

    public void setStartX(int x) {
        coinSprite.setStartX(x);
    }

    public void moveY(int dy) {
        coinSprite.moveY(dy);
    }

    public int getWidth() {
        return coinSprite.getWidth();
    }

    public int getHeight() {
        return coinSprite.getHeight();
    }

    public int getStartY() {
        return coinSprite.getStartY();
    }

    public int getStartX() {
        return coinSprite.getStartX();
    }

    public int getEndX() {
        return coinSprite.getEndX();
    }

    public int getEndY() {
        return coinSprite.getEndY();
    }

    public Rectangle getRectangle() {
        return coinSprite.getRectangle();
    }

    public BufferedImage getImage() {
        return coinSprite.getImage();
    }

    public Sprite getSprite() {
        return coinSprite;
    }

    @Override
    public Coin clone()
    {
        return new Coin(coinSprite.clone());
    }
}
