package gui.panels.gameplay;

import gui.panels.GameFinishPanel;
import gui.GameFrame;

/**
 * Created by Leo on 10-Oct-15.
 */
public class DragRaceFinishPanel extends GameFinishPanel
{
    private boolean playerWins, opponentWins;

    /* Constructor */
    public DragRaceFinishPanel(GameFrame windowReference)
    {
        super(windowReference);
    }

    public void showWhoWon(boolean playerWins, boolean opponentWins)
    {
        this.playerWins = playerWins;
        this.opponentWins = opponentWins;

        if (this.opponentWins)
            this.setMessageText("You Lost!");

        else if (this.playerWins)
            this.setMessageText("You Won!");

        this.repaint();
    }
}
