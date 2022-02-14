package engine.math;

/**
 * Created by Leo on 23-Sep-15.
 */

/*
    Geometrical Point
 */
public class Point
{
    /* X and Y coordinates */
    private double x;
    private double y;

    /* No-argument Constructor*/
    public Point()
    {
        this(0, 0);
    }

    /* Constructor */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /* Increase X */
    public void incrementX(double dx)
    {
        this.x += dx;
    }

    /* Increase Y */
    public void incrementY(double dy)
    {
        this.y += dy;
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- Getters and Setters ----------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /* Distance from this point to another point */
    public double distance(Point otherPoint)
    {
        return this.distance(otherPoint.getX(), otherPoint.getY());
    }

    /* Distance from this point to another point with specified x and y coordinates */
    public double distance(double otherX, double otherY)
    {
        double deltaX = (otherX - this.x);
        double deltaY = (otherY - this.y);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }


    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------- Utility -------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        /* If the objects point to the same address */
        if (this == o)
            return true;

        /* If the other object is null */
        if (o == null)
            return false;

        /* If the objects are of different classes */
        if (this.getClass() != o.getClass())
            return false;

        /* Convert other object to Point and perform the comparison */
        Point point = (Point) o;
        boolean condition1 = Double.compare(point.getX(), this.getX()) == 0;
        boolean condition2 = Double.compare(point.getY(), this.getY()) == 0;
        return condition1 && condition2;
    }

    @Override
    public String toString()
    {
        return "Point: (" + "x=" + x + ", y=" + y + ")";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
