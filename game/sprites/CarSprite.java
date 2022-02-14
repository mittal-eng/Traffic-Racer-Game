package game.sprites;

import engine.graphics.Sprite;
import engine.ImageOperations;

import java.awt.image.BufferedImage;

/**
 * Created by Leo on 08-Oct-15.
 */
public class CarSprite extends Sprite
{
    private static final double RATIO = 606.0 / 4.287;
    private double length, width;

    public CarSprite(BufferedImage image, double length, double width)
    {
        this.setImage(cut(image, length, width));
        this.length = length;
        this.width = width;
    }

    private BufferedImage cut(BufferedImage image, double length, double width)
    {
        int newHeightPX = (int) (length * RATIO);
        int newWidthPX = (int) (width * RATIO);

        BufferedImage newImage = new BufferedImage(newWidthPX, newHeightPX, BufferedImage.TYPE_INT_ARGB);

        int dx = (int) ((image.getWidth() - newWidthPX) / 2.0);
        int dy = (int) ((image.getHeight() - newHeightPX) / 2.0);

        ImageOperations.concatenateImage(newImage, image, -dx, -dy);

        return newImage;
    }

    @Override
    public CarSprite clone()
    {
        return new CarSprite(this.getImage(), length, width);
    }
}
