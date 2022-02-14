package engine.math;

/**
 * Created by Leo on 22-Sep-15.
 */

/*
    Geometrical Rectangle
 */
public class Rectangle
{
    /* Start and end Points */
    private Point start;
    private Point end;

    /* Width and height */
    private double width;
    private double height;

    /* No-argument Constructor */
    public Rectangle()
    {
        this(1, 1);
    }

    /* Constructor, takes only width and height */
    public Rectangle(double width, double height)
    {
        this(0, 0, width, height);
    }

    /* Constructor */
    public Rectangle(double startX, double startY, double width, double height)
    {
        this.start = new Point(startX, startY);
        this.end = new Point(startX + width, startY + height);

        this.width = width;
        this.height = height;
    }

    /* Move the in X */
    public void moveX(double dx)
    {
        start.incrementX(dx);
        end.incrementX(dx);
    }

    /* Move the in Y */
    public void moveY(double dy)
    {
        start.incrementY(dy);
        end.incrementY(dy);
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- Getters and Setters ----------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public double getStartX()
    {
        return start.getX();
    }

    public double getStartY()
    {
        return start.getY();
    }

    public double getEndX()
    {
        return end.getX();
    }

    public double getEndY()
    {
        return end.getY();
    }

    public void setStartX(double x)
    {
        start.setX(x);
        end.setX(x + width);
    }

    public void setStartY(double y)
    {
        start.setY(y);
        end.setY(y + height);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


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

        /* Convert other object to Rectangle and perform the comparison */
        Rectangle rectangle = (Rectangle) o;
        boolean condition1 = Double.compare(rectangle.getWidth(), this.getWidth()) == 0;
        boolean condition2 = Double.compare(rectangle.getHeight(), this.getHeight()) == 0;
        boolean condition3 = this.start.equals(rectangle.start);
        boolean condition4 = this.end.equals(rectangle.end);
        return condition1 && condition2 && condition3 && condition4;
    }

    @Override
    public String toString()
    {
        return "Rectangle: [" + "startPoint=" + start + ", endPoint=" + end + ", width=" + width + ", height=" + height + "]";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}

