package gui.panels.menus;

import engine.graphics.GraphicsAdapter;
import gui.buttons.MenuButton;
import gui.panels.MenuPanel;
import gui.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leo on 10-Oct-15.
 */
public class AboutPanel extends MenuPanel
{
    private MenuButton exitButton;

    public AboutPanel(GameFrame windowReference)
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
        int strWidth = this.getFontMetrics(this.getFont()).stringWidth("Controls");
        int strHeight = 2 * this.getFontMetrics(this.getFont()).getHeight();
        int stepVertical = this.getFontMetrics(this.getFont()).getHeight() + 10;

        g2d.drawString("Controls", width / 2 - strWidth / 2, strHeight);
        strHeight += stepVertical;



        strWidth = this.getFontMetrics(this.getFont()).stringWidth("__________________");
        g2d.drawString("W    - Accelerate  ", width/2 - strWidth/2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("S     - Brake       ", width/2 - strWidth/2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("A     - Steer Left  ", width/2 - strWidth/2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("D     - Steer Right ", width/2 - strWidth/2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("E     - Shift Up    ", width/2 - strWidth/2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("Q     - Shift Down  ", width/2 - strWidth/2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("ESC - Pause       ", width/2 - strWidth/2, strHeight);
        strHeight += stepVertical;

        strHeight += stepVertical;

        strWidth = this.getFontMetrics(this.getFont()).stringWidth("Features");
        g2d.drawString("Features", width / 2 - strWidth / 2, strHeight);
        strHeight += stepVertical;

        strWidth = this.getFontMetrics(this.getFont()).stringWidth("____________________");
        g2d.drawString("- Java 2D Game      ", width / 2 - strWidth / 2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("- Realistic Physics ", width / 2 - strWidth / 2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("- 11 cars           ", width / 2 - strWidth / 2, strHeight);
        strHeight += stepVertical;
        g2d.drawString("- 3 Game Modes      ", width / 2 - strWidth / 2, strHeight);
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
