package gui;

import engine.graphics.GraphicsAdapter;
import gui.panels.GamePanel;
import gui.panels.gameplay.*;
import gui.panels.menus.*;
import game.canvases.BackgroundCanvas;
import game.Current;
import game.loops.BackgroundLoop;
import game.traffics.BackgroundTraffic;
import engine.InputController;
import engine.ImageFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by Leo on 30-Sep-15.
 */

/*
    The main window of the game
 */
public class GameFrame extends JFrame
{
    /* Changeable Panel */
    private JPanel changeablePanel;
    //------------------------------------------------------------------------------------------------------------------
        /* Menu Panels */
        //--------------------------------------------------------------------------------------------------------------
        private MainMenuPanel mainMenuPanel;
        private ModeMenuPanel modeMenuPanel;
        private SettingsMenuPanel settingsMenuPanel;
        private StatisticsPanel statisticsPanel;
        private AboutPanel aboutPanel;
        //--------------------------------------------------------------------------------------------------------------

        /* Gameplay Panels */
        //--------------------------------------------------------------------------------------------------------------
            /* Drag Race */
            private DragRacePanel dragRacePanel;
            private DragRacePausePanel dragRacePausePanel;
            private DragRaceFinishPanel dragRaceFinishPanel;

            /* Freeroam */
            private FreeroamPanel freeroamPanel;
            private FreeroamPausePanel freeroamPausePanel;
            private FreeroamFinishPanel freeroamFinishPanel;

            /* Score Attack */
            private ScoreAttackPanel scoreAttackPanel;
            private ScoreAttackPausePanel scoreAttackPausePanel;
            private ScoreAttackFinishPanel scoreAttackFinishPanel;
        //--------------------------------------------------------------------------------------------------------------

        /* Car selection */
        //--------------------------------------------------------------------------------------------------------------
        private GaragePlayerPanel garagePlayerPanel;
        //--------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /* Background */
    //------------------------------------------------------------------------------------------------------------------
    private BackgroundTraffic backgroundTraffic;
    private BackgroundLoop backgroundLoop;
    private BackgroundCanvas backgroundCanvas;
    //------------------------------------------------------------------------------------------------------------------

    /* Constructor */
    public GameFrame()
    {
        /* Initialize Components */
        this.initializePanels();
        this.initializeFrameProperties();
        this.initializeBackground();

        /* Set changeable Panel to MainMenu in the beginning */
        this.setToMainMenu();
    }



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ INITIALIZATION --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Initialize changeable Panels */
    private void initializePanels()
    {
        this.mainMenuPanel = new MainMenuPanel(this);
        this.modeMenuPanel = new ModeMenuPanel(this);
        this.settingsMenuPanel = new SettingsMenuPanel(this);
        this.statisticsPanel = new StatisticsPanel(this);
        this.aboutPanel = new AboutPanel(this);

        this.garagePlayerPanel = new GaragePlayerPanel(this);

        this.dragRacePanel = new DragRacePanel(Current.roadDrag, Current.carPlayer, Current.carOpponent, this);
        this.dragRacePausePanel = new DragRacePausePanel(this);
        this.dragRaceFinishPanel = new DragRaceFinishPanel(this);

        this.freeroamPanel = new FreeroamPanel(Current.roadFreeroam, Current.carPlayer, this);
        this.freeroamPausePanel = new FreeroamPausePanel(this);
        this.freeroamFinishPanel = new FreeroamFinishPanel(this);

        this.scoreAttackPanel = new ScoreAttackPanel(Current.roadScoreAttack, Current.carPlayer, this);
        this.scoreAttackPausePanel = new ScoreAttackPausePanel(this);
        this.scoreAttackFinishPanel = new ScoreAttackFinishPanel(this);
    }

    /* Initialize properties of the Frame */
    private void initializeFrameProperties()
    {
        /* Find window size */
        this.setSize(GraphicsAdapter.WINDOW_WIDTH, GraphicsAdapter.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        /* Action on close */
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowInput());

        /* Key Listener */
        this.addKeyListener(new KeyInput(this));
        this.setFocusable(true);

        /* Title and Icon */
        this.setTitle("traffic Racer");
        this.setIconImage(ImageFiles.ICON);

        /* Show the window */
        this.setVisible(true);
    }

