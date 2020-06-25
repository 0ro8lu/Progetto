package model;

public class Operaio extends Persona {
    Operaio()
    {
        super(impiego.OPERAIO);

        eta = super.randInt(18, 63);
        super.set_Letalita(eta);
        velocita = 15;
    }
}
