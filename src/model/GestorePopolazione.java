package model;

import controller.Controller;
import eventi.EventoDisegna;

import java.util.ArrayList;
import java.util.Random;

public class GestorePopolazione
{

    public GestorePopolazione(int numero_Persone, int costo_Tampone, int numero_Risorse)
    {
        array_popolazione = new ArrayList<>();

        this.numero_pop = numero_Persone;
        this.c_tampone  = costo_Tampone;
        this.ris        = numero_Risorse;

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
        indice_primo = randInt(0, array_popolazione.size()-1);
        array_popolazione.get(indice_primo).set_contagiato();
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

    protected int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void Disegna()
    {
        EventoDisegna evento = new EventoDisegna(array_popolazione);
        Controller.m_GestoreEventi.AttivaEvento(evento);
    }



    private int indice_primo;
    private int c_tampone;
    private int ris;
    private int numero_pop;
    ArrayList<Persona> array_popolazione;
    private int num_medici;
    private int num_operai;
    private int num_disoccupati;
}
