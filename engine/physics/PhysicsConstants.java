package engine.physics;

/**
 * Created by Leo on 19-Sep-15.
 */

/*
    Physical constants
 */
public interface PhysicsConstants
{
    public static final double G = 9.8;
    public static final double AIR_DENSITY = 1.225;

    public static final double DRAG_COEFFICIENT_SUPER_CARS = 0.25;
    public static final double DRAG_COEFFICIENT_SPORT_CARS = 0.31;
    public static final double DRAG_COEFFICIENT_MUSCLE_CARS = 0.36;
    public static final double DRAG_COEFFICIENT_SEDAN = 0.42;

    public static final double ROLLING_FRICTION_COEFFICIENT_CAR_TIRE = 0.015;
    public static final double KINETIC_FRICTION_COEFFICIENT_CAR_TIRE = 0.8;

    public static final double ENGINE_BRAKING_COEFFICIENT = 0.85;
}
