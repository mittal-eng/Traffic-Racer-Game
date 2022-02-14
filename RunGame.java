import engine.Player;
import game.Current;
import gui.GameFrame;
import engine.graphics.GraphicsAdapter;
import engine.ImageFiles;
import gui.login.LoginFrame;

public class RunGame
{
    /* Start the execution from here */
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.opengl", "True");
        System.setProperty("sun.java2d.accthreshold", "0");

        /* Initialize image files */
        ImageFiles.initialize();

        /* Initialize graphic constants */
        GraphicsAdapter.initialize();

        LoginFrame loginFrame = new LoginFrame();
//        /* Create the game window */
//        Current.player = Player.readFromFile("leo");
//        GameFrame window = new GameFrame();
    }
}
