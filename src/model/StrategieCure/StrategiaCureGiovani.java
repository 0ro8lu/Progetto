package model.StrategieCure;

import model.GestorePopolazione;
import model.Persona;

public class StrategiaCureGiovani extends StrategiaCure
{

    public StrategiaCureGiovani(GestorePopolazione gestorePopolazione)
    {
        super(gestorePopolazione);
    }

    @Override
    public void update(Persona persona)
    {
        if (m_GestorePopolazione.risorse > 3 * (m_GestorePopolazione.c_tampone))
        {
            if (persona.eta <= 50)
            {
                persona.letalita -= persona.letalita / 4;
                m_GestorePopolazione.risorse -= 3 * (m_GestorePopolazione.c_tampone);
            }
        } else
        {
            ///TODO: Lancia evento fine simulazione di tipo FineRisorse.
        }
    }
}
