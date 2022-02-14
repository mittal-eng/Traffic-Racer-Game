package engine.cars;

import game.sprites.CarSprite;
import engine.InputController;
import engine.math.MathFunctions;
import engine.physics.CarBehaviour;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class that represents a player's car
 * @see CarBehaviour
 * @see CarSprite
 * @author Lev Ertuna
 */
public class CarPlayer
{
    private CarBehaviour behaviour;
    private CarSprite sprite;
    private int health = 100;
    private int score = 0;

    /**
     * Constructor
     * @param behaviour behaviour of the car
     * @param image image of the car
     */
    public CarPlayer(CarBehaviour behaviour, BufferedImage image)
    {
        this.behaviour = behaviour;
        this.sprite = new CarSprite(image, behaviour.getLength(), behaviour.getWidth());
    }

    /**
     * Reset car to initial values
     * @param x starting X coordinate
     * @param y starting Y coordinate
     */
    public void reset(int x, int y)
    {
        InputController.reset();
        this.behaviour.reset(); this.health = 100; this.score = 0;
        this.sprite.setStartX(x); this.sprite.setStartY(y);
    }

    /**
     * Update of the car
     * @param deltaTime time
     */
    public void update(double deltaTime)
    {
        /* If the player is dead */
        if (health <= 0)
        {
            /* Shift down */
            behaviour.shiftDown(); behaviour.shiftDown(); behaviour.shiftDown(); behaviour.shiftDown(); behaviour.shiftDown(); behaviour.shiftDown(); behaviour.shiftDown();

            /* Release throttle and apply brakes */
            behaviour.setThrottlePosition(0); behaviour.setBrakePosition(1);
        }

        /* Update behaviour */
        behaviour.update(deltaTime);
    }

