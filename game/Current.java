package game;

import engine.Player;
import engine.cars.CarOpponent;
import engine.cars.CarPlayer;
import game.sprites.RoadSprite;
import game.storage.StorageCarsOpponent;
import game.storage.StorageCarsPlayer;
import game.storage.StorageRoads;

/**
 * All data about current game session
 * @author Lev Ertuna
 */
public abstract class Current
{
    public static int DISTANCE = DragConstants.LONG_1KM_DRAG;

    public static Player player;
    public static CarPlayer carPlayer = StorageCarsPlayer.FORD_MUSTANG_GT390_1968;
    public static CarOpponent carOpponent = StorageCarsOpponent.FORD_MUSTANG_GT390_1968;

    public static final RoadSprite roadBackground = StorageRoads.ROAD_SPRITE;
    public static final RoadSprite roadDrag = StorageRoads.ROAD_SPRITE;
    public static final RoadSprite roadFreeroam = StorageRoads.ROAD_SPRITE;
    public static final RoadSprite roadScoreAttack = StorageRoads.ROAD_SPRITE;
}
