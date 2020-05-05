package controller;

import view.*;

public class Controller {

    public Controller()
    {
        Start();
    }

    public void Start()
    {
        m_View = new GameView();
        Loop();
    }

    public void Loop()
    {
        double previous = System.currentTimeMillis();
        double lag = 0.0;

        while(true)
        {
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= UPS)
            {
                Human test = GameCanvas.getHuman(0);
                test.setHumanX(test.getHumanX() + 0.2f);
                GameCanvas.setHuman(test, 0);

                lag -= UPS;
            }
            m_View.Draw();
        }
    }

    private GameView m_View;
    private final double FPS = 1000/60;
    private final double UPS = 1000/90;
}
