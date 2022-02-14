package engine.physics;

/**
 * Created by Leo on 19-Sep-15.
 */

/*
    A simplified representation of an engine's torque curve. Links engine's RPM with torque.
 */
public class CarTorqueCurve
{
    /* Torque Curve Data */
    private int startRPM;                                                // Minimum RPM
    private int torqueAtStartRPM;                                        // Torque at minimum RPM

    private int peakRPM;                                                 // Peak RPM (when torque reaches its maximum)
    private int torqueAtPeakRPM;                                         // Torque at peak RPM (the maximum torque)

    private int maxRPM;                                                  // Redline RPM
    private int torqueAtMaxRPM;                                          // Torque at redline RPM

    /* Data for simplified representation of torque curve */
    private double m1, b1, m2, b2;



    /* Constructor */
    public CarTorqueCurve(int torqueAtStartRPM, int peakRPM, int torqueAtPeakRPM, int maxRPM)
    {
        this.startRPM = 1000;
        this.torqueAtStartRPM = torqueAtStartRPM;

        this.peakRPM = peakRPM;
        this.torqueAtPeakRPM = torqueAtPeakRPM;

        this.maxRPM = maxRPM;
        this.torqueAtMaxRPM = torqueAtStartRPM;

        this.computeTorqueCurveData();
    }

    /* Compute properties of torque curve */
    private void computeTorqueCurveData()
    {
        m1 = (1.0 * torqueAtPeakRPM - 1.0 * torqueAtStartRPM) / (1.0 * peakRPM - 1.0 * startRPM);
        b1 = 1.0 * torqueAtPeakRPM - m1 * peakRPM;

        m2 = (1.0 * torqueAtMaxRPM - 1.0 * torqueAtPeakRPM) / (1.0 * maxRPM - 1.0 * peakRPM);
        b2 = 1.0 * torqueAtPeakRPM - m2 * peakRPM;
    }

    /* Compute engine torque based on RPM */
    public double getEngineTorque(double currentEngineRPM)
    {
        if (currentEngineRPM == peakRPM)
            return torqueAtPeakRPM;

        else if (currentEngineRPM <= startRPM)
            return torqueAtStartRPM;

        else if (currentEngineRPM >= maxRPM)
            return torqueAtMaxRPM;

        else if ((startRPM < currentEngineRPM) && (currentEngineRPM < peakRPM))
            return (m1 * currentEngineRPM + b1);

        else if ((peakRPM < currentEngineRPM) && (currentEngineRPM < maxRPM))
            return (m2 * currentEngineRPM + b2);

        else
            throw new IllegalArgumentException();
    }



    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- GETTERS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public int getStartRPM()
    {
        return startRPM;
    }

    public int getMaxRPM()
    {
        return maxRPM;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
