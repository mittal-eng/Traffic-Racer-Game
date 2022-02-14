package engine;

import engine.math.MathFunctions;

/**
 * Created by Leo on 11-Sep-15.
 */
public abstract class GameLoop
{
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Loop Threads */
    protected GameThreadFixed gameThreadFixed = null;
    protected GameThreadFree gameThreadFree = null;

    /* Control variables */
    protected boolean isRunning = false;
    protected boolean isPaused = false;

    /* Time management */
    private final double UPDATES_PER_SECOND = 200;
    private final double DELTA_TIME_S = 1.0 / UPDATES_PER_SECOND;
    private final long DELTA_TIME_NS = (long) MathFunctions.convertStoNS(getDeltaTimeS());
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------- Loop Control -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void initializeGameLoop()
    {
        /* Create new Threads */
        gameThreadFixed = new GameThreadFixed(this);
        gameThreadFree = new GameThreadFree(this);

        /* Start execution */
        gameThreadFixed.start();
        gameThreadFree.start();

        this.isRunning = true;
        this.isPaused = true;
    }

    public void startGameLoop()
    {
        this.isRunning = true;
        this.isPaused = false;
    }

    public void stopGameLoop()
    {
        this.isRunning = false;
        this.isPaused = false;
    }

    public void pauseGameLoop()
    {
        this.isRunning = false;
        this.isPaused = false;

        /* Wait for threads to finish */
        try {
            gameThreadFree.join();
            gameThreadFixed.join();
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeGameLoop()
    {
        this.initializeGameLoop();
        this.isRunning = true;
        this.isPaused = false;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ Loop Update -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /* Fixed Game Loop */
    private void fixedGameLoop()
    {
        long nowTimeNS = System.nanoTime();
        long lastUpdateTimeNS = System.nanoTime();
        long nowTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());
        long lastTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());
        int updateCounter = 0;

        /* While loop is not terminated */
        while (isRunning)
        {
            /* Save current time in NS and S*/
            nowTimeNS = System.nanoTime();
            nowTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());

            /* If loop is not paused */
            if(!isPaused)
            {
                /* If it's time to updatePhysics */
                if (Math.abs(nowTimeNS - lastUpdateTimeNS) >= DELTA_TIME_NS)
                {
                    this.fixedUpdate();                                                                                 // Perform the updatePhysics
                    updateCounter++;                                                                                    // Increase updatePhysics counter
                    lastUpdateTimeNS = System.nanoTime();                                                               // Save last updatePhysics time in NS
                }

                /* If one second has passed */
                if (Math.abs(nowTimeS - lastTimeS) >= 1)
                {
                    System.out.println("Fixed update! New second! Updated " + updateCounter + " times per second");     // Print the stats
                    updateCounter = 0;                                                                                  // Reset updatePhysics counter
                    lastTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());                                   // Save last time in S
                }
            }
        }
    }

    /* Free Game Loop */
    private void freeGameLoop()
    {
        long nowTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());
        long lastTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());
        int updateCounter = 0;

        /* While loop is not terminated */
        while (isRunning)
        {
            /* Save current time in S*/
            nowTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());

            /* If loop is not paused */
            if(!isPaused)
            {
                this.freeUpdate();                                                                                      // Perform the updatePhysics
                updateCounter++;                                                                                        // Increase updatePhysics counter

                /* If one second has passed */
                if (Math.abs(nowTimeS - lastTimeS) >= 1)
                {
                    System.out.println("Free update! New second! Updated " + updateCounter + " times per second");      // Print the stats
                    updateCounter = 0;                                                                                  // Reset updatePhysics counter
                    lastTimeS = (long) MathFunctions.convertNStoS(System.nanoTime());                                   // Save last time in S
                }
            }
        }
    }

    /* The updates performed in fixed thread */
    /* !!!!!!! ALWAYS OVERRIDE !!!!!!! */
    protected abstract void fixedUpdate();

    /* The updates performed in free thread */
    /* !!!!!!! ALWAYS OVERRIDE !!!!!!! */
    protected abstract void freeUpdate();
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ Game Threads ----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public class GameThreadFixed extends Thread
    {
        GameLoop gameLoopReference;

        public GameThreadFixed(GameLoop gameLoopReference)
        {
            this.gameLoopReference = gameLoopReference;
        }

        @Override
        public void run()
        {
            super.run();
            gameLoopReference.fixedGameLoop();
        }
    }

    public class GameThreadFree extends Thread
    {
        GameLoop gameLoopReference;

        public GameThreadFree(GameLoop gameLoopReference)
        {
            this.gameLoopReference = gameLoopReference;
        }

        @Override
        public void run()
        {
            super.run();
            gameLoopReference.freeGameLoop();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------- Getters -------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public double getDeltaTimeS() {
        return DELTA_TIME_S;
    }

    public double getUpdatesPerSecond() {
        return UPDATES_PER_SECOND;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isRunning() {
        return isRunning;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
