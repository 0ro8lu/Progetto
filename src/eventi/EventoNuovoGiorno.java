package eventi;

public class EventoNuovoGiorno extends Evento
{
    public EventoNuovoGiorno(int dayCount)
    {
        super(1, "Events.EventoNuovoGiorno");
        this.m_dayCount = dayCount;
    }

    public int getDayCount() { return m_dayCount; }

    private int m_dayCount;
}
