package model.StrategieCure;

import model.GestorePopolazione;
import model.Persona;

public abstract class StrategiaCure
{
    protected GestorePopolazione m_GestorePopolazione; // Servira' per accedere ai contenuti e a gestire la strategia.

    public StrategiaCure(GestorePopolazione gestorePopolazione)
    {
        this.m_GestorePopolazione = gestorePopolazione;
    }

    public abstract void update(Persona persona);

}
