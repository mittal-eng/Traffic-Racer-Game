package game.sprites;

import engine.graphics.Sprite;
import engine.math.MathFunctions;
import engine.ImageOperations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Leo on 08-Oct-15.
 */
public class RoadSprite extends Sprite
{
    private ArrayList<Integer> linesCoordinates = new ArrayList<Integer>();
    private Sprite roadOne;
    private Sprite roadTwo;

    /* Constructor */
    public RoadSprite(BufferedImage road, int ... linesCoordinates)
    {
        BufferedImage newRoad = new BufferedImage(road.getWidth(), 3 * road.getHeight(), BufferedImage.TYPE_INT_ARGB);

        /* Compose new Image */
        ImageOperations.concatenateImage(newRoad, road, 0, 0);
        ImageOperations.concatenateImage(newRoad, road, 0, road.getHeight());
        ImageOperations.concatenateImage(newRoad, road, 0, 2 * road.getHeight());

        this.setImage(newRoad);

        this.roadOne = new Sprite(newRoad);
        this.roadTwo = new Sprite(newRoad);
        this.roadOne.setStartY(-newRoad.getHeight());
        this.roadTwo.setStartY(0);

        for (int x : linesCoordinates)
            this.linesCoordinates.add(x);
    }

    public void update(double velocityMS, double deltaTime)
    {
        /* Update Road's position */
        double roadDY = velocityMS * deltaTime * 606.0 / 4.287;
        this.moveY((int) roadDY);

        /* If the ROAD_SPRITE should be scrolled */
        if (this.getPartOneStartY() >= 0)
            this.moveY(-this.getHeight());
    }

    public ArrayList<Integer> getLinesCoordinates() {
        return linesCoordinates;
    }

    public BufferedImage getPartOneImage() {
        return roadOne.getImage();
    }

    public BufferedImage getPartTwoImage() {
        return roadTwo.getImage();
    }

    public int getPartOneStartX() {
        return roadOne.getStartX();
    }

    public int getPartOneStartY() {
        return roadOne.getStartY();
    }

    public int getPartTwoStartX() {
        return roadTwo.getStartX();
    }

    public int getPartTwoStartY() {
        return roadTwo.getStartY();
    }

    public void moveY(int dy)
    {
        roadOne.moveY(dy);
        roadTwo.moveY(dy);
    }

    public int getRandomLine() {
        return linesCoordinates.get(MathFunctions.randomInRange(0, linesCoordinates.size()));
    }
}

