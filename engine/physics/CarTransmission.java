package engine.physics;

import java.util.ArrayList;

/**
 * Created by Leo on 19-Sep-15.
 */

/*
    Representation of a carPlayer's gearbox
 */
public class CarTransmission
{
    /* Transmission Data */
    private ArrayList<Double> gearRatios;                               // Array of gear ratios
    private double finalGearRatio;                                      // Ratio of final gear
    private double numberOfGears;                                       // Number of gears



    /* Constructor */
    public CarTransmission(double finalGearRatio, double... gearRatios)
    {
        this.finalGearRatio = finalGearRatio;
        this.numberOfGears = gearRatios.length;

        this.gearRatios = new ArrayList<Double>();

        for (double gearRatio : gearRatios)
            this.gearRatios.add(gearRatio);
    }



    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- GETTERS ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public double getGearRatio(int currentGear)
    {
        if (currentGear <= gearRatios.size())
            return gearRatios.get(currentGear - 1);

        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public double getFinalGearRatio()
    {
        return finalGearRatio;
    }

    public double getNumberOfGears()
    {
        return numberOfGears;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
