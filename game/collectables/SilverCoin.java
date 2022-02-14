package game.collectables;

import engine.Coin;
import engine.graphics.Sprite;
import engine.ImageFiles;

/**
 * Created by Leo on 07-Nov-15.
 */
public class SilverCoin extends Coin
{
    public SilverCoin()
    {
        super(new Sprite(ImageFiles.COLLECTIBLES_SILVER_COIN));
        this.setValue(50);
        this.isDeath = false;
        this.isHealth = false;
    }
}
