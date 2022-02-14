package gui.panels.gameplay;

import engine.graphics.Gauges;
import gui.panels.GamePanel;
import game.traffics.ScoreAttackTraffic;
import engine.cars.CarPlayer;
import engine.Coins;
import game.canvases.ScoreAttackCanvas;
import game.sprites.RoadSprite;
import gui.GameFrame;
import game.loops.ScoreAttackLoop;

/**
 * Created by Leo on 05-Nov-15.
 */
public class ScoreAttackPanel extends GamePanel
{
    /* Game objects */
    private RoadSprite roadSprite;
    private CarPlayer carPlayer;
    private Gauges display;
    private Coins coins;
    private ScoreAttackTraffic scoreAttackTraffic;

    /* Constructor */
    public ScoreAttackPanel(RoadSprite roadSprite, CarPlayer carPlayer, GameFrame windowReference)
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
        this.scoreAttackTraffic = new ScoreAttackTraffic(roadSprite, carPlayer);
        this.scoreAttackTraffic.initialize();

        this.coins = new Coins(roadSprite, carPlayer, scoreAttackTraffic);
        this.coins.initialize();

        /* Initialize game loop */
        this.gameLoop = new ScoreAttackLoop(roadSprite, carPlayer, scoreAttackTraffic, coins, this);

        /* Initialize canvas */
        this.canvas = new ScoreAttackCanvas(roadSprite, carPlayer, null, scoreAttackTraffic, display, coins);
    }
}
