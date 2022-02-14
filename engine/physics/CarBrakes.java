package engine.physics;

/**
 * Created by Leo on 19-Sep-15.
 */

/*
    Representation of a braking system, computes braking acceleration.
 */
public class CarBrakes
{
    /* Braking Data */
    private double initialVelocity = 26.8;                      // Velocity from which the carPlayer was brought to stop (in m/s; default value is equal to 100 km/h)
    private double brakingDistance;                             // How long it took the carPlayer to come to stop (in m)
    private double brakingAcceleration;                         // Calculated acceleration

    /* Constructor */
    public CarBrakes(double brakingDistance)
    {
        this.brakingDistance = brakingDistance;
        this.brakingAcceleration = Math.pow(initialVelocity, 2.0) / (2.0 * brakingDistance);
    }

    /* Getter */
    public double getBrakingAcceleration()
    {
        return brakingAcceleration;
    }
}
