package game.collectables;

import engine.Coin;
import engine.graphics.Sprite;
import engine.ImageFiles;

/**
 * Created by Leo on 07-Nov-15.
 */
public class DeathCoin extends Coin
{
    public DeathCoin()
    {
        super(new Sprite(ImageFiles.COLLECTIBLES_DEATH));
        this.value = 0;
        this.isDeath = true;
        this.isHealth = false;
    }
}
