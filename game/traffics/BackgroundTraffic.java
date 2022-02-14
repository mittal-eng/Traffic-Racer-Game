package game.traffics;

import engine.Traffic;
import engine.TrafficHelper;
import engine.cars.CarTraffic;
import engine.graphics.GraphicsAdapter;
import game.sprites.RoadSprite;
import engine.graphics.Sprite;
import engine.math.MathFunctions;

import java.util.ArrayList;

/**
 * Created by Leo on 08-Oct-15.
 */
public class BackgroundTraffic extends Traffic
{
    public BackgroundTraffic(RoadSprite road)
    {
        super(road, null, SCROLL_DOWN);
    }

    @Override
    protected boolean resetCondition() {
        return TrafficHelper.findMaxY(this.getCurrentCars()) < 0;
    }

    @Override
    protected void updateCarCoordinate(CarTraffic trafficCar, double deltaTime)
    {
        int deltaDisplacementPX = (int) ((MathFunctions.convertKMHtoMS(trafficCar.getVelocity())) * deltaTime * GraphicsAdapter.PIXELS_METER_RATIO);
        trafficCar.moveY(-deltaDisplacementPX);
    }

    @Override
    protected void generateNewCoordinates()
    {
        int randomCarCoordinateX;
        int randomCarCoordinateY;
        boolean validPosition;

        /* Create new cars array */
        ArrayList<CarTraffic> newTrafficCars = new ArrayList<CarTraffic>();

        for (CarTraffic trafficCar : currentCars)
        {
            validPosition = false;

            /* While its position is not valid */
            while (!validPosition)
            {
                randomCarCoordinateX = road.getRandomLine();
                randomCarCoordinateY = MathFunctions.randomInRange(0, road.getHeight() - trafficCar.getHeight());

                trafficCar.setStartX(randomCarCoordinateX);
                trafficCar.setStartY(randomCarCoordinateY);

                 /* Counter of non-overlapping cars */
                int carsNotOverlappingCount = 0;

                /* For each carPlayer with new position */
                for (CarTraffic newlyPlacedCar : newTrafficCars)
                {
                    if (newlyPlacedCar.getStartX() != trafficCar.getStartX())
                    {
                        carsNotOverlappingCount++;
                        continue;
                    }

                    /* If there is any overlapping - try new coordinates */
                    if (!Sprite.areAtSafeDistanceY(newlyPlacedCar.getSprite(), trafficCar.getSprite(), SAFE_DISTANCE))
                        break;

                    /* If not - increase counter */
                    else
                        carsNotOverlappingCount++;
                }

                /* If the new position is valid - save it */
                if (carsNotOverlappingCount == newTrafficCars.size())
                    validPosition = true;

                else
                    continue;
            }

            /* If the valid position was found - save this carPlayer */
            newTrafficCars.add(trafficCar);

        }

        /* After all the cars were processed */
        currentCars = newTrafficCars;
    }
}
