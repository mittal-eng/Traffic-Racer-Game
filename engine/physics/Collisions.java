package engine.physics;

import engine.cars.CarPlayer;
import engine.cars.CarTraffic;
import engine.math.MathFunctions;

/**
 * Created by Leo on 14-Oct-15.
 */
public class Collisions
{
    public static void applyCollisionY(CarPlayer carPlayer, CarTraffic carTraffic)
    {
        double speedP = carPlayer.getVelocity();
        double speedT = MathFunctions.convertKMHtoMS(carTraffic.getVelocity());

        double massP = carPlayer.getMass();
        double massT = 1500.0;

        double finalP;
        double finalT;

        double a1 = massT * speedT + massP * speedP;
        double b1 = (massT * speedT * speedT) / 2.0 + (massP * speedP * speedP) / 2.0;

        double a = massT * massP + massT * massT;
        double b = -2.0 * massT * a1;
        double c = -1.0 * (2 * b1 * massP - a1 * a1);

        finalT = MathFunctions.solveQuadraticEquation(a, b, c);
        finalP = (a1 - massT * finalT) / massP;

        double dvP = finalP - speedP;
        double dvT = finalT - speedT;

        finalP = speedP + dvP;
        finalT = speedT + dvT;

        carPlayer.setVelocity(finalP);
        carTraffic.setVelocity(MathFunctions.convertMStoKMH(finalT));

        carPlayer.decreaseHealth();
    }
}
