package game.collectables;

import engine.Coin;
import engine.graphics.Sprite;
import engine.ImageFiles;

/**
 * Created by Leo on 07-Nov-15.
 */
public class HealthCoin extends Coin
{
    public HealthCoin()
    {
        super(new Sprite(ImageFiles.COLLECTIBLES_HEALTH));
        this.value = 0;
        this.isDeath = false;
        this.isHealth = true;
    }
}
