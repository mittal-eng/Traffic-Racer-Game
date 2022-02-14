package game.loops;

import game.traffics.BackgroundTraffic;
import engine.GameLoop;
import gui.GameFrame;

/**
 * Created by Leo on 08-Oct-15.
 */
public class BackgroundLoop extends GameLoop
{
    private GameFrame windowReference;
    private BackgroundTraffic backgroundTraffic;

    public BackgroundLoop(BackgroundTraffic backgroundTraffic, GameFrame windowReference)
    {
        this.windowReference = windowReference;
        this.backgroundTraffic = backgroundTraffic;
    }

    @Override
    protected void fixedUpdate()
    {
        backgroundTraffic.update(this.getDeltaTimeS());
    }

    @Override
    protected void freeUpdate()
    {
        windowReference.updateBackgroundImage();
        windowReference.repaint();
    }
}
