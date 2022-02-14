package game.collectables;

import engine.Coin;
import engine.graphics.Sprite;
import engine.ImageFiles;

/**
 * Created by Leo on 07-Nov-15.
 */
public class GoldCoin extends Coin
{
    public GoldCoin()
    {
        super(new Sprite(ImageFiles.COLLECTIBLES_GOLD_COIN));
        this.setValue(100);
        this.isDeath = false;
        this.isHealth = false;
    }


}
