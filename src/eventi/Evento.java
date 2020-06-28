package eventi;

public abstract class Evento
{
    Evento(long type, String name)
    {
        TipoEvento = type;
        NomeEvento = name;
    }

    public long GetType() { return TipoEvento; }
    public String GetName() { return NomeEvento; }

    protected long   TipoEvento;
    protected String NomeEvento;
}
