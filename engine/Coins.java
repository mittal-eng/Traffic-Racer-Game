package engine;

import engine.cars.CarPlayer;
import engine.graphics.GraphicsAdapter;
import game.collectables.*;
import game.traffics.ScoreAttackTraffic;
import engine.graphics.Sprite;
import game.sprites.RoadSprite;
import engine.math.MathFunctions;

import java.util.ArrayList;

/**
 * Created by Leo on 05-Nov-15.
 */
public class Coins
{
    private ArrayList<Coin> coinsOne = new ArrayList<Coin>();
    private ArrayList<Coin> coinsTwo = new ArrayList<Coin>();

    protected static int NUMBER_OF_SILVER_COINS = 0;
    protected static final int MIN_NUMBER_OF_SILVER_COINS = 5;
    protected static final int MAX_NUMBER_OF_SILVER_COINS = 10;

    protected static int NUMBER_OF_GOLD_COINS = 0;
    protected static final int MIN_NUMBER_OF_GOLD_COINS = 0;
    protected static final int MAX_NUMBER_OF_GOLD_COINS = 3;

    protected static int NUMBER_OF_PURPLE_COINS = 0;
    protected static final int MIN_NUMBER_OF_PURPLE_COINS = 0;
    protected static final int MAX_NUMBER_OF_PURPLE_COINS = 1;

    protected static int NUMBER_OF_DEATHS = 0;
    protected static final int MIN_NUMBER_OF_DEATHS = 0;
    protected static final int MAX_NUMBER_OF_DEATHS = 5;

    protected static final int MIN_DISTANCE_Y_BETWEEN_COINS = 250;

    /* Road and carPlayer */
    protected RoadSprite road;
    protected ScoreAttackTraffic traffic;
    protected CarPlayer carPlayer;

    public Coins(RoadSprite road, CarPlayer carPlayer, ScoreAttackTraffic traffic)
    {
        this.road = road;
        this.traffic = traffic;
        this.carPlayer = carPlayer;
    }

    public ArrayList<Coin> getCoins()
    {
        ArrayList<Coin> newCoins = new ArrayList<Coin>();
        newCoins.addAll(coinsOne);
        newCoins.addAll(coinsTwo);
        return newCoins;
    }

    public void update(double deltaTime)
    {
        updateCoinsOne(deltaTime);
        updateCoinsTwo(deltaTime);
    }

    public void initialize()
    {
        initializeOne(-road.getHeight());
        initializeTwo(-road.getHeight());

        Coins.moveCoins(coinsOne, -road.getHeight());
    }

    private void initializeOne(int deltaDisplacementY)
    {
        /* Determine number of coins */
        NUMBER_OF_SILVER_COINS = MathFunctions.randomInRange(MIN_NUMBER_OF_SILVER_COINS, MAX_NUMBER_OF_SILVER_COINS);
        NUMBER_OF_GOLD_COINS = MathFunctions.randomInRange(MIN_NUMBER_OF_GOLD_COINS, MAX_NUMBER_OF_GOLD_COINS);
        NUMBER_OF_PURPLE_COINS = MathFunctions.randomInRange(MIN_NUMBER_OF_PURPLE_COINS, MAX_NUMBER_OF_PURPLE_COINS);
        NUMBER_OF_DEATHS = MathFunctions.randomInRange(MIN_NUMBER_OF_DEATHS, MAX_NUMBER_OF_DEATHS);

        /* Create new list */
        coinsOne = new ArrayList<Coin>();

        /* Add new coins */
        for (int i = 0; i < NUMBER_OF_SILVER_COINS; i++)
            coinsOne.add(new SilverCoin());

        for (int i = 0; i < NUMBER_OF_GOLD_COINS; i++)
            coinsOne.add(new GoldCoin());

        for (int i = 0; i < NUMBER_OF_PURPLE_COINS; i++)
            coinsOne.add(new PurpleCoin());

        if (NUMBER_OF_DEATHS == MAX_NUMBER_OF_DEATHS - 1)
            coinsOne.add(new DeathCoin());

        coinsOne.add(new HealthCoin());

        /* Generate new coordinates */
        generateNewCoordinatesOne();

        Coins.moveCoins(coinsOne, deltaDisplacementY);
    }

