package view;

import model.VariabiliSimulazione;

public class InterfacciaMenu
{

    private VariabiliSimulazione m_VariabiliSimulazione;

    public InterfacciaMenu()
    {
        VariabiliSimulazione.numeroPersone = 2000;
        VariabiliSimulazione.costoTampone  = 1;
        VariabiliSimulazione.numeroRisorse = 5000; // Ricordarsi che deve essere < 10 * P * C

        /*
        Strategia Cure:
            -0: Cure per tutti
            -1: Cure solo per i giovani.
            -2: Cure solo per gli anziani.
            -3: Cure per nessuno.

         Strateiga Tamponi:
            -0: Test a campione.
            -1: Nessun tampone.
            -2: Nessun tampone ma si ferma metà della popolazione.
         */

        VariabiliSimulazione.strategiaCureID    = 3;
        VariabiliSimulazione.strategiaTamponeID = 0;

        VariabiliSimulazione.letalitaVirus = 100;
        VariabiliSimulazione.infettivita = 80;
        VariabiliSimulazione.sintomaticita = 50;

        VariabiliSimulazione.durata = 10;
    }

}
