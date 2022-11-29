/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Localization;
import model.Osa;
import model.Paketti;
import model.Paketti_rivi;

/**
 *
 * @author hannu.korhonen
 */
public class packagePopUp extends Application {

    //Paketti
    Paketti tmpPackage;
    TextField txtPackage;
    TextField txtPackageCost;
    TextField txtPackageAmount;

    //Osa
    VBox vProducts;
    TextField txtProductCost;

    Text lblPackage;
    Button addBtn;
    Text lblProductCost;
    Text lblPackageCost;
    Button btnSave;
    
    Stage newStage;
    
    public packagePopUp() {
    }

    public void newPackagePopup(Paketti updatePackage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        //Paketti
        VBox vPackageInformation = new VBox();

        //Paketti nimi
        HBox hPackage = new HBox();
        hPackage.setPadding(new Insets(10, 10, 10, 0));
        hPackage.setAlignment(Pos.TOP_RIGHT);
        //Otsikko
        Text lblPackage = new Text("Paketti:");
        hPackage.getChildren().add(lblPackage);
        //Syöttökenttä
        txtPackage = new TextField();
        hPackage.getChildren().add(txtPackage);

        //Paketti lkm
        HBox hPackageAmount = new HBox();
        hPackageAmount.setPadding(new Insets(10, 10, 10, 0));
        hPackageAmount.setAlignment(Pos.CENTER_RIGHT);
        //Otsikko
        Text lblPackageAmount = new Text("Paketti lkm:");
        hPackageAmount.getChildren().add(lblPackageAmount);
        //Syöttö kenttä
        txtPackageAmount = new TextField();
        hPackageAmount.getChildren().add(txtPackageAmount);

        vPackageInformation.getChildren().add(hPackage);
        vPackageInformation.getChildren().add(hPackageAmount);
        grid.add(vPackageInformation, 0, 1);

        //Aliosat lista
        vProducts = new VBox();
        vProducts.setPrefSize(400, 120);

        //Aliosille scrollbar
        ScrollPane sp = new ScrollPane();
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setContent(vProducts);

        grid.add(sp, 1, 1);

        addBtn = new Button();
        addBtn.setText("Lisää osa");
        addBtn.setPrefSize(100, 50);
        addBtn.setPadding(new Insets(20));
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                //Aliosa
                HBox hProductLine = createProductLine(vProducts, null);
                vProducts.getChildren().add(hProductLine);
            }
        }
        );
