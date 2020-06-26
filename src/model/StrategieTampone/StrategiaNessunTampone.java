package model.StrategieTampone;

import model.GestorePopolazione;
import model.Persona;

public class StrategiaNessunTampone extends StrategiaTampone{

    public StrategiaNessunTampone(GestorePopolazione gestorePopolazione) {
        super(gestorePopolazione);
    }

    @Override
    public void update(Persona persona) {

    }
}
