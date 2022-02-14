package engine;

import engine.cars.CarPlayer;
import engine.cars.CarTraffic;
import engine.math.MathFunctions;
import game.sprites.RoadSprite;

import java.util.ArrayList;

/**
 * Traffic
 * @see TrafficHelper
 * @see CarTraffic
 * @author Lev Ertuna
 */
public abstract class Traffic
{
    /* Constants */
    protected static final int MIN_NUMBER_OF_CARS = 4;
    protected static final int MAX_NUMBER_OF_CARS = 8;
    protected static final int MIN_VELOCITY = 70;
    protected static final int MAX_VELOCITY = 120;
    protected static final int SAFE_DISTANCE = 50;
    protected static final int SCROLL_DOWN = 1111;
    protected static final int SCROLL_UP = 2222;

    /* Cars */
    protected int scrollingType;
    protected int currentNumberOfCars = 0;
    protected ArrayList<CarTraffic> currentCars = new ArrayList<CarTraffic>();
    protected ArrayList<CarTraffic> possibleCars = new ArrayList<CarTraffic>();

    /* Road and player */
    protected RoadSprite road;
    protected CarPlayer carPlayer;



    /**
     * Constructor
     * @param road ROAD_SPRITE
     * @param carPlayer player
     * @param scrollingType type of scrolling (either SCROLL_DOWN or SCROLL_UP)
     */
    public Traffic(RoadSprite road, CarPlayer carPlayer, int scrollingType)
    {
        /* Save ROAD_SPRITE and carPlayer */
        this.road = road;
        this.carPlayer = carPlayer;
        this.scrollingType = scrollingType;

        /* Create possible cars */
        TrafficHelper.fillPossibleCars(possibleCars);
    }

    /**
     * Getter
     * @return current cars
     */
    public ArrayList<CarTraffic> getCurrentCars()
    {
        return currentCars;
    }

    /**
     * Initialization
     */
    public void initialize()
    {
        /* Mark all as invalid */
        for (CarTraffic trafficCar : currentCars)
            trafficCar.setValid(false);

        /* Determine number of cars */
        currentNumberOfCars = MathFunctions.randomInRange(MIN_NUMBER_OF_CARS, MAX_NUMBER_OF_CARS);

        /* Generate new cars */
        TrafficHelper.fillCurrentCars(currentCars, possibleCars, currentNumberOfCars, MIN_VELOCITY, MAX_VELOCITY);

        /* Mark all as invalid */
        for (CarTraffic trafficCar : currentCars)
            trafficCar.setValid(false);

        /* Generate new coordinates */
        this.generateNewCoordinates();

        /* Mark all as valid */
        for (CarTraffic trafficCar : currentCars)
            trafficCar.setValid(true);

        /* Set initial distance based on scrolling type */
        if (this.scrollingType == SCROLL_DOWN)
            TrafficHelper.moveCars(this.currentCars, road.getHeight());
        else
            TrafficHelper.moveCars(this.currentCars, -road.getHeight());
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ Updates ---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Update of this traffic
     * @param deltaTime time
     */
    public void update(double deltaTime) {
        /* Update physics and coordinates */
        this.updatePhysics(deltaTime);
        this.updateCoordinates(deltaTime);

        /* Perform a reset if needed */
        if (this.resetCondition())
            this.initialize();
    }

    /**
     * Update physics of current cars (control speed and crashes)
     * @param deltaTime time
     */
    private void updatePhysics(double deltaTime)
    {
        TrafficHelper.controlSpeed(currentCars, deltaTime, MIN_VELOCITY, MAX_VELOCITY);
        TrafficHelper.controlCrashes(currentCars, carPlayer, 100);
    }

    /**
     * Update coordinates of current cars
     * @param deltaTime time
     */
    private void updateCoordinates(double deltaTime)
    {
        for (CarTraffic t : currentCars)
            updateCarCoordinate(t, deltaTime);

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * How should the car coordinates be updated?
     * @param trafficCar car
     * @param deltaTime time
     */
    protected abstract void updateCarCoordinate(CarTraffic trafficCar, double deltaTime);

    /**
     * How should the cars be placed?
     */
    protected abstract void generateNewCoordinates();

    /**
     * When should we regenerate cars?
     * @return whether new cars must be generated
     */
    protected abstract boolean resetCondition();
}
