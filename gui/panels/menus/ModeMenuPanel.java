package gui.panels.menus;

import engine.InputController;
import game.storage.StorageCarsOpponent;
import gui.buttons.MenuButton;
import gui.panels.MenuPanel;
import gui.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leo on 02-Oct-15.
 */
public class ModeMenuPanel extends MenuPanel
{
    /* Buttons */
    private MenuButton exitButton;
    private MenuButton gameModeDragButton;
    private MenuButton gameModeFreeRoamButton;
    private MenuButton gameModeTimeAttackButton;
    private MenuButton gameModeScoreAttackButton;

    public ModeMenuPanel(GameFrame windowReference)
    {
        super(windowReference);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ INITIALIZATION --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void initializeButtons()
    {
        exitButton = new MenuButton("Exit");
        gameModeDragButton = new MenuButton("Drag Race");
        gameModeFreeRoamButton = new MenuButton("Freeroam");
        gameModeTimeAttackButton = new MenuButton("Time Attack");
        gameModeScoreAttackButton = new MenuButton("Score Attack");

        exitButton.addActionListener(new MouseInput());
        gameModeDragButton.addActionListener(new MouseInput());
        gameModeFreeRoamButton.addActionListener(new MouseInput());
        gameModeTimeAttackButton.addActionListener(new MouseInput());
        gameModeScoreAttackButton.addActionListener(new MouseInput());
    }

    @Override
    protected void initializePanelProperties()
    {
        this.setLayout(new GridLayout(5, 1, 0, 100));
        this.add(gameModeDragButton);
        this.add(gameModeFreeRoamButton);
        //this.add(gameModeTimeAttackButton);
        this.add(gameModeScoreAttackButton);
        this.add(exitButton);
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
            if ((MenuButton) e.getSource() == exitButton)
                windowReference.setToMainMenu();

            else if ((MenuButton) e.getSource() == gameModeDragButton)
            {
                StorageCarsOpponent.chooseOpponent();
                windowReference.setToDragRace();
            }

            else if ((MenuButton) e.getSource() == gameModeFreeRoamButton)
            {
                windowReference.setToFreeroam();
            }

            else if ((MenuButton) e.getSource() == gameModeTimeAttackButton)
            {
                windowReference.setToDragRace();
            }

            else if ((MenuButton) e.getSource() == gameModeScoreAttackButton)
            {
                windowReference.setToScoreAttack();
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
