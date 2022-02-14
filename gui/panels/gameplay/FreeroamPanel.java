package gui.panels.gameplay;

import engine.graphics.Gauges;
import gui.panels.GamePanel;
import game.traffics.FreeroamTraffic;
import engine.cars.CarPlayer;
import game.canvases.FreeroamCanvas;
import game.sprites.RoadSprite;
import gui.GameFrame;
import game.loops.FreeroamLoop;

/**
 * Created by Leo on 11-Oct-15.
 */
public class FreeroamPanel extends GamePanel
{
    /* Game objects */
    private RoadSprite roadSprite;
    private CarPlayer carPlayer;
    private Gauges display;
    private FreeroamTraffic freeroamTraffic;

    /* Constructor */
    public FreeroamPanel(RoadSprite roadSprite, CarPlayer carPlayer, GameFrame windowReference)
    {
        /* Deny focus */
        this.setFocusable(false);

        /* Store window */
        this.windowReference = windowReference;

        /* Store game objects */
        this.roadSprite = roadSprite;
        this.carPlayer = carPlayer;
        this.display = new Gauges();

        /* Initialize traffic */
        this.freeroamTraffic = new FreeroamTraffic(roadSprite, carPlayer);
        freeroamTraffic.initialize();

        /* Initialize game loop */
        this.gameLoop = new FreeroamLoop(roadSprite, carPlayer, freeroamTraffic, this);

        /* Initialize canvas */
        this.canvas = new FreeroamCanvas(roadSprite, carPlayer, null, freeroamTraffic, display);
    }
}
