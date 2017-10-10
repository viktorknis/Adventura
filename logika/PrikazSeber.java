/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Seber představují ...
 *
 * @author    Viktor Knis
 * @version   0.00.000
 */
public class PrikazSeber implements IPrikaz{
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            
            return "Co mám sebrat? Musíš zadat název věci";
        }

        String nazevSbiraneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.odeberVec(nazevSbiraneVeci);

        if (sbirana == null) {
            return "To tu není!";
        }
        else {
            if(sbirana.jePrenositelna())
            {
                if(plan.getBatoh().vratObsahBatohu().size()<plan.getBatoh().getKapacita())
                {
                plan.getBatoh().pridejVec(sbirana);
                return "Úspěšně jsi vložil do batohu " + sbirana.getNazev();
                }   
                else
                {
                aktualniProstor.vlozVec(sbirana);
                return "Batoh je plný";
                }
            }
            else{
                aktualniProstor.vlozVec(sbirana);
                return "To neuzvedneš!";
            }
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