    /**
     * Is this car overlapping with a traffic car?
     * @param carTraffic traffic car
     * @return whether this player's car overlaps with traffic car
     */
    public boolean isOverlappingWith(CarTraffic carTraffic)
    {
        return (MathFunctions.rectanglesAreOverlapping(this.getSprite().getRectangle(), carTraffic.getSprite().getRectangle()));
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------ Health and Score Wrappers ---------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Decrease health by 10 points if its not below 10
     * Call when a car is crashed
     */
    public void decreaseHealth()
    {
        if (this.health >= 10)
            this.health -= 10;
    }

    /**
     * Reset health to 100
     * Call when a car picked health icon
     */
    public void pickedHealth()
    {
        this.health = 100;
    }

    /**
     * Reset health to 0
     * Call when a car picked death icon
     */
    public void pickedDeath()
    {
        this.health = 0;
    }

    /**
     * Increase score
     * Call when a car picked coin icon
     * @param score value of icon
     */
    public void increaseScore(int score)
    {
        this.score += score;
    }

    /**
     * Getter
     * @return health
     */
    public int getHealth()
    {
        return this.health;
    }

    /**
     * Getter
     * @return score
     */
    public int getScore()
    {
        return score;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- Turning ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private int turningLeft_originalX = 0;
    private int turningLeft_targetX = 0;
    private int turningLeft_deltaDisplacement = 0;

    private int turningRight_originalX = 0;
    private int turningRight_targetX = 0;
    private int turningRight_deltaDisplacement = 0;

    public int turnLeft(ArrayList<Integer> linesCoordinates, double deltaTime, double currentVelocity)
    {
        /* If this method is called for the first time */
        if (turningLeft_originalX == 0)
        {
            turningLeft_originalX = (int) sprite.getStartX();

            /* Find target X*/
            for (int i = 0; i < linesCoordinates.size(); i++)
            {
                if (linesCoordinates.get(i) == turningLeft_originalX)
                {
                    if (i != 0)
                        turningLeft_targetX = linesCoordinates.get(i - 1);

                    /* If the turn is not needed */
                    else {
                        turningRight_originalX = 0;
                        turningRight_targetX = 0;
                        turningRight_deltaDisplacement = 0;
                        turningLeft_originalX = 0;
                        turningLeft_targetX = 0;
                        turningLeft_deltaDisplacement = 0;
                        return 1;
                    }
                }
            }

            /* Compute step */
            turningLeft_deltaDisplacement = (int) ((turningLeft_targetX - turningLeft_originalX) * currentVelocity / 6.0 * deltaTime);

            return 0;
        }

        else
        {
            /* Compute step */
            turningLeft_deltaDisplacement = (int) ((turningLeft_targetX - turningLeft_originalX) * currentVelocity / 6.0 * deltaTime);

            /* If the target is not reached yet */
            if (sprite.getStartX() > turningLeft_targetX) {
                sprite.moveX(turningLeft_deltaDisplacement);

                return 0;
            }

            /* If the turn was finished */
            else {
                /* Reset carPlayer's position to avoid conflicts */
                sprite.setStartX(turningLeft_targetX);

                /* Reset all the variables */
                turningRight_originalX = 0;
                turningRight_targetX = 0;
                turningRight_deltaDisplacement = 0;
                turningLeft_originalX = 0;
                turningLeft_targetX = 0;
                turningLeft_deltaDisplacement = 0;

                return 1;
            }
        }
    }

    public int stopTurnLeft(double deltaTime, double currentVelocity)
    {
        /* If this method is called for the first time */
        if (turningRight_originalX == 0)
        {
            turningRight_originalX = (int) sprite.getStartX();
            turningRight_targetX = turningLeft_originalX;

            /* Compute step */
            turningRight_deltaDisplacement = (int) ((turningRight_targetX - turningRight_originalX) * currentVelocity / 3.0 * deltaTime);

            return 0;
        }

        else
        {
            /* Compute step */
            turningRight_deltaDisplacement = (int) ((turningRight_targetX - turningRight_originalX) * currentVelocity / 3.0 * deltaTime);

            /* If the target is not reached yet */
            if (sprite.getStartX() < turningRight_targetX) {
                sprite.moveX(turningRight_deltaDisplacement);

                return 0;
            }

            /* If the turn was finished */
            else {
                /* Reset carPlayer's position to avoid conflicts */
                sprite.setStartX(turningRight_targetX);

                /* Reset all the variables */
                turningRight_originalX = 0;
                turningRight_targetX = 0;
                turningRight_deltaDisplacement = 0;
                turningLeft_originalX = 0;
                turningLeft_targetX = 0;
                turningLeft_deltaDisplacement = 0;

                return 1;
            }
        }
    }

    public int turnRight(ArrayList<Integer> linesCoordinates, double deltaTime, double currentVelocity)
    {
        /* If this method is called for the first time */
        if (turningRight_originalX == 0)
        {
            turningRight_originalX = (int) sprite.getStartX();

            /* Find target X*/
            for (int i = 0; i < linesCoordinates.size(); i++)
            {
                if (linesCoordinates.get(i) == turningRight_originalX)
                {
                    if (i != linesCoordinates.size() - 1)
                        turningRight_targetX = linesCoordinates.get(i + 1);

                    /* If the turn is not needed */
                    else {
                        turningRight_originalX = 0;
                        turningRight_targetX = 0;
                        turningRight_deltaDisplacement = 0;
                        turningLeft_originalX = 0;
                        turningLeft_targetX = 0;
                        turningLeft_deltaDisplacement = 0;
                        return 1;
                    }
                }
            }

            /* Compute step */
            turningRight_deltaDisplacement = (int) ((turningRight_targetX - turningRight_originalX) * currentVelocity / 6.0 * deltaTime);

            return 0;
        }

        else
        {
            /* Compute step */
            turningRight_deltaDisplacement = (int) ((turningRight_targetX - turningRight_originalX) * currentVelocity / 6.0 * deltaTime);

            /* If the target is not reached yet */
            if (sprite.getStartX() < turningRight_targetX) {
                sprite.moveX(turningRight_deltaDisplacement);

                return 0;
            }

            /* If the turn was finished */
            else {
                /* Reset carPlayer's position to avoid conflicts */
                sprite.setStartX(turningRight_targetX);

                /* Reset all the variables */
                turningRight_originalX = 0;
                turningRight_targetX = 0;
                turningRight_deltaDisplacement = 0;
                turningLeft_originalX = 0;
                turningLeft_targetX = 0;
                turningLeft_deltaDisplacement = 0;

                return 1;
            }
        }
    }

    public int stopTurnRight(double deltaTime, double currentVelocity)
    {
        /* If this method is called for the first time */
        if (turningLeft_originalX == 0)
        {
            turningLeft_originalX = (int) sprite.getStartX();

            turningLeft_targetX = turningRight_originalX;

            /* Compute step */
            turningLeft_deltaDisplacement = (int) ((turningLeft_targetX - turningLeft_originalX) * currentVelocity / 3.0 * deltaTime);

            return 0;
        }

        else
        {
            /* Compute step */
            turningLeft_deltaDisplacement = (int) ((turningLeft_targetX - turningLeft_originalX) * currentVelocity / 3.0 * deltaTime);

            /* If the target is not reached yet */
            if (sprite.getStartX() > turningLeft_targetX) {
                sprite.moveX(turningLeft_deltaDisplacement);

                return 0;
            }

            /* If the turn was finished */
            else {
                /* Reset carPlayer's position to avoid conflicts */
                sprite.setStartX(turningLeft_targetX);

                /* Reset all the variables */
                turningRight_originalX = 0;
                turningRight_targetX = 0;
                turningRight_deltaDisplacement = 0;
                turningLeft_originalX = 0;
                turningLeft_targetX = 0;
                turningLeft_deltaDisplacement = 0;

                return 1;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ Behaviour Wrappers ----------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setThrottlePosition(double throttlePosition)
    {
        behaviour.setThrottlePosition(throttlePosition);
    }

    public void setBrakePosition(double brakePosition)
    {
        behaviour.setBrakePosition(brakePosition);
    }

    public void shiftDown()
    {
        behaviour.shiftDown();
    }

    public void shiftUp()
    {
        behaviour.shiftUp();
    }

    public double getVelocity()
    {
        return behaviour.getVelocity();
    }

    public double getEngineRPM()
    {
        return behaviour.getEngineRPM();
    }

    public double getRedlineRPM()
    {
        return behaviour.getRedlineRPM();
    }

    public int getGear()
    {
        return behaviour.getGear();
    }

    public double getDistanceTravelled()
    {
        return behaviour.getDistanceTravelled();
    }

    public double getMass()
    {
        return behaviour.getMass();
    }

    public void setVelocity(double velocityMS)
    {
        this.behaviour.setVelocity(velocityMS);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ Sprite Wrappers -------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public int getHeightPX()
    {
        return sprite.getHeight();
    }

    public int getWidthPX()
    {
        return sprite.getWidth();
    }

    public int getStartX()
    {
        return sprite.getStartX();
    }

    public int getStartY()
    {
        return sprite.getStartY();
    }

    public int getEndX()
    {
        return sprite.getEndX();
    }

    public int getEndY()
    {
        return sprite.getEndY();
    }

    public void moveX(int dx)
    {
        sprite.moveX(dx);
    }

    public void moveY(int dy)
    {
        sprite.moveY(dy);
    }

    public void setStartX(int x)
    {
        sprite.setStartX(x);
    }

    public void setStartY(int y)
    {
        sprite.setStartY(y);
    }

    public BufferedImage getImage()
    {
        return sprite.getImage();
    }

    public CarSprite getSprite()
    {
        return sprite;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
