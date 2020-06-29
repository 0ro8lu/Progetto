package controller;

import eventi.Evento;
import eventi.EventoNuovoGiorno;
import eventi.IGestoreEventi;
import model.GestorePopolazione;
import view.*;
import eventi.GestoreEventi;

enum Scenario
{
    FINE_RISORSE, MALATTIA_SCONFITTA, MALATTIA_TAKEOVER
}

public class Controller {

    public Controller() { Start(); }

    public void Start()
    {

        m_InterfacciaMenu = new InterfacciaMenu();

        m_GestoreEventi = new GestoreEventi();

        m_GestoreEventi.AggiungiDelegato(this::StopDelegate, 2); // Evento fine risorse
        m_GestoreEventi.AggiungiDelegato(this::StopDelegate, 3); // Evento malattia sconfitta
        m_GestoreEventi.AggiungiDelegato(this::StopDelegate, 4); // Evento malattia sopravvento

        m_View = new GameView();
        m_GestorePopolazione = new GestorePopolazione();
        Loop();
    }

    private void StopDelegate(Evento evt)
    {
        Stop();

        switch ((int) evt.GetType())
        {
            case 2 -> m_Scenario = Scenario.FINE_RISORSE;
            case 3 -> m_Scenario = Scenario.MALATTIA_SCONFITTA;
            case 4 -> m_Scenario = Scenario.MALATTIA_TAKEOVER;
        }
    }

    public void Stop()
    {
        m_Stop = true;
    }

    public void Loop()
    {
        double previous = System.currentTimeMillis();
        double lag = 0.0;

        int tickCount = 0;
        int dayCount = 0;

        while(!m_Stop)
        {
            int TICKPERDAY = 300;
            double UPS = 1000 / 90;

            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= UPS)
            {
                tickCount++;

                if(tickCount >= TICKPERDAY)
                {
                    tickCount = 0;
                    dayCount++;
                    EventoNuovoGiorno evento = new EventoNuovoGiorno(dayCount);
                    IGestoreEventi.Get().AttivaEvento(evento);
                }

                m_GestorePopolazione.Update();

                lag -= UPS;
            }
            m_GestorePopolazione.Disegna();
            m_View.Draw();
        }

        ///TODO: sostituisci enum scenario con una stringa
        System.out.print("Simulazione finita per ");
        switch (m_Scenario)
        {
            case FINE_RISORSE       -> System.out.println("fine risorse");
            case MALATTIA_SCONFITTA -> System.out.println("malattia sconfitta");
            case MALATTIA_TAKEOVER  -> System.out.println("malattia takeover");
        }
    }

    // Enra x Sleepermane - getaway

    private GameView m_View;
    private GestorePopolazione m_GestorePopolazione;
    public static GestoreEventi m_GestoreEventi;
    private final double FPS = 1000/60;

    private boolean  m_Stop = false;
    private Scenario m_Scenario;

    private InterfacciaMenu m_InterfacciaMenu;
}
