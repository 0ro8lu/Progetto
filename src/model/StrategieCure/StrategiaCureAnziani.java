package model.StrategieCure;

import controller.Controller;
import eventi.EventoFineRisorse;
import model.GestorePopolazione;
import model.Persona;

public class StrategiaCureAnziani extends StrategiaCure
{

    public StrategiaCureAnziani(GestorePopolazione gestorePopolazione)
    {
        super(gestorePopolazione);
    }

    @Override
    public void update(Persona persona)
    {
        if (m_GestorePopolazione.risorse > 3 * (m_GestorePopolazione.c_tampone))
        {
            if (persona.eta > 50)
            {
                persona.letalita -= persona.letalita / 3;
                m_GestorePopolazione.risorse -= 3 * (m_GestorePopolazione.c_tampone);
            }
        } else
        {
            EventoFineRisorse evento = new EventoFineRisorse();
            Controller.m_GestoreEventi.AttivaEvento(evento);
        }
    }
}
