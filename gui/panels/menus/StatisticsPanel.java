package gui.panels.menus;

import engine.graphics.GraphicsAdapter;
import game.Current;
import gui.GameFrame;
import gui.buttons.MenuButton;
import gui.panels.MenuPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leo on 25-Dec-15.
 */
public class StatisticsPanel extends MenuPanel
{
    private MenuButton exitButton;

    public StatisticsPanel(GameFrame windowReference)
    {
        super(windowReference);

        exitButton = new MenuButton("Exit");
        exitButton.addActionListener(new MouseInput());

        this.setLayout(new BorderLayout());
        this.add(exitButton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setFont(GraphicsAdapter.customFont.deriveFont(Font.PLAIN, GraphicsAdapter.CUSTOM_FONT_SIZE_ABOUT));

        /* Obtain 2D graphics */
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(GraphicsAdapter.COLOR_ORANGE);

        int width = this.getWidth();
        int height = this.getHeight();

        int strWidth = this.getFontMetrics(this.getFont()).stringWidth("Player Statistics");
        int strHeight = 2 * this.getFontMetrics(this.getFont()).getHeight();
        int stepVertical = this.getFontMetrics(this.getFont()).getHeight() + 10;

        g2d.drawString("Player Statistics", width / 2 - strWidth / 2, strHeight);
        strHeight += stepVertical;
        strHeight += stepVertical;

        g2d.drawString("Races won:       " + Current.player.getRacesWon(), (int) (width * 0.05), strHeight);
        strHeight += stepVertical;

        g2d.drawString("Races lost:      " + Current.player.getRacesLost(), (int) (width * 0.05), strHeight);
        strHeight += stepVertical;

        g2d.drawString("Deaths:              " + Current.player.getDeaths(), (int) (width * 0.05), strHeight);
        strHeight += stepVertical;

        g2d.drawString("Total score:    " + Current.player.getScore(), (int) (width * 0.05), strHeight);
        strHeight += stepVertical;

        long longVal = Current.player.getTime();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        String time = hours + " h " + mins + " m " + secs + " s ";
        g2d.drawString("Total time: " + time, (int) (width * 0.05), strHeight);
        strHeight += stepVertical;
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
            {
                windowReference.setToMainMenu();
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
