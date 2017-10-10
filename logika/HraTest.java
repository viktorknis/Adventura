package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Viktor Knis
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Test testuje dohratelnost hry. Protože hra generuje předměty, které se dají sebrat náhodně, tak je nutné si vytvořit menší ,,hack,,
     * Vytvoříme si věci, dáme si je do místnosti jako kdybysme je sebrali.
     * 
     */
    @Test
    public void testVyhry() 
    {
        hra.zpracujPrikaz("seber česnek");
        hra.zpracujPrikaz("seber bylinky");
        hra.zpracujPrikaz("seber cibule");
        hra.zpracujPrikaz("jdi Statek");
        hra.zpracujPrikaz("seber prase");
        hra.zpracujPrikaz("seber sláma");
        assertEquals(4, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // test že do batohu lze vložit jen 4 věci.
        hra.zpracujPrikaz("vyhod česnek");
        assertEquals(3, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // test vyhození věci.
        hra.zpracujPrikaz("seber česnek");
        assertEquals(4, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // test opětovného sebrání
        hra.zpracujPrikaz("jdi Farma");
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Město");
        hra.zpracujPrikaz("jdi Krčma");
        hra.zpracujPrikaz("kup meč");
        assertEquals(1, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // test že jsme koupili za všechny naše věci meč.
        hra.zpracujPrikaz("jdi Město");
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Farma");
        hra.zpracujPrikaz("jdi Chatrč");
        hra.zpracujPrikaz("seber luk");
        assertEquals(2, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // test že máme luk a meč
        hra.zpracujPrikaz("jdi Farma");
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Bílý_les");
        hra.zpracujPrikaz("zabij vlka");
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Černý_les");
        hra.zpracujPrikaz("zabij draka");
        assertEquals(true, hra.getHerniPlan().getHra().konecHry()); // test konce hry
    }
    @Test
    public void testProhry() 
    {
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Bílý_les");
        hra.zpracujPrikaz("zabij vlka");
        assertEquals(true, hra.getHerniPlan().getHra().konecHry()); // test konce hry
    }
    @Test
    public void testProhry2() 
    {
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Černý_les");
        hra.zpracujPrikaz("zabij draka");
        assertEquals(true, hra.getHerniPlan().getHra().konecHry()); // test konce hry
    }
    
}
