package logika;

import java.util.ArrayList;
import java.util.List;
import util.ObserverZmenyProstoru;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author    Viktor Knis
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {
    private Prostor aktualniProstor;
    private Batoh batuzek;
    private Hra hra;
    private boolean zavrenaMistnostVlk;
    private boolean zavrenaMistnostDrak;
    private List<ObserverZmenyProstoru> seznamPozorovatelu;
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batuzek = new Batoh();
        this.hra = hra;
        zavrenaMistnostVlk = false;
        zavrenaMistnostDrak = false;
        seznamPozorovatelu = new ArrayList<>();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory   
        Prostor bily_les = new Prostor("Bílý_les","Krásný bílý les, ve kterém žije zlý vlk",260,130);
        Prostor louka = new Prostor("Louka","Zelená louka",260,230);
        Prostor mesto = new Prostor("Město","Město plné lidí",60,230);
        Prostor krcma = new Prostor("Krčma","Klasická hospoda, pod pultem lze koupit meč za cibuli, bylinky, česnek a prase",60,330);
        Prostor farma = new Prostor("Farma","Obilná farma",430,230);
        Prostor chatrc = new Prostor("Chatrč","Stará chatrč",560,230);
        Prostor statek = new Prostor("Statek","Statek plný zvířat",430,330);
        Prostor cerny_les = new Prostor("Černý_les","Tmavý hustý černý les ve kterém žije zlý drak",260,330);
        
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        bily_les.setVychod(louka);
        louka.setVychod(bily_les);
        louka.setVychod(mesto);
        louka.setVychod(farma);
        louka.setVychod(cerny_les);
        mesto.setVychod(louka);
        mesto.setVychod(krcma);
        krcma.setVychod(mesto);
        farma.setVychod(louka);
        farma.setVychod(chatrc);
        farma.setVychod(statek);
        chatrc.setVychod(farma);
        statek.setVychod(farma);
        cerny_les.setVychod(louka);
        
        

                
        aktualniProstor = farma;  // hra začíná v místnosti farma
        
        
        Vec cibule = new Vec("cibule",true,"cibule.jpg");
        Vec cesnek = new Vec("česnek", true,"cesnek.jpg");
        Vec bylinky = new Vec("bylinky", true,"bylinky.jpg");
        
        farma.vlozVec(cibule);
        farma.vlozVec(cesnek);
        farma.vlozVec(bylinky);
        
        Vec prase = new Vec("prase", true,"prase.jpg");
        Vec slama = new Vec("sláma", true,"slama.jpg");
        
        statek.vlozVec(prase);
        statek.vlozVec(slama);
        
        Vec strasak = new Vec("strašák", false,"strasak.jpg");

        louka.vlozVec(strasak);
        
        
        Vec pavucina = new Vec("pavučina", true,"pavucina.jpg");
        
        cerny_les.vlozVec(pavucina);
        
        Vec koste = new Vec("koště", true,"koste.jpg");
        
        mesto.vlozVec(koste);
        
        Vec pivo = new Vec ("pivo",true,"pivo.jpg");
        krcma.vlozVec(pivo);

        Vec luk = new Vec ("luk",true,"luk.jpg");
        
        chatrc.vlozVec(luk);

        
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;

    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    // Metoda vrací batoh
    public Batoh getBatoh() 
    {
       return this.batuzek;
    }
    // Metoda vrací hru kterou hrajeme
    public Hra getHra()
    {
       return this.hra;
    }
    
    // Metoda otevírá místnost
    public void Otevri(int a)
    {
        if(a == 0)
        {
            this.zavrenaMistnostVlk = true;
        }
        else
        {
            this.zavrenaMistnostDrak = true;
        }
    }
    // Metoda vrací stav lokace s vlkem
    public boolean getZavrenaMistnostVlk()
    {
        return this.zavrenaMistnostVlk;
    }
    
    // Metoda vrací stav lokace s drakem
    public boolean getZavrenaMistnostDrak()
    {
        return this.zavrenaMistnostDrak;
    }
    
    public void zaregistrujPozorovatele(ObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    public void odregistrujPozorovatele(ObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    public void upozorniPozorovatele() 
    {
        for (ObserverZmenyProstoru pozorovatel : seznamPozorovatelu) 
        {
            pozorovatel.aktualizuj();
        }
    }
    
    
    }