    private void initializeTwo(int deltaDisplacementY)
    {
        /* Determine number of coins */
        NUMBER_OF_SILVER_COINS = MathFunctions.randomInRange(MIN_NUMBER_OF_SILVER_COINS, MAX_NUMBER_OF_SILVER_COINS);
        NUMBER_OF_GOLD_COINS = MathFunctions.randomInRange(MIN_NUMBER_OF_GOLD_COINS, MAX_NUMBER_OF_GOLD_COINS);
        NUMBER_OF_PURPLE_COINS = MathFunctions.randomInRange(MIN_NUMBER_OF_PURPLE_COINS, MAX_NUMBER_OF_PURPLE_COINS);
        NUMBER_OF_DEATHS = MathFunctions.randomInRange(MIN_NUMBER_OF_DEATHS, MAX_NUMBER_OF_DEATHS);

        /* Create new list */
        coinsTwo = new ArrayList<Coin>();

        /* Add new coins */
        for (int i = 0; i < NUMBER_OF_SILVER_COINS; i++)
            coinsTwo.add(new SilverCoin());

        for (int i = 0; i < NUMBER_OF_GOLD_COINS; i++)
            coinsTwo.add(new GoldCoin());

        for (int i = 0; i < NUMBER_OF_PURPLE_COINS; i++)
            coinsTwo.add(new PurpleCoin());

        if (NUMBER_OF_DEATHS == MAX_NUMBER_OF_DEATHS - 1)
            coinsTwo.add(new DeathCoin());

        coinsTwo.add(new HealthCoin());

        /* Generate new coordinates */
        generateNewCoordinatesTwo();

        Coins.moveCoins(coinsTwo, deltaDisplacementY);
    }

    private void updateCoinsOne(double deltaTime)
    {
        ArrayList<Coin> newCoins = new ArrayList<Coin>();
        double playerVelocity = carPlayer.getVelocity();
        int deltaDisplacement = (int) (playerVelocity * deltaTime * GraphicsAdapter.PIXELS_METER_RATIO);
        Coins.moveCoins(coinsOne, deltaDisplacement);

        /* If current coins are already below the visible screen - reinitialize them */
        if (Coins.findHighestCoinY(coinsOne) > road.getHeight())
            initializeOne(-road.getHeight());

        /* For each current coin - if carPlayer eats it, remove it from the list */
        for (Coin coin : coinsOne)
        {
            if (!Sprite.areOverlappedAtCenter(coin.getSprite(), carPlayer.getSprite(), 165))
            {
                newCoins.add(coin);
            }

            else
            {
                if (coin.getValue() != 0)
                {
                    carPlayer.increaseScore(coin.getValue());
                }

                if (coin.isHealth)
                {
                    carPlayer.pickedHealth();
                }

                if (coin.isDeath)
                {
                    carPlayer.pickedDeath();
                }
            }
        }

        coinsOne = newCoins;
    }


    private void updateCoinsTwo(double deltaTime)
    {
        ArrayList<Coin> newCoins = new ArrayList<Coin>();
        double playerVelocity = carPlayer.getVelocity();
        int deltaDisplacement = (int) (playerVelocity * deltaTime * GraphicsAdapter.PIXELS_METER_RATIO);
        Coins.moveCoins(coinsTwo, deltaDisplacement);

        /* If current coins are already below the visible screen - reinitialize them */
        if (Coins.findHighestCoinY(coinsOne) > road.getHeight())
            initializeTwo(-road.getHeight());

        /* For each current coin - if carPlayer eats it, remove it from the list */
        for (Coin coin : coinsTwo)
        {
            if (!Sprite.areOverlappedAtCenter(coin.getSprite(), carPlayer.getSprite(), 165))
            {
                newCoins.add(coin);
            }

            else
            {
                if (coin.getValue() != 0)
                {
                    carPlayer.increaseScore(coin.getValue());
                }

                if (coin.isHealth)
                {
                    carPlayer.pickedHealth();
                }

                if (coin.isDeath)
                {
                    carPlayer.pickedDeath();
                }
            }
        }

        coinsTwo = newCoins;
    }


