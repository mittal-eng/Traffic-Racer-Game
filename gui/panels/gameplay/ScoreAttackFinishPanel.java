package gui.panels.gameplay;

import gui.panels.GameFinishPanel;
import gui.GameFrame;

/**
 * Created by Leo on 07-Nov-15.
 */
public class ScoreAttackFinishPanel extends GameFinishPanel
{
    public ScoreAttackFinishPanel(GameFrame windowReference)
    {
        super(windowReference);
        this.setMessageText("You Died!");
    }
}
