package game.canvases;

import engine.graphics.Gauges;
import engine.Traffic;
import engine.cars.CarOpponent;
import engine.cars.CarPlayer;
import engine.graphics.Canvas;
import game.sprites.RoadSprite;

import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

/**
 * Created by Leo on 27-Oct-15.
 */
public class BackgroundCanvas extends Canvas
{
    public BackgroundCanvas(RoadSprite road, CarPlayer carPlayer, CarOpponent carOpponent, Traffic traffic, Gauges display)
    {
        super(road, carPlayer, carOpponent, traffic, null, display);
    }

    @Override
    public void updateGameImage()
    {
        /* Initialize new image */
        this.bufferedImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);

        /* Draw objects */
        this.drawRoad();
        this.drawTraffic();

        /* Save as gameImage */
        this.gameImage = this.bufferedImage;
    }
}
