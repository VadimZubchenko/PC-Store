/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Asiakas;
import model.Localization;
import model.Osa;
import model.Paketti;
import model.Product;
import model.Tilaus_rivi;


/**
 *
 * @author RJulin
 */
/*
     * Rakentaa käyttöliittymän Myynti sivun
     * Sivulla luodaan asiakkaan tilauksia.
     * 1. Valitse paketti/osa
     * 2. Valitse kappalemäärä
     * 3. Paina Lisää painiketta, Tilaukset listaan syntyy rivi
     * 4. Paina Lähetä painiketta, Tilaukset listan riveistä luodaan uusi tilaus tietokantaan
 */
public class Tab1 extends Tab {

    private static Tab1 INSTANCE = null;
    private final Controller controller = Controller.getInstance();
    private final Button btnPlusOrderAmount = new Button();
    private final Button btnMinusOrderAmount = new Button();
    private final TextField txtOrderAmount = new TextField();
    private final TableView productsTable = new TableView();
    public TextField UnitPriceTxt;

    // yleiset
    private ObservableList<Product> data;
    private List<Product> tilausrivit;
    private List<Paketti> pakettiLista;
    private ObservableList<Paketti> pakettiData;
    private List<Osa> osaLista;
    private ObservableList<Osa> osaData;

    // ekasivu
    final Tab tab1 = new Tab();
    private final GridPane grid1 = new GridPane();
    public final TableView tableTemp = new TableView();

    private final TextField searchField = new TextField();
    private final Text lblProduct = new Text();
    private final Text lblUnitPrice = new Text();
    private final Text lblPrice = new Text();
    private final Text lblAddproduct = new Text();
    private final Text lblCompany = new Text();
    private final Text lblCustomer = new Text();
    private final Text lblAddress = new Text();
    private final Text lblEmail = new Text();
    private final Text lblPhone = new Text();
    public final Text lblWarning = new Text();
    public final Text lblWarning2 = new Text();
    public final TextField companyTxt = new TextField();
    public final TextField addressTxt = new TextField();
    public final TextField emailTxt = new TextField();
    private final Button btnAddproduct = new Button();
    private final Button btnDelproduct = new Button();
    private final Button btnSend = new Button();
    private final RadioButton radio1 = new RadioButton();
    private final RadioButton radio2 = new RadioButton();
    private final ToggleGroup group = new ToggleGroup();
    private final TextField PriceTxt = new TextField();
    public final Text lblWarning3 = new Text();
    TableColumn productCol1 = new TableColumn();
    TableColumn nameCol1 = new TableColumn();
    TableColumn amountCol1 = new TableColumn();
    TableColumn priceCol1 = new TableColumn();
    TableColumn productCol = new TableColumn();
    TableColumn amountCol = new TableColumn();
    TableColumn priceCol = new TableColumn();

    private Tab1() {
        createTab1();
    }

