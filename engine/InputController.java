package engine;

/**
 * Class that provides linkage between pressed buttons that are listened to in main Window class, and physical updates of car movements
 * @author Lev Ertuna
 */
public abstract class InputController
{
    public static boolean THROTTLE_PRESSED = false;
    public static boolean BRAKE_PRESSED = false;
    public static boolean TURN_LEFT_PRESSED = false;
    public static boolean TURN_RIGHT_PRESSED = false;
    public static boolean COLLISION_TURN_LEFT_PRESSED = false;
    public static boolean COLLISION_TURN_RIGHT_PRESSED = false;
    public static boolean SHIFT_DOWN_PRESSED = false;
    public static boolean SHIFT_UP_PRESSED = false;

    /**
     * Resets all the variables to initial state
     */
    public static void reset()
    {
        THROTTLE_PRESSED = false;
        BRAKE_PRESSED = false;
        TURN_LEFT_PRESSED = false;
        TURN_RIGHT_PRESSED = false;
        COLLISION_TURN_LEFT_PRESSED = false;
        COLLISION_TURN_RIGHT_PRESSED = false;
        SHIFT_DOWN_PRESSED = false;
        SHIFT_UP_PRESSED = false;
    }
}