    /* Initialize background of the Frame */
    private void initializeBackground()
    {
        this.backgroundTraffic = new BackgroundTraffic(Current.roadBackground);
        backgroundTraffic.initialize();
        this.backgroundLoop = new BackgroundLoop(backgroundTraffic, this);
        this.backgroundCanvas = new BackgroundCanvas(Current.roadBackground, null, null, backgroundTraffic, null);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- CHANGE PANEL -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private void changePanelTo(JPanel otherPanel)
    {
        if (changeablePanel != null)
        {
            /* Remove previous panel */
            remove(changeablePanel);
        }

        /* Add new panel */
        add(otherPanel);

        /* Revalidate and repaint */
        invalidate(); validate(); repaint();

        /* Save reference */
        changeablePanel = otherPanel;
    }

    public void setToGaragePlayer()
    {
        garagePlayerPanel = new GaragePlayerPanel(this);
        changePanelTo(garagePlayerPanel);
        this.backgroundLoop.stopGameLoop();
        this.setTitle("Choose Your Car");
    }

    public void setToMainMenu()
    {
        if (!this.isMenu())
        {
            backgroundLoop.initializeGameLoop();
            backgroundLoop.startGameLoop();
        }

        changePanelTo(mainMenuPanel);
        this.setTitle("Traffic Racer");
    }

    public void setToModeMenu()
    {
        if (!this.isMenu())
        {
            backgroundLoop.initializeGameLoop();
            backgroundLoop.startGameLoop();
        }

        changePanelTo(modeMenuPanel);
        this.setTitle("Choose Game Mode");
    }

    public void setToSettingsMenu()
    {
        changePanelTo(settingsMenuPanel);
        this.setTitle("Settings");
    }

    public void setToStatistics()
    {
        changePanelTo(statisticsPanel);
        this.setTitle("Statistics");
    }

    public void setToAbout()
    {
        changePanelTo(aboutPanel);
        this.setTitle("About");
    }

    public void setToDragRace()
    {
        if (this.isMainMenu() || this.isMenu())
            backgroundLoop.stopGameLoop();

        /* If this method is called after the carOpponent's carPlayer was selected */
        if (this.isModeMenu())
        {
            /* Reinitialize the panel */
            dragRacePanel = new DragRacePanel(Current.roadDrag, Current.carPlayer, Current.carOpponent, this);
            dragRacePanel.gameLoop.initializeGameLoop();
            dragRacePanel.gameLoop.startGameLoop();

            /* Display the panel */
            changePanelTo(dragRacePanel);
        }

        /* If this method is called from the pause menu */
        else if (this.isDragRacePause())
        {
            /* Continue the loop */
            dragRacePanel.gameLoop.resumeGameLoop();

            dragRacePanel.updateGameImage();

            /* Display the panel */
            changePanelTo(dragRacePanel);
        }

        this.setTitle("Drag Race");
    }

    public void setToFreeroam()
    {
        if (this.isMainMenu() || this.isMenu())
            backgroundLoop.stopGameLoop();

        if (this.isModeMenu())
        {
            /* Reinitialize the panel */
            freeroamPanel = new FreeroamPanel(Current.roadFreeroam, Current.carPlayer, this);
            freeroamPanel.gameLoop.initializeGameLoop();
            freeroamPanel.gameLoop.startGameLoop();

            /* Display the panel */
            changePanelTo(freeroamPanel);
        }

        /* If this method is called from the pause menu */
        else if (this.isFreeroamPause())
        {
            /* Continue the loop */
            freeroamPanel.gameLoop.resumeGameLoop();

            freeroamPanel.updateGameImage();

            /* Display the panel */
            changePanelTo(freeroamPanel);
        }

        this.setTitle("Freeroam");
    }

    public void setToScoreAttack()
    {
        if (this.isMainMenu() || this.isMenu())
            backgroundLoop.stopGameLoop();

        if (this.isModeMenu())
        {
            /* Reinitialize the panel */
            scoreAttackPanel = new ScoreAttackPanel(Current.roadScoreAttack, Current.carPlayer, this);
            scoreAttackPanel.gameLoop.initializeGameLoop();
            scoreAttackPanel.gameLoop.startGameLoop();

            /* Display the panel */
            changePanelTo(scoreAttackPanel);
        }

        /* If this method is called from the pause menu */
        else if (this.isScoreAttackPause())
        {
            /* Continue the loop */
            scoreAttackPanel.gameLoop.resumeGameLoop();

            scoreAttackPanel.updateGameImage();

            /* Display the panel */
            changePanelTo(scoreAttackPanel);
        }

        this.setTitle("Score Attack");
    }

    public void setToDragRaceFinish(boolean playerWins, boolean opponentWins)
    {
        dragRaceFinishPanel.setGameImage(this.getDragRacePanelGameImage());
        dragRaceFinishPanel.showWhoWon(playerWins, opponentWins);
        changePanelTo(dragRaceFinishPanel);
        this.setTitle("Race Finished");
    }

    public void setToDragRacePause()
    {
        dragRacePanel.gameLoop.pauseGameLoop();
        dragRacePausePanel.setGameImage(this.getDragRacePanelPauseImage());
        changePanelTo(dragRacePausePanel);
        this.setTitle("Paused");
    }

    public void setToFreeroamPause()
    {
        freeroamPanel.gameLoop.pauseGameLoop();
        freeroamPausePanel.setGameImage(this.getFreeroamPanelPauseImage());
        changePanelTo(freeroamPausePanel);
        this.setTitle("Paused");
    }

    public void setToFreeroamFinish()
    {
        freeroamFinishPanel.setGameImage(this.getFreeroamPanelGameImage());
        changePanelTo(freeroamFinishPanel);
        this.setTitle("Race Finished");
    }

    public void setToScoreAttackPause()
    {
        scoreAttackPanel.gameLoop.pauseGameLoop();
        scoreAttackPausePanel.setGameImage(this.getScoreAttackPanelPauseImage());
        changePanelTo(scoreAttackPausePanel);
        this.setTitle("Paused");
    }

    public void setToScoreAttackFinish()
    {
        scoreAttackFinishPanel.setGameImage(this.getScoreAttackPanelGameImage());
        changePanelTo(scoreAttackFinishPanel);
        this.setTitle("Race Finished");
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- WHICH PANEL? -----------------------------------------------------------
    //-------------------------------------------------- ----------------------------------------------------------------
    public boolean isGame()
    {
        if (this.isDragRace() || this.isFreeroam() || this.isScoreAttack())
            return true;
        else
            return false;
    }

    public boolean isMenu()
    {
        if (this.isMainMenu() || this.isModeMenu() || this.isSettingsMenu() || this.isAbout() || this.isStatistics())
            return true;
        else
            return false;
    }

    public boolean isGarage()
    {
        if (this.isGaragePlayer())
            return true;
        else
            return false;
    }

    public boolean isPause()
    {
        if (this.isDragRacePause() || this.isFreeroamPause() || this.isScoreAttackPause())
            return true;
        else
            return false;
    }

    public boolean isDragRace()
    {
        return changeablePanel == dragRacePanel;
    }

    public boolean isFreeroam()
    {
        return changeablePanel == freeroamPanel;
    }

    public boolean isScoreAttack()
    {
        return changeablePanel == scoreAttackPanel;
    }

    public boolean isDragRacePause()
    {
        return changeablePanel == dragRacePausePanel;
    }

    public boolean isFreeroamPause()
    {
        return changeablePanel == freeroamPausePanel;
    }

    public boolean isScoreAttackPause() {
        return changeablePanel == scoreAttackPausePanel;
    }

    public boolean isGaragePlayer()
    {
        return changeablePanel == garagePlayerPanel;
    }

    public boolean isMainMenu()
    {
        return changeablePanel == mainMenuPanel;
    }

    public boolean isModeMenu()
    {
        return changeablePanel == modeMenuPanel;
    }

    public boolean isSettingsMenu()
    {
        return changeablePanel == settingsMenuPanel;
    }

    public boolean isStatistics()
    {
        return changeablePanel == statisticsPanel;
    }

    public boolean isAbout()
    {
        return changeablePanel == aboutPanel;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- INPUT LISTENER ---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private class KeyInput implements KeyListener

    {
        GameFrame windowReference;

        public KeyInput(GameFrame windowReference)
        {
            this.windowReference = windowReference;
        }

        private void handleMenuInput(KeyEvent e)
        {
            // ESC
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                windowReference.setToMainMenu();
            }
        }

        private void handleGameInputPressed(KeyEvent e)
        {
            // W
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
                InputController.THROTTLE_PRESSED = true;

            // S
            if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
                InputController.BRAKE_PRESSED = true;

            // E
            if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_SHIFT)
                InputController.SHIFT_UP_PRESSED = true;

            // Q
            if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_CONTROL)
                InputController.SHIFT_DOWN_PRESSED = true;

            // A
            if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
                if (!InputController.TURN_RIGHT_PRESSED)
                    InputController.TURN_LEFT_PRESSED = true;

            // D
            if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
                if (!InputController.TURN_LEFT_PRESSED)
                    InputController.TURN_RIGHT_PRESSED = true;

            // ESC
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                boolean raceStarted = ((GamePanel) windowReference.changeablePanel).isRaceStarted();
                if (raceStarted)
                {
                    if (windowReference.isDragRace())
                        windowReference.setToDragRacePause();

                    if (windowReference.isFreeroam())
                        windowReference.setToFreeroamPause();

                    if (windowReference.isScoreAttack())
                        windowReference.setToScoreAttackPause();
                }
            }
        }

