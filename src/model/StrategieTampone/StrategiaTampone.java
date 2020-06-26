package model.StrategieTampone;

import model.GestorePopolazione;
import model.Persona;

public abstract class StrategiaTampone {
    protected GestorePopolazione m_GestorePopolazione; // Servira' per accedere ai contenuti e a gestire la strategia.

    public StrategiaTampone(GestorePopolazione gestorePopolazione)
    {
        this.m_GestorePopolazione = gestorePopolazione;
    }

    public abstract void update(Persona persona);
}
