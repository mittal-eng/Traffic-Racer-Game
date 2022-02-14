package gui.panels.gameplay;

import engine.graphics.Gauges;
import gui.panels.GamePanel;
import game.traffics.DragRaceTraffic;
import game.canvases.DragRaceCanvas;
import engine.cars.CarOpponent;
import engine.cars.CarPlayer;
import game.sprites.RoadSprite;
import gui.GameFrame;
import game.loops.DragRaceLoop;

/**
 * Created by Leo on 08-Oct-15.
 */
public class DragRacePanel extends GamePanel
{
   /* Game objects */
    private RoadSprite roadSprite;
    private CarPlayer carPlayer;
    private Gauges display;
    private CarOpponent carOpponent;
    private DragRaceTraffic dragRaceTraffic;

    /* Constructor */
    public DragRacePanel(RoadSprite roadSprite, CarPlayer carPlayer, CarOpponent carOpponent, GameFrame windowReference)
    {
        /* Deny focus */
        this.setFocusable(false);

        /* Store window */
        this.windowReference = windowReference;

        /* Store game objects */
        this.roadSprite = roadSprite;
        this.carPlayer = carPlayer;
        this.carOpponent = carOpponent;
        this.display = new Gauges();

        /* Initialize traffic */
        this.dragRaceTraffic = new DragRaceTraffic(roadSprite, carPlayer);
        dragRaceTraffic.initialize();

        /* Initialize game loop */
        this.gameLoop = new DragRaceLoop(roadSprite, carPlayer, carOpponent, dragRaceTraffic, this);

        /* Initialize canvas */
        this.canvas = new DragRaceCanvas(roadSprite, carPlayer, carOpponent, dragRaceTraffic, display);
    }
}