    public static Tab1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tab1();
        }
        return INSTANCE;
    }

    private void createTab1() {
        grid1.setHgap(10); // Horizontal gap
        grid1.setVgap(20); // Vertical gap

        // SIVUSTON KOMPONENTIT
        radio1.setToggleGroup(group);
        radio1.setSelected(true);
        radio2.setToggleGroup(group);

        lblProduct.setFont(Font.font(null, 15));
        lblProduct.setFill(Color.BLACK);

        lblUnitPrice.setFont(Font.font(null, 15));
        lblUnitPrice.setFill(Color.BLACK);
        TextField UnitPriceTxt = new TextField();

        lblUnitPrice.setFont(Font.font(null, 15));
        lblUnitPrice.setFill(Color.BLACK);
        companyTxt.setPromptText("Jarmonkauppa Oy");
        emailTxt.setPromptText("jarmo@jarmonkauppa.com");
        addressTxt.setPromptText("Jarmonkuja 13A, 01666 Pekkala");

        productCol1.setStyle("-fx-font-size: 12pt;");
        productCol1.setMinWidth(100);

        nameCol1.setStyle("-fx-font-size: 12pt;");
        nameCol1.setMinWidth(300);
        
        amountCol1.setStyle("-fx-font-size: 12pt;");
        amountCol1.setMinWidth(150);

        
        priceCol1.setStyle("-fx-font-size: 12pt;");
        priceCol1.setMinWidth(150);

        productsTable.getColumns().addAll(productCol1, nameCol1, amountCol1, priceCol1);
        productsTable.setPrefHeight(300);
        emailTxt.setPrefSize(400, 10);
        //Taulukon Vbox
        final VBox vboxProcuctsTable = new VBox();
        vboxProcuctsTable.setSpacing(5);
        vboxProcuctsTable.getChildren().addAll(lblWarning, productsTable);

        txtOrderAmount.setText(""+1);
        txtOrderAmount.setPrefWidth(40);
        txtOrderAmount.setEditable(false);
        btnPlusOrderAmount.setText("+");
        btnPlusOrderAmount.setOnAction(e -> {
            plusOrminusamount(1);
        });
        btnMinusOrderAmount.setText("-");
        btnMinusOrderAmount.setOnAction(e -> {
            int totalAmount = Integer.parseInt(txtOrderAmount.getText());
            if (totalAmount > 1) {
                plusOrminusamount(-1);
            }
        });
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() == radio1) {
                    haePaketit();
                    priceCol1.setCellValueFactory(
                            new PropertyValueFactory<Product, Double>("paketinHinta"));
                    nameCol1.setCellValueFactory(
                            new PropertyValueFactory<Product, String>("paketinNimi"));
                    amountCol1.setCellValueFactory(
                            new PropertyValueFactory<Product, Integer>("varastoMaara"));
                    filterList();
                }
                
                if (group.getSelectedToggle() == radio2) {
                    haeOsat();
                    productCol1.setCellValueFactory(
                            new PropertyValueFactory<Product, Double>("tyyppi"));
                    priceCol1.setCellValueFactory(
                            new PropertyValueFactory<Product, Double>("osaHinta"));
                    nameCol1.setCellValueFactory(
                            new PropertyValueFactory<Product, String>("osaNimi"));
                    amountCol1.setCellValueFactory(
                            new PropertyValueFactory<Product, Integer>("varastoMaara"));
                    filterList();
                }

            }
        });


        lblAddproduct.setFont(Font.font(null, 15));
        lblAddproduct.setFill(Color.BLACK);

        btnAddproduct.setPrefSize(150, 50);
        btnDelproduct.setPrefSize(150, 50);
        btnSend.setId("btnSend");
        btnSend.setPrefSize(250, 50);

        // Tilaus taulukko
        tilausrivit = new ArrayList<Product>();

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        lblWarning.setFont(Font.font(null, FontWeight.BOLD, 15));
        lblWarning.setFill(Color.rgb(255, 0, 0));
        lblWarning2.setFont(Font.font(null, FontWeight.BOLD, 15));
        lblWarning2.setFill(Color.rgb(255, 0, 0));
        lblWarning3.setFont(Font.font(null, FontWeight.BOLD, 15));
        PriceTxt.setText(""+0);
        PriceTxt.setEditable(false);

        tableTemp.setEditable(true);

        
        productCol.setStyle("-fx-font-size: 12pt;");
        productCol.setMinWidth(200);
        productCol.setCellValueFactory(
                new PropertyValueFactory<Product, String>("name"));

        
        amountCol.setStyle("-fx-font-size: 12pt;");
        amountCol.setMinWidth(100);
        amountCol.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("amount"));

        
        priceCol.setStyle("-fx-font-size: 12pt;");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(
                new PropertyValueFactory<Product, Double>("price"));

        tableTemp.getColumns().addAll(productCol, amountCol, priceCol);
        tableTemp.setPrefHeight(300);

        //Taulukon Vbox
        final VBox vboxTempTable = new VBox();
        vboxTempTable.setSpacing(5);
        vboxTempTable.getChildren().addAll(lblWarning2, tableTemp);

        btnAddproduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Product product;
                if (productsTable.getSelectionModel().getSelectedItem() != null) {
                    if (group.getSelectedToggle() == radio1) {
                        product = new Product(getValittuPaketti(), getOrderAmount());
                        tilausrivit.add(product);
                        updatePrice(product.getPrice()*getOrderAmount());
                    }
                    
                    if (group.getSelectedToggle() == radio2) {
                        product = new Product(getValittuOsa(), getOrderAmount());
                        tilausrivit.add(product);
                        updatePrice(product.getPrice()* product.getAmount());
                    }
                    
                    txtOrderAmount.setText(""+1);
                    data = FXCollections.observableArrayList(tilausrivit);
                    tableTemp.setItems(data);
                    clearWarnings();
                } else {
                    lblWarning.setText(Localization.getInstance().getBundle().getString("lbl_choose_warning"));
                }   
            }
        });
        btnDelproduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (tableTemp.getSelectionModel().getSelectedItem() != null) {
                    Product removeItem =(Product)tableTemp.getSelectionModel().getSelectedItem();
                    tableTemp.getItems().remove(removeItem);
                    updatePrice(-(removeItem.getPrice()* removeItem.getAmount()));
                    tilausrivit.remove(removeItem);
                    clearWarnings();
                } else {
                    lblWarning2.setText(Localization.getInstance().getBundle().getString("lbl_del_warning"));
                }
            }
        });

        // Tarkistaa onko asiakaskenttiin annettu syötteet
        // Ilmoittaa, jos syöte ei ole validi
        btnSend.setOnAction(e -> {
            InputValidator iv = InputValidator.getINSTANCE();
            if (iv.isEmailValid(emailTxt.getText()) && iv.isInputNotEmpty(companyTxt.getText()) && iv.isInputNotEmpty(addressTxt.getText())) {
                controller.createOrder(Double.parseDouble(PriceTxt.getText()));
                tilausrivit.clear();
                PriceTxt.setText(""+0);
            } else {
                if (!iv.isInputNotEmpty(companyTxt.getText())) {
                    ContextMenu yritysValidator = new ContextMenu();
                    yritysValidator.setAutoHide(true);
                    yritysValidator.getItems().add(
                            new MenuItem("Syötä nimi")
                    );
                    yritysValidator.show(companyTxt, Side.RIGHT, 10, 10);
                    companyTxt.setOnMouseClicked(event ->  {
                        yritysValidator.hide();
                    });
                }

                if (!iv.isInputNotEmpty(addressTxt.getText())) {
                    ContextMenu osoiteValidator = new ContextMenu();
                    osoiteValidator.setAutoHide(true);
                    osoiteValidator.getItems().add(
                            new MenuItem("Syötä osoite")
                    );
                    osoiteValidator.show(addressTxt, Side.RIGHT, 10, 10);
                    addressTxt.setOnMouseClicked(event ->  {
                        osoiteValidator.hide();
                    });
                }

                if (!iv.isEmailValid(emailTxt.getText())) {
                    ContextMenu emailValidator = new ContextMenu();
                    emailValidator.setAutoHide(true);
                    emailValidator.getItems().add(
                            new MenuItem("Syötä email")
                    );
                    emailValidator.show(emailTxt, Side.RIGHT, 10, 10);
                    emailTxt.setOnMouseClicked(event ->  {
                        emailValidator.hide();
                    });
                }
            }


            //Product taulun tyhjennys ja ilmoitus että homma onnistui
        });

        // LISÄYKSET GRIDII
        grid1.add(radio1, 3, 3);
        grid1.add(radio2, 5, 3);
        grid1.add(lblProduct, 2, 5);
        grid1.add(searchField, 3, 5, 8, 1);
        grid1.add(vboxProcuctsTable, 3, 7, 8, 4);
        grid1.add(btnMinusOrderAmount, 12, 8);
        grid1.add(txtOrderAmount, 13, 8);
        grid1.add(btnPlusOrderAmount, 14, 8);
        grid1.add(btnAddproduct, 12, 9, 3, 1);
        grid1.add(btnDelproduct, 12, 10, 3, 1);
        grid1.add(vboxTempTable, 15, 7, 8, 4);
        grid1.add(lblPrice, 12, 11,2,1);
        grid1.add(PriceTxt, 15, 11);
        grid1.add(lblWarning3,16,11);
        grid1.add(btnSend, 15, 14, 6, 6);
        grid1.add(lblCompany, 2, 13);
        grid1.add(companyTxt, 3, 13, 4, 1);
        grid1.add(lblAddress, 2, 15);
        grid1.add(addressTxt, 3, 15, 4, 1);
        grid1.add(lblEmail, 2, 17);
        grid1.add(emailTxt, 3, 17, 4, 1);
        tab1.setContent(grid1);
        this.setContent(grid1);

        localizationSetText();

        group.selectToggle(radio2);
        group.selectToggle(radio1);

    }

    private void filterList() {
        if (group.getSelectedToggle() == radio1) {
            FilteredList<Paketti> filteredData = new FilteredList<>(pakettiData, p -> true);
            searchField.textProperty().addListener(((observable, oldValue, newValue) ->  {
                filteredData.setPredicate(Paketti -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(Paketti.getPaketinNimi()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            }));

            SortedList<Paketti> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productsTable.comparatorProperty());
            productsTable.setItems(sortedData);
        }
            
        if (group.getSelectedToggle() == radio2) {
            FilteredList<Osa> filteredData = new FilteredList<>(osaData, p -> true);
            searchField.textProperty().addListener(((observable, oldValue, newValue) ->  {
                filteredData.setPredicate(Osa -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(Osa.getOsaNimi()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            }));

            SortedList<Osa> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productsTable.comparatorProperty());
            productsTable.setItems(sortedData);
        }

    }

    public int getOrderAmount() {
        return Integer.parseInt(txtOrderAmount.getText());
    }

    /**
     * Palauttaa pudotusvalikosta valitun paketin
     *
     * @return
     */
    public Paketti getValittuPaketti() {
        return (Paketti) productsTable.getSelectionModel().getSelectedItem();
    }

    public Osa getValittuOsa() {
        return (Osa) productsTable.getSelectionModel().getSelectedItem();
    }

    public int getValitunPaketinIndex() {
        return productsTable.getSelectionModel().getSelectedIndex();
    }

    /**
     * Palauttaa taulukosta valitut paketit ja osat
     *
     * @return
     */
    public ArrayList<Tilaus_rivi> getTilausrivit() {
        ArrayList<Tilaus_rivi> prodTilaukset = new ArrayList<>();

        //Loop Product table
        tilausrivit.forEach((prod) -> {
            prodTilaukset.add(prod.getTilaus_rivi());
        });

        return prodTilaukset;
    }

    public Asiakas getCustomer() {
        Asiakas uusiasiakas = new Asiakas(companyTxt.getText(), addressTxt.getText(), emailTxt.getText());
        return uusiasiakas;
    }

    public void localizationSetText() {
        Localization localization = Localization.getInstance();

        //Myyntisivu
        lblProduct.setText(localization.getBundle().getString("lbl_product"));  //  ("TUOTE:");
        lblUnitPrice.setText(localization.getBundle().getString("lbl_product_unit_price"));  //  ("YKSIKKÖHINTA:");
        lblPrice.setText(localization.getBundle().getString("lbl_order_total_price"));  //  ("HINTA YHTEENSÄ:");
        btnAddproduct.setText(localization.getBundle().getString("btn_add_product"));  // = ("Lisää");
        btnDelproduct.setText(localization.getBundle().getString("btn_delete_product"));  // = ("Poista");
        btnSend.setText(localization.getBundle().getString("btn_send_order"));  // = ("Luo tilaus");
        lblAddproduct.setText(localization.getBundle().getString("lbl_product_add"));  // = ("LISÄÄ TUOTE:");
        lblCompany.setText(localization.getBundle().getString("lbl_customer_company"));  //("Yritys:");
        lblCustomer.setText(localization.getBundle().getString("lbl_customer_contact_name"));  // = ("Yhteyshenkilö:");
        lblAddress.setText(localization.getBundle().getString("lbl_customer_address"));  // = ("Postiosoite:");
        
        radio1.setText(localization.getBundle().getString("btn_package"));
        radio2.setText(localization.getBundle().getString("btn_component"));
        lblEmail.setText(localization.getBundle().getString("lbl_email"));
        lblPhone.setText(localization.getBundle().getString("lbl_phone"));
        productCol1.setText(localization.getBundle().getString("lbl_product"));
        nameCol1.setText(localization.getBundle().getString("lbl_name"));
        amountCol1.setText(localization.getBundle().getString("lbl_amount"));
        priceCol1.setText(localization.getBundle().getString("lbl_price"));
        btnDelproduct.setText(localization.getBundle().getString("lbl_delProduct"));
        productCol.setText(localization.getBundle().getString("lbl_product"));
        amountCol.setText(localization.getBundle().getString("lbl_amount"));
        priceCol.setText(localization.getBundle().getString("lbl_price"));
        
    }

    public void haePaketit() {
        pakettiLista = controller.getAllComputerNames();
        pakettiData = FXCollections.observableArrayList(pakettiLista);
        productsTable.setItems(pakettiData);
    }

    public void haeOsat() {
        osaLista = controller.getAllOsat();
        osaData = FXCollections.observableArrayList(osaLista);
        productsTable.setItems(osaData);
    }

    public void updatePrice(Double priceChange) {
        double totalPrice = Double.parseDouble(PriceTxt.getText());
        totalPrice = priceChange + totalPrice;
        PriceTxt.setText(Double.toString(totalPrice));
    }
    
    public void plusOrminusamount(int amountChange) {
        int tAmount = Integer.parseInt(txtOrderAmount.getText());
        tAmount = amountChange + tAmount;
        txtOrderAmount.setText(Integer.toString(tAmount));
    }
    public void clearWarnings(){
        lblWarning.setText("");
        lblWarning2.setText("");
        lblWarning3.setText("");
    }
}
