package gui.panels;

import engine.GameLoop;
import engine.graphics.Canvas;
import gui.GameFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 05-Nov-15.
 */
public abstract class GamePanel extends RenderPanel
{
    /* Reference to window */
    public GameFrame windowReference;

    /* Game loop */
    public GameLoop gameLoop;

    /* Indicates whether the race was started */
    protected boolean raceStarted = false;

    /* graphics*/
    protected Canvas canvas;
    protected Graphics2D g2d;

    public boolean isRaceStarted()
    {
        return raceStarted;
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------- GRAPHICS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        /* Obtain graphics */
        g2d = (Graphics2D) g;

        /* Find Drawing position */
        int startDrawingX = (getWidth() - this.getGameImage().getWidth()) / 2 ;
        int startDrawingY = (getHeight() - this.getGameImage().getHeight()) / 2;

        /* Draw image */
        g2d.drawImage(this.getGameImage(), startDrawingX, startDrawingY, this);
    }

    public void drawCountdown()
    {
        canvas.drawCountdown();
        raceStarted = true;
    }

    public void updateGameImage()
    {
        canvas.updateGameImage();
    }

    public BufferedImage getGameImage()
    {
        return canvas.getGameImage();
    }

    public BufferedImage getPauseImage()
    {
        return canvas.getPauseImage();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
