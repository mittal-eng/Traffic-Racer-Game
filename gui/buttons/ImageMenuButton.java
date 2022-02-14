package gui.buttons;

import engine.graphics.GraphicsAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 30-Sep-15.
 */
public abstract class ImageMenuButton extends JButton
{
    private BufferedImage icon;
    private BufferedImage rolloverIcon;
    private BufferedImage pressedIcon;
    private BufferedImage disabledIcon;

    protected ImageMenuButton()
    {
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setContentAreaFilled(false);
        this.setFocusable(false);
    }

    public ImageMenuButton(BufferedImage iconImage)
    {
        this(iconImage, iconImage, iconImage, iconImage);
    }

    public ImageMenuButton(BufferedImage iconImage, BufferedImage rolloverIconImage, BufferedImage pressedIconImage, BufferedImage disabledIconImage)
    {
        this.icon = iconImage;
        this.rolloverIcon = rolloverIconImage;
        this.pressedIcon = pressedIconImage;
        this.disabledIcon = disabledIconImage;

        this.setBorderPainted(false);
        this.setBorder(null);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setContentAreaFilled(false);
        this.setFocusable(false);

        this.setIcon(new ImageIcon(iconImage));
        this.setRolloverIcon(new ImageIcon(rolloverIconImage));
        this.setPressedIcon(new ImageIcon(pressedIconImage));
        this.setDisabledIcon(new ImageIcon(disabledIconImage));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension((int) (400 * GraphicsAdapter.ratioWidth), (int) (200 * GraphicsAdapter.ratioWidth));
    }
}
