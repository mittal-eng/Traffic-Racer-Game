package gui.login;

import engine.ImageFiles;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Leo on 04-Nov-15.
 */
public class LoginFrame extends JFrame
{
    public LoginFrame()
    {
        this.setSize(370, 250);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        /* Action on close */
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /* Key Listener */
        this.setFocusable(true);

        /* Title and Icon */
        this.setTitle("Login");
        this.setIconImage(ImageFiles.ICON);

        this.add(new LoginPanel(this));

        /* Show the window */
        this.setVisible(true);
    }
}
