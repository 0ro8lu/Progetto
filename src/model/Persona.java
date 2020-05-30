package model;

import view.Window;

import java.util.Random;
import java.lang.Math;

enum impiego
{
    MEDICO, OPERAIO, DISOCCUPATO
}

//disoccupato: chi è in pensione, chi non lavora, chi ha un lavoro e viene fermato durante l'epidemia
//operaio: chi lavora anche durante l'epidemia
//medico: lavora sempre
// la persona può avere un numero di età compreso da 1 a 100

public class Persona
{
    impiego individuo;
    protected int eta = 0;
    protected float letalita = 0;//probabilita' malato sintomatico
    protected int durata = 5;// numero di giorni tra contagio e guarigione
    protected int sintomaticita = 0;//probabilità di sviluppare sintomi
    Stato_salute stato_salute;
    protected int velocita = 0;
    /* Gli stati sono:
    0=sano(verde)
    1=guariti(blu)
    2=contagiati sintomatici(rosso)
    3=asintomatici(giallo)
    4=morto(nero)
   */

    protected boolean vivo = true;
    //protected boolean asintomatico = false;


    protected float x_Pos = 0.0f;
    protected float y_Pos = 0.0f; //posizione della persone
    private int m_Punto[];
    private boolean m_UltimoAsse; //Quando false l'ultimo movimento e' stato fatto sull'asse delle ascisse, altrimenti su quello delle ordinate =D

    Persona(impiego v)
    {
        this.individuo = v;

        stato_salute = model.Stato_salute.SANO;
        sintomaticita = randInt(40, 70);

        x_Pos = (float) randInt(10, Window.getWidth() - 15);
        y_Pos = (float) randInt(10, Window.getHeight() - 15);

        m_Punto = new int[2];
        m_Punto[0] = randInt(10, Window.getWidth() - 15);
        m_Punto[1] = randInt(10, Window.getHeight() - 15);

        if (randInt(0, 1) == 0)
        {
            m_UltimoAsse = false;
        } else
        {
            m_UltimoAsse = true;
        }
    }

    protected int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public float getX()
    {
        return x_Pos;
    }

    public float getY()
    {
        return y_Pos;
    }

    public int getVelocita()
    {
        return velocita;
    }

    /*public boolean getIsSintomatico()
    {
        return asintomatico;
    }
*/
    public int getSintomaticita()
    {
        return sintomaticita;
    }

    public void set_sano()
    {
        this.stato_salute = model.Stato_salute.SANO;
    }

    public void set_guarito()
    {
        this.stato_salute = model.Stato_salute.GUARITO;
    }

    public void set_contagiato()
    {
        this.stato_salute = model.Stato_salute.CONTAGIATO;
    }

    public void set_asintomatico()
    {
        this.stato_salute = model.Stato_salute.ASINTOMATICO;
    }

    public void set_morto()
    {
        this.stato_salute = model.Stato_salute.MORTO;
    }

    public void decrementa_durata()
    {
        if (stato_salute != Stato_salute.MORTO) {
            if (durata <= 0) {
                set_guarito();
            }
            else if(letalita>70)
            {
                stato_salute=Stato_salute.MORTO;
            }
            else
            {
                durata--;
            }
        }
    }


    public Stato_salute get_stato_salute()
    {
        return this.stato_salute;
    }

    // la letalita e' stata decisa,basandoci sulle fonti provenienti dal sito Source: Chinese Center for Disease Control and Prevention
    protected void set_Letalita(int eta)
    {
        if (eta <= 50)
        {
            letalita = randInt(0,100);
        }
        if (eta > 50 && eta <= 70)
        {
            letalita = randInt(10,100);
        }
        if (eta > 70 && eta <= 80)
        {
            letalita = randInt(20,100);
        }
        if (eta > 80)
        {
            letalita = randInt(30,100);
        }
    }

    ///TODO: Rendere astratto
    ///TODO: Aggiungere controllo per vedere se persona e' in movimento
    void Update()
    {
        if (this.stato_salute == Stato_salute.SANO || this.stato_salute == Stato_salute.ASINTOMATICO)
        {
            if ((int) x_Pos == m_Punto[0] && (int) y_Pos == m_Punto[1])
            {
                m_Punto[0] = randInt(10, Window.getWidth() - 15);
                m_Punto[1] = randInt(10, Window.getHeight() - 15);
            }
            if (Math.abs(m_Punto[0] - x_Pos) != 0 && Math.abs(m_Punto[1] - y_Pos) != 0)
            {
                if (m_UltimoAsse == true)
                {
                    if (m_Punto[0] - x_Pos < 0)
                        x_Pos = x_Pos - 0.5f;
                    else
                        x_Pos = x_Pos + 0.5f;

                    m_UltimoAsse = false;
                } else
                {
                    if (m_Punto[1] - y_Pos < 0)
                        y_Pos = y_Pos - 0.5f;
                    else
                        y_Pos = y_Pos + 0.5f;

                    m_UltimoAsse = true;
                }
                return;
            }

            if ((int) x_Pos != m_Punto[0])
            {
                if (m_Punto[0] - x_Pos < 0)
                    x_Pos = x_Pos - 0.5f;
                else
                    x_Pos = x_Pos + 0.5f;
            }

            if ((int) y_Pos != m_Punto[1])
            {
                if (m_Punto[1] - y_Pos < 0)
                    y_Pos = y_Pos - 0.5f;
                else
                    y_Pos = y_Pos + 0.5f;
            }
        }
    }
}


