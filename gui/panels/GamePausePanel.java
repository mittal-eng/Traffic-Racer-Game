package gui.panels;

import gui.buttons.MenuButton;
import gui.GameFrame;
import engine.ImageOperations;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 05-Nov-15.
 */
public abstract class GamePausePanel extends RenderPanel
{
    /* Reference to parent Window */
    protected GameFrame windowReference;

    /* Buttons */
    protected MenuButton resumeButton;
    protected MenuButton exitButton;

    /* graphics */
    private Graphics2D g2d;
    private BufferedImage gameImage;

    /* Constructor */
    public GamePausePanel(GameFrame windowReference)
    {
        this.windowReference = windowReference;

        this.initializeButtons();
        this.initializePanelProperties();
    }



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ INITIALIZATION --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Initialize Buttons */
    private void initializeButtons()
    {
        resumeButton = new MenuButton("Resume");
        exitButton = new MenuButton("Exit");
    }

    /* Initialize properties of the Panel */
    private void initializePanelProperties()
    {
        this.setLayout(new GridLayout(5, 1, 0, 100));
        this.add(new MenuButton());
        this.add(resumeButton);
        this.add(new MenuButton());
        this.add(exitButton);
        this.add(new MenuButton());
        this.setFocusable(false);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------- GRAPHICS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setGameImage(BufferedImage gameImage)
    {
        this.gameImage = new BufferedImage(gameImage.getWidth(), gameImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ImageOperations.concatenateImage(this.gameImage, gameImage, 0, 0);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        /* Obtain graphics */
        g2d = (Graphics2D) g;

        /* Find Drawing position */
        int startDrawingX = (getWidth() - this.gameImage.getWidth()) / 2 ;
        int startDrawingY = (getHeight() - this.gameImage.getHeight()) / 2;

        /* Draw image */
        g2d.drawImage(this.gameImage, startDrawingX, startDrawingY, this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
