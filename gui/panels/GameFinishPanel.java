package gui.panels;

import gui.buttons.MenuButton;
import gui.GameFrame;
import engine.ImageOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 07-Nov-15.
 */
public abstract class GameFinishPanel extends RenderPanel
{
    /* Reference to parent Window */
    private GameFrame windowReference;

    /* Buttons */
    private MenuButton exitButton;
    private MenuButton messageButton;

    /* graphics */
    private Graphics2D g2d;
    private BufferedImage gameImage;
    private BufferedImage messageImage;


    public GameFinishPanel(GameFrame windowReference)
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
        exitButton = new MenuButton("Exit");
        messageButton = new MenuButton();
        messageButton.useAsMessage();
        exitButton.addActionListener(new MouseInput());
    }

    /* Initialize properties of the Panel */
    private void initializePanelProperties()
    {
        this.setLayout(new GridLayout(5, 1, 0, 100));
        this.add(new MenuButton());
        this.add(messageButton);
        this.add(new MenuButton());
        this.add(exitButton);
        this.add(new MenuButton());
        this.setFocusable(false);
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

    public void setMessageText(String str)
    {
        this.messageButton.setText(str);
    }
//    public void setMessageImage(BufferedImage messageImage)
//    {
//        this.messageImage = new BufferedImage(messageImage.getWidth(), messageImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
//        ImageOperations.concatenateImage(this.messageImage, messageImage, 0, 0);
//        this.messageImage = ImageOperations.resize(this.messageImage, new Dimension(360, 180));
//    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int startDrawingX;
        int startDrawingY;

        /* Obtain graphics */
        g2d = (Graphics2D) g;
        
        startDrawingX = (getWidth() - this.gameImage.getWidth()) / 2 ;
        startDrawingY = (getHeight() - this.gameImage.getHeight()) / 2;
        g2d.drawImage(this.gameImage, startDrawingX, startDrawingY, this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- INPUT LISTENER ---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private class MouseInput implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == exitButton)
            {
                windowReference.setToMainMenu();
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
