package model.StrategieTampone;

import model.GestorePopolazione;
import model.Persona;

public class StrategiaFermaPopolazione extends StrategiaTampone
{
    public StrategiaFermaPopolazione(GestorePopolazione gestorePopolazione)
    {
        super(gestorePopolazione);

        int popolazioneDaFermare = gestorePopolazione.numero_persone / 2;

        for(Persona persona : gestorePopolazione.array_popolazione)
        {
            if(popolazioneDaFermare > 0)
            {
                persona.set_movimento(false);
                popolazioneDaFermare--;
            }
            else
                break;
        }
    }

    @Override
    public void update(Persona persona)
    {

    }
}
