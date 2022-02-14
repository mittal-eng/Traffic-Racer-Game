package gui.panels.gameplay;

import gui.panels.GamePausePanel;
import gui.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leo on 07-Oct-15.
 */
public class DragRacePausePanel extends GamePausePanel
{
    /* Constructor */
    public DragRacePausePanel(GameFrame windowReference)
    {
        super(windowReference);

        resumeButton.addActionListener(new MouseInput());
        exitButton.addActionListener(new MouseInput());
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- INPUT LISTENER ---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private class MouseInput implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == exitButton)
                windowReference.setToMainMenu();

            if (e.getSource() == resumeButton)
                windowReference.setToDragRace();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
