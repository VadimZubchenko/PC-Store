/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

/**
 *
 * @author RJulin
 */
/**
 * Rakentaa käyttöliittymän Varasto sivun Sivulla tarkastellaan varaston
 * paketteja ja osia. Painiketta painamalla voi näyttää tietokannasta paketit
 * tai halutun tyyppisen osan
 */
public class Tab2 extends Tab {

    private static Tab2 INSTANCE = null;
    private final Controller controller = Controller.getInstance();
    private final int BUTTONWIDTH = 200;
    private final int BUTTONHEIGHT = 75;
    private final GridPane grid2 = new GridPane();
    private final TableView tableVarasto = new TableView();
    private final Button btnMotherboard = new Button();
    private final Button btnProcessors = new Button();
    private final Button btnGraphics = new Button();
    private final Button btnRam = new Button();
    private final Button btnPower = new Button();
    private final Button btnSsd = new Button();
    private final Button btnHdd = new Button();
    private final Button btnCase = new Button();
    private final Button btnPackage = new Button();
    private final Button btnAddProduct = new Button();
    private final Button btnAddPackage = new Button();
    private final Text merkki = new Text();
    private TextField productBrand = new TextField();
    private TextField productName = new TextField();
    private Text type = new Text();
    private Text name = new Text();
    private Text price = new Text();
    private TextField productPrice = new TextField();
    private TextField warehouseAmount = new TextField();
    private Text warehouseLocation = new Text();
    private TextField warehouseLoca = new TextField();
    private Button addProduct = new Button();
    private ComboBox selectType = new ComboBox();
    private Text amount = new Text();
    private Text processor = new Text();
    private Text motherboard = new Text();
    private Text graphics = new Text();
    private Text RAM = new Text();
    private Text powersupply = new Text();
    private Text SSD = new Text();
    private Text HDD = new Text();
    private Text cases = new Text();
    private TableColumn brand = new TableColumn();
    private TableColumn product = new TableColumn();
    private TableColumn arriveDate = new TableColumn();
    private TableColumn amountTable = new TableColumn();
    private TableColumn additionalInfo = new TableColumn();
    String[] osat = osienTietokantaTyypit();

    private Tab2() {
        createTab2();
    }

