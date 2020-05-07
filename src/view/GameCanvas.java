package view;

import eventi.*;
import model.Persona;

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

    /*
    public static void addHuman(Human h)
    {
        m_HumanList.add(h);
    }
    public static void setHuman(Human h, int index)
    {
        m_HumanList.set(index, h);
    }
    public static Human getHuman(int index)
    {
        return m_HumanList.get(index);
    }

     */

    public void paintDelegate(Evento e)
    {
        EventoDisegna eventoDisegna = (EventoDisegna)e;
        m_ListaPersona = eventoDisegna.getUmani();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        for(Persona persona : m_ListaPersona)
        {
            g.fillRect((int)persona.getX(), (int)persona.getY(), 3, 3);
        }
    }

    private ArrayList<Persona> m_ListaPersona;
    //private static ArrayList<Human> m_HumanList;
}