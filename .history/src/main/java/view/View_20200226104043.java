/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Localization;

public class View extends Application {

    private Controller controller = Controller.getInstance();
    // yleiset
    Scene scene;
    TabPane tabPane;

    //Tabit
    private Tab tab1;
    private Tab tab2;
    private Tab tab3;
    private Tab tab4;

    //Lokalisointi
    Localization localization = Localization.getInstance();

    //
    Text textWelcome = new Text();
    Text textTime = new Text();
    public Text textField1 = new Text();
    public Text textField2 = new Text();
    Label lblLanguageFI = new Label();
    Label lblLanguageRUS = new Label();
    Label lblLanguageEN = new Label();
    Label lblLogOut = new Label();
    HBox hEmpty = new HBox();
    GridPane gridPane = new GridPane();

    public View() {
    }

    public View(String username) {
        textField1.setText(username);
    }

    /**
     * Luo käyttöliittymä näkymä.<br>
     * Käyttöliittymässä on sivut:<br>
     * Varasto: Pakettien ja Osien käsittely<br>
     * Myynti: Tilauksien käsittely<br>
     * Taloustiedot: Tilauksien ja myyntien tarkastelu<br>
     *
     * @param primaryStage
     */
    public void start(Stage primaryStage) {

        // Käyttöliittymän rakentaminen
        try {
            tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
            
            BorderPane borderPane = new BorderPane();
            
            //Luo View sivulle yläreunan sessiotiedot
            gridPane = createViewHeaderGrid(primaryStage);
            
            //Luo View sivulle välilehdet sovelluksen eri sivuja varten
            tab1 = Tab1.getInstance();
            tab2 = Tab2.getInstance();
            tab3 = Tab3.getInstance();
            tab4 = Tab4.getInstance();
            
            tab1.styleProperty().set("-fx-pref-width: 100");
            tab2.styleProperty().set("-fx-pref-width: 100");
            tab3.styleProperty().set("-fx-pref-width: 100");
            tab4.styleProperty().set("-fx-pref-width: 100");
            
            //Tabit tabpaneen
            tabPane.getTabs().add(tab1);
            tabPane.getTabs().add(tab2);
            tabPane.getTabs().add(tab3);
            tabPane.getTabs().add(tab4);
            
            //View koostuu yläreunan gridPanesta ja sen alla olevasta tabPanesta
            VBox vBox = new VBox();
            vBox.getChildren().add(gridPane);
            vBox.getChildren().add(tabPane);
            
            scene = new Scene(vBox, 1900, 1000);
            scene.getStylesheets().add(this.getClass().getResource("/styles/stylesheet.css").toExternalForm());

            borderPane.prefHeightProperty().bind(scene.heightProperty());
            borderPane.prefWidthProperty().bind(scene.widthProperty());

            //Lokalisointi
            localizationSetText();

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    
    public GridPane createViewHeaderGrid(Stage primaryStage) {
        //Creating Text Filed for Tervetuloa        
            //Creating Text Filed for Kirjautumisaika
            Date date = new Date();
            
            textField2.setText(date.toLocaleString());

            //Creating Buttons 
            Image image = new Image("Finland-icon.png");
            lblLanguageFI.setGraphic(new ImageView(image));
            lblLanguageFI.setOnMouseClicked(e -> {
                localization.changeLocale("FI");
                localizationSetText();
                localization.translateAll((Tab1) tab1, (Tab2) tab2, (Tab3) tab3, (Tab4) tab4);
            });

            Image image2 = new Image("Russia-icon.png");
            lblLanguageRUS.setGraphic(new ImageView(image2));
            lblLanguageRUS.setOnMouseClicked(e -> {
                localization.changeLocale("RUS");
                localizationSetText();
                localization.translateAll((Tab1) tab1, (Tab2) tab2, (Tab3) tab3, (Tab4) tab4);
            });
            
            Image image3 = new Image("United-Kingdom-icon.png");
            lblLanguageEN.setGraphic(new ImageView(image3));
            lblLanguageEN.setOnMouseClicked(e -> {
                localization.changeLocale("US");
                localizationSetText();
                localization.translateAll((Tab1) tab1, (Tab2) tab2, (Tab3) tab3, (Tab4) tab4);
            });
            
            Image image4 = new Image("logout-icon.png");
            lblLogOut.setGraphic(new ImageView(image4));
            lblLogOut.setOnMouseClicked(e -> {
                controller.logOut(primaryStage);
            });

            //Setting size for the pane  
            gridPane.setMinSize(1900, 80);
            //Setting the vertical and horizontal gaps between the columns 
            gridPane.setVgap(5);
            gridPane.setHgap(10);
            //Setting the padding  
            gridPane.setPadding(new Insets(10, 10, 10, 10));

            hEmpty.setMinSize(1400, 30);

            //Arranging all the nodes in the grid 
            gridPane.add(textWelcome, 0, 0);
            gridPane.add(textField1, 1, 0);
            gridPane.add(textTime, 0, 1);
            gridPane.add(textField2, 1, 1);
            gridPane.add(hEmpty, 2, 0);
            gridPane.add(lblLanguageFI, 3, 0,1,2);
            gridPane.add(lblLanguageRUS, 4, 0,1,2);
            gridPane.add(lblLanguageEN, 5, 0,1,2);
            gridPane.add(lblLogOut, 6, 0,1,2);
            gridPane.setId("ylapalkki");
            
            return gridPane;
    }
    
    public void localizationSetText() {
        //Aseta tekstikenttien teksti uudelleen

        //Yleiset
        tab1.setText(localization.getBundle().getString("tab_sales"));
        tab2.setText(localization.getBundle().getString("tab_warehouse"));
        tab3.setText(localization.getBundle().getString("tab_orders"));
        tab4.setText(localization.getBundle().getString("tab_finance"));

        textWelcome.setText(localization.getBundle().getString("welcome_text"));
        textTime.setText(localization.getBundle().getString("time_text"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
