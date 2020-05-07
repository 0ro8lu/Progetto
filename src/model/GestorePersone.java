package model;

import controller.Controller;
import eventi.EventoDisegna;

import java.util.ArrayList;

public class GestorePersone
{

    public GestorePersone()
    {
        m_Persone = new ArrayList<>();
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
        m_Persone.add(new Disoccupato());
    }

    public void creaPersone(int numero)
    {
        for (int i = 0; i < numero; i++)
        {
            ///TODO: Decide how many people of each type of career.
            m_Persone.add(new Disoccupato());
        }
    }

    public void Update()
    {
        for (Persona persona : m_Persone)
        {
            ///TODO: Maybe implement this inside each different person, and have them do different things based on their job.
            persona.Update();
            ///TODO: Maybe implement a check for dead people and remove them from the list, or we renderer the person's update method null.
        }
    }

    public void Disegna()
    {
        EventoDisegna evento = new EventoDisegna(m_Persone);
        Controller.m_GestoreEventi.AttivaEvento(evento);
    }

    ArrayList<Persona> m_Persone;
}
