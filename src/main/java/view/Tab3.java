/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Asiakas;
import model.Localization;
import model.Osa;
import model.Paketti;
import model.Tilaus;
import model.Tilaus_rivi;

/**
 *
 * @author RJulin
 */

/*
     * Rakentaa käyttöliittymän Tilaukset sivun
     * Sivulla tarkastellaan luotuja tilauksia ja niiden tilaus rivejä.
     * Tilaukset näkyvät listassa. Listaa painamalla kaikki tilauksen tilaus rivit näytetään toisessa listassa.
     * Luotuja tilauksia ja niiden tilaus rivejä voi muuttaa tai poistaa.
 */
public class Tab3 extends Tab {

    //Yleiset
    private static Tab3 INSTANCE = null;
    Controller controller = Controller.getInstance();
    Scene scene;
    TabPane tabPane;
    private final GridPane grid3 = new GridPane();

    List<Tilaus> tilausLista = new ArrayList<Tilaus>();
    ObservableList<Tilaus> tilausData;
    private final TableView tableOrders = new TableView();
    private final TableView tableDetails = new TableView();
    private final Button btnOrders = new Button();
    private final Button btnRemoveOrder = new Button();

    private final TableColumn brand = new TableColumn("Tilaus ID");
    private final TableColumn client = new TableColumn("Asiakas");
    private final TableColumn orderDate = new TableColumn("Tilauspvm");
    private final TableColumn amount = new TableColumn("Summa (€)");
    private final TableColumn additionalInfo = new TableColumn("HUOM");
    private final TableColumn products = new TableColumn("Osa");
    private final TableColumn productamount = new TableColumn("Paketti");
    private final TableColumn ordersum = new TableColumn("Määrä");
    private final TableColumn itemPrice = new TableColumn("Tuotehinta");

    private Tab3() {
        createTab3();
    }
        public static Tab3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tab3();
        }
        return INSTANCE;
    }

    private void createTab3() {
        grid3.setHgap(0); // Horizontal gap
        grid3.setVgap(0); // Vertical gap

        btnOrders.setPrefSize(200, 100);
        btnOrders.setPadding(new Insets(10, 10, 10, 10));
        btnOrders.setOnAction(e -> {
            tilausLista = controller.getTilaukset();
            tilausData = FXCollections.observableArrayList(tilausLista);
            tableOrders.setItems(tilausData);
        });
        grid3.add(btnOrders, 0, 0);

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        tableOrders.setEditable(true);

        brand.setStyle("-fx-font-size: 14pt;");
        brand.setMinWidth(200);
        brand.setCellValueFactory(new PropertyValueFactory<Tilaus, Integer>("tilausId"));

        client.setStyle("-fx-font-size: 14pt;");
        client.setMinWidth(1000);
        client.setCellValueFactory(new PropertyValueFactory<Tilaus, Asiakas >("asiakas"));

        orderDate.setStyle("-fx-font-size: 14pt;");
        orderDate.setMinWidth(200);
        orderDate.setCellValueFactory(new PropertyValueFactory<Tilaus, Date>("tilausPvm"));

        amount.setStyle("-fx-font-size: 14pt;");
        amount.setMinWidth(200);
        amount.setCellValueFactory(new PropertyValueFactory<Tilaus, Double>("yhteishinta"));


        tableOrders.getColumns().addAll(brand, client, orderDate, amount);
        tableOrders.setPrefHeight(500);
        tableOrders.setPrefWidth(1600);

        final VBox vboxOrders = new VBox();
        vboxOrders.setSpacing(5);
        vboxOrders.setPadding(new Insets(10, 10, 10, 20));
        vboxOrders.getChildren().addAll(tableOrders);

        tableDetails.setEditable(true);

        products.setStyle("-fx-font-size: 14pt;");
        products.setMinWidth(500);
        products.setCellValueFactory(new PropertyValueFactory<Tilaus_rivi, Osa>("osa"));

        productamount.setStyle("-fx-font-size: 14pt;");
        productamount.setMinWidth(500);
        productamount.setCellValueFactory(new PropertyValueFactory<Tilaus_rivi, Paketti>("paketti"));

        ordersum.setStyle("-fx-font-size: 14pt;");
        ordersum.setMinWidth(150);
        ordersum.setCellValueFactory(new PropertyValueFactory<Tilaus_rivi, Integer>("maara"));

        itemPrice.setStyle("-fx-font-size: 14pt;");
        itemPrice.setMinWidth(150);
        itemPrice.setCellValueFactory(new PropertyValueFactory<Tilaus_rivi, Double>("hinta"));

        tableDetails.getColumns().addAll(products, productamount, ordersum, itemPrice);
        tableDetails.setPrefHeight(300);
        tableDetails.setPrefWidth(1600);

        final VBox vboxDetails = new VBox();
        vboxDetails.setSpacing(5);
        vboxDetails.setPadding(new Insets(10, 10, 10, 20));
        vboxDetails.getChildren().addAll(tableDetails);

        btnRemoveOrder.setPrefSize(200, 100);
        btnRemoveOrder.setText("Poista");

        btnRemoveOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (tableOrders.getSelectionModel().getSelectedItem() != null) {
                    Tilaus removeItem = (Tilaus) tableOrders.getSelectionModel().getSelectedItem();
                    tableOrders.getItems().remove(removeItem);
                    controller.objectDelete(removeItem);
                    tableDetails.getItems().clear();
                } else {
                }
            }
        });

        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(30);
        buttonsBox.setPadding(new Insets(20, 20, 20, 20));

        buttonsBox.getChildren().addAll(btnRemoveOrder);

        grid3.add(vboxOrders, 1, 0, 7, 7);
        grid3.add(vboxDetails, 1, 10, 7, 2);
        grid3.add(buttonsBox, 7, 12, 7, 10);

        this.setContent(grid3);

        localizationSetText();

        tableOrders.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showRows();
            }
        });
    }

    public void showRows() {
        Tilaus valittuTilaus = (Tilaus) tableOrders.getSelectionModel().getSelectedItem();

        List<Object> tilausriviLista = new ArrayList<>();
        tilausriviLista.addAll(controller.getObjectRows(valittuTilaus));
        ObservableList<Object> rividata = FXCollections.observableArrayList(tilausriviLista);
        tableDetails.setItems(rividata);
    }

    public void localizationSetText() {
        Localization localization = Localization.getInstance();

        btnOrders.setText(localization.getBundle().getString("btn_orders"));  // = .setText("Tilaukset");
        btnRemoveOrder.setText(localization.getBundle().getString("btn_delete_product"));

        brand.setText(localization.getBundle().getString("lbl_orderId"));
        client.setText(localization.getBundle().getString("lbl_client"));
        orderDate.setText(localization.getBundle().getString("lbl_order_date"));
        amount.setText(localization.getBundle().getString("lbl_price"));
        additionalInfo.setText(localization.getBundle().getString("lbl_additionalInfo"));
        products.setText(localization.getBundle().getString("lbl_product"));
        productamount.setText(localization.getBundle().getString("btn_package"));
        ordersum.setText(localization.getBundle().getString("lbl_amount"));
        itemPrice.setText(localization.getBundle().getString("lbl_price"));
    }
}
