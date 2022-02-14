package engine.graphics;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Leo on 27-Oct-15.
 */
public abstract class GraphicsAdapter
{
    public static final int WINDOW_WIDTH_IDEAL = 2300 / 3;
    public static final int WINDOW_HEIGHT_IDEAL = 1020;
    public static final double PIXELS_METER_RATIO = 606.0 / 4.287;

    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;
    public static Dimension WINDOW_SIZE = new Dimension(0, 0);

    public static double ratioWidth;
    public static double ratioHeight;
    public static double ratioFontDisplay;
    public static double ratioFontMenu;
    public static double ratioFontAbout;

    public static Font customFont;
    private final static int CUSTOM_FONT_SIZE_DISPLAY_IDEAL = 20;
    private final static int CUSTOM_FONT_SIZE_MENU_IDEAL = 60;
    private final static int CUSTOM_FONT_SIZE_ABOUT_IDEAL = 40;
    public static int CUSTOM_FONT_SIZE_DISPLAY;
    public static int CUSTOM_FONT_SIZE_MENU;
    public static int CUSTOM_FONT_SIZE_ABOUT;

    public final static Color COLOR_ORANGE = new Color(255, 106, 0);

    public static void initialize()
    {
        ratioWidth = (WINDOW_WIDTH_IDEAL * 1.0) / 1920.0;
        ratioHeight = (WINDOW_HEIGHT_IDEAL * 1.0) / 1080.0;
        ratioFontDisplay = (CUSTOM_FONT_SIZE_DISPLAY_IDEAL * 1.0) / (WINDOW_HEIGHT_IDEAL * 1.0);
        ratioFontMenu = (CUSTOM_FONT_SIZE_MENU_IDEAL * 1.0) / (WINDOW_HEIGHT_IDEAL * 1.0);
        ratioFontAbout = (CUSTOM_FONT_SIZE_ABOUT_IDEAL * 1.0) / (WINDOW_HEIGHT_IDEAL * 1.0);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        WINDOW_WIDTH = (int) (ratioWidth * screenWidth);
        WINDOW_HEIGHT = (int) (ratioHeight * screenHeight);
        WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

        CUSTOM_FONT_SIZE_DISPLAY = (int) (ratioFontDisplay * WINDOW_HEIGHT);
        CUSTOM_FONT_SIZE_MENU = (int) (ratioFontMenu * WINDOW_HEIGHT);
        CUSTOM_FONT_SIZE_ABOUT = (int) (ratioFontAbout * WINDOW_HEIGHT);

        setCustomFont();
    }

    private static void setCustomFont()
    {
        InputStream inputStream = Gauges.class.getResourceAsStream("/resources/NeedForFont.ttf");

        try
        {
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font = font.deriveFont(Font.PLAIN, 20);

            customFont = font;
        }

        catch (IOException | FontFormatException e)
        {
            System.out.println("Custom font not found!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void setRenderingMode(Graphics2D graphics2D)
    {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
    }
}
