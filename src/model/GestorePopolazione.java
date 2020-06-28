package model;

import controller.Controller;
import eventi.*;
import model.StrategieCure.StrategiaCure;
import model.StrategieCure.StrategiaCureGiovani;
import model.StrategieCure.StrategiaCureNessuno;
import model.StrategieCure.StrategiaCureTutti;
import model.StrategieTampone.StrategiaFermaPopolazione;
import model.StrategieTampone.StrategiaNessunTampone;
import model.StrategieTampone.StrategiaTampone;
import model.StrategieTampone.StrategiaTamponeCampione;

import java.util.ArrayList;
import java.util.Random;

public class GestorePopolazione
{

    public GestorePopolazione(int numero_Persone, int costo_Tampone, int numero_Risorse, int letalitaVirus)
    {
        IGestoreEventi.Get().AggiungiDelegato(this::DayUpdate, 1);

        GestorePopolazione.letalitaVirus = letalitaVirus;
        array_popolazione = new ArrayList<>();

        this.numero_persone = numero_Persone;
        this.c_tampone = costo_Tampone;
        this.risorse = numero_Risorse;

        suddividiPopolazione(numero_persone);

        m_StrategiaCure = new StrategiaCureNessuno(this); ///TODO: Aggiungere logica in modo da cambiare strategia a piacere.
        m_StrategiaTampone=new StrategiaFermaPopolazione(this);
    }

    void suddividiPopolazione(int numero_pop)
    {
        num_medici = (numero_pop / 100);
        num_operai = (numero_pop / 100) * 40;
        num_disoccupati = (numero_pop / 100) * 59;
        System.out.println("ho creato " + num_medici + " medici");
        System.out.println("ho creato " + num_operai + " operai");
        System.out.println("ho creato " + num_disoccupati + " disoccupati");

        for (int i = 0; i < num_medici; i++)
        {
            array_popolazione.add(new Medico());
        }
        for (int i = 0; i < num_operai; i++)
        {
            array_popolazione.add(new Operaio());
        }
        for (int i = 0; i < num_disoccupati; i++)
        {
            array_popolazione.add(new Disoccupato());
        }
        ///TODO: Vedere di rendere locale.
        int indice_primo = RandomUtil.randInt(0, array_popolazione.size() - 1);
        array_popolazione.get(indice_primo).stato_salute = Stato_salute.ASINTOMATICO;

        for (Persona persona : array_popolazione)
        {
            System.out.print(persona.eta + " ");
            System.out.print(persona.individuo);
            System.out.println();
        }
    }

    public void DayUpdate(Evento evento)
    {
        System.out.println();
        EventoNuovoGiorno eventoNuovoGiorno = (EventoNuovoGiorno) evento;
        System.out.println("Giorno " + eventoNuovoGiorno.getDayCount());
        int sani = 0;
        int contagiati_asintomatici = 0;
        int contagiati_sintomatici = 0;
        int contagiati_non_rilevati = 0;
        int morti = 0;
        int guariti = 0;

        fattore_contagiosita = 0;
        somma_velocita = 0;

        // Aumento le risorse nel loop in base al movimento, e non più in base a questa somma.
        //risorse += numero_persone - (morti + contagiati_sintomatici);

        for (Persona persona : array_popolazione)
        {
            if (persona.stato_salute == Stato_salute.CONTAGIATO) contagiati_sintomatici++;
            if (persona.stato_salute == Stato_salute.GUARITO) guariti++;
            if (persona.stato_salute == Stato_salute.SANO) sani++;
            if (persona.stato_salute == Stato_salute.MORTO) morti++;

            //Strategie di cura.
            if(persona.stato_salute == Stato_salute.CONTAGIATO)
            {
                m_StrategiaCure.update(persona);
            }
            //Strategie di tampone
            m_StrategiaTampone.update(persona);

            if (persona.stato_salute == Stato_salute.ASINTOMATICO && persona.get_tampone())
            {
                contagiati_asintomatici++;
                somma_velocita += persona.getVelocita();
            }
            else if(persona.stato_salute == Stato_salute.ASINTOMATICO && !persona.get_tampone())
            {
                somma_velocita += persona.getVelocita();
                contagiati_non_rilevati++;
            }

            persona.decrementa_durata();

            ///TODO: Parlare ai ragazzi del fatto che nel PDF sta scritto che ogni persona consuma una unità di risorse al giorno.
            // Se uno non è morto consuma risorse
            if(persona.stato_salute != Stato_salute.MORTO)
                risorse--;

            if(persona.movimento)
                risorse++;
        }

        if(contagiati_non_rilevati == 0 && contagiati_sintomatici == 0)
        {
            EventoMalattiaSconfitta evt = new EventoMalattiaSconfitta();
            Controller.m_GestoreEventi.AttivaEvento(evt);
        }

        if(morti == numero_persone)
        {
            EventoMalattiaSopravvento evt = new EventoMalattiaSopravvento();
            Controller.m_GestoreEventi.AttivaEvento(evt);
        }

        if(risorse <= 0)
        {
            EventoFineRisorse evt = new EventoFineRisorse();
            Controller.m_GestoreEventi.AttivaEvento(evt);
        }

        //fattore_contagiosita=somma_velocita/num_asintomatici;
        //System.out.println(fattore_contagiosita);

        System.out.println("morti " + morti);
        System.out.println("sani " + sani);
        System.out.println("contagiati asintomatici " + contagiati_asintomatici);
        System.out.println("contagiati asintomatici non rilevati " + contagiati_non_rilevati);
        System.out.println("contagiati sintomatici " + contagiati_sintomatici);
        System.out.println("guariti " + guariti);
        System.out.println("somma velocita " + somma_velocita);
        System.out.println("risorse: " + risorse);
        Infetta();

    }

    public void Infetta()
    {

        int conta_incontri = 0;
        while (conta_incontri < somma_velocita)
        {
            int indice = RandomUtil.randInt(0, array_popolazione.size() - 1);
            if (array_popolazione.get(indice).get_stato_salute() == Stato_salute.SANO)
            {
                ///TODO: Discutere di questo valore: perchè tra 10 e 70 e non tra 0 e 100 e poi aggiustare il valore "threshold" in basso?
                int infettivita = RandomUtil.randInt(10, 70);
                if (infettivita > 20) //Vecchio valore 65
                {
                    ///TODO: Anche questo valore è da immettere nella classe delle variabili della simulazione.
                    if (array_popolazione.get(indice).getSintomaticita() > 65) //Vecchio valore = 52
                    {
                        array_popolazione.get(indice).set_contagiato();
                    } else
                    {
                        array_popolazione.get(indice).set_asintomatico();
                    }
                }
            }
            conta_incontri++;
        }
    }

    public void Update()
    {
        for (Persona persona : array_popolazione)
        {
            persona.Update();
        }
    }

    public void Disegna()
    {
        EventoDisegna evento = new EventoDisegna(array_popolazione);
        Controller.m_GestoreEventi.AttivaEvento(evento);
    }

    private StrategiaCure m_StrategiaCure;
    private StrategiaTampone m_StrategiaTampone;

    public static int letalitaVirus;
    public int c_tampone;
    public int risorse;
    public int numero_persone;
    public ArrayList<Persona> array_popolazione;
    private int num_medici;
    private int num_operai;
    private int num_disoccupati;

    private int somma_velocita;// è dato dalla somma degli incontri delle persone malate e asintomatiche (aggiornato quotidianamente)
    private int fattore_contagiosita;
    private int num_asintomatici;
}
