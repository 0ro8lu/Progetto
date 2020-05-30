package model;

import controller.Controller;
import eventi.EventoDisegna;
import eventi.EventoNuovoGiorno;
import eventi.IGestoreEventi;
import eventi.Evento;

import java.util.ArrayList;
import java.util.Random;

public class GestorePopolazione
{

    public GestorePopolazione(int numero_Persone, int costo_Tampone, int numero_Risorse)
    {
        IGestoreEventi.Get().AggiungiDelegato(evt -> { DayUpdate(evt); }, 1);

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
        array_popolazione.get(indice_primo).set_asintomatico();

        for(Persona persona : array_popolazione)
        {
            System.out.print(persona.eta + " ");
            System.out.print(persona.individuo);
            System.out.println();
        }
    }

    public void DayUpdate(Evento evento)
    {
        EventoNuovoGiorno eventoNuovoGiorno = (EventoNuovoGiorno)evento;
        System.out.println("Giorno " + eventoNuovoGiorno.getDayCount());

        num_asintomatici=0;
        fattore_contagiosita=0;
        somma_velocita = 0;
        for(Persona persona : array_popolazione)
        {
            if(persona.stato_salute == Stato_salute.ASINTOMATICO)
            {
                num_asintomatici++;
                somma_velocita += persona.getVelocita();
            }
            if(persona.stato_salute == Stato_salute.ASINTOMATICO || persona.stato_salute == Stato_salute.CONTAGIATO)
            {
                persona.decrementa_durata();
            }
        }
        //fattore_contagiosita=somma_velocita/num_asintomatici;
        //System.out.println(fattore_contagiosita);

        System.out.println(somma_velocita);

        Infetta();
    }

    public void Infetta()
    {

        int conta_incontri = 0;
        while (conta_incontri < somma_velocita)
        {
            int indice = randInt(0, array_popolazione.size()-1);
            if (array_popolazione.get(indice).get_stato_salute() == Stato_salute.SANO)
            {
                int infettivita = randInt(10, 70);
                if ( infettivita > 65)
                {
                    if (array_popolazione.get(indice).getSintomaticita() > 52)
                    {
                        array_popolazione.get(indice).set_contagiato();
                        /// TODO: impostare il non movimento in persona.update
                    }
                    else{
                        array_popolazione.get(indice).set_asintomatico();
                    }

                }
                conta_incontri++;
            }
        }

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

    private int somma_velocita;// è dato dalla somma degli incontri delle persone malate e asintomatiche (aggiornato quotidianamente)
    private int fattore_contagiosita;
    int num_asintomatici;
}
