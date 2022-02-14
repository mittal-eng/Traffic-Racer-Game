package gui.panels.menus;


import engine.graphics.GraphicsAdapter;
import gui.buttons.MenuButton;
import game.Current;
import gui.buttons.CarButton;
import gui.GameFrame;
import engine.ImageFiles;
import engine.ImageOperations;
import game.storage.StorageCarsPlayer;
import game.storage.StorageRoads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 30-Sep-15.
 */
public class GaragePlayerPanel extends JPanel
{
    /* Reference to parent Window */
    private GameFrame windowReference;

    /* Buttons */
    private CarButton carButton_FerrariTestarossa;
    private CarButton carButton_LamborghiniCountachLP400;
    private CarButton carButton_BmwM1;

    private CarButton carButton_MclarenF1;
    private CarButton carButton_BugattiVeyron;
    private CarButton carButton_PaganiZondaCinque;

    private CarButton carButton_PontiacGto;
    private CarButton carButton_FordMustangGT390;
    private CarButton carButton_Porsche911;

    private CarButton carButton_FordGT40;
    private MenuButton menuButton_Exit;
    private CarButton carButton_Porsche917K;

    /* Constructor */
    public GaragePlayerPanel(GameFrame windowReference)
    {
        this.windowReference = windowReference;

        this.initializeButtons();
        this.initializePanelProperties();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ INITIALIZATION --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Initialize Buttons */
    private void initializeButtons()
    {
        carButton_FerrariTestarossa = new CarButton(ImageFiles.CAR_FERRARI_TESTAROSSA, "Ferrari", "Testarossa", "1980");
        carButton_LamborghiniCountachLP400 = new CarButton(ImageFiles.CAR_LAMBORGHINI_COUNTACH_LP400, "LAMBORGHINI", "COUNTACH", "1980");
        carButton_BmwM1 = new CarButton(ImageFiles.CAR_BMW_M1, "BMW", "M1", "1980");

        carButton_MclarenF1 = new CarButton(ImageFiles.CAR_MCLAREN_F1_1992, "Mclaren", "F1", "1992");
        carButton_BugattiVeyron = new CarButton(ImageFiles.CAR_BUGATTI_VEYRON_2006, "Bugatti", "Veyron", "2006");
        carButton_PaganiZondaCinque = new CarButton(ImageFiles.CAR_PAGANI_ZONDA_CINQUE, "Pagani", "Zonda Cinque", "2009");

        carButton_PontiacGto = new CarButton(ImageFiles.CAR_PONTIAC_GTO_1966, "PONTIAC", "GTO", "1966");
        carButton_FordMustangGT390 = new CarButton(ImageFiles.CAR_FORD_MUSTANG_GT390_1968, "Ford", "Mustang", "1968");
        carButton_Porsche911 = new CarButton(ImageFiles.CAR_PORSCHE_911_1968, "Porsche", "911", "1968");

        carButton_FordGT40 = new CarButton(ImageFiles.CAR_FORD_GT40_1969, "Ford", "GT40", "1969");
        menuButton_Exit = new MenuButton("Exit");
        carButton_Porsche917K = new CarButton(ImageFiles.CAR_PORSCHE_917K_1970, "Porsche", "917K", "1970");

        carButton_FerrariTestarossa.addActionListener(new MouseInput());
        carButton_LamborghiniCountachLP400.addActionListener(new MouseInput());
        carButton_BmwM1.addActionListener(new MouseInput());

        carButton_MclarenF1.addActionListener(new MouseInput());
        carButton_BugattiVeyron.addActionListener(new MouseInput());
        carButton_PaganiZondaCinque.addActionListener(new MouseInput());

        carButton_PontiacGto.addActionListener(new MouseInput());
        carButton_FordMustangGT390.addActionListener(new MouseInput());
        carButton_Porsche911.addActionListener(new MouseInput());

        carButton_FordGT40.addActionListener(new MouseInput());
        menuButton_Exit.addActionListener(new MouseInput());
        carButton_Porsche917K.addActionListener(new MouseInput());
    }

    /* Initialize properties of the Panel */
    private void initializePanelProperties()
    {
        this.setLayout(new GridLayout(4, 3, 10, 10));

        if (Current.player.getScore() > 1000)
            this.add(carButton_FerrariTestarossa);
        else
            this.add(new JLabel());

        if (Current.player.getScore() > 2000)
            this.add(carButton_LamborghiniCountachLP400);
        else
            this.add(new JLabel());

        if (Current.player.getScore() > 3000)
            this.add(carButton_BmwM1);
        else
            this.add(new JLabel());

        if (Current.player.getScore() > 6000)
            this.add(carButton_MclarenF1);
        else
            this.add(new JLabel());

        if (Current.player.getScore() > 9000)
            this.add(carButton_BugattiVeyron);
        else
            this.add(new JLabel());

        if (Current.player.getScore() > 12000)
            this.add(carButton_PaganiZondaCinque);
        else
            this.add(new JLabel());

        this.add(carButton_PontiacGto);
        this.add(carButton_FordMustangGT390);
        this.add(carButton_Porsche911);

        if (Current.player.getScore() > 20000)
            this.add(carButton_FordGT40);
        else
            this.add(new JLabel());

        this.add(menuButton_Exit);

        if (Current.player.getScore() > 30000)
            this.add(carButton_Porsche917K);
        else
            this.add(new JLabel());

        this.setFocusable(false);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    protected void paintComponent(Graphics g)
    {
        /* Objects that are necessary to let us draw something on the screen */
        BufferedImage container;
        Graphics2D g2d;

        /* Paint super-class */
        super.paintComponent(g);

        /* Obtain 2D graphics */
        g2d = (Graphics2D) g;

        /* Create a container image */
        container = StorageRoads.GRASS_SPRITE.getImage();

         /* Resize it to fit the screen */
        container = ImageOperations.resize(container, GraphicsAdapter.WINDOW_SIZE);

        /* Draw container */
        g2d.drawImage(container, 0, 0, this);
    }

    private class MouseInput implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == menuButton_Exit)
                windowReference.setToMainMenu();

            else if (e.getSource() == carButton_FerrariTestarossa)
                Current.carPlayer = StorageCarsPlayer.FERRARI_TESTAROSSA;

            else if (e.getSource() == carButton_LamborghiniCountachLP400)
                Current.carPlayer = StorageCarsPlayer.LAMBORGHINI_COUNTACH_LP400;

            else if (e.getSource() == carButton_BmwM1)
                Current.carPlayer = StorageCarsPlayer.BMW_M1;

            else if (e.getSource() == carButton_MclarenF1)
                Current.carPlayer = StorageCarsPlayer.MCLAREN_F1_1992;

            else if (e.getSource() == carButton_BugattiVeyron)
                Current.carPlayer = StorageCarsPlayer.BUGATTI_VEYRON_2006;

            else if (e.getSource() == carButton_PaganiZondaCinque)
                Current.carPlayer = StorageCarsPlayer.PAGANI_ZONDA_CINQUE;

            else if (e.getSource() == carButton_PontiacGto)
                Current.carPlayer = StorageCarsPlayer.PONTIAC_GTO_1966;

            else if (e.getSource() == carButton_FordMustangGT390)
                Current.carPlayer = StorageCarsPlayer.FORD_MUSTANG_GT390_1968;

            else if (e.getSource() == carButton_Porsche911)
                Current.carPlayer = StorageCarsPlayer.PORSCHE_911_1968;

            else if (e.getSource() == carButton_FordGT40)
                Current.carPlayer = StorageCarsPlayer.FORD_GT40_1969;

            else if (e.getSource() == carButton_Porsche917K)
                Current.carPlayer = StorageCarsPlayer.PORSCHE_917K_1970;

            if (e.getSource() instanceof CarButton)
                windowReference.setToMainMenu();

        }
    }
}

