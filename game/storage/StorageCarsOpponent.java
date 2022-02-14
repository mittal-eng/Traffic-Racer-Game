package game.storage;

import engine.math.MathFunctions;
import engine.physics.*;
import engine.cars.CarOpponent;
import engine.ImageFiles;
import game.Current;

/**
 * Created by Leo on 09-Oct-15.
 */
public abstract class StorageCarsOpponent
{
    /* MCLAREN F1 1992 */
    private static CarTorqueCurve MCLAREN_F1_1992_TORQUE_CURVE = new CarTorqueCurve(250, 5600, 650, 7500);
    private static CarTransmission MCLAREN_F1_1992_TRANSMISSION = new CarTransmission(2.37, 3.23, 2.19, 1.71, 1.39, 1.16, 0.93);
    private static CarBrakes MCLAREN_F1_1992_BRAKES = new CarBrakes(30);
    private static CarBehaviour MCLAREN_F1_1992_BEHAVIOUR = new CarBehaviour(1140, 0.32, 4.287, 1.820 + 0.1, 1.140, MCLAREN_F1_1992_TORQUE_CURVE, MCLAREN_F1_1992_TRANSMISSION, MCLAREN_F1_1992_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent MCLAREN_F1_1992 = new CarOpponent(MCLAREN_F1_1992_BEHAVIOUR, ImageFiles.CAR_MCLAREN_F1_1992);

    /* BUGATTI VEYRON 2006 */
    private static CarTorqueCurve BUGATTI_VEYRON_2006_TORQUE_CURVE = new CarTorqueCurve(800, 4500, 1250, 6600);
    private static CarTransmission BUGATTI_VEYRON_2006_TRANSMISSION = new CarTransmission(2.7, 3.18, 2.26, 1.68, 1.29, 1.06, 0.88, 0.80);
    private static CarBrakes BUGATTI_VEYRON_2006_BRAKES = new CarBrakes(30);
    private static CarBehaviour BUGATTI_VEYRON_2006_BEHAVIOUR = new CarBehaviour(1888, 0.35, 4.463 + 0.2, 1.990, 1.212, BUGATTI_VEYRON_2006_TORQUE_CURVE, BUGATTI_VEYRON_2006_TRANSMISSION, BUGATTI_VEYRON_2006_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent BUGATTI_VEYRON_2006 = new CarOpponent(BUGATTI_VEYRON_2006_BEHAVIOUR, ImageFiles.CAR_BUGATTI_VEYRON_2006);

    /* PORSCHE 911 1968 */
    private static CarTorqueCurve PORSCHE_911_1968_TORQUE_CURVE = new CarTorqueCurve(110, 5500, 183, 8200);
    private static CarTransmission PORSCHE_911_1968_TRANSMISSION = new CarTransmission(4.2, 2.833, 1.778, 1.217, 0.962, 0.821);
    private static CarBrakes PORSCHE_911_1968_BRAKES = new CarBrakes(50);
    private static CarBehaviour PORSCHE_911_1968_BEHAVIOUR = new CarBehaviour(1080, 0.3, 4.163, 1.613 + 0.15, 1.321, PORSCHE_911_1968_TORQUE_CURVE, PORSCHE_911_1968_TRANSMISSION, PORSCHE_911_1968_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SPORT_CARS);
    public static CarOpponent PORSCHE_911_1968 = new CarOpponent(PORSCHE_911_1968_BEHAVIOUR, ImageFiles.CAR_PORSCHE_911_1968);

    /* PONTIAC GTO 1966 */
    private static CarTorqueCurve PONTIAC_GTO_1966_TORQUE_CURVE = new CarTorqueCurve(250, 3600, 575, 6500);
    private static CarTransmission PONTIAC_GTO_1966_TRANSMISSION = new CarTransmission(3.60, 2.52, 1.78, 1.36, 0.9);
    private static CarBrakes PONTIAC_GTO_1966_BRAKES = new CarBrakes(50);
    private static CarBehaviour PONTIAC_GTO_1966_BEHAVIOUR = new CarBehaviour(1633, 0.3, 5.247, 1.897, 1.364, PONTIAC_GTO_1966_TORQUE_CURVE, PONTIAC_GTO_1966_TRANSMISSION, PONTIAC_GTO_1966_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_MUSCLE_CARS);
    public static CarOpponent PONTIAC_GTO_1966 = new CarOpponent(PONTIAC_GTO_1966_BEHAVIOUR, ImageFiles.CAR_PONTIAC_GTO_1966);

    /* FORD MUSTANG GT390 1968 */
    private static CarTorqueCurve FORD_MUSTANG_GT390_1968_TORQUE_CURVE = new CarTorqueCurve(250, 3400, 603, 6500);
    private static CarTransmission FORD_MUSTANG_GT390_1968_TRANSMISSION = new CarTransmission(3.25, 2.78, 1.93, 1.36, 1);
    private static CarBrakes FORD_MUSTANG_GT390_1968_BRAKES = new CarBrakes(50);
    private static CarBehaviour FORD_MUSTANG_GT390_1968_BEHAVIOUR = new CarBehaviour(1500, 0.3, 4.663, 1.801, 1.311, FORD_MUSTANG_GT390_1968_TORQUE_CURVE, FORD_MUSTANG_GT390_1968_TRANSMISSION, FORD_MUSTANG_GT390_1968_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_MUSCLE_CARS);
    public static CarOpponent FORD_MUSTANG_GT390_1968 = new CarOpponent(FORD_MUSTANG_GT390_1968_BEHAVIOUR, ImageFiles.CAR_FORD_MUSTANG_GT390_1968);

    /* LAMBORGHINI COUNTACH LP400 */
    private static CarTorqueCurve LAMBORGHINI_COUNTACH_LP400_TORQUE_CURVE = new CarTorqueCurve(150, 5000, 365, 9000);
    private static CarTransmission LAMBORGHINI_COUNTACH_LP400_TRANSMISSION = new CarTransmission(4.091, 2.256, 1.769, 1.310, 0.990, 0.775);
    private static CarBrakes LAMBORGHINI_COUNTACH_LP400_BRAKES = new CarBrakes(40);
    private static CarBehaviour LAMBORGHINI_COUNTACH_LP400_BEHAVIOUR = new CarBehaviour(1065, 0.3, 4.140 + 0.2, 1.890 + 0.1, 1.070, LAMBORGHINI_COUNTACH_LP400_TORQUE_CURVE, LAMBORGHINI_COUNTACH_LP400_TRANSMISSION, LAMBORGHINI_COUNTACH_LP400_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent LAMBORGHINI_COUNTACH_LP400 = new CarOpponent(LAMBORGHINI_COUNTACH_LP400_BEHAVIOUR, ImageFiles.CAR_LAMBORGHINI_COUNTACH_LP400);

    /* FERRARI TESTAROSSA */
    private static CarTorqueCurve FERRARI_TESTAROSSA_TORQUE_CURVE = new CarTorqueCurve(200, 4500, 480, 7200);
    private static CarTransmission FERRARI_TESTAROSSA_TRANSMISSION = new CarTransmission(3.110, 3.139, 2.104, 1.526, 1.167, 0.875);
    private static CarBrakes FERRARI_TESTAROSSA_BRAKES = new CarBrakes(40);
    private static CarBehaviour FERRARI_TESTAROSSA_BEHAVIOUR = new CarBehaviour(1506, 0.31, 4.485 + 0.2, 1.976, 1.130, FERRARI_TESTAROSSA_TORQUE_CURVE, FERRARI_TESTAROSSA_TRANSMISSION, FERRARI_TESTAROSSA_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent FERRARI_TESTAROSSA = new CarOpponent(FERRARI_TESTAROSSA_BEHAVIOUR, ImageFiles.CAR_FERRARI_TESTAROSSA);

    /* BMW M1 */
    private static CarTorqueCurve BMW_M1_TORQUE_CURVE = new CarTorqueCurve(150, 5000, 330, 7500);
    private static CarTransmission BMW_M1_TRANSMISSION = new CarTransmission(4.22, 2.42, 1.61, 1.14, 0.846, 0.704);
    private static CarBrakes BMW_M1_BRAKES = new CarBrakes(40);
    private static CarBehaviour BMW_M1_BEHAVIOUR = new CarBehaviour(1360, 0.32, 4.346, 1.823, 1.140, BMW_M1_TORQUE_CURVE, BMW_M1_TRANSMISSION, BMW_M1_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent BMW_M1 = new CarOpponent(BMW_M1_BEHAVIOUR, ImageFiles.CAR_BMW_M1);

    /* PAGANI ZONDA CINQUE */
    private static CarTorqueCurve PAGANI_ZONDA_CINQUE_TORQUE_CURVE = new CarTorqueCurve(300, 6000, 780, 8000);
    private static CarTransmission PAGANI_ZONDA_CINQUE_TRANSMISSION = new CarTransmission(2.37, 3.23, 2.19, 1.71, 1.39, 1.16, 0.97);
    private static CarBrakes PAGANI_ZONDA_CINQUE_BRAKES = new CarBrakes(40);
    private static CarBehaviour PAGANI_ZONDA_CINQUE_BEHAVIOUR = new CarBehaviour(1220, 0.32, 4.555, 2.055, 1.141, PAGANI_ZONDA_CINQUE_TORQUE_CURVE, PAGANI_ZONDA_CINQUE_TRANSMISSION, PAGANI_ZONDA_CINQUE_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent PAGANI_ZONDA_CINQUE = new CarOpponent(PAGANI_ZONDA_CINQUE_BEHAVIOUR, ImageFiles.CAR_PAGANI_ZONDA_CINQUE);

    /* FORD GT40 1969 */
    private static CarTorqueCurve FORD_GT40_1969_TORQUE_CURVE = new CarTorqueCurve(250, 4750, 540, 7500);
    private static CarTransmission FORD_GT40_1969_TRANSMISSION = new CarTransmission(3.33, 2.42, 1.47, 1.14, 1, 0.846);
    private static CarBrakes FORD_GT40_1969_BRAKES = new CarBrakes(30);
    private static CarBehaviour FORD_GT40_1969_BEHAVIOUR = new CarBehaviour(950, 0.30, 4.178, 1.905, 1.029, FORD_GT40_1969_TORQUE_CURVE, FORD_GT40_1969_TRANSMISSION, FORD_GT40_1969_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent FORD_GT40_1969 = new CarOpponent(FORD_GT40_1969_BEHAVIOUR, ImageFiles.CAR_FORD_GT40_1969);

    /* PORSCHE 917K 1970 */
    private static CarTorqueCurve PORSCHE_917K_1970_TORQUE_CURVE = new CarTorqueCurve(250, 6800, 510, 9500);
    private static CarTransmission PORSCHE_917K_1970_TRANSMISSION = new CarTransmission(3.32, 2.07, 1.38, 1.135, 0.926, 0.875);
    private static CarBrakes PORSCHE_917K_1970_BRAKES = new CarBrakes(30);
    private static CarBehaviour PORSCHE_917K_1970_BEHAVIOUR = new CarBehaviour(800, 0.31, 4.320, 1.980, 0.940, PORSCHE_917K_1970_TORQUE_CURVE, PORSCHE_917K_1970_TRANSMISSION, PORSCHE_917K_1970_BRAKES, PhysicsConstants.DRAG_COEFFICIENT_SUPER_CARS);
    public static CarOpponent PORSCHE_917K_1970 = new CarOpponent(PORSCHE_917K_1970_BEHAVIOUR, ImageFiles.CAR_PORSCHE_917K_1970);

    public static CarOpponent[] array = new CarOpponent[]{
            PORSCHE_911_1968, FORD_MUSTANG_GT390_1968, PONTIAC_GTO_1966,
            LAMBORGHINI_COUNTACH_LP400, FERRARI_TESTAROSSA, BMW_M1,
            BUGATTI_VEYRON_2006, MCLAREN_F1_1992, PAGANI_ZONDA_CINQUE,
            FORD_GT40_1969, PORSCHE_917K_1970};

    public static void chooseOpponent()
    {
        if (Current.carPlayer == StorageCarsPlayer.FORD_MUSTANG_GT390_1968 || Current.carPlayer == StorageCarsPlayer.PONTIAC_GTO_1966 || Current.carPlayer == StorageCarsPlayer.PORSCHE_911_1968)
        {
            Current.carOpponent = StorageCarsOpponent.array[MathFunctions.randomInRange(0, 3)];
        }

        else if (Current.carPlayer == StorageCarsPlayer.FERRARI_TESTAROSSA || Current.carPlayer == StorageCarsPlayer.BMW_M1 || Current.carPlayer == StorageCarsPlayer.LAMBORGHINI_COUNTACH_LP400)
        {
            Current.carOpponent = StorageCarsOpponent.array[MathFunctions.randomInRange(3, 6)];
        }

        else if (Current.carPlayer == StorageCarsPlayer.BUGATTI_VEYRON_2006 || Current.carPlayer == StorageCarsPlayer.MCLAREN_F1_1992 || Current.carPlayer == StorageCarsPlayer.PAGANI_ZONDA_CINQUE)
        {
            Current.carOpponent = StorageCarsOpponent.array[MathFunctions.randomInRange(6, 9)];
        }

        else if (Current.carPlayer == StorageCarsPlayer.FORD_GT40_1969 || Current.carPlayer == StorageCarsPlayer.PORSCHE_917K_1970)
        {
            Current.carOpponent = StorageCarsOpponent.array[MathFunctions.randomInRange(6, 9)];
        }
    }
}
