package logika;

/**
 *  Třída Kup se stará o výměnu věcí za meč v lokaci krčma.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 */
class PrikazKup implements IPrikaz {
    private static final String NAZEV = "kup";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazKup(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *
     *@param parametry - Jako parametr musí být uveden meč - to co chceme koupit.
     *  
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) {
            return "Co mám koupit? Nerozumím";
        }

        String vec = parametry[0];
        Batoh pomocny = plan.getBatoh();
        
        if(plan.getAktualniProstor().getNazev().equals("Krčma") && vec.equals("meč"))
        {
            if(pomocny.obsahujeVec("cibule") && pomocny.obsahujeVec("bylinky") && pomocny.obsahujeVec("česnek") && pomocny.obsahujeVec("prase"))
            {
                Vec mec = new Vec("meč",true,"mec.jpg");
                pomocny.pridejVec(mec);
                pomocny.vyhodVec("cibule");
                pomocny.vyhodVec("bylinky");
                pomocny.vyhodVec("česnek");
                pomocny.vyhodVec("prase");
                plan.upozorniPozorovatele();
                return "Úspěšně jsi koupil meč!";
            }
            else
            {
                 return "Nemáš všechno potřebné na nákup meče";
            }    
        }
        else
        {
            return "Kupovat můžeš jen v Krčmě. Koupit lze jen meč";
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


