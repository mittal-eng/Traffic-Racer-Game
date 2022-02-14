package gui.panels;

import gui.GameFrame;
import engine.ImageOperations;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 06-Nov-15.
 */
public abstract class MenuPanel extends RenderPanel
{
    /* Reference to parent Window */
    protected GameFrame windowReference;

    public MenuPanel(GameFrame windowReference)
    {
        this.windowReference = windowReference;
        this.initializeButtons();
        this.initializePanelProperties();
        this.setFocusable(false);
    }

    /* Initialize Buttons */
    protected void initializeButtons()
    {

    }

    /* Initialize properties of the Panel */
    protected void initializePanelProperties()
    {

    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------- GRAPHICS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private Graphics2D g2d;
    private BufferedImage container;

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        /* Obtain 2D graphics */
        g2d = (Graphics2D) g;

        /* Create a container image */
        container = new BufferedImage(windowReference.getBackgroundImage().getWidth(), windowReference.getBackgroundImage().getHeight(), BufferedImage.TYPE_INT_ARGB);

        /* Add the ROAD_SPRITE to the container */
        ImageOperations.concatenateImage(container, windowReference.getBackgroundImage(), 0, 0);

        /* Draw container */
        g2d.drawImage(container, 0, 0, this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