    private static int findHighestCoinY(ArrayList<Coin> currentCoins)
    {
        int m = Integer.MAX_VALUE;

        for (Coin c : currentCoins)
        {
            int a = c.getStartY();

            if (a < m)
                m = a;
        }

        return m;
    }

    /* Shift all cars */
    private static void moveCoins(ArrayList<Coin> coins, int deltaDisplacement)
    {
        /* For each coin */
        for (Coin coin : coins)
        {
            /* Move it */
            coin.moveY(deltaDisplacement);
        }
    }

    private void generateNewCoordinatesOne()
    {
        boolean invalidPosition;
        int randomCoinCoordinateX;
        int randomCoinCoordinateY;
        int coinsNotOverlappingCount;

        /* Create new coins */
        ArrayList<Coin> newCoins = new ArrayList<Coin>();

        /* For each currently existing coin */
        for (Coin currentCoin : coinsOne)
        {
            /* Reset indicator */
            invalidPosition = true;

            /* While coin's position is not valid */
            while (invalidPosition)
            {
                /* Generate new coordinates */
                randomCoinCoordinateX = road.getRandomLine();
                randomCoinCoordinateY = MathFunctions.randomInRange(0, road.getHeight());

                /* Set new coordinates */
                currentCoin.setStartX(randomCoinCoordinateX);
                currentCoin.setStartY(randomCoinCoordinateY);

                 /* Counter of non-overlapping coins */
                coinsNotOverlappingCount = 0;

                /* For each new coin, check if it overlaps */
                for (Coin newCoin : newCoins)
                {
                    /* If the X coordinates are not the same */
                    if (newCoin.getStartX() != currentCoin.getStartX())
                    {
                        coinsNotOverlappingCount++;
                    }

                    /* If there is any overlapping - try new coordinates */
                    else if (Sprite.areAtSafeDistanceY(newCoin.getSprite(), currentCoin.getSprite(), MIN_DISTANCE_Y_BETWEEN_COINS))
                    {
                        coinsNotOverlappingCount++;
                    }
                }

                /* If the new position is valid - save it */
                if (coinsNotOverlappingCount == newCoins.size())
                {
                    invalidPosition = false;
                }
            }

            /* If the valid position was found - save this coin */
            newCoins.add(currentCoin);
        }

        /* After all the coins were processed */
        coinsOne = newCoins;
    }

    private void generateNewCoordinatesTwo()
    {
        boolean invalidPosition;
        int randomCoinCoordinateX;
        int randomCoinCoordinateY;
        int coinsNotOverlappingCount;

        /* Create new coins */
        ArrayList<Coin> newCoins = new ArrayList<Coin>();

        /* For each currently existing coin */
        for (Coin currentCoin : coinsTwo)
        {
            /* Reset indicator */
            invalidPosition = true;

            /* While coin's position is not valid */
            while (invalidPosition)
            {
                /* Generate new coordinates */
                randomCoinCoordinateX = road.getRandomLine();
                randomCoinCoordinateY = MathFunctions.randomInRange(0, road.getHeight());

                /* Set new coordinates */
                currentCoin.setStartX(randomCoinCoordinateX);
                currentCoin.setStartY(randomCoinCoordinateY);

                 /* Counter of non-overlapping coins */
                coinsNotOverlappingCount = 0;

                /* For each new coin, check if it overlaps */
                for (Coin newCoin : newCoins)
                {
                    /* If the X coordinates are not the same */
                    if (newCoin.getStartX() != currentCoin.getStartX())
                    {
                        coinsNotOverlappingCount++;
                    }

                    /* If there is any overlapping - try new coordinates */
                    else if (Sprite.areAtSafeDistanceY(newCoin.getSprite(), currentCoin.getSprite(), MIN_DISTANCE_Y_BETWEEN_COINS))
                    {
                        coinsNotOverlappingCount++;
                    }
                }

                /* If the new position is valid - save it */
                if (coinsNotOverlappingCount == newCoins.size())
                {
                    invalidPosition = false;
                }
            }

            /* If the valid position was found - save this coin */
            newCoins.add(currentCoin);
        }

        /* After all the coins were processed */
        coinsTwo = newCoins;
    }
}
