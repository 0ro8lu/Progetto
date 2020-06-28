package model.StrategieCure;

import model.GestorePopolazione;
import model.Persona;

public class StrategiaCureNessuno extends StrategiaCure
{

    public StrategiaCureNessuno(GestorePopolazione gestorePopolazione)
    {
        super(gestorePopolazione);
    }

    @Override
    public void update(Persona persona)
    {
        // Non curo nessuno.
    }
}
