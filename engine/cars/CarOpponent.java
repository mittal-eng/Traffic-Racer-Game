package engine.cars;

import game.sprites.CarSprite;
import engine.physics.CarBehaviour;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Leo on 09-Oct-15.
 */
public class CarOpponent
{
    private CarBehaviour behaviour;
    private CarSprite sprite;

    private int turningLeft_originalX = 0;
    private int turningLeft_targetX = 0;
    private int turningLeft_deltaDisplacement = 0;

    private int turningRight_originalX = 0;
    private int turningRight_targetX = 0;
    private int turningRight_deltaDisplacement = 0;

    public CarOpponent(CarBehaviour behaviour, BufferedImage image)
    {
        this.behaviour = behaviour;
        this.sprite = new CarSprite(image, behaviour.getLength(), behaviour.getWidth());
    }

    public void update(double velocityMS, double deltaTime)
    {
        this.setThrottlePosition(1);
        this.setBrakePosition(0);

        if (this.getEngineRPM() >= this.getRedlineRPM() - 2500)
            shiftUp();

        behaviour.update(deltaTime);

        double opponentDY = (velocityMS - this.getVelocity()) * deltaTime * 606.0 / 4.287;
        this.getSprite().moveY((int) opponentDY);
    }

    public CarSprite getSprite() {
        return sprite;
    }

    public void reset(int x, int y)
    {
        behaviour.reset();
        sprite.setStartX(x); sprite.setStartY(y);
    }

    public int getStartX() {
        return sprite.getStartX();
    }

    public int getStartY() {
        return sprite.getStartY();
    }

    public BufferedImage getImage() {
        return sprite.getImage();
    }

    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- HANDLING -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setThrottlePosition(double throttlePosition) {
        behaviour.setThrottlePosition(throttlePosition);
    }

    public void setBrakePosition(double brakePosition) {
        behaviour.setBrakePosition(brakePosition);
    }

    public void shiftDown() {
        behaviour.shiftDown();
    }

    public void shiftUp() {
        behaviour.shiftUp();
    }

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
                        turningLeft_originalX = 0;
                        turningLeft_targetX = 0;
                        turningLeft_deltaDisplacement = 0;
                        return 1;
                    }
                }
            }

            /* Compute step */
            turningLeft_deltaDisplacement = (int) ((turningLeft_targetX - turningLeft_originalX) * currentVelocity / 20.0 * deltaTime);

            return 0;
        }

        else
        {
            /* Compute step */
            turningLeft_deltaDisplacement = (int) ((turningLeft_targetX - turningLeft_originalX) * currentVelocity / 20.0 * deltaTime);

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
                        return 1;
                    }
                }
            }

            /* Compute step */
            turningRight_deltaDisplacement = (int) ((turningRight_targetX - turningRight_originalX) * currentVelocity / 20.0 * deltaTime);

            return 0;
        }

        else
        {
            /* Compute step */
            turningRight_deltaDisplacement = (int) ((turningRight_targetX - turningRight_originalX) * currentVelocity / 20.0 * deltaTime);

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

                return 1;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- GETTERS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public double getVelocity() {
        return behaviour.getVelocity();
    }

    public double getEngineRPM() {
        return behaviour.getEngineRPM();
    }

    public double getRedlineRPM() {
        return behaviour.getRedlineRPM();
    }

    public int getGear() {
        return behaviour.getGear();
    }

    public double getDistanceTravelled()
    {
        return behaviour.getDistanceTravelled();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
