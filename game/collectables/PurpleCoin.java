package game.collectables;

import engine.Coin;
import engine.graphics.Sprite;
import engine.ImageFiles;

/**
 * Created by Leo on 07-Nov-15.
 */
public class PurpleCoin extends Coin
{
    public PurpleCoin()
    {
        super(new Sprite(ImageFiles.COLLECTIBLES_PURPLE_COIN));
        this.setValue(500);
        this.isDeath = false;
        this.isHealth = false;
    }
}
