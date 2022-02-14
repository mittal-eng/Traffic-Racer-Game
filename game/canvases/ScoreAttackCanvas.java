package game.canvases;

import engine.graphics.Gauges;
import engine.Traffic;
import engine.cars.CarOpponent;
import engine.cars.CarPlayer;
import engine.Coins;
import engine.graphics.Canvas;
import game.sprites.RoadSprite;

import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

/**
 * Created by Leo on 05-Nov-15.
 */
public class ScoreAttackCanvas extends Canvas
{


    public ScoreAttackCanvas(RoadSprite road, CarPlayer carPlayer, CarOpponent carOpponent, Traffic traffic, Gauges display, Coins coins)
    {
        super(road, carPlayer, carOpponent, traffic, coins, display);
    }

    @Override
    public void updateGameImage()
    {
                    /* Initialize new image */
        this.bufferedImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);

            /* Draw objects */
        this.drawRoad();
        this.drawCoins();
        this.drawPlayer();
        this.drawTraffic();
        this.drawDisplay();

            /* Save as gameImage */
        this.gameImage = this.bufferedImage;
    }
}
