/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

/**
 *
 * @author Sami Sikkilä
 */
public class UserCreation extends Application {
    private Text name = new Text("Nimi");
    private TextField nimi = new TextField();
    private Text username = new Text("Tunnus");
    private TextField tunnus = new TextField();
    private Text password = new Text("Salasana");
    private PasswordField salasana = new PasswordField();
    private Button createUser = new Button("Lähetä");
    private Controller cont = Controller.getInstance();
    private Encryption enc = Encryption.getInstance();
    private InputValidator iv = InputValidator.getINSTANCE();

   public void createUserPopUp() {
       Stage newStage = new Stage();
       newStage.setTitle("Luo uusi tuote");
       GridPane comp = new GridPane();
       comp.setHgap(50); // Horizontal gap
       comp.setVgap(50); // Vertical gap

        comp.add(name, 1, 0);
        comp.add(nimi, 2, 0);
        comp.add(username, 1, 1);
        comp.add(tunnus, 2, 1);
        comp.add(password, 1, 2);
        comp.add(salasana, 2, 2);
        comp.add(createUser, 2, 3);

        createUser.setOnAction(event -> {
            if (iv.isInputNotEmpty(nimi.getText()) && iv.isInputLongEnough(salasana.getText()) && iv.isInputNotEmpty(tunnus.getText())) {
                cont.createUser(new Henkilosto(nimi.getText(), null, enc.encrypt(salasana.getText()), tunnus.getText()));
                 newStage.close();
            } else {
                if (!iv.isInputNotEmpty(nimi.getText())) {
                    ContextMenu nimiValidator = new ContextMenu();
                    nimiValidator.setAutoHide(true);
                    nimiValidator.getItems().add(
                            new MenuItem("Syötä nimi")
                    );
                    nimiValidator.show(nimi, Side.RIGHT, 10, 10);
                    nimi.setOnMouseClicked(eventi ->  {
                        nimiValidator.hide();
                    });
                }

                if (!iv.isInputNotEmpty(tunnus.getText())) {
                    ContextMenu tunnusValidator = new ContextMenu();
                    tunnusValidator.setAutoHide(true);
                    tunnusValidator.getItems().add(
                            new MenuItem("Vähintään 8 merkkiä")
                    );
                    tunnusValidator.show(tunnus, Side.RIGHT, 10, 10);
                    tunnus.setOnMouseClicked(eventi ->  {
                        tunnusValidator.hide();
                    });
                }

                if (!iv.isInputLongEnough(salasana.getText())) {
                    ContextMenu passuValidator = new ContextMenu();
                    passuValidator.setAutoHide(true);
                    passuValidator.getItems().add(
                            new MenuItem("Vähintään 8 merkkiä")
                    );
                    passuValidator.show(salasana, Side.RIGHT, 10, 10);
                    salasana.setOnMouseClicked(eventi ->  {
                        passuValidator.hide();
                    });
                }
            }
           
        });
       createUser.setDefaultButton(true); 
       Scene stageScene = new Scene(comp, 450, 300);
       newStage.setScene(stageScene);
       newStage.show();
   }
    @Override
    public void start(Stage primaryStage) throws Exception {
        createUserPopUp();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
