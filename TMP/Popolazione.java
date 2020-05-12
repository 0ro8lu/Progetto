package model;

import java.util.ArrayList;

public class Popolazione {
    Popolazione(int num, int costo_tampone, int risorse){
        // creazione array popolazione
        set_numero_pop(num);
        set_c_tampone(costo_tampone);
        set_ris(risorse);
        set_suddivisione(num);
    }

    void set_c_tampone(int t){
        c_tampone=t;
    }

    void set_ris(int r){
        ris=r;
    }

    void set_numero_pop(int p){
        numero_pop=p;
    }

    int get_c_tampone(){return c_tampone;}
    int get_ris(){return ris;}
    int get_numero_pop(){return numero_pop;}

    void set_suddivisione(int numero_pop){
        num_medici=(numero_pop/100)*1;
        num_operai=(numero_pop/100)*40;
        num_disoccupati=(numero_pop/100)*59;
        System.out.println("ho creato " +num_medici + " medici");
        System.out.println("ho creato " +num_operai + " operai");
        System.out.println("ho creato " +num_disoccupati + " disoccupati");

        for (int i=0;i<num_medici;i++)      { array_popolazione.add(new Medico()); }
        for (int i=0;i<num_operai;i++)      { array_popolazione.add(new Operaio()); }
        for (int i=0;i<num_disoccupati;i++) { array_popolazione.add(new Disoccupato()); }
    }
}
