package gui.panels.gameplay;

import gui.panels.GamePausePanel;
import gui.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leo on 15-Oct-15.
 */
public class FreeroamPausePanel extends GamePausePanel
{
    /* Constructor */
    public FreeroamPausePanel(GameFrame windowReference)
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
                windowReference.setToFreeroam();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}