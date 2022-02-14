package engine.graphics;

import engine.Coin;
import engine.Coins;
import engine.Traffic;
import engine.cars.CarOpponent;
import engine.cars.CarPlayer;
import engine.cars.CarTraffic;
import game.sprites.RoadSprite;
import engine.ImageOperations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by Leo on 22-Oct-15.
 */
public abstract class Canvas
{
    /* Objects to be drawn */
    private Traffic traffic;
    private CarPlayer carPlayer;
    private CarOpponent carOpponent;
    private Coins coins;
    private RoadSprite road;
    private Gauges display;

    /* Temporary and finalized images */
    protected BufferedImage bufferedImage;
    protected BufferedImage gameImage;
    protected BufferedImage pauseImage;

    /* Width, height and resize ratio of canvas */
    protected int canvasWidth;
    protected int canvasHeight;
    protected double resizeImageRatio;

    BufferedImage resizedRoadPartOne;
    BufferedImage resizedRoadPartTwo;

    /* Constructor */
    public Canvas(RoadSprite road, CarPlayer carPlayer, CarOpponent carOpponent, Traffic traffic, Coins coins, Gauges display)
    {
        /* Save objects */
        this.road = road;
        this.carPlayer = carPlayer;
        this.carOpponent = carOpponent;
        this.coins = coins;
        this.traffic = traffic;
        this.display = display;

        /* Get width and height */
        this.canvasWidth = (int) GraphicsAdapter.WINDOW_SIZE.getWidth();
        this.canvasHeight = (int) GraphicsAdapter.WINDOW_SIZE.getHeight();

        if (display != null)
        {
            display.setWidth(canvasWidth);
            display.setHeight(canvasHeight);
        }

        /* Get resize ratio */
        this.computeResizeRatio();

        int roadSliceWidth = (int) (road.getPartOneImage().getWidth() * resizeImageRatio);
        int roadSliceHeight = (int) (road.getPartOneImage().getHeight() * resizeImageRatio);

        resizedRoadPartOne = new BufferedImage(roadSliceWidth, roadSliceHeight, BufferedImage.TYPE_INT_ARGB);
        resizedRoadPartTwo = new BufferedImage(roadSliceWidth, roadSliceHeight, BufferedImage.TYPE_INT_ARGB);

        ImageOperations.concatenateResizedImage(resizedRoadPartOne, road.getPartOneImage(), 0, 0, resizeImageRatio);
        ImageOperations.concatenateResizedImage(resizedRoadPartTwo, road.getPartTwoImage(), 0, 0, resizeImageRatio);
    }

    /* Find resize ratio */
    private void computeResizeRatio()
    {
        resizeImageRatio = (canvasHeight * 1.0) / (road.getImage().getHeight() * 1.0);
    }

    /* Prepare BufferedImage to be used in Pause panels */
    public BufferedImage getPauseImage()
    {
        this.updatePauseImage();

        if (pauseImage != null)
            return pauseImage;
        else
            return new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
    }

    /* Prepare BufferedImage to be used in Game panels */
    public BufferedImage getGameImage()
    {
        if (gameImage != null)
            return gameImage;

        else
            return new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------- Draw elements -------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void drawRoad()
    {
        if (road != null)
        {
            int roadOneX = (int) (road.getPartOneStartX() * resizeImageRatio);
            int roadOneY = (int) (road.getPartOneStartY() * resizeImageRatio);

            int roadTwoX = (int) (road.getPartTwoStartX() * resizeImageRatio);
            int roadTwoY = (int) (road.getPartTwoStartY() * resizeImageRatio);

            ImageOperations.concatenateImage(bufferedImage, resizedRoadPartOne, roadOneX, roadOneY);
            ImageOperations.concatenateImage(bufferedImage, resizedRoadPartTwo, roadTwoX, roadTwoY);
        }
    }

    protected void drawOpponent()
    {
        if (carOpponent != null)
        {
            ImageOperations.concatenateResizedImage(bufferedImage, carOpponent.getImage(), carOpponent.getStartX(), carOpponent.getStartY(), resizeImageRatio);
        }
    }

    protected void drawPlayer()
    {
        if (carPlayer != null)
        {
            ImageOperations.concatenateResizedImage(bufferedImage, carPlayer.getImage(), carPlayer.getStartX(), carPlayer.getStartY(), resizeImageRatio);
        }
    }

    protected void drawTraffic()
    {
        /* If there is traffic */
        if (traffic != null)
        {
            /* For each car in traffic */
            for (CarTraffic carTraffic : (new ArrayList<>(traffic.getCurrentCars())))
            {
                /* If the car exists */
                if (carTraffic != null)
                {
                    /* If the car is valid*/
                    if (carTraffic.isValid())
                    {
                        ImageOperations.concatenateResizedImage(bufferedImage, carTraffic.getImage(), carTraffic.getStartX(), carTraffic.getStartY(), resizeImageRatio);
                    }
                }
            }
        }
    }

    protected void drawCoins()
    {
        if (coins != null)
        {
            for (Coin c : coins.getCoins())
            {
                ImageOperations.concatenateResizedImage(bufferedImage, c.getImage(), c.getStartX(), c.getStartY(), resizeImageRatio);
            }
        }
    }

    protected void drawDisplay()
    {
        if (display != null && carPlayer != null)
        {
            display.update(carPlayer);
            ImageOperations.concatenateImage(bufferedImage, display.getDisplayImage(), 0, 0);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------- Draw countdown ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private void showText(String text, Color color)
    {
        Graphics2D graphics2D = gameImage.createGraphics();
        GraphicsAdapter.setRenderingMode(graphics2D);
        graphics2D.setFont(GraphicsAdapter.customFont.deriveFont(Font.PLAIN, 70));
        graphics2D.setColor(color);
        long startTime = System.currentTimeMillis() / 100;
        while (System.currentTimeMillis() / 100 < startTime + 10)
        {
            int stringWidth = graphics2D.getFontMetrics().stringWidth(text);
            int stringHeight = graphics2D.getFontMetrics().getHeight();
            graphics2D.drawString(text, (canvasWidth / 2 - stringWidth / 2), (canvasHeight / 2 + stringHeight / 2));
            graphics2D.dispose();
        }
    }

    public void drawCountdown()
    {
        this.updateGameImage();

        /* Initialize new image */
        bufferedImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);

        /* Backup current game image */
        ImageOperations.concatenateImage(bufferedImage, gameImage, 0, 0);

        /* Show 3 */
        this.showText("3", Color.RED);
        ImageOperations.concatenateImage(gameImage, bufferedImage, 0, 0);

        /* Show 2 */
        this.showText("2", Color.ORANGE);
        ImageOperations.concatenateImage(gameImage, bufferedImage, 0, 0);

        /* Show 1 */
        this.showText("1", Color.YELLOW);
        ImageOperations.concatenateImage(gameImage, bufferedImage, 0, 0);

        /* Show GO! */
        this.showText("GO!", Color.GREEN);
        ImageOperations.concatenateImage(gameImage, bufferedImage, 0, 0);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


    public abstract void updateGameImage();

    private void updatePauseImage()
    {
        /* Initialize new image */
        this.pauseImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);

        /* Save as pauseImage */
        ImageOperations.concatenateImage(pauseImage, bufferedImage, 0, 0);
    }



}

