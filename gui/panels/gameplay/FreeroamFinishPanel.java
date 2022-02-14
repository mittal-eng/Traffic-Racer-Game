package gui.panels.gameplay;

import gui.panels.GameFinishPanel;
import gui.GameFrame;

/**
 * Created by Leo on 07-Nov-15.
 */
public class FreeroamFinishPanel extends GameFinishPanel
{
    public FreeroamFinishPanel(GameFrame windowReference)
    {
        super(windowReference);
        this.setMessageText("You Died!");
    }
}
