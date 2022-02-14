package game.loops;

import game.traffics.DragRaceTraffic;
import engine.cars.CarOpponent;
import engine.cars.CarPlayer;
import game.sprites.RoadSprite;
import gui.panels.gameplay.DragRacePanel;
import engine.GameLoop;
import engine.InputController;
import game.Current;

/**
 * Created by Leo on 09-Oct-15.
 */
public class DragRaceLoop extends GameLoop
{
    private DragRacePanel dragRacePanel;

    /* Game objects */
    private RoadSprite roadSprite;
    private CarPlayer carPlayer;
    private CarOpponent carOpponent;
    private DragRaceTraffic dragRaceTraffic;

    private boolean raceStarted = false;

    /* Constructor */
    public DragRaceLoop(RoadSprite roadSprite, CarPlayer carPlayer, CarOpponent carOpponent, DragRaceTraffic dragRaceTraffic, DragRacePanel dragRacePanel)
    {
        this.roadSprite = roadSprite;
        this.carOpponent = carOpponent;
        this.carPlayer = carPlayer;
        this.dragRaceTraffic = dragRaceTraffic;
        this.dragRacePanel = dragRacePanel;

        carPlayer.reset(roadSprite.getLinesCoordinates().get(2), roadSprite.getHeight() - 900);
        carOpponent.reset(roadSprite.getLinesCoordinates().get(3), roadSprite.getHeight() - 900);
    }

    private void updateInput(double deltaTime)
    {
        /* Respond to throttle */
        if (InputController.THROTTLE_PRESSED)
            carPlayer.setThrottlePosition(1);
        else
            carPlayer.setThrottlePosition(0);

        /* Respond to brake */
        if (InputController.BRAKE_PRESSED)
            carPlayer.setBrakePosition(1);
        else
            carPlayer.setBrakePosition(0);

        /* Respond to shift down */
        if (InputController.SHIFT_DOWN_PRESSED)
        {
            carPlayer.shiftDown();
            InputController.SHIFT_DOWN_PRESSED = false;
        }

        /* Respond to shift up */
        if (InputController.SHIFT_UP_PRESSED)
        {
            carPlayer.shiftUp();
            InputController.SHIFT_UP_PRESSED = false;
        }
    }

    @Override
    protected void fixedUpdate()
    {
        /* Update input */
        updateInput(getDeltaTimeS());

        /* Update game objects */
        carPlayer.update(getDeltaTimeS());
        carOpponent.update(carPlayer.getVelocity(), getDeltaTimeS());
        dragRaceTraffic.update(getDeltaTimeS());
        roadSprite.update(carPlayer.getVelocity(), getDeltaTimeS());

        if (!raceStarted)
        {
            this.dragRaceTraffic.initialize();
            this.dragRacePanel.updateGameImage();
            this.dragRacePanel.repaint();
            this.dragRacePanel.drawCountdown();
            raceStarted = true;
        }

        Current.player.addTimeInRace((int) (this.getDeltaTimeS() * 1000));

        if (carPlayer.getDistanceTravelled() >= Current.DISTANCE || carOpponent.getDistanceTravelled() >= Current.DISTANCE)
        {
            /* If the carPlayer wins this race */
            if (carPlayer.getDistanceTravelled() >= carOpponent.getDistanceTravelled())
            {
                Current.player.addTime();
                Current.player.wonRace();
                Current.player.saveToFile();
                dragRacePanel.windowReference.setToDragRaceFinish(true, false);
                this.pauseGameLoop();
            }

            /* If the carOpponent wins this race */
            else
            {
                Current.player.addTime();
                Current.player.lostRace();
                Current.player.saveToFile();
                dragRacePanel.windowReference.setToDragRaceFinish(false, true);
                this.pauseGameLoop();
            }
        }
    }

    @Override
    protected void freeUpdate()
    {
        /* Update graphics if race started */
        if (raceStarted)
            dragRacePanel.updateGameImage();

        /* Repaint panel */
        dragRacePanel.repaint();
    }
}
