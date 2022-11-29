/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Encryption;
import model.Localization;

/**
 *
 * @author RJulin
 */
public class loginView extends Application {
    
    //HK 29.3.2019 tietokantayhteyden luominen jo kirjautumisessa
    Controller controller = Controller.getInstance();
    loginView loginscreen = this;
    public Button loginBtn = new Button();
    Button reconnectBtn = new Button();
    Label lblMessage = new Label();
    Label userlabel = new Label();
    Label passlabel = new Label();
    private Encryption encryption = Encryption.getInstance();
    private Button createUser = new Button();
    
    public TextField user = new TextField();
    public PasswordField password = new PasswordField();
    
    //Localisation
    Localization localization = Localization.getInstance();
    
    Stage loginPrimaryStage;
    
    public void start(Stage primaryStage) {
        // Käyttöliittymän rakentaminen
        try {
            loginPrimaryStage = primaryStage;
            
            user.setId("user");
            user.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            
            password.setId("password");
            password.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            
            lblMessage.setId("fail");
            lblMessage.setTextFill(Color.RED);
            
            loginBtn.setId("login");
            loginBtn.setDefaultButton(true); 
            loginBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    controller.loginUser(loginscreen, primaryStage, user.getText().toString(), password.getText().toString());
                }}
            );
            
            reconnectBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    controller = Controller.getInstance();
                }}
            );

            createUser.setOnAction(event -> {
                UserCreation uc = new UserCreation();
                uc.createUserPopUp();
            });
            
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(20);
            grid.setHgap(10);


            grid.add(userlabel, 1, 0);
            grid.add(passlabel, 2, 0);
            grid.add(user, 1, 1);
            grid.add(password, 2, 1);
            grid.add(loginBtn,1, 2);
            grid.add(createUser, 2, 2);
            grid.add(lblMessage, 2, 3);
            grid.add(reconnectBtn, 3, 0);
            grid.add(createLanguageButtons(), 1, 4);
            
            Scene scene = new Scene(grid, 750, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            localizationSetText();
            
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    public HBox createLanguageButtons() {
        //Creating Buttons 
        Image image = new Image("Finland-icon.png");
        
        Label lblLanguageFI = new Label();
        lblLanguageFI.setGraphic(new ImageView(image));
        lblLanguageFI.setOnMouseClicked(e -> {
            localization.changeLocale("FI");
            localizationSetText();
        });

        Image image2 = new Image("Russia-icon.png");
        Label lblLanguageRUS = new Label();
        lblLanguageRUS.setGraphic(new ImageView(image2));
        lblLanguageRUS.setOnMouseClicked(e -> {
            localization.changeLocale("RUS");
            localizationSetText();
        });

        Image image3 = new Image("United-Kingdom-icon.png");
        Label lblLanguageEN = new Label();
        lblLanguageEN.setGraphic(new ImageView(image3));
        lblLanguageEN.setOnMouseClicked(e -> {
            localization.changeLocale("US");
            localizationSetText();
        });
        
        HBox hLanguageButtons = new HBox();
        hLanguageButtons.getChildren().add(lblLanguageFI);
        hLanguageButtons.getChildren().add(lblLanguageRUS);
        hLanguageButtons.getChildren().add(lblLanguageEN);
        return hLanguageButtons;
    }
    
    public void localizationSetText() {
        //Aseta tekstikenttien teksti uudelleen
        loginPrimaryStage.setTitle(localization.getBundle().getString("view_header"));
        userlabel.setText(localization.getBundle().getString("lbl_username"));
        passlabel.setText(localization.getBundle().getString("lbl_password"));
        loginBtn.setText(localization.getBundle().getString("btn_login"));
        reconnectBtn.setText(localization.getBundle().getString("btn_reconnect"));
        createUser.setText(localization.getBundle().getString("btn_creatUser"));
    }
    
    public void setErrorMessage(String message) {
        lblMessage.setText(localization.getBundle().getString("lbl_incorrect_password"));
        
        user.setText("");
        password.setText("");
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}
