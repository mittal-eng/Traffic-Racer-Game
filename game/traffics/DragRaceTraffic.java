package game.traffics;

import engine.Traffic;
import engine.TrafficHelper;
import engine.cars.CarPlayer;
import engine.cars.CarTraffic;
import game.sprites.RoadSprite;
import engine.graphics.Sprite;
import engine.math.MathFunctions;

import java.util.ArrayList;

/**
 * Created by Leo on 10-Oct-15.
 */
public class DragRaceTraffic extends Traffic
{
    public DragRaceTraffic(RoadSprite road, CarPlayer carPlayer)
    {
        super(road, carPlayer, SCROLL_UP);
    }

    @Override
    public boolean resetCondition()
    {
        return TrafficHelper.findMinY(this.getCurrentCars()) > road.getHeight();
    }

    @Override
    protected void updateCarCoordinate(CarTraffic trafficCar, double deltaTime)
    {
        int deltaDisplacementPX = (int) ((carPlayer.getVelocity() - MathFunctions.convertKMHtoMS(trafficCar.getVelocity())) * deltaTime * 606.0 / 4.287);
        trafficCar.moveY(deltaDisplacementPX);
    }

    @Override
    protected void generateNewCoordinates()
    {
        /* Create new cars array */
        ArrayList<CarTraffic> newTrafficCars = new ArrayList<CarTraffic>();

        int freeX_1 = road.getLinesCoordinates().get(2);
        int freeX_2 = road.getLinesCoordinates().get(3);

        for (CarTraffic trafficCar : currentCars)
        {
            /* Generate new coordinates */
            int randomCarCoordinateX = road.getRandomLine();
            int randomCarCoordinateY = MathFunctions.randomInRange(0, road.getHeight() - trafficCar.getHeight());

            while ((randomCarCoordinateX == freeX_1) || (randomCarCoordinateX == freeX_2))
                randomCarCoordinateX = road.getRandomLine();

            trafficCar.setStartX(randomCarCoordinateX);
            trafficCar.setStartY(randomCarCoordinateY);

            boolean validPosition = false;

            /* While its position is not valid */
            int numberOfRepetitions = 0;
            while (!validPosition)
            {
                numberOfRepetitions++;
                if (numberOfRepetitions > 100)
                {
                    currentCars = newTrafficCars;
                    return;
                }

                randomCarCoordinateX = road.getRandomLine();

                while ((randomCarCoordinateX == freeX_1) || (randomCarCoordinateX == freeX_2))
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
                    if (!Sprite.areAtSafeDistanceY(newlyPlacedCar.getSprite(), trafficCar.getSprite(), 10))
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
