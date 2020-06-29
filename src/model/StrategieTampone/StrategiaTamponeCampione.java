package model.StrategieTampone;

import model.RandomUtil;
import controller.Controller;
import eventi.EventoFineRisorse;
import model.GestorePopolazione;
import model.Persona;
import model.Stato_salute;

public class StrategiaTamponeCampione extends StrategiaTampone
{
    public StrategiaTamponeCampione(GestorePopolazione gestorePopolazione)
    {
        super(gestorePopolazione);
    }

    @Override
    public void update(Persona persona)
    {
        if (m_StrategiaAttiva)
        {
            if (persona.get_stato_salute() != Stato_salute.CONTAGIATO && persona.get_stato_salute() != Stato_salute.MORTO && persona.get_stato_salute() != Stato_salute.GUARITO)
            {
                int valore = RandomUtil.randInt(0, 100);
                if (valore > 90)
                {
                    if (m_GestorePopolazione.risorse > m_GestorePopolazione.c_tampone)
                    {
                        m_GestorePopolazione.risorse -= m_GestorePopolazione.c_tampone;
                        if (persona.get_stato_salute() == Stato_salute.ASINTOMATICO)
                        {
                            persona.set_movimento(false);
                            persona.set_tampone();
                        }
                    } else
                    {
                        EventoFineRisorse evento = new EventoFineRisorse();
                        Controller.m_GestoreEventi.AttivaEvento(evento);
                    }
                }
            }
        }
    }
}
