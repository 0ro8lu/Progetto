package eventi;

public abstract class IGestoreEventi
{
    public IGestoreEventi()
    {
        gestoreEventi = this;
    }

    public static IGestoreEventi Get()
    {
        return gestoreEventi;
    }

    public abstract boolean AggiungiDelegato(DelegatoEvento delegatoEvento, long tipoEvento);
    public abstract boolean RimuoviDelegato(DelegatoEvento delegatoEvento, long tipoEvento);
    public abstract boolean AttivaEvento(Evento evento);

    public static IGestoreEventi gestoreEventi;
}
