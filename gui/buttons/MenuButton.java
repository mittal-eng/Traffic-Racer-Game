package gui.buttons;

import engine.graphics.GraphicsAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Leo on 28-Nov-15.
 */
public class MenuButton extends JButton
{
    private boolean isMessage = false;

    public void useAsMessage()
    {
        this.isMessage = true;
    }

    public MenuButton()
    {
        this(" ");
    }

    public MenuButton(String text)
    {
        super(text);
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setFont(GraphicsAdapter.customFont.deriveFont(Font.PLAIN, GraphicsAdapter.CUSTOM_FONT_SIZE_MENU));
        this.setForeground(Color.WHITE);
    }


    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D graphics2D = (Graphics2D) g;

        GraphicsAdapter.setRenderingMode(graphics2D);

        if (!this.isMessage)
        {
            if (this.getModel().isRollover())
                graphics2D.setColor(Color.CYAN);

            if (this.getModel().isPressed())
                graphics2D.setColor(Color.GREEN);
        }

        int width = this.getWidth();
        int height = this.getHeight();
        int strWidth = this.getFontMetrics(this.getFont()).stringWidth(this.getText());
        int strHeight = this.getFontMetrics(this.getFont()).getHeight();

        graphics2D.drawString(this.getText(), width / 2 - strWidth / 2, height / 2 + strHeight / 2);

        graphics2D.dispose();
    }



    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension((int) (400 * GraphicsAdapter.ratioWidth), (int) (this.getFontMetrics(this.getFont()).getHeight() * 1.5));
    }
}
