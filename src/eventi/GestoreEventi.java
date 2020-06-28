package eventi;

import java.util.ArrayList;
import java.util.HashMap;

public class GestoreEventi extends IGestoreEventi
{
    public GestoreEventi()
    {
        super();
    }

    @Override
    public boolean AggiungiDelegato(DelegatoEvento delegatoEvento, long tipoEvento)
    {
        ArrayList<DelegatoEvento> tmpList;

        if(m_AscoltatoriEventi.containsKey(tipoEvento))
            tmpList = m_AscoltatoriEventi.get(tipoEvento);
        else
            tmpList = new ArrayList<>();

        for (DelegatoEvento delegateFunction : tmpList)
        {
            if (delegatoEvento == delegateFunction)
            {
                return false;
            }
        }

        tmpList.add(delegatoEvento);
        m_AscoltatoriEventi.put(tipoEvento, tmpList);
        return true;
    }

    @Override
    public boolean RimuoviDelegato(DelegatoEvento delegatoEvento, long tipoEvento)
    {
        if(m_AscoltatoriEventi.containsKey(tipoEvento))
        {
            ArrayList<DelegatoEvento> tmpList = m_AscoltatoriEventi.get(tipoEvento);

            for(DelegatoEvento delegateFunction : tmpList)
            {
                if (delegatoEvento == delegateFunction)
                {
                    tmpList.remove(delegateFunction);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean AttivaEvento(Evento evento)
    {
        boolean processato = false;

        if(m_AscoltatoriEventi.containsKey(evento.GetType()))
        {
            ArrayList<DelegatoEvento> tmpList = m_AscoltatoriEventi.get(evento.GetType());
            for(DelegatoEvento delegateFunction : tmpList)
            {
                delegateFunction.CallBack(evento);
                processato = true;
            }
        }
        return processato;
    }

    HashMap<Long, ArrayList<DelegatoEvento>> m_AscoltatoriEventi = new HashMap<>();
}