//        grid.add(addBtn, 1, 0);

        vPackageInformation.getChildren().add(addBtn);
        vPackageInformation.setAlignment(Pos.CENTER_RIGHT);

        //Osien yhteishinta
        lblProductCost = new Text("Osien hinta:");
        grid.add(lblProductCost, 0, 3);
        txtProductCost = new TextField("0.0");
        grid.add(txtProductCost, 1, 3);

        //Paketin hinta
        lblPackageCost = new Text("Paketin hinta:");
        grid.add(lblPackageCost, 0, 4);
        txtPackageCost = new TextField("0.0");
        grid.add(txtPackageCost, 1, 4);

        //Tallenna painike
        btnSave = createSaveButton();
        btnSave.setText("Tallenna");
        btnSave.setPrefSize(100, 50);
        grid.add(btnSave, 1, 5);

        //Tarkista onko kyseessä insert vai update
        if (updatePackage != null) {
            //Kyseessä on update, täydennä pakkauksen tiedot kenttiin
            txtPackage.setText(updatePackage.getPaketinNimi());

//            //Luo pakkauksen aliosa kentät
//            for (Paketti_rivi tmpPackage_row : arr_paketti) {//updatePackage.getPakettiRivit()) {
//                HBox hbox = createProductLine(vProducts, tmpPackage_row.getOsa());
//                vProducts.getChildren().add(hbox);
//            }
        }

        localizationSetText();
        
        newStage = new Stage();
        newStage.setTitle("Luo uusi paketti");
        Scene stageScene = new Scene(grid, 720, 300);
        newStage.setScene(stageScene);
        newStage.show();

    }

    private HBox createProductLine(VBox v, Osa osa) {
        HBox hProduct = new HBox();
        hProduct.setPadding(new Insets(10, 10, 10, 10));

        Text lblType = new Text("Osa:");
        Text lblProduct = new Text("Osa:");
        //lblProduct.setText(Localization.getInstance().getBundle().getString("lbl_product"));
        Text lblPrice = new Text("0.0");
        ComboBox<Osa> cboProduct = createProductComboBox(lblPrice);
        ComboBox cboProductType = createProductTypeList(cboProduct);

        Button deleteButton = createDeleteButton(v, hProduct, cboProduct);

        //Label Type
        hProduct.getChildren().add(lblType);
        //Product type
        hProduct.getChildren().add(cboProductType);
        //Label Product
        hProduct.getChildren().add(lblProduct);
        //Select Product
        hProduct.getChildren().add(cboProduct);
        //Price
        hProduct.getChildren().add(lblPrice);
        //Delete product line
        hProduct.getChildren().add(deleteButton);
        hProduct.setSpacing(10);
        //Jos parametri Osa ei ole null niin täydennä sen tiedot
        if (osa != null) {
            cboProductType.getSelectionModel().select(osa.getTyyppi());
            lblPrice.setText(Double.toString(osa.getOsaHinta()));
            updateProductTotalCost(osa.getOsaHinta());
            cboProduct.getSelectionModel().select(osa);
        }

        return hProduct;
    }

    private ComboBox createProductTypeList(ComboBox cboSelectProduct) {
        ComboBox<String> cboType = new ComboBox();

        //Luo combobox jossa valitaan Osa tyyppi (esim RAM, HDD, emolevy)
        cboType.getItems().addAll(
                Localization.getInstance().getBundle().getString("btn_processor"),
                Localization.getInstance().getBundle().getString("btn_motherboard"),
                Localization.getInstance().getBundle().getString("btn_graphics_card"),
                Localization.getInstance().getBundle().getString("btn_ram"),
                Localization.getInstance().getBundle().getString("btn_power_source"),
                Localization.getInstance().getBundle().getString("btn_ssd"),
                Localization.getInstance().getBundle().getString("btn_hhd"),
                Localization.getInstance().getBundle().getString("btn_casing")
        );

        //Tyhjennä product combobox ja aseta sisällöksi vain valitun tyypin osat
        cboType.setOnAction(e -> {
            String sType = getValittuTietokantaTyyppi(cboType.getSelectionModel().getSelectedIndex());
//            String sType = cboType.getSelectionModel().getSelectedItem();
            cboSelectProduct.getItems().clear();
            cboSelectProduct.getItems().addAll(Controller.getInstance().getOsat(sType));
        });

        return cboType;
    }

    private ComboBox<Osa> createProductComboBox(Text lblPrice) {
        ComboBox<Osa> cboProduct = new ComboBox();

        cboProduct.setOnAction(e -> {
            //Aseta valitun Osan hinta price kenttään
            Double productPrice = cboProduct.getSelectionModel().getSelectedItem().getOsaHinta();
            lblPrice.setText(Double.toString(productPrice));
            updateProductTotalCost(productPrice);
        });

        return cboProduct;
    }

    private Button createDeleteButton(VBox deleteSource, HBox deleteTarget, ComboBox<Osa> cboProduct) {
        //Poista aliosa painike
        Button delBtn = new Button();
        delBtn.setText("Poista");
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                //Tarkista oliko osa valittuna
                if (cboProduct.getSelectionModel().getSelectedItem() != null) {
                    //Päivitä hinta
                    Double productPrice = cboProduct.getSelectionModel().getSelectedItem().getOsaHinta();
                    updateProductTotalCost(-productPrice);
                }
                //Poista HBox
                deleteSource.getChildren().remove(deleteTarget);
            }
        }
        );
        return delBtn;
    }

    private Button createSaveButton() {
        Button btnSave = new Button();

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                //Tarkista pakolliset kentät
                if (txtPackage.getText() == "" || txtPackageCost.getText() == "") {
                    return;
                }

                //Tarkistus onko kyseessä päivittäminen vai uuden luominen
                if (tmpPackage != null) {
                    //Paketin päivittäminen
                    tmpPackage.setPaketinHinta(Double.parseDouble(txtPackageCost.getText()));
                    tmpPackage.setPaketinNimi(txtPackage.getText());
                    tmpPackage.setVarastoMaara(Integer.parseInt(txtPackageAmount.getText()));
                } else {
                    //Rakenna paketti olio
                    tmpPackage = new Paketti(txtPackage.getText(), Double.parseDouble(txtPackageCost.getText()));
                    /*      try {
                        Integer.parseInt(txtPackageCost.getText());
                        tmpPackage.setVarastoMaara(Integer.parseInt(txtPackageAmount.getText()));
                    } catch (NumberFormatException ex) {
                        tmpPackage.setVarastoMaara(0);
                    }*/
                    tmpPackage.setVarastoMaara(Integer.parseInt(txtPackageAmount.getText()));

                }
                //Looppaa HBoxit ja lisää niiden Osat pakettiin
                ArrayList<Object> package_rows = new ArrayList<>();

                for (Node child : vProducts.getChildren()) {
                    if (child instanceof HBox) {
                        Node cboProduct = ((HBox) child).getChildren().get(2);
                        if (cboProduct instanceof ComboBox) {
                            Osa product = ((ComboBox<Osa>) cboProduct).getSelectionModel().getSelectedItem();
                            package_rows.add(new Paketti_rivi(tmpPackage, product));
                        }
                    }
                }
                Controller.getInstance().objectAndRowsSaveOrUpdate(tmpPackage, package_rows);
//                newStage.close();
            }}
        );
        return btnSave;
    }

    public void updateProductTotalCost(Double dPriceChange) {
        Double dProductCost = Double.parseDouble(this.txtProductCost.getText());
        //Osien yhteishinta
        dProductCost = dProductCost + dPriceChange;
        txtProductCost.setText(Double.toString(dProductCost));
        //Paketin yhteishinta = osien hinta + (2% osien hinnasta)
        Double dPackageCost = dProductCost + (dProductCost / 100 * 2);
        dPackageCost = Math.round(dPackageCost * 100.0) / 100.0;
        txtPackageCost.setText(Double.toString(dPackageCost));
    }

    private String getValittuTietokantaTyyppi(int i) {
        String[] osat = new String[8];
        osat[0] = "Prosessori";
        osat[1] = "Emolevy";
        osat[2] = "Näytönohjain";
        osat[3] = "RAM";
        osat[4] = "Virtalähde";
        osat[5] = "SSD";
        osat[6] = "HDD";
        osat[7] = "Kotelo";

        return osat[i];
    }

    public void localizationSetText() {
        Localization localization = Localization.getInstance();
//        lblPackage.setText(localization.getBundle().getString("lbl_package"));
//        addBtn.setText(localization.getBundle().getString("btn_add"));
//        lblProductCost.setText(localization.getBundle().getString("lbl_cost"));
//        lblPackageCost.setText(localization.getBundle().getString("lbl_cost"));
//        btnSave.setText(localization.getBundle().getString("btn_save"));
//        newStage.setTitle(localization.getBundle().getString("title_package"));

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        newPackagePopup(null);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
