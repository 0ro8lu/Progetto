package model.StrategieCure;

import controller.Controller;
import eventi.EventoFineRisorse;
import model.GestorePopolazione;
import model.Persona;

public class StrategiaCureTutti extends StrategiaCure
{
    public StrategiaCureTutti(GestorePopolazione gestorePopolazione)
    {
        super(gestorePopolazione);
    }

    @Override
    public void update(Persona persona)
    {
        if(m_GestorePopolazione.risorse > 3 * (m_GestorePopolazione.c_tampone))
        {
            persona.letalita -= persona.letalita / 5;
            m_GestorePopolazione.risorse -= 3 * (m_GestorePopolazione.c_tampone);
        }
        else
        {
            EventoFineRisorse evento = new EventoFineRisorse();
            Controller.m_GestoreEventi.AttivaEvento(evento);
        }
    }
}
