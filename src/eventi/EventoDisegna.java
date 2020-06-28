package eventi;

import model.Persona;

import java.util.ArrayList;

public class EventoDisegna extends Evento
{
    public EventoDisegna(ArrayList<Persona> listaUmani)
    {
        super(0, "Events.EventoDisegna");
        this.m_ListaUmani = listaUmani;
    }

    public ArrayList<Persona> getUmani() { return m_ListaUmani; }

    private ArrayList<Persona> m_ListaUmani;
}
