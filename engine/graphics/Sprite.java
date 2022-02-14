package engine.graphics;

import engine.math.MathFunctions;
import engine.math.Rectangle;

import java.awt.image.BufferedImage;

/**
 * Created by Leo on 08-Oct-15.
 */
public class Sprite
{
    private BufferedImage image;
    private Rectangle rectangle;

    protected Sprite()
    {

    }

    public Sprite(BufferedImage image)
    {
        this.image = image;
        this.rectangle = new Rectangle(image.getWidth(), image.getHeight());
    }

    protected void setImage(BufferedImage image)
    {
        this.image = image;
        this.rectangle = new Rectangle(image.getWidth(), image.getHeight());
    }

    public Sprite clone()
    {
        return new Sprite(image);
    }

    public int getWidth() {
        return (int) rectangle.getWidth();
    }

    public int getHeight() {
        return (int) rectangle.getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getStartX() {
        return (int) rectangle.getStartX();
    }

    public int getStartY() {
        return (int) rectangle.getStartY();
    }

    public int getEndX() {
        return (int) rectangle.getEndX();
    }

    public int getEndY() {
        return (int) rectangle.getEndY();
    }

    public void setStartX(int x) {
        rectangle.setStartX(x);
    }

    public void setStartY(int y) {
        rectangle.setStartY(y);
    }

    public void moveX(int dx) {
        rectangle.moveX(dx);
    }

    public void moveY(int dy) {
        rectangle.moveY(dy);
    }

    public static boolean areOverlapping(Sprite a, Sprite b) {
        return (MathFunctions.rectanglesAreOverlapping(a.rectangle, b.rectangle));
    }

    public static boolean areOverlappedAtCenter(Sprite a, Sprite b, double radiusOfOverlap) {
        return (MathFunctions.rectanglesAreOverlappedAtCenter(a.rectangle, b.rectangle, radiusOfOverlap));
    }

    public static boolean areAtSafeDistanceY(Sprite a, Sprite b, double safeDistanceY) {
        return (MathFunctions.rectanglesAreAtSafeDistanceY(a.rectangle, b.rectangle, safeDistanceY));
    }

    public static Sprite findUpper(Sprite a, Sprite b)
    {
        if (MathFunctions.rectanglesFindUpper(a.rectangle, b.rectangle) == a.rectangle)
            return a;

        else if (MathFunctions.rectanglesFindUpper(a.rectangle, b.rectangle) == b.rectangle)
            return b;

        else
            return null;
    }

    public static Sprite findLower(Sprite a, Sprite b)
    {
        if (MathFunctions.rectanglesFindLower(a.rectangle, b.rectangle) == a.rectangle)
            return a;

        else if (MathFunctions.rectanglesFindLower(a.rectangle, b.rectangle) == b.rectangle)
            return b;

        else
            return null;
    }
}
