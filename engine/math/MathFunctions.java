package engine.math;

/**
 * Created by Leo on 19-Sep-15.
 */

/*
    Collection of Mathematical Functions
 */
public abstract class MathFunctions
{
    /* Find the root of quadratic equation */
    public static  double solveQuadraticEquation(double a, double b, double c)
    {
        double root1, root2;
        double D = Math.sqrt(Math.pow(b, 2) - 4 * a * c);

        root1 = (-b + D) / (2 * a);
        root2 = (-b - D) / (2 * a);

        return Math.max(root1, root2);
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------- Randomization Functions ---------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Random integer in range */
    public static int randomInRange(int lowerLimitInclusive, int upperLimitExclusive)
    {
        return (int)(Math.random() * (upperLimitExclusive - lowerLimitInclusive) + lowerLimitInclusive);
    }

    /* Random double in range */
    public static double randomInRange(double lowerLimitInclusive, double upperLimitExclusive)
    {
        return (Math.random() * (upperLimitExclusive - lowerLimitInclusive) + lowerLimitInclusive);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------- Clamp Functions ---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Lock between too values */
    public static double clamp(double x, double lowerLimit, double upperLimit)
    {
        if (x < lowerLimit)
            return lowerLimit;

        if (x > upperLimit)
            return upperLimit;

        return x;
    }

    /* Lock between too values, with upper smoothing */
    public static double smoothUpperClamp(double x, double lowerLimit, double upperLimit, double deltaTime)
    {
        if (x < lowerLimit)
            return lowerLimit;

        if (x > upperLimit)
            return x - (50 * x / 100.0) * deltaTime;

        return x;
    }

    /* Lock between too values, with lower smoothing */
    public static double smoothLowerClamp(double x, double lowerLimit, double upperLimit, double deltaTime)
    {
        if (x < lowerLimit)
            return lowerLimit + (50 * x / 100.0) * deltaTime;

        if (x > upperLimit)
            return upperLimit;

        return x;
    }

    /* Lock between too values, with both upper and lower smoothing */
    public static double smoothClamp(double x, double lowerLimit, double upperLimit, double deltaTime)
    {
        if (x < lowerLimit)
            return lowerLimit + (50 * x / 100.0) * deltaTime;

        if (x > upperLimit)
            return x - (50 * x / 100.0) * deltaTime;

        return x;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------- Units Functions -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Convert M/S to KM/H */
    public static double convertMStoKMH(double MS)
    {
        return MS * 18.0 / 5.0;
    }

    /* Convert KM/H to M/S */
    public static double convertKMHtoMS(double KMH)
    {
        return KMH * 1000.0 / 60.0 / 60.0;
    }

    public static double convertMStoS(double MS)
    {
        return MS / 1000.0;
    }

    public static double convertStoMS(double S)
    {
        return S * 1000;
    }

    public static double convertNStoS(double NS)
    {
        return NS * Math.pow(10, -9);
    }

    public static double convertStoNS(double S)
    {
        return S * Math.pow(10, 9);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------ Geometrical Functions -------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Are the two rectangles overlapping? */
    public static boolean rectanglesAreOverlapping(Rectangle a, Rectangle b)
    {
        /* If they start from the same point */
        if (a.getStartX() == b.getStartX() && a.getStartY() == b.getStartY())
            return true;

        /* A is to the left of B */
        else if (a.getEndX() < b.getStartX())
            return false;

        /* B is to the left of A */
        else if (b.getEndX() < a.getStartX())
            return false;

        /* A is higher than B */
        else if (a.getEndY() < b.getStartY())
            return false;

        /* B is higher than A */
        else if (b.getEndY() < a.getStartY())
            return false;

        else
            return true;
    }

    public static boolean rectanglesAreOverlappedAtCenter(Rectangle a, Rectangle b, double radiusOfOverlap)
    {
        Point centerA = new Point((a.getStartX() + a.getEndX()) / 2.0, (a.getStartY() + a.getEndY() / 2.0));
        Point centerB = new Point((b.getStartX() + b.getEndX()) / 2.0, (b.getStartY() + b.getEndY() / 2.0));

        if (centerA.distance(centerB) < radiusOfOverlap)
            return true;
        else
            return false;
    }

    /* Are the two rectangles placed at safe distance? */
    public static boolean rectanglesAreAtSafeDistanceY(Rectangle a, Rectangle b, double safeDistanceY)
    {
        /* If rectangles are not overlapping */
        if (!rectanglesAreOverlapping(a, b))
        {
            boolean condition1 = a.getEndY() + safeDistanceY < b.getStartY();
            boolean condition2 = b.getEndY() + safeDistanceY < a.getStartY();

            /* If any of the conditions is true */
            if (condition1 || condition2)
                return true;
            else
                return false;
        }

        else
            return false;
    }

    /* Which of the two rectangles is placed higher? */
    public static Rectangle rectanglesFindUpper(Rectangle a, Rectangle b)
    {
        if (a.getStartY() < b.getStartY())
            return a;
        else
            return b;
    }

    /* Which of the two rectangles is placed lower? */
    public static Rectangle rectanglesFindLower(Rectangle a, Rectangle b)
    {
        if (a.getStartY() > b.getStartY())
            return a;
        else
            return b;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
