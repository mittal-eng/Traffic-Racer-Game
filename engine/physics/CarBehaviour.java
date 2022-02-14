package engine.physics;

import engine.math.MathFunctions;

/**
 * Created by Leo on 19-Sep-15.
 */

/*
    Simulation of carPlayer physics
 */
public class CarBehaviour
{
    /* Input */
    private double throttlePosition;
    private double brakePosition;

    /* Car Data */
    private CarTorqueCurve torqueCurve;
    private CarTransmission transmission;
    private CarBrakes brakes;

    /* Car Body Data */
    private double mass;                                                // Mass of the carPlayer in kg
    private double wheelRadius;                                         // Radius of the carPlayer's wheel in m
    private double length, width, height;                               // Length Width and height of the carPlayer
    private double frontalArea;                                         // Car frontal area

    /* Physics Coefficients */
    private double dragCoefficient = PhysicsConstants.DRAG_COEFFICIENT_SPORT_CARS;
    private double rollingFrictionCoefficient = PhysicsConstants.ROLLING_FRICTION_COEFFICIENT_CAR_TIRE;
    private double kineticFrictionCoefficient = PhysicsConstants.KINETIC_FRICTION_COEFFICIENT_CAR_TIRE;
    private double engineBrakingCoefficient = PhysicsConstants.ENGINE_BRAKING_COEFFICIENT;

    /* Current Data */
    private double velocity;                                            // Current velocity
    private double maxVelocity;                                         // Maximum possible velocity for current gear
    private double acceleration;                                        // Current acceleration
    private int gear = 1;                                               // Current gear
    private double distanceTravelled = 0;

    /* Differential Data */
    private double deltaVelocity;                                       // Change in velocity
    private double deltaDisplacement;                                   // Change in displacement

    /* Different forces and torques acting on the carPlayer */
    private double normalForce;                                         // Normal force
    private double rollingFrictionForce;                                // Rolling friction force
    private double tractionForce;                                       // Traction force
    private double maxTractionForce;                                    // Maximum possible traction force
    private double dragForce;                                           // Drag resistance force
    private double netForce;                                            // Net force
    private double maxWheelTorque;                                      // Maximum torque acting on wheels
    private double actualWheelTorque;                                   // Actual torque acting on wheels
    private double wheelAngularVelocity;                                // Angular velocity of wheels
    private double enginePower;                                         // engine power
    private double engineRPM;                                           // Current RPM
    private double engineTorque;                                        // engine torque
    private double engineBrakingTorque;                                 // engine braking torque
    private double engineAngularVelocity;                               // Angular velocity of the engine
    private double maxBrakingForce;                                     // Maximum possible braking force
    private double actualBrakingForce;                                  // Actual braking force



    /* Constructor */
    public CarBehaviour(double mass, double wheelRadius, double length, double width, double height, CarTorqueCurve torqueCurve, CarTransmission transmission, CarBrakes brakes, double dragCoefficient)
    {
        this.mass = mass;
        this.wheelRadius = wheelRadius;
        this.length = length;
        this.width = width;
        this.height = height;
        this.torqueCurve = torqueCurve;
        this.transmission = transmission;
        this.brakes = brakes;
        this.dragCoefficient = dragCoefficient;

        frontalArea = width * height * 0.85;
        normalForce = mass * PhysicsConstants.G;
        rollingFrictionForce = rollingFrictionCoefficient * normalForce;
        maxTractionForce = kineticFrictionCoefficient * normalForce;
        maxBrakingForce = brakes.getBrakingAcceleration() * mass;
    }

