package logika;
/**
 * @author    Viktor Knis
 * Tato Třída se stará o vyhazování věcí.
 * Prvně zjistí, zda hráč zadal jakou věc chce vyhodit.
 * Následně zjistí, jestli má danou věc baťůžku.
 * Pokuď ano tak jí vyhodí a věc skončí v prostoru ve kterém se hráč nachází a může jí znovu sebrat.
 */
class PrikazVyhod implements IPrikaz {
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;
    
    /**
     * Konstruktor třídy, který nastavuje herní plán.
    */    
    public PrikazVyhod(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Tato metoda se stará o veškerou funkcionalitu popsanou v základním popisu třídy.
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if(parametry.length == 0){
            return "Co mám vyhodit? Musíš zadat název věci.";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        if(plan.getBatoh().obsahujeVec(nazevVeci))
        {
         Vec vyhozena = plan.getBatoh().vyhodVec(nazevVeci);
         aktualniProstor.vlozVec(vyhozena);
         plan.upozorniPozorovatele();
         return "Vyhodil jsi z batohu " + vyhozena.getNazev();
        }
        else
        {
         return "Zadaná věc se v batohu nevyskytuje ";   
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
