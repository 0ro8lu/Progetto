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
    public int eta = 0;
    public float letalita = 0;//probabilita' malato sintomatico
    protected int durata;// numero di giorni tra contagio e guarigione
    protected int sintomaticita = 0;//probabilità di sviluppare sintomi
    Stato_salute stato_salute; ///TODO: Togliere questi zero iniziali?
    protected int velocita = 0;
    /* Gli stati sono:
    0=sano(verde)
    1=guariti(blu)
    2=contagiati sintomatici(rosso)
    3=asintomatici(giallo)
    4=morto(nero)
   */

    protected boolean tampone = false;
    protected boolean movimento = true;

    protected float x_Pos = 0.0f;
    protected float y_Pos = 0.0f; //posizione della persone
    private int m_Punto[];
    private boolean m_UltimoAsse; //Quando false l'ultimo movimento e' stato fatto sull'asse delle ascisse, altrimenti su quello delle ordinate =D

    private Incubazione m_Incubazione = null;
    private Guarigione  m_Guarigione;

    Persona(impiego v)
    {
        durata = VariabiliSimulazione.durata;

        this.individuo = v;

        stato_salute = model.Stato_salute.SANO;
        //sintomaticita = RandomUtil.randInt(40, 70);
        sintomaticita = RandomUtil.randInt(0, 100);

        x_Pos = (float) RandomUtil.randInt(10, Window.getWidth() - 15);
        y_Pos = (float) RandomUtil.randInt(10, Window.getHeight() - 15);

        m_Punto = new int[2];
        m_Punto[0] = RandomUtil.randInt(10, Window.getWidth() - 15);
        m_Punto[1] = RandomUtil.randInt(10, Window.getHeight() - 15);

        m_UltimoAsse = RandomUtil.randInt(0, 1) != 0;

        m_Guarigione = new Guarigione();
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
        this.movimento = true;
    }

    public void set_contagiato() { m_Incubazione = new Incubazione(durata/3, false); }

    public void set_asintomatico() { m_Incubazione = new Incubazione(durata/6, true); }

    public void set_morto()
    {
        this.stato_salute = model.Stato_salute.MORTO;
        this.movimento = false;
    }

    public void set_movimento(boolean t)
    {
        movimento = t;
    }

    public void set_tampone()
    {
        this.tampone = true;
    }

    public boolean get_tampone()
    {
        return this.tampone;
    }

    public void decrementa_durata()
    {
        if(m_Incubazione != null)
        {
            if(!m_Incubazione.haFinito())
                m_Incubazione.decrementaGiorni();
            else
            {
                m_Guarigione.decrementaGiorni();
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
            letalita = RandomUtil.randInt(0, 100);
        }
        if (eta > 50 && eta <= 70)
        {
            letalita = RandomUtil.randInt(10, 100);
        }
        if (eta > 70 && eta <= 80)
        {
            letalita = RandomUtil.randInt(20, 100);
        }
        if (eta > 80)
        {
            letalita = RandomUtil.randInt(30, 100);
        }
    }

    void Update()
    {
        if (movimento)
        {
            if ((int) x_Pos == m_Punto[0] && (int) y_Pos == m_Punto[1])
            {
                m_Punto[0] = RandomUtil.randInt(10, Window.getWidth() - 15);
                m_Punto[1] = RandomUtil.randInt(10, Window.getHeight() - 15);
            }
            if (Math.abs(m_Punto[0] - x_Pos) != 0 && Math.abs(m_Punto[1] - y_Pos) != 0)
            {
                if (m_UltimoAsse)
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

    private class Guarigione
    {
        public void decrementaGiorni()
        {
            if(stato_salute == Stato_salute.ASINTOMATICO || stato_salute == Stato_salute.CONTAGIATO)
            {
                if (durata <= 0)
                {
                    set_guarito();
                }
                if (letalita < VariabiliSimulazione.letalitaVirus && stato_salute == Stato_salute.CONTAGIATO)
                {
                    set_morto();
                }
                durata--;
            }
        }
    }

    private class Incubazione
    {
        private int m_Tempo;
        private boolean m_Asintomatico = false;
        private boolean m_Finito = false;

        public Incubazione(int tempo, boolean asintomatico)
        {
            m_Tempo        = tempo;
            m_Asintomatico = asintomatico;
        }

        public boolean haFinito() { return m_Finito; }

        public void decrementaGiorni()
        {
            if(m_Tempo > 0)
                m_Tempo--;
            else
            {
                m_Finito = true;
                if(m_Asintomatico)
                {
                    stato_salute = Stato_salute.ASINTOMATICO;
                }
                else
                {
                    stato_salute = Stato_salute.CONTAGIATO;
                    movimento = false;
                }
            }
        }
    }
}


