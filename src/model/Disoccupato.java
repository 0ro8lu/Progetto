package model;

public class Disoccupato extends Persona {
    Disoccupato()
    {
        super(impiego.DISOCCUPATO);

        eta = RandomUtil.randInt(1, 100);
        super.set_Letalita(eta);
        velocita = 5;
    }
}
