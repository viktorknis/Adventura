/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.HerniPlan;
import util.ObserverZmenyProstoru;

/**
 *
 * @author buchalc
 */
public class OknoProstoru implements ObserverZmenyProstoru
{

    private HerniPlan plan;
    private FlowPane horniPanel;
    private TextArea popis ;
    private Circle tecka ;

    public OknoProstoru(HerniPlan plan)
      {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);

        init();
      }

    private void init()
      {
        horniPanel = new FlowPane();
        AnchorPane obrazekPane = new AnchorPane();
        ImageView obrazek = new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("/zdroje/Mapa_adventura.png"), 650, 525, false, false));

          tecka = new Circle(10, Paint.valueOf("red"));

//        AnchorPane.setTopAnchor(tecka, 25.0);
//        AnchorPane.setLeftAnchor(tecka, 100.0);

        obrazekPane.getChildren().addAll(obrazek, tecka);
       

        popis = new TextArea();
        popis.setPrefWidth(350);
//        popis.setText(plan.getAktualniProstor().dlouhyPopis());
        popis.setEditable(false);

        horniPanel.getChildren().add(popis);
        horniPanel.getChildren().add(obrazekPane);
         aktualizuj();
      }

    public FlowPane getPanel()
      {
        return horniPanel;
      }

    @Override
    public void aktualizuj()
      {
        // aktualizuj popis prostoru
        popis.setText(plan.getAktualniProstor().dlouhyPopis());
        //aktualizuj umisteni tecky  
        AnchorPane.setTopAnchor(tecka, plan.getAktualniProstor().getPosTop());
        AnchorPane.setLeftAnchor(tecka, plan.getAktualniProstor().getPosLeft());

      }
         /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * 
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj();
    }


}