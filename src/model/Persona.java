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
    protected int infettivita = 0; //probabilita' di essere infettato
    protected float letalita = 0;//probabilita' malato sintomatico
    protected int durata = 0;// numero di giorni tra contagio e guarigione
    protected int sintomaticita = 0;//probabilità di sviluppare sintomi
    protected int stato_salute = 0;
    protected int velocita = 0;
    /* Gli stati sono:
    0=sano(verde)
    1=guariti(blu)
    2=contagiati sintomatici(rosso)
    3=asintomatici(giallo)
    4=morto(nero)
   */

    protected boolean vivo = true;

    protected float x_Pos = 0.0f;
    protected float y_Pos = 0.0f; //posizione della persone
    private int m_Punto[];
    private boolean m_UltimoAsse; //Quando false l'ultimo movimento e' stato fatto sull'asse delle ascisse, altrimenti su quello delle ordinate =D
    protected boolean movimento = true;

    Persona(impiego v)
    {
        this.individuo = v;

        stato_salute = 0;
        infettivita = randInt(10, 70);

        x_Pos = (float) randInt(10, Window.getWidth() - 10);
        y_Pos = (float) randInt(10, Window.getHeight() - 10);

        m_Punto = new int[2];
        m_Punto[0] = randInt(10,  Window.getWidth() - 10);
        m_Punto[1] = randInt(10, Window.getHeight() - 10);

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

    public void setStato_salute(int stato_salute)
    {
        this.stato_salute = stato_salute;
    }

    // la letalita e' stata decisa,basandoci sulle fonti provenienti dal sito Source: Chinese Center for Disease Control and Prevention
    protected void set_Letalita(int eta)
    {
        if (eta <= 50)
        {
            letalita = (float) 0.3;
        }
        if (eta > 50 && eta <= 70)
        {
            letalita = (float) 2.5;
        }
        if (eta > 70 && eta <= 80)
        {
            letalita = (float) 8.0;
        }
        if (eta > 80)
        {
            letalita = (float) 14.8;
        }
    }

    ///TODO: Rendere astratto
    ///TODO: Aggiungere controllo per vedere se persona e' in movimento
    void Update()
    {

        System.out.println("(X: " + m_Punto[0] + ", Y: " + m_Punto[1] + ")");
        System.out.println("Persona X:" + x_Pos + ", Y: " + y_Pos + ")");

        if ((int) x_Pos == m_Punto[0] && (int) y_Pos == m_Punto[1])
        {
            m_Punto[0] = randInt(10, Window.getWidth() - 10);
            m_Punto[1] = randInt(10, Window.getHeight() - 10);
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

