package game.sprites;

import engine.graphics.Sprite;
import engine.ImageOperations;

import java.awt.image.BufferedImage;

/**
 * Created by Leo on 09-Oct-15.
 */
public class TerrainSprite extends Sprite
{
    /* Constructor */
    public TerrainSprite(BufferedImage grass)
    {
        BufferedImage newRoad = new BufferedImage(grass.getWidth(), 3 * grass.getHeight(), BufferedImage.TYPE_INT_ARGB);

        /* Compose new Image */
        ImageOperations.concatenateImage(newRoad, grass, 0, 0);
        ImageOperations.concatenateImage(newRoad, grass, 0, grass.getHeight());
        ImageOperations.concatenateImage(newRoad, grass, 0, 2 * grass.getHeight());

        this.setImage(newRoad);
    }
}
