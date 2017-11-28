/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author buchalc
 */
public class AdventuraZakladni extends Application {

    BorderPane border;
    AnchorPane obrazekPane;
    TextArea centerTextArea;
    FlowPane dolniFlowPane;
    Label zadejPrikazLabel;
    TextField prikazTextField;
    ListView<String> vychodyListView;
    IHra hra;
    private OknoProstoru oknoProstoru;
    private PanelVychodu panelVychodu;
    private PanelBatohu panelBatohu;
    private PanelVeci panelVeci;
    private MenuBar menuBar;

    @Override
    public void start(Stage primaryStage) {
        hra = new Hra();
        border = new BorderPane();

        nastavHorniPanel();

        centerTextArea = nastavTextArea(hra);

        border.setCenter(centerTextArea);

        nastavDolniPanel(hra);

        border.setBottom(dolniFlowPane);

        oknoProstoru = new OknoProstoru(hra.getHerniPlan());
        border.setTop(oknoProstoru.getPanel());

        panelVychodu = new PanelVychodu(hra.getHerniPlan(),centerTextArea,prikazTextField);
        panelVeci = new PanelVeci(hra.getHerniPlan(),centerTextArea);
        
        BorderPane praveListy = new BorderPane();
        praveListy.setLeft(panelVeci.getList());
        praveListy.setRight(panelVychodu.getList());
        
        border.setRight(praveListy);
        
        panelBatohu = new PanelBatohu(hra.getHerniPlan());
        border.setLeft(panelBatohu.getList());

        initMenu();

        Scene scene = new Scene(new VBox(), 1000, 850);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, border);

        primaryStage.setTitle("Adventura - Útěk ze školy");
        primaryStage.setScene(scene);
        prikazTextField.requestFocus();
        primaryStage.show();
    }

    private void initMenu() {
        menuBar = new MenuBar();

        // --- Menu Soubor
        Menu menuSoubor = new Menu("Menu");

        //MenuItem novaHra = new MenuItem("Nová hra");
        MenuItem novaHra = new MenuItem("Nová hra",
                new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("/zdroje/new.png"))));
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                hra = new Hra();
                oknoProstoru.nastaveniHernihoPlanu(hra.getHerniPlan());
                panelVychodu.nastaveniHernihoPlanu(hra.getHerniPlan());
                centerTextArea.setText(hra.vratUvitani());
                prikazTextField.requestFocus();

            }
        });

        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem konec = new MenuItem("_Konec",
                new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("/zdroje/end.png"))));
        konec.setMnemonicParsing(true);

        konec.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });

        menuSoubor.getItems().addAll(novaHra, new SeparatorMenuItem(), konec);

        Menu menuNapoveda = new Menu("Nápověda");
        MenuItem oProgramu = new MenuItem("Informace o programu");
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události O programu
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Grafická adventura - Farmar Honza");
                alert.setHeaderText("Farmar Honza je jednoduchá Java adventura.");
                alert.setContentText("Verze 1.0.2");

                alert.showAndWait();
            }
        });

        MenuItem napovedaKAplikaci = new MenuItem("Nápověda k aplikaci");
        napovedaKAplikaci.setAccelerator(KeyCombination.keyCombination("F1"));
        napovedaKAplikaci.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události Nápověda k aplikaci
                // sekundární okno
                Stage stage = new Stage();
                stage.setTitle("Nápověda k adventuře");
                WebView webview = new WebView();
                webview.getEngine().load(
                        AdventuraZakladni.class.getResource("/zdroje/napoveda.html").toExternalForm()
                );
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            }
        });

        menuNapoveda.getItems().addAll(oProgramu, new SeparatorMenuItem(), napovedaKAplikaci);

        menuBar.getMenus().addAll(menuSoubor, menuNapoveda);

    }

    public void nastavHorniPanel() {
        obrazekPane = new AnchorPane();

        ImageView obrazek = new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("/zdroje/Mapa_adventura.png"), 650, 525, false, false));

        Circle tecka = new Circle(10, Paint.valueOf("red"));

        AnchorPane.setTopAnchor(tecka, 25.0);
        AnchorPane.setLeftAnchor(tecka, 100.0);

        obrazekPane.getChildren().addAll(obrazek, tecka);

    }

    public void nastavDolniPanel(IHra hra) {
        dolniFlowPane = new FlowPane();
        dolniFlowPane.setAlignment(Pos.CENTER);
        zadejPrikazLabel = new Label("Zadej příkaz: ");
        prikazTextField = new TextField();
        prikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String radek = prikazTextField.getText();
                String text = hra.zpracujPrikaz(radek);
                centerTextArea.appendText("\n\n" + radek + "\n");
                centerTextArea.appendText("\n" + text + "\n");
                prikazTextField.setText("");
                nastavVychody();
                if (hra.konecHry()) {
                    prikazTextField.setEditable(false);
                }
            }

        });

        dolniFlowPane.getChildren().addAll(zadejPrikazLabel, prikazTextField);
    }

    public TextArea nastavTextArea(IHra hra) {
        TextArea centerTextArea = new TextArea();
        centerTextArea.setText(hra.vratUvitani());
        centerTextArea.setEditable(false);
        return centerTextArea;
    }

    public void nastavVychody() {
        vychodyListView = new ListView<String>();
        ObservableList<String> data = FXCollections.observableArrayList();
        vychodyListView.setItems(data);
        vychodyListView.setPrefWidth(100);

        String vychody = hra.getHerniPlan().getAktualniProstor().seznamVychodu();

        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else if (args[0].equals("-text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            System.out.println("Neplatný parametr");
        }

    }

}
