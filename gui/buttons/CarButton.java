package gui.buttons;

import engine.ImageFiles;
import engine.ImageOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 30-Sep-15.
 */
public class CarButton extends ImageMenuButton
{
    private BufferedImage icon;
    private BufferedImage rolloverIcon;
    private BufferedImage pressedIcon;

    private Dimension size = new Dimension(400, 270);

    public CarButton(BufferedImage carImage, String carMake, String carModel, String year)
    {
        this.createIcon(carImage);
        this.createRolloverIcon(carImage);
        this.createPressedIcon(carImage);

        this.setVerticalTextPosition(JButton.BOTTOM);
        this.setHorizontalTextPosition(JButton.CENTER);

        this.setFocusable(false);

        this.setToolTipText((carMake + " " + carModel + " " + year).toUpperCase());
    }

    private void createIcon(BufferedImage carImage)
    {
        icon = new BufferedImage(carImage.getWidth(), carImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        ImageOperations.concatenateImage(icon, carImage, 0, 0);

        icon = ImageOperations.resize(icon, size);

        this.setIcon(new ImageIcon(icon));
    }

    private void createRolloverIcon(BufferedImage carImage) {
        rolloverIcon = new BufferedImage(carImage.getWidth(), carImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        ImageOperations.concatenateImage(rolloverIcon, ImageFiles.GUI_CAR_BUTTON_BACKGROUND_ROLLOVER, 0, 0);
        ImageOperations.concatenateImage(rolloverIcon, carImage, 0, 0);
        rolloverIcon = ImageOperations.resize(rolloverIcon, size);

        this.setRolloverIcon(new ImageIcon(rolloverIcon));
    }

    private void createPressedIcon(BufferedImage carImage) {
        pressedIcon = new BufferedImage(carImage.getWidth(), carImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        ImageOperations.concatenateImage(pressedIcon, ImageFiles.GUI_CAR_BUTTON_BACKGROUND_PRESSED, 0, 0);
        ImageOperations.concatenateImage(pressedIcon, carImage, 0, 0);
        pressedIcon = ImageOperations.resize(pressedIcon, size);

        this.setPressedIcon(new ImageIcon(pressedIcon));
    }




}
