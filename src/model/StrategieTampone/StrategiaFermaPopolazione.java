package model.StrategieTampone;

import model.GestorePopolazione;
import model.Persona;

public class StrategiaFermaPopolazione extends StrategiaTampone
{

    private boolean m_Fatto;

    public StrategiaFermaPopolazione(GestorePopolazione gestorePopolazione)
    {
        super(gestorePopolazione);

        m_Fatto = false;
    }

    @Override
    public void update(Persona persona)
    {
        if(!m_Fatto && m_StrategiaAttiva)
        {
            int popolazioneDaFermare = m_GestorePopolazione.numero_persone / 2;

            for (Persona p_Persona : m_GestorePopolazione.array_popolazione)
            {
                if (popolazioneDaFermare > 0)
                {
                    p_Persona.set_movimento(false);
                    popolazioneDaFermare--;
                } else
                    break;
            }
            m_Fatto = true;
        }
    }
}
