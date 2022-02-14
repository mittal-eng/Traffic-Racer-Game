package engine;

import engine.graphics.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 08-Oct-15.
 */
public abstract class ImageOperations
{
    public static void concatenateImage(BufferedImage containerImage, BufferedImage addedImage, int startX, int startY)
    {
        /* Obtain GameEngine.GraphicsEngine from containerImage */
        Graphics2D g2d = containerImage.createGraphics();

        /* Draw addedImage */
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2d.drawImage(addedImage, startX, startY, null);
    }

    private static void concatenateResizedImage(BufferedImage container, Sprite someSprite, double ratio)
    {
        concatenateResizedImage(container, someSprite.getImage(), someSprite.getStartX(), someSprite.getStartY(), ratio);
    }

    public static void concatenateResizedImage(BufferedImage container, BufferedImage someImage, int startX, int startY, double ratio)
    {
        /* Find new width and height */
        short resizeImageNewWidth = (short) (someImage.getWidth() * ratio);
        short resizeImageNewHeight = (short) (someImage.getHeight() * ratio);

        /* Find new X and Y */
        short resizeImageNewStartX = (short) (startX * ratio);
        short resizeImageNewStartY = (short) (startY * ratio);

        /* Find new Dimensions */
        Dimension resizeImageNewDimension = new Dimension(resizeImageNewWidth, resizeImageNewHeight);

        /* Resize the image */
        BufferedImage resizeImageNewBufferedImage = resize(someImage, resizeImageNewDimension);

        /* Add the resized image to the container */
        concatenateImage(container, resizeImageNewBufferedImage, resizeImageNewStartX, resizeImageNewStartY);
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------- Image Resize --------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /*
        Find new Dimensions for scaling Image with keeping aspect ration
     */
    private static Dimension resizeDimension(Dimension imgSize, Dimension boundary)
    {
        /* Obtain given Width and Height */
        int originalWidth = (int) imgSize.getWidth();
        int originalHeight = (int) imgSize.getHeight();

        /* Obtain boundary Width and Height */
        int boundaryWidth = (int) boundary.getWidth();
        int boundaryHeight = (int) boundary.getHeight();

        /* Initialize new Width and Height */
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        /* Check if we need to scale width */
        if (originalWidth > boundaryWidth)
        {
            /* Scale width to fit */
            newWidth = boundaryWidth;

            /* Scale height to maintain aspect ratio */
            newHeight = (newWidth * originalHeight) / originalWidth;
        }

        /* Check if we need to scale height, even with the new height */
        if (newHeight > boundaryHeight)
        {
            /* Scale height to fit instead */
            newHeight = boundaryHeight;

            /* Scale width to maintain aspect ratio */
            newWidth = (newHeight * originalWidth) / originalHeight;
        }

        /* Return new Dimension */
        return new Dimension(newWidth, newHeight);
    }

    /*
        Resize an Image
     */
    private static BufferedImage resize(BufferedImage originalImage, int imgType, int newWidth, int newHeight)
    {
        /* Create new Image */
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, imgType);

        /* Obtain GameEngine.GraphicsEngine from new Image */
        Graphics2D g = resizedImage.createGraphics();

        /* Draw original Image */
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);

        /* Delete GameEngine.GraphicsEngine*/
        g.dispose();

        /* Return new Image */
        return resizedImage;
    }

    /*
        Resize an Image based on newWidth and newHeight
     */
    private static BufferedImage resize(BufferedImage originalImage, int newWidth, int newHeight)
    {
        return resize(originalImage, originalImage.getType(), newWidth, newHeight);
    }

    /*
        Resize an Image based on boundary Dimension
     */
    public static BufferedImage resize(BufferedImage originalImage, Dimension boundarySize)
    {
        /* Find original Image Dimensions */
        Dimension originalSize = new Dimension(originalImage.getWidth(), originalImage.getHeight());

        /* Find resized Image Dimensions*/
        Dimension newSize = resizeDimension(originalSize, boundarySize);

        /* Get new Width and new Height */
        int newWidth = (int) newSize.getWidth();
        int newHeight = (int) newSize.getHeight();

        /* Return resized Image*/
        return resize(originalImage, originalImage.getType(), newWidth, newHeight);
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