    /* Updating physics */
    public void update(double deltaTime)
    {
        actualBrakingForce = maxBrakingForce * brakePosition;
        maxVelocity = (2.0 * Math.PI * wheelRadius * torqueCurve.getMaxRPM()) / (60 * transmission.getGearRatio(gear) * transmission.getFinalGearRatio());

        updateDragForce();
        updateEngineRPM();

        updateEngineTorque();
        updateEngineBrakingTorque();
        if (throttlePosition < 1)
            engineTorque = - engineBrakingTorque;
        updateMaxWheelTorque();
        updateActualWheelTorque(throttlePosition);
        updateTractionForce();
        updateTractionForceSliding();

        netForce = tractionForce - actualBrakingForce - rollingFrictionForce - dragForce;

        updateAcceleration();

        deltaVelocity = acceleration * deltaTime;
        velocity += deltaVelocity;
        velocity = MathFunctions.smoothUpperClamp(velocity, 0, maxVelocity, deltaTime);

        deltaDisplacement = velocity * deltaTime;
        distanceTravelled += deltaDisplacement;
    }


    /* Reset all the forces acting on the carPlayer */
    public void reset()
    {
        throttlePosition = 0; brakePosition = 0;
        velocity = 0; maxVelocity = 0; acceleration = 0; gear = 1; distanceTravelled = 0;
        deltaVelocity = 0; deltaDisplacement = 0;

        tractionForce = 0; dragForce = 0; netForce = 0; maxWheelTorque = 0; actualWheelTorque = 0; wheelAngularVelocity = 0;
        enginePower = 0; engineRPM = 0; engineTorque = 0; engineBrakingTorque = 0; engineAngularVelocity = 0; actualBrakingForce = 0;
    }



    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------- UPDATING PHYSICS --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private void updateDragForce()
    {
        dragForce = 0.5 * dragCoefficient * PhysicsConstants.AIR_DENSITY * velocity * velocity * frontalArea;
    }

    private void updateEngineRPM()
    {
        double a = velocity * 60.0 * transmission.getGearRatio(gear) * transmission.getFinalGearRatio();
        double b = 2.0 * Math.PI * wheelRadius;
        engineRPM = a / b;
        engineRPM = MathFunctions.clamp(engineRPM, torqueCurve.getStartRPM(), torqueCurve.getMaxRPM());
    }

    private void updateEngineTorque()
    {
        engineTorque = torqueCurve.getEngineTorque(engineRPM);
    }

    private void updateEngineBrakingTorque()
    {
        engineBrakingTorque = engineBrakingCoefficient * engineRPM / 60.0;
    }

    private void updateMaxWheelTorque()
    {
        maxWheelTorque = engineTorque * transmission.getGearRatio(gear) * transmission.getFinalGearRatio();
    }

    private void updateActualWheelTorque(double throttlePosition)
    {
        actualWheelTorque = maxWheelTorque * throttlePosition;
    }

    private void updateTractionForce()
    {
        tractionForce = actualWheelTorque / wheelRadius;
    }

    private void updateTractionForceSliding()
    {
        if (tractionForce > maxTractionForce)
            tractionForce = maxTractionForce;
    }

    private void updateAcceleration()
    {
        acceleration = netForce / mass;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------- SHIFTING GEARS ----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void shiftUp() {
        if (gear < transmission.getNumberOfGears()) {
            gear++;
            this.shiftUpdateEngineRPM(gear - 1, gear);
        }
    }

    public void shiftDown() {
        if (gear > 1) {
            gear--;
            this.shiftUpdateEngineRPM(gear + 1, gear);
        }
    }

    private void shiftUpdateEngineRPM(int oldGear, int newGear) {
        engineRPM = engineRPM * (newGear * 1.0) / (oldGear * 1.0);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- GETTERS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public double getLength()
    {
        return length;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public double getEngineRPM()
    {
        return engineRPM;
    }

    public double getRedlineRPM()
    {
        return torqueCurve.getMaxRPM();
    }

    public int getGear()
    {
        return gear;
    }

    public double getDistanceTravelled()
    {
        return distanceTravelled;
    }

    public double getMass()
    {
        return mass;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- SETTERS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    public void setThrottlePosition(double throttlePosition)
    {
        this.throttlePosition = throttlePosition;
    }

    public void setBrakePosition(double brakePosition)
    {
        this.brakePosition = brakePosition;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}