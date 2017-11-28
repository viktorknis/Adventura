/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;
import java.util.*;
/*******************************************************************************
 * @author    Viktor Knis
 * Batoh představuje třídu, do které se ukládají jednotlivé věci - nese záznam toho co má člověk  u sebe.
 */
public class Batoh
{
    public Map<String, Vec> seznamVeci;
    public int kapacita = 4;
    // konstruktor
    public Batoh()
    {
    seznamVeci = new HashMap<>();
    }
    // přidání věci
    public void pridejVec(Vec vec)
    {
     this.seznamVeci.put(vec.getNazev(),vec);   
    }
    // vrátí seznam věcí z batohu
    public Map<String, Vec> vratObsahBatohu()
    {
        return this.seznamVeci;
    }
    // vyhodí věc z batohu
    public Vec vyhodVec(String nazev)
    {
        return seznamVeci.remove(nazev);
    }
    // Detekovací metoda, která řekne jestli věc v batohu je či není.
    public Boolean obsahujeVec(String nazev)
    {
      boolean hledana = false;
      Vec pomocna = null;
      pomocna = this.seznamVeci.get(nazev);
      if(pomocna != null)
      {
          hledana = true;
      }
     return hledana;
    }
    // Vrací kapacitu batohu
    public int getKapacita()
    {
        return this.kapacita;
    }
}
