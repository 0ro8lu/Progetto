package controller;

import model.GestorePersone;
import view.*;
import eventi.GestoreEventi;

public class Controller {

    public Controller()
    {
        Start();
    }

    public void Start()
    {
        m_GestoreEventi = new GestoreEventi();
        m_View = new GameView();
        m_GestorePersone = new GestorePersone();
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
                /*
                Human test = GameCanvas.getHuman(0);
                test.setHumanX(test.getHumanX() + 0.2f);
                GameCanvas.setHuman(test, 0);
                 */

                m_GestorePersone.Update();

                lag -= UPS;
            }
            m_GestorePersone.Disegna();
            m_View.Draw();
        }
    }

    // Enra x Sleepermane - getaway

    private GameView m_View;
    private GestorePersone m_GestorePersone;
    public static GestoreEventi m_GestoreEventi;
    private final double FPS = 1000/60;
    private final double UPS = 1000/90;
}
