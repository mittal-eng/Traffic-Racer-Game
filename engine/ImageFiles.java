package engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Leo on 08-Oct-15.
 */
public abstract class ImageFiles
{
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static BufferedImage ICON;
    public static BufferedImage GUI_CAR_BUTTON_BACKGROUND_ROLLOVER;
    public static BufferedImage GUI_CAR_BUTTON_BACKGROUND_PRESSED;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static BufferedImage ROAD;
    public static BufferedImage GRASS;

    public static BufferedImage CAR_MCLAREN_F1_1992;
    public static BufferedImage CAR_BUGATTI_VEYRON_2006;
    public static BufferedImage CAR_PORSCHE_911_1968;
    public static BufferedImage CAR_PONTIAC_GTO_1966;
    public static BufferedImage CAR_FORD_MUSTANG_GT390_1968;
    public static BufferedImage CAR_LAMBORGHINI_COUNTACH_LP400;
    public static BufferedImage CAR_FERRARI_TESTAROSSA;
    public static BufferedImage CAR_BMW_M1;
    public static BufferedImage CAR_PAGANI_ZONDA_CINQUE;
    public static BufferedImage CAR_FORD_GT40_1969;
    public static BufferedImage CAR_PORSCHE_917K_1970;

    public static BufferedImage TRAFFIC_AUSTIN_COOPER_S;
    public static BufferedImage TRAFFIC_CITROEN_2CV_1959;
    public static BufferedImage TRAFFIC_CITROEN_DS_21_1966;
    public static BufferedImage TRAFFIC_VOLKSWAGEN_BEETLE_1948;
    public static BufferedImage TRAFFIC_ROLLS_ROYCE_SILVER_CLOUD_II_1959;

    public static BufferedImage COLLECTIBLES_SILVER_COIN;
    public static BufferedImage COLLECTIBLES_GOLD_COIN;
    public static BufferedImage COLLECTIBLES_PURPLE_COIN;
    public static BufferedImage COLLECTIBLES_DEATH;
    public static BufferedImage COLLECTIBLES_HEALTH;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    /* Load all the image files */
    public static void initialize()
    {
        ImageFiles.loadGUI();
        ImageFiles.loadTerrain();
        ImageFiles.loadCars();
        ImageFiles.loadTraffic();
        ImageFiles.loadCollectibles();
    }

    /* Load BufferedImage from a relative path in the project */
    private static BufferedImage loadImage(String filePathInProject)
    {
        BufferedImage newImage;

        try {
            newImage = ImageIO.read(ImageFiles.class.getResource(filePathInProject));
        }

        catch (IOException e) {
            newImage = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
        }

        return newImage;
    }

    private static void loadGUI()
    {
        ICON = loadImage("/resources/gui/SPEEDOMETER.png");
        GUI_CAR_BUTTON_BACKGROUND_ROLLOVER = loadImage("/resources/gui/CAR_BUTTON_ROLLOVER_BACKGROUND.png");
        GUI_CAR_BUTTON_BACKGROUND_PRESSED = loadImage("/resources/gui/CAR_BUTTON_SELECTED_BACKGROUND.png");
    }

    private static void loadTerrain()
    {
        ROAD = loadImage("/resources/roads/ROAD.png");
        GRASS = loadImage("/resources/roads/GRASS.png");
    }

    private static void loadCars()
    {
        CAR_MCLAREN_F1_1992 = loadImage("/resources/cars/MCLAREN_F1_1992.png");
        CAR_BUGATTI_VEYRON_2006 = loadImage("/resources/cars/BUGATTI_VEYRON_2006.png");
        CAR_PORSCHE_911_1968 = loadImage("/resources/cars/PORSCHE_911_1968.png");
        CAR_PONTIAC_GTO_1966 = loadImage("/resources/cars/PONTIAC_GTO_1966.png");
        CAR_FORD_MUSTANG_GT390_1968 = loadImage("/resources/cars/FORD_MUSTANG_GT390_1968.png");
        CAR_LAMBORGHINI_COUNTACH_LP400 = loadImage("/resources/cars/LAMBORGHINI_COUNTACH_LP400.png");
        CAR_FERRARI_TESTAROSSA = loadImage("/resources/cars/FERRARI_TESTAROSSA.png");
        CAR_BMW_M1 = loadImage("/resources/cars/BMW_M1.png");
        CAR_PAGANI_ZONDA_CINQUE = loadImage("/resources/cars/PAGANI_ZONDA_CINQUE.png");
        CAR_FORD_GT40_1969 = loadImage("/resources/cars/FORD_GT40_1969.png");
        CAR_PORSCHE_917K_1970 = loadImage("/resources/cars/PORSCHE_917K_1970.png");
    }

    private static void loadTraffic()
    {
        TRAFFIC_AUSTIN_COOPER_S = loadImage("/resources/traffic/AUSTIN_COOPER_S.png");
        TRAFFIC_CITROEN_2CV_1959 = loadImage("/resources/traffic/CITROEN_2CV_1959.png");
        TRAFFIC_CITROEN_DS_21_1966 = loadImage("/resources/traffic/CITROEN_DS_21_1966.png");
        TRAFFIC_VOLKSWAGEN_BEETLE_1948 = loadImage("/resources/traffic/VOLKSWAGEN_BEETLE_1948.png");
        TRAFFIC_ROLLS_ROYCE_SILVER_CLOUD_II_1959 = loadImage("/resources/traffic/ROLLS_ROYCE_SILVER_CLOUD_II_1959.png");
    }

    private static void loadCollectibles()
    {
        COLLECTIBLES_SILVER_COIN = loadImage("/resources/collectibles/COIN_SILVER.png");
        COLLECTIBLES_GOLD_COIN = loadImage("/resources/collectibles/COIN_GOLD.png");
        COLLECTIBLES_PURPLE_COIN = loadImage("/resources/collectibles/COIN_PURPLE.png");
        COLLECTIBLES_DEATH = loadImage("/resources/collectibles/DEATH.png");
        COLLECTIBLES_HEALTH = loadImage("/resources/collectibles/HEALTH.png");
    }
}

