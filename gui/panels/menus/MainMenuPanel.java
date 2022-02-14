package gui.panels.menus;

import gui.buttons.MenuButton;
import gui.panels.MenuPanel;
import gui.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leo on 30-Sep-15.
 */
public class MainMenuPanel extends MenuPanel
{

    /* Buttons */
    private MenuButton raceButton;
    private MenuButton carSelectionButton;
    private MenuButton settingsButton;
    private MenuButton statisticsButton;
    private MenuButton aboutButton;
    private MenuButton exitButton;

    public MainMenuPanel(GameFrame windowReference)
    {
        super(windowReference);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ INITIALIZATION --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void initializeButtons()
    {
        raceButton = new MenuButton("Race");
        carSelectionButton = new MenuButton("Garage");
        settingsButton = new MenuButton("Settings");
        statisticsButton = new MenuButton("Statistics");
        aboutButton = new MenuButton("About");
        exitButton = new MenuButton("Exit");

        raceButton.addActionListener(new MouseInput());
        carSelectionButton.addActionListener(new MouseInput());
        settingsButton.addActionListener(new MouseInput());
        statisticsButton.addActionListener(new MouseInput());
        aboutButton.addActionListener(new MouseInput());
        exitButton.addActionListener(new MouseInput());
    }

    @Override
    public void initializePanelProperties()
    {
        this.setLayout(new GridLayout(6, 1, 0, 100));
        this.add(raceButton);
        this.add(carSelectionButton);
        //this.add(settingsButton);
        this.add(statisticsButton);
        this.add(aboutButton);
        this.add(exitButton);
        this.setFocusable(false);
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
        public void actionPerformed(ActionEvent e) {
            if ((MenuButton) e.getSource() == raceButton)
            {
                windowReference.setToModeMenu();
            }

            if ((MenuButton) e.getSource() == carSelectionButton)
            {
                windowReference.setToGaragePlayer();
            }

            if ((MenuButton) e.getSource() == settingsButton)
            {
                windowReference.setToSettingsMenu();
            }

            if ((MenuButton) e.getSource() == statisticsButton)
            {
                windowReference.setToStatistics();
            }

            if ((MenuButton) e.getSource() == aboutButton)
            {
                windowReference.setToAbout();
            }

            if ((MenuButton) e.getSource() == exitButton)
            {
                System.exit(0);
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