        private void handleGameInputReleased(KeyEvent e)
        {
            // W
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
                InputController.THROTTLE_PRESSED = false;

            // S
            if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
                InputController.BRAKE_PRESSED = false;
        }

        private void handlePauseInput(KeyEvent e)
        {
            /* If ESC key */
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                if (windowReference.isDragRacePause())
                    windowReference.setToDragRace();

                if (windowReference.isFreeroamPause())
                    windowReference.setToFreeroam();

                if (windowReference.isScoreAttackPause())
                    windowReference.setToScoreAttack();
            }
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            /* If in game */
            if (windowReference.isGame())
                handleGameInputPressed(e);

            /* If in menus */
            else if (windowReference.isMenu())
                handleMenuInput(e);

            /* If in garage */
            else if (windowReference.isGarage())
                handleMenuInput(e);

            /* If in pause */
            else if (windowReference.isPause())
                handlePauseInput(e);
        }

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            /* If in game */
            if (windowReference.isGame())
                handleGameInputReleased(e);
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


    private class WindowInput implements WindowListener
    {
        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            //UserCollection.save();
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {
            //UserCollection.save();
            System.exit(0);
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {
//            UserCollection.save();
//            System.exit(0);
        }
    }



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ GRAPHICS --------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void updateBackgroundImage()
    {
        backgroundCanvas.updateGameImage();
    }

    public BufferedImage getBackgroundImage()
    {
        return backgroundCanvas.getGameImage();
    }

    public BufferedImage getFreeroamPanelGameImage()
    {
        return freeroamPanel.getGameImage();
    }

    public BufferedImage getFreeroamPanelPauseImage()
    {
        return freeroamPanel.getPauseImage();
    }

    public BufferedImage getScoreAttackPanelGameImage()
    {
        return scoreAttackPanel.getGameImage();
    }

    public BufferedImage getScoreAttackPanelPauseImage()
    {
        return scoreAttackPanel.getPauseImage();
    }

    public BufferedImage getDragRacePanelGameImage()
    {
        return dragRacePanel.getGameImage();
    }

    public BufferedImage getDragRacePanelPauseImage()
    {
        return dragRacePanel.getPauseImage();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
