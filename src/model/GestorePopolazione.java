package model;

import controller.Controller;
import eventi.EventoDisegna;

import java.util.ArrayList;

public class GestorePopolazione
{

    public GestorePopolazione(int numero_Persone, int costo_Tampone, int numero_Risorse)
    {
        array_popolazione = new ArrayList<>();

        this.numero_pop = numero_Persone;
        this.c_tampone  = costo_Tampone;
        this.ris        = numero_Risorse;

        creaPersone(numero_pop);
    }

    public void creaPersone(int numero)
    {
        ///TODO: Generare unico infetto.
        suddividiPopolazione(numero_pop);
    }

    void suddividiPopolazione(int numero_pop)
    {
        num_medici = (numero_pop / 100) * 1;
        num_operai = (numero_pop / 100) * 40;
        num_disoccupati = (numero_pop / 100) * 59;
        System.out.println("ho creato " + num_medici + " medici");
        System.out.println("ho creato " + num_operai + " operai");
        System.out.println("ho creato " + num_disoccupati + " disoccupati");

        for (int i = 0; i < num_medici; i++)      { array_popolazione.add(new Medico()); }
        for (int i = 0; i < num_operai; i++)      { array_popolazione.add(new Operaio()); }
        for (int i = 0; i < num_disoccupati; i++) { array_popolazione.add(new Disoccupato()); }
    }

    public void Update()
    {
        for (Persona persona : array_popolazione)
        {
            ///TODO: Maybe implement this inside each different person, and have them do different things based on their job.
            persona.Update();
            ///TODO: Maybe implement a check for dead people and remove them from the list, or we renderer the person's update method null.
        }
    }

    public void Disegna()
    {
        EventoDisegna evento = new EventoDisegna(array_popolazione);
        Controller.m_GestoreEventi.AttivaEvento(evento);
    }

    private int c_tampone;
    private int ris;
    private int numero_pop;
    ArrayList<Persona> array_popolazione;
    private int num_medici;
    private int num_operai;
    private int num_disoccupati;
}
