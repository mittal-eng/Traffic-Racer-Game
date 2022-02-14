package engine;

import engine.cars.CarPlayer;
import engine.cars.CarTraffic;
import engine.math.MathFunctions;
import engine.math.Rectangle;
import game.storage.StorageTraffic;

import java.util.ArrayList;

/**
 * Helper class to control Traffic in the game
 * @see Traffic
 * @see CarTraffic
 * @author Lev Ertuna
 */
public abstract class TrafficHelper
{
    /**
     * Fill a list with possible cars
     * @param possibleCars a list to be filled
     */
    public static void fillPossibleCars(ArrayList<CarTraffic> possibleCars)
    {
        possibleCars.clear();

        possibleCars.add(StorageTraffic.TRAFFIC_PORSCHE_911_1968);
        possibleCars.add(StorageTraffic.TRAFFIC_PONTIAC_GTO_1966);
        possibleCars.add(StorageTraffic.TRAFFIC_FORD_MUSTANG_GT390_1968);
        possibleCars.add(StorageTraffic.TRAFFIC_LAMBORGHINI_COUNTACH_LP400);
        possibleCars.add(StorageTraffic.TRAFFIC_FERRARI_TESTAROSSA);
        possibleCars.add(StorageTraffic.TRAFFIC_BMW_M1);
        possibleCars.add(StorageTraffic.TRAFFIC_AUSTIN_COOPER_S);
        possibleCars.add(StorageTraffic.TRAFFIC_CITROEN_2CV_1959);
        possibleCars.add(StorageTraffic.TRAFFIC_CITROEN_DS_21_1966);
        possibleCars.add(StorageTraffic.TRAFFIC_VOLKSWAGEN_BEETLE_1948);
        possibleCars.add(StorageTraffic.TRAFFIC_ROLLS_ROYCE_SILVER_CLOUD_II_1959);
    }

    /**
     * Fill a list with current cars
     * @param currentCars a list to be filled
     * @param possibleCars a list of possible car, from where new cars will be picked
     * @param numberOfCars number of cars to fill
     * @param minSpeed minimum speed of the car in KM/H
     * @param maxSpeed maximum speed of the car in KM/H
     */
    public static void fillCurrentCars(ArrayList<CarTraffic> currentCars, ArrayList<CarTraffic> possibleCars, int numberOfCars, int minSpeed, int maxSpeed)
    {
        currentCars.clear();

        /* For each car */
        for (int i = 0; i < numberOfCars; i++)
        {
            /* Add a random car */
            currentCars.add(possibleCars.get(MathFunctions.randomInRange(0, possibleCars.size())).clone());
        }

        /* For each car */
        for (int i = 0; i < numberOfCars; i++)
        {
            /* Set random velocity */
            currentCars.get(i).setVelocity(MathFunctions.randomInRange(minSpeed, maxSpeed));
        }
    }

    /**
     * Lock speed of cars
     * @param currentCars a list of cars
     * @param deltaTime time
     * @param minSpeed minimum speed of the car in KM/H
     * @param maxSpeed maximum speed of the car in KM/H
     */
    public static void controlSpeed(ArrayList<CarTraffic> currentCars, double deltaTime, int minSpeed, int maxSpeed)
    {
        for (int i = 0; i < currentCars.size(); i++)
        {
            if (currentCars.get(i).getVelocity() < minSpeed)
                TrafficHelper.applyThrottle(currentCars.get(i));

            else
                TrafficHelper.applyBalanceThrottle(currentCars.get(i), deltaTime, minSpeed, maxSpeed);
        }
    }

