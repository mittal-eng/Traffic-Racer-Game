package game.storage;

import game.sprites.TerrainSprite;
import game.sprites.RoadSprite;
import engine.ImageFiles;

/**
 * Created by Leo on 08-Oct-15.
 */
public interface StorageRoads
{
    public static final RoadSprite ROAD_SPRITE = new RoadSprite(ImageFiles.ROAD, 120, 480, 830, 1180, 1530, 1870);
    public static final TerrainSprite GRASS_SPRITE = new TerrainSprite(ImageFiles.GRASS);
}
