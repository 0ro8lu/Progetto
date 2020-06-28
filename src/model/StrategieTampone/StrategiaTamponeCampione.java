package model.StrategieTampone;

import model.GestorePopolazione;
import model.Persona;
import model.Stato_salute;

public class StrategiaTamponeCampione extends StrategiaTampone {
    public StrategiaTamponeCampione(GestorePopolazione gestorePopolazione) {
        super(gestorePopolazione);
    }

    @Override
    public void update(Persona persona) {
        if(persona.get_stato_salute()!= Stato_salute.CONTAGIATO && persona.get_stato_salute()!=Stato_salute.MORTO)
        {
            int valore = m_GestorePopolazione.randInt(0,100);
            if(valore>90)
            {
              if(m_GestorePopolazione.risorse>m_GestorePopolazione.c_tampone)
              {

                  //persona.set_tampone();
                  if(persona.get_stato_salute()==Stato_salute.ASINTOMATICO && persona.get_stato_salute()!=Stato_salute.GUARITO )
                  {   m_GestorePopolazione.risorse-=m_GestorePopolazione.c_tampone;
                      System.out.println("PRESO");
                      persona.set_movimento(false);
                      ///TODO finire(stato salute, asintomatico non tampone)
                  }
              }
              else{
                  System.out.println("Stai  a picco");
                  ///TODO evento per Giacomo
              }
            }
        }



    }




}
