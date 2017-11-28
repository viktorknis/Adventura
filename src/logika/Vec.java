/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy vec představují ...
 *
 * @author    Viktor Knis
 * @version   0.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private String obrazek;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost,String obrazek)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.obrazek = obrazek;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){
        return nazev;
    }

    public boolean jePrenositelna(){
        return prenositelnost;
    }
    //== Soukromé metody (instancí i třídy) ========================================
    public String getObrazek() {
        return obrazek;
    }
}
