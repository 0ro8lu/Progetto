package model.StrategieTampone;

import model.GestorePopolazione;
import model.Persona;

public abstract class StrategiaTampone {

    protected boolean m_StrategiaAttiva;
    protected GestorePopolazione m_GestorePopolazione; // Servira' per accedere ai contenuti e a gestire la strategia.

    public StrategiaTampone(GestorePopolazione gestorePopolazione)
    {
        this.m_GestorePopolazione = gestorePopolazione;
        m_StrategiaAttiva = false;
    }

    public void attiva() { m_StrategiaAttiva = true; }

    public abstract void update(Persona persona);
}
