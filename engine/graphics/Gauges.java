package engine.graphics;

import engine.math.MathFunctions;
import engine.cars.CarPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 28-Nov-15.
 */
public class Gauges
{
    /* Current display */
    private BufferedImage displayImage;
    private int width;
    private int height;

    public Gauges()
    {

    }

    public Gauges(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void update(CarPlayer carPlayer)
    {
        int carSpeed = (int) MathFunctions.convertMStoKMH(carPlayer.getVelocity());
        int carRpm = (int) carPlayer.getEngineRPM();
        int carGear = carPlayer.getGear();
        int carHealth = carPlayer.getHealth();
        int carScore = carPlayer.getScore();
        boolean carNeedsShift = (carPlayer.getEngineRPM() > carPlayer.getRedlineRPM() - 1000);

        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        String speedMessage  = "Speed:     " + String.valueOf(carSpeed) + " KM/H";
        String rpmMessage    = "RPM:        " + String.valueOf(carRpm);
        String gearMessage   = "Gear:        " + String.valueOf(carGear);
        String healthMessage = "Health:  " + String.valueOf(carHealth);
        String scoreMessage  = "Score:    " + String.valueOf(carScore);
        String shiftMessage  = "SHIFT UP!";

        Graphics2D graphics2D = (Graphics2D) displayImage.getGraphics();
        graphics2D.setFont(GraphicsAdapter.customFont.deriveFont(Font.PLAIN, GraphicsAdapter.CUSTOM_FONT_SIZE_DISPLAY));
        GraphicsAdapter.setRenderingMode(graphics2D);

        int stepHeight = graphics2D.getFontMetrics().getHeight();
        int strHeight = (int) (height - height * 0.10);

        if (carNeedsShift)
        {
            graphics2D.setColor(Color.RED);
            graphics2D.drawString(shiftMessage, 50, strHeight);
            graphics2D.setColor(Color.WHITE);
        }
        strHeight += stepHeight;

        graphics2D.drawString(gearMessage, 50, strHeight);
        strHeight += stepHeight;

        graphics2D.drawString(speedMessage, 50, strHeight);
        strHeight += stepHeight;

        graphics2D.drawString(rpmMessage, 50, strHeight);

        strHeight -= stepHeight;
        strHeight -= stepHeight;

        graphics2D.drawString(healthMessage, width - 220, strHeight);
        strHeight += stepHeight;

        graphics2D.drawString(scoreMessage, width - 220, strHeight);
        strHeight += stepHeight;

        graphics2D.dispose();
    }

    public BufferedImage getDisplayImage()
    {
        return displayImage;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