    /**
     * Prevent cars from crashing
     * @param currentCars a list of cars
     * @param carPlayer player's car
     * @param safeDistance acceptable safe distance
     */
    public static void controlCrashes(ArrayList<CarTraffic> currentCars, CarPlayer carPlayer, int safeDistance)
    {
        /* For each t1 */
        for (CarTraffic t1 : currentCars)
        {
            /* For each t2 */
            for (CarTraffic t2 : currentCars)
            {
                /* If these are different cars */
                if (t2 != t1)
                {
                    /* If they are in the same line */
                    if (t1.getStartX() == t2.getStartX())
                    {
                        /* If they are not at safe distance*/
                        if (!CarTraffic.areAtSafeDistanceY(t1, t2, safeDistance))
                        {
                            TrafficHelper.preventCollision(t1, t2);
                        }
                    }
                }
            }

            /* Prevent collision with carPlayer */
            if (carPlayer != null)
            {
                /* Represent carPlayer car and traffic car as rectangles */
                Rectangle carTrafficRectangle = t1.getSprite().getRectangle();
                Rectangle carPlayerRectangle = carPlayer.getSprite().getRectangle();

                /* If they are in the same lane */
                if (carTrafficRectangle.getStartX() == carPlayerRectangle.getStartX())
                {
                    /* If they are not at safe distance */
                    if (!MathFunctions.rectanglesAreAtSafeDistanceY(carTrafficRectangle, carPlayerRectangle, safeDistance))
                    {
                        /* If traffic car is following carPlayer car */
                        if (MathFunctions.rectanglesFindUpper(carTrafficRectangle, carPlayerRectangle) == carPlayerRectangle)
                        {
                            /* Force braking on traffic */
                            TrafficHelper.applyBrakes(t1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Change coordinates of all cars in Y axis
     * @param currentCars a list of cars
     * @param deltaDisplacementPX amount of PX in Y axis
     */
    public static void moveCars(ArrayList<CarTraffic> currentCars, int deltaDisplacementPX)
    {
        for (int i = 0; i < currentCars.size(); i++)
        {
            currentCars.get(i).moveY(deltaDisplacementPX);
        }
    }



    /**
     * Brake
     * @param carTraffic car
     */
    private static void applyBrakes(CarTraffic carTraffic)
    {
        carTraffic.setVelocity(carTraffic.getVelocity() - carTraffic.getVelocity() * 0.5);
    }

    /**
     * Accelerate
     * @param carTraffic car
     */
    private static void applyThrottle(CarTraffic carTraffic)
    {
        carTraffic.setVelocity(carTraffic.getVelocity() + carTraffic.getVelocity() * 0.01);
    }

    /**
     * Balance car acceleration within given speed limits
     * @param carTraffic car
     * @param deltaTime time
     * @param minSpeed minimum speed of the car in KM/H
     * @param maxSpeed maximum speed of the car in KM/H
     */
    private static void applyBalanceThrottle(CarTraffic carTraffic, double deltaTime, int minSpeed, int maxSpeed)
    {
        carTraffic.setVelocity(MathFunctions.smoothUpperClamp(carTraffic.getVelocity(), minSpeed, maxSpeed, deltaTime));
    }

    /**
     * Prevent two cars from hitting each other
     * @param t1 car one
     * @param t2 car two
     */
    private static void preventCollision(CarTraffic t1, CarTraffic t2)
    {
        /* If t1 is on top */
        if (CarTraffic.findUpper(t1, t2) == t1)
        {
            if (t1.getVelocity() > t2.getVelocity())
            {
                t1.setVelocity(t1.getVelocity() + 10);
                t2.setVelocity(t2.getVelocity() - 15);
            }

            else
            {
                t1.setVelocity(t1.getVelocity() + 25);
                t2.setVelocity(t2.getVelocity() - 15);
            }
        }

        /* If t2 is on top */
        else if (CarTraffic.findUpper(t1, t2) == t2)
        {
            if (t2.getVelocity() > t1.getVelocity())
            {
                t2.setVelocity(t2.getVelocity() + 10);
                t1.setVelocity(t1.getVelocity() - 15);
            }

            else
            {
                t2.setVelocity(t1.getVelocity() + 25);
                t1.setVelocity(t2.getVelocity() - 15);
            }
        }
    }



    /**
     * Find minimum Y coordinate of cars' positions
     * @param currentCars a list of cars
     * @return minimum Y coordinate
     */
    public static int findMinY(ArrayList<CarTraffic> currentCars)
    {
        int minimum = Integer.MAX_VALUE;

        for (CarTraffic carTraffic : currentCars)
        {
            int currentStartY = carTraffic.getStartY();

            if (currentStartY < minimum)
                minimum = currentStartY;
        }

        return minimum;
    }

    /**
     * Find maximum Y coordinate of cars' positions
     * @param currentCars a list of cars
     * @return maximum Y coordinate
     */
    public static int findMaxY(ArrayList<CarTraffic> currentCars)
    {
        int maximum = Integer.MIN_VALUE;

        for (CarTraffic carTraffic : currentCars)
        {
            int currentEndY = carTraffic.getEndY();

            if (currentEndY > maximum)
                maximum = currentEndY;
        }

        return maximum;
    }
}
