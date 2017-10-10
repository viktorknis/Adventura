package logika;

/**
 * @author    Viktor Knis
 *  Třída PrikazZabij implementuje pro hru příkaz zabij.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 */
class PrikazZabij implements IPrikaz {
    private static final String NAZEV = "zabij";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazZabij(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "zabij". Hráč se může pokusit zabít draka, pokuď má oslíka tak se drak přidá na jeho stranu. Metoda také řeší několik špatných konců hry.
     *
     *@param parametry - jako  parametr musí být uveden drak ( draka ) nebo vlk ( vlka ),
     *  
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) {
            return "Koho mám zabít? Nerozumím";
        }

        String vec = parametry[0];
        if(vec.equals("draka"))
        {
          if(plan.getAktualniProstor().getNazev().equals("Černý_les"))
          {
            if(plan.getBatoh().obsahujeVec("meč"))
            {
                if(plan.getZavrenaMistnostVlk())
                {
                    plan.getHra().setKonecHry(true);
                    return "Zabil jsi Draka a Vlk je už také mrtev! Vyhrál jsi!";
                }
                else
                {
                    plan.Otevri(1);
                    return "Drak byl zabit teď eště vlk!";
                }
            }
            else
            {
                plan.getHra().setKonecHry(true);
                return "Na zabití draka potřebuješ meč - ten nemáš - umřel jsi - konec hry.";
            }
          }
          else
          {
              return "Zabít draka můžeš jen v prostoru černý les!";
          }
        }
        else if(vec.equals("vlka"))
        {
          if(plan.getAktualniProstor().getNazev().equals("Bílý_les"))
          {
            if(plan.getBatoh().obsahujeVec("luk"))
            {
                if(plan.getZavrenaMistnostVlk())
                {
                    plan.getHra().setKonecHry(true);
                    return "Zabil jsi Vlka a Drak je už také mrtev! Vyhrál jsi!";
                }
                else
                {
                    plan.Otevri(0);
                    return "Vlk byl zabit teď eště Drak!";
                }
            }
            else
            {
                plan.getHra().setKonecHry(true);
                return "Na zabití vlka potřebuješ luk - ten nemáš - umřel jsi - konec hry.";
            }
          }
          else
          {
              return "Zabít Vlka můžeš jen v prostoru Bílý les!";
          }
        }
        else
        {
            return "Zabít lze jen drak ( draka ) nebo vlk ( vlka )";
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


