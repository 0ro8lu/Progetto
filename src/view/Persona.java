import java.util.Random;

enum impiego{
        MEDICO,OPERAIO,DISOCCUPATO
    }
    //disoccupato: chi è in pensione, chi non lavora, chi ha un lavoro e viene fermato durante l'epidemia
    //operaio: chi lavora anche durante l'epidemia
    //medico: lavora sempre
    // la persona può avere un numero di età compreso da 1 a 100

    public class Persona {
        impiego individuo;
        protected int eta=0;
        protected int infettivita=0; //probabilita' di essere infettato
        protected float letalita=0;//probabilita' malato sintomatico
        protected int durata=0;// numero di giorni tra contagio e guarigione
        protected int sintomaticita=0;//probabilità di sviluppare sintomi
        protected int stato_salute=0;
        protected int velocita=0;
    /* Gli stati sono:
    0=sano(verde)
    1=guariti(blu)
    2=contagiati sintomatici(rosso)
    3=asintomatici(giallo)
    4=morto(nero)
   */

        protected boolean vivo=true;
        protected float posizione=0;//posizione della persone
        protected boolean movimento=true;

        public static int randInt(int min, int max) {
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            return randomNum;
        }

        Persona(impiego v)
        {
            this.individuo=v;
            set_eta();
            set_Letalita(eta);
            set_velocita(v);

        }

        void set_eta(){
            if(this.individuo==impiego.MEDICO) {
                eta=randInt(35,65);
            }
            if(this.individuo==impiego.OPERAIO) {
                eta=randInt(18,63);
            }
            if(this.individuo==impiego.DISOCCUPATO) {
                eta=randInt(1,100);
            }
        }
        // la letalita e' stata decisa,basandoci sulle fonti provenienti dal sito Source: Chinese Center for Disease Control and Prevention
        void set_Letalita(int eta)
        {
            if(eta<=50)
            {
                letalita=(float) 0.3;
            }
            if(eta>50 && eta<=70)
            {
                letalita= (float) 2.5;
            }
            if(eta>70 && eta<=80)
            {
                letalita=(float) 8.0;
            }
            if(eta>80)
            {
                letalita=(float) 14.8;
            }
        }
        void setStato_salute(int n)
        {
            stato_salute=n;

        }
        void set_velocita(impiego t)
        {
            if(t==impiego.MEDICO)
            {
                velocita=20;
            }
            if(t==impiego.DISOCCUPATO)
            {
                velocita=5;
            }
            if(t==impiego.OPERAIO)
            {
                velocita=15;
            }
        }

    }

