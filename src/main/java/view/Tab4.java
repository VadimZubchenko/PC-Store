/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import model.Localization;

/**
 *
 * @author RJulin
 */

/*
* Rakentaa käyttöliittymän Taloustiedot sivun
* Sivulla tarkastellaan myyntien ja tilauksien tietoja.
*/

public class Tab4 extends Tab {
    
    //Yleiset
    private static Tab4 INSTANCE = null;
    private final Controller controller = Controller.getInstance();
    private final GridPane grid4 = new GridPane();
    
    private final Button btnSales = new Button();
    
    private Tab4(){
       createTab4();
    }
    
    public static Tab4 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tab4();
        }
        return INSTANCE;
    }
    
    private void createTab4() {

        grid4.setHgap(20); // Horizontal gap
        grid4.setVgap(0); // Vertical gap

        btnSales.setPrefSize(200, 100);
        btnSales.setOnAction((event) -> {
            showSalesChart();
        });
        grid4.add(btnSales, 0, 0);

        this.setContent(grid4);
        
        localizationSetText();
    }

    /**
     * Näytä tilasto eri vuosien myyntitilastoista
     */
    private void showSalesChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final BarChart<String, Number> lineChart = new BarChart<String, Number>(xAxis, yAxis);
        
        lineChart.setTitle("Myyntitiedot");

        XYChart.Series series = new XYChart.Series();
        series.setName("2018");

        XYChart.Series series2019 = new XYChart.Series();
        series2019.setName("2019");

        double[] year2019 = controller.getSalesOfYear(2019);

        series.getData().add(new XYChart.Data("Jan", 23004));
        series.getData().add(new XYChart.Data("Feb", 14002));
        series.getData().add(new XYChart.Data("Mar", 15228));
        series.getData().add(new XYChart.Data("Apr", 24225));
        series.getData().add(new XYChart.Data("May", 34557));
        series.getData().add(new XYChart.Data("Jun", 36754));
        series.getData().add(new XYChart.Data("Jul", 22454));
        series.getData().add(new XYChart.Data("Aug", 45544));
        series.getData().add(new XYChart.Data("Sep", 43454));
        series.getData().add(new XYChart.Data("Oct", 17454));
        series.getData().add(new XYChart.Data("Nov", 29454));
        series.getData().add(new XYChart.Data("Dec", 25197));

        series2019.getData().add(new XYChart.Data("Jan", year2019[0]));
        series2019.getData().add(new XYChart.Data("Feb", year2019[1]));
        series2019.getData().add(new XYChart.Data("Mar", year2019[2]));
        series2019.getData().add(new XYChart.Data("Apr", year2019[3]));
        series2019.getData().add(new XYChart.Data("May", year2019[4]));
        series2019.getData().add(new XYChart.Data("Jun", year2019[5]));
        series2019.getData().add(new XYChart.Data("Jul", year2019[6]));
        series2019.getData().add(new XYChart.Data("Aug", year2019[7]));
        series2019.getData().add(new XYChart.Data("Sep", year2019[8]));
        series2019.getData().add(new XYChart.Data("Oct", year2019[9]));
        series2019.getData().add(new XYChart.Data("Nov", year2019[10]));
        series2019.getData().add(new XYChart.Data("Dec", year2019[11]));

        lineChart.setPrefHeight(700);
        lineChart.setPrefWidth(1600);
        lineChart.getData().addAll(series, series2019);
        lineChart.setPadding(new Insets(20, 20, 20, 20));
        grid4.add(lineChart, 1, 1, 7, 7);
        setContent(grid4);
    }

    /**
     * Lokalisoi tab
     */
    public void localizationSetText() {
        Localization localization = Localization.getInstance();
        
        btnSales.setText(localization.getBundle().getString("btn_sales"));
    }
}