    public static Tab2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tab2();
        }
        return INSTANCE;
    }

    private void createTab2() {
        grid2.setId("Varasto");
        grid2.setHgap(0); // Horizontal gap
        grid2.setVgap(0); // Vertical gap

        // Nappula, josta saa prosessorit näkyviin
        btnProcessors.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        btnProcessors.setOnAction(e -> {
            haeOsat("Prosessori");
        });
        grid2.add(btnProcessors, 0, 0);

        // Nappula, josta saa emolevyt näkyviin
        btnMotherboard.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnMotherboard, 0, 1);
        btnMotherboard.setOnAction(e -> {
            haeOsat("Emolevy");
        });

        // Nappula, josta saa näytönohjaimet näkyviin
        btnGraphics.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnGraphics, 0, 2);
        btnGraphics.setOnAction(e -> {
            haeOsat("Näytönohjain");
        });

        // Nappula, josta saa muistit näkyviin
        btnRam.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnRam, 0, 3);
        btnRam.setOnAction(e -> {
            haeOsat("RAM");
        });

        // Nappula, josta saa virtalähteet näkyviin
        btnPower.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnPower, 0, 4);
        btnPower.setOnAction(e -> {
            haeOsat("Virtalähde");
        });

        // Nappula, josta saa SSD:t näkyviin
        btnSsd.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnSsd, 0, 5);
        btnSsd.setOnAction(e -> {
            haeOsat("SSD");
        });

        // Nappula, josta saa HDD:t näkyviin
        btnHdd.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnHdd, 0, 6);
        btnHdd.setOnAction(e -> {
            haeOsat("HDD");
        });

        // Nappula, josta saa HDD:t näkyviin
        btnCase.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnCase, 0, 7);
        btnCase.setOnAction(e -> {
            haeOsat("Kotelo");
        });

        btnPackage.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        grid2.add(btnPackage, 0, 8);
        btnPackage.setOnAction(e -> {
            haeOsat("Paketti");
        });

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        tableVarasto.setEditable(true);

        brand.setStyle("-fx-font-size: 14pt;");
        brand.setMinWidth(200);

        product.setStyle("-fx-font-size: 14pt;");
        product.setMinWidth(500);

        arriveDate.setStyle("-fx-font-size: 14pt;");
        arriveDate.setMinWidth(200);

        amountTable.setStyle("-fx-font-size: 14pt;");
        amountTable.setMinWidth(200);

        additionalInfo.setStyle("-fx-font-size: 14pt;");
        additionalInfo.setMinWidth(500);

        tableVarasto.getColumns().addAll(brand, product, arriveDate, amountTable, additionalInfo);
        tableVarasto.setPrefHeight(700);
        tableVarasto.setPrefWidth(1600);

        final VBox vboxVarasto = new VBox();
        vboxVarasto.setSpacing(5);
        vboxVarasto.setPadding(new Insets(0, 0, 0, 20));
        vboxVarasto.getChildren().addAll(tableVarasto);

        // gridin paikka
        grid2.add(vboxVarasto, 1, 0, 7, 10);
        btnAddProduct.setPrefSize(200, 100);
        btnAddProduct.setOnAction(event -> {
            newProductPopup();
        });

        //Uusi paketti painike
        btnAddPackage.setPrefSize(200, 100);
        btnAddPackage.setOnAction(event -> {
            packagePopUp pop = new packagePopUp();
            pop.newPackagePopup(null);
        });

        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(30);
        buttonsBox.setPadding(new Insets(20, 20, 20, 20));

        buttonsBox.getChildren().addAll(btnAddProduct, btnAddPackage);

        grid2.add(buttonsBox, 7, 11, 7, 10);

        this.setContent(grid2);

        localizationSetText();
    }

    public void newProductPopup() {
        Stage newStage = new Stage();
        newStage.setTitle("Luo uusi tuote");
        GridPane comp = new GridPane();
        comp.setHgap(15); // Horizontal gap
        comp.setVgap(15); // Vertical gap
        comp.add(name, 0, 1);
        productName.setPrefWidth(350);
        comp.add(productName, 1, 1);

        comp.add(type, 0, 2);

        selectType.getItems().clear();
        selectType.getItems().addAll(
                processor.getText(),
                motherboard.getText(),
                graphics.getText(),
                RAM.getText(),
                powersupply.getText(),
                SSD.getText(),
                HDD.getText(),
                cases.getText()
        );
        selectType.getSelectionModel().selectFirst();

        comp.add(selectType, 1, 2);
        comp.add(price, 0, 3);

        productPrice.setPrefWidth(350);
        comp.add(productPrice, 1, 3);
        comp.add(amount, 0, 4);

        warehouseAmount.setPrefWidth(350);
        comp.add(warehouseAmount, 1, 4);

        comp.add(warehouseLocation, 0, 5);
        comp.add(warehouseLoca, 1, 5);

        addProduct.setOnAction(e -> {
            luoOsa(new Osa(productName.getText(),
                    Double.parseDouble(productPrice.getText()),
                    Integer.parseInt(warehouseAmount.getText()),
                    osat[selectType.getSelectionModel().getSelectedIndex()],
                    warehouseLoca.getText()));
        });
        comp.add(addProduct, 1, 6);

        Scene stageScene = new Scene(comp, 500, 350);
        newStage.setScene(stageScene);
        newStage.show();
    }

    public void luoOsa(Osa osa) {
        controller.objectSaveOrUpdate(osa);
    }

    public void haeOsat(String tyyppi) {
        if (tyyppi.equals("Paketti")) {
            product.setCellValueFactory(new PropertyValueFactory<Paketti, String>("paketinNimi"));
            amountTable.setCellValueFactory(new PropertyValueFactory<Paketti, Integer>("varastoMaara"));
            arriveDate.setCellValueFactory(new PropertyValueFactory<Paketti, Double>("paketinHinta"));
            tableVarasto.setItems(FXCollections.observableArrayList(controller.getAllComputerNames()));
        } else {
            brand.setCellValueFactory(new PropertyValueFactory<Osa, String>("hyllynNumero"));
            product.setCellValueFactory(new PropertyValueFactory<Osa, String>("osaNimi"));
            arriveDate.setCellValueFactory(new PropertyValueFactory<Osa, Double>("osaHinta"));
            amountTable.setCellValueFactory(new PropertyValueFactory<Osa, Integer>("varastoMaara"));
            additionalInfo.setCellValueFactory(new PropertyValueFactory<Osa, String>("tyyppi"));
            tableVarasto.setItems(FXCollections.observableArrayList(controller.getOsat(tyyppi)));

        }

    }

    public void localizationSetText() {
        Localization localization = Localization.getInstance();
        //Varastosivu
        btnProcessors.setText(localization.getBundle().getString("btn_processor"));  // = .setText("Prosessorit");
        btnMotherboard.setText(localization.getBundle().getString("btn_motherboard"));  // = .setText("Emolevyt");
        btnGraphics.setText(localization.getBundle().getString("btn_graphics_card"));  // = .setText("Näytönohjaimet");
        btnRam.setText(localization.getBundle().getString("btn_ram"));  // = .setText("RAM");
        btnPower.setText(localization.getBundle().getString("btn_power_source"));  // = .setText("Virtalähteet");
        btnSsd.setText(localization.getBundle().getString("btn_ssd"));  // = .setText("SSD");
        btnHdd.setText(localization.getBundle().getString("btn_hhd"));  // = .setText("HHD");
        btnCase.setText(localization.getBundle().getString("btn_casing"));  // = .setText("Kotelo");
        btnAddProduct.setText(localization.getBundle().getString("btn_create_product"));  // = .setText("Lisää Tuote");
        merkki.setText(localization.getBundle().getString("lbl_brand_name"));
        name.setText(localization.getBundle().getString("lbl_product_name"));
        type.setText(localization.getBundle().getString("lbl_product_type"));
        price.setText(localization.getBundle().getString("lbl_product_price"));
        warehouseLocation.setText(localization.getBundle().getString("lbl_warehouse_location"));
        amount.setText(localization.getBundle().getString("lbl_product_amount"));
        addProduct.setText(localization.getBundle().getString("btn_add_product"));
        btnPackage.setText(localization.getBundle().getString("btn_packages"));
        processor.setText(localization.getBundle().getString("btn_processor"));
        graphics.setText(localization.getBundle().getString("btn_graphics_card"));
        
        motherboard.setText(localization.getBundle().getString("btn_motherboard"));
        RAM.setText(localization.getBundle().getString("btn_ram"));
        SSD.setText(localization.getBundle().getString("btn_ssd"));
        HDD.setText(localization.getBundle().getString("btn_hhd"));
        cases.setText(localization.getBundle().getString("btn_casing"));
        powersupply.setText(localization.getBundle().getString("btn_power_source"));

        brand.setText(localization.getBundle().getString("lbl_warehouse_location"));
        product.setText(localization.getBundle().getString("tbl_col_order_name"));
        arriveDate.setText(localization.getBundle().getString("tbl_col_order_unit_price"));
        amountTable.setText(localization.getBundle().getString("tbl_col_order_quantity"));
        additionalInfo.setText(localization.getBundle().getString("tbl_extra")); 
        btnAddPackage.setText(localization.getBundle().getString("btn_add_package")); 

    }

    private String[] osienTietokantaTyypit() {
        String[] osat = new String[8];
        osat[0] = "Prosessori";
        osat[1] = "Emolevy";
        osat[2] = "Näytönohjain";
        osat[3] = "RAM";
        osat[4] = "Virtalähde";
        osat[5] = "SSD";
        osat[6] = "HDD";
        osat[7] = "Kotelo";
        return osat;
    }

    private class Item {

        int id;
        String desc;

        private Item(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }
    }
}
