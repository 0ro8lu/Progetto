package view;

import eventi.*;
import model.Persona;
import model.Stato_salute;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameCanvas extends JPanel
{
    public GameCanvas()
    {
        m_ListaPersona = new ArrayList<>();

        IGestoreEventi.Get().AggiungiDelegato(evt -> { paintDelegate(evt); }, 0);
    }

    public void paintDelegate(Evento e)
    {
        EventoDisegna eventoDisegna = (EventoDisegna)e;
        m_ListaPersona = eventoDisegna.getUmani();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        super.setBackground(Color.black);

        for(Persona persona : m_ListaPersona)
        {
            switch (persona.get_stato_salute()){
                case SANO:
                    g.setColor(Color.green);
                    break;
                case GUARITO:
                    g.setColor(Color.blue);
                    break;
                case CONTAGIATO:
                    g.setColor(Color.red);
                    break;
                case ASINTOMATICO:
                    g.setColor(Color.yellow);
                    break;
                case MORTO:
                    g.setColor(Color.black);
                    break;
            }
            g.fillRect((int)persona.getX(), (int)persona.getY(), 3, 3);
        }
    }

    private ArrayList<Persona> m_ListaPersona;
}