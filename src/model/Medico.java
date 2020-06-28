package model;


public class Medico extends Persona {
    Medico()
    {
        super(impiego.MEDICO);

        eta = RandomUtil.randInt(35, 65);
        super.set_Letalita(eta);
        velocita = 20;
    }
}
