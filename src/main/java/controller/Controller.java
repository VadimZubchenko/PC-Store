/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Asiakas;
import model.Henkilosto;
import model.Osa;
import model.Paketti;
import model.TietokonekauppaDAO;
import model.Tilaus;
import model.Tilaus_rivi;
import model.Encryption;
import view.Tab1;
import view.View;
import view.loginView;

/**
 * Sovelluksen toiminnallisuutta ohjaava luokka
 * @author Sami Sikkilä
 */
public class Controller {
    //luodaan singleton-luokan Controller staattinen muuttuja INSTANCE
    private static Controller INSTANCE = null;
    /**
     * Käyttöliittymät
     */
    View gui;
    loginView lv;
    // henkilosto's Id can be used in Tilaus table, cor by Vadim
    private Henkilosto user;
    
    /**
     * Tietokannan kanssa asioiva DataAccesObject
     */
    private TietokonekauppaDAO dao;
    /**
     * Hakee salaukseen käytettävän Encryption olion
     */
    private Encryption encryption = Encryption.getInstance();

    /**
     * Luo Controller olion
     */
    private Controller() {
        this.dao = new TietokonekauppaDAO();
    }


    /**
     * Luo Controllerista olion, ellei sitä ole vielä aikaisemmin luotu
     * @return Controller olio
     */

    //käytetään getInstance-metoodi singleton-luokan Controller:in olion luomiseen
    public static synchronized Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }
    
    public void setGui(View gui) {
        this.gui = gui;
    }
    public void setGui2(loginView gui) {
        this.lv = gui;
    }

    /**
     * Luo TietokonekauppaDAO sekä tietokantayhteys uudelleen
     */
    public void reconnectDAO() {
        this.dao = new TietokonekauppaDAO();
    }

    /**
     * Kirjaudu sisään
     * @param loginscreen LoginView
     * @param primaryStage Stage, jolla sisäänkirjautuminen on
     * @param nimi Käyttäjän syöttämä käyttäjänimi
     * @param salasana Käyttäjän syöttämä salasana
     */
    public void loginUser(loginView loginscreen, Stage primaryStage, String nimi, String salasana) {
        user = dao.haeKayttaja(nimi, encryption.encrypt(salasana));
        
        //Kirjautuminen epäonnistui
        if (user == null) {
            //Ilmoita virheestä ja tyhjennä tekstikentät
            loginscreen.setErrorMessage("Salasana väärin.");
        } else {
            
            //Luo view
            Stage Viewclass = new Stage();
            View v = new View(user.getHenkiloNimi());
            setGui(v);
            v.start(Viewclass);
            
            primaryStage.close();
            
        }
        
    }

    /**
     * Kirjautuu ulos, sulkee View'n ja avaa LoginView
     * @param primaryStage Stage, joka on auki näppäintä painettaessa.
     */
    public void logOut(Stage primaryStage){
            // luo loginView ja sulje View
            Stage loginViewclass = new Stage();
            lv = new loginView();
            setGui2(lv);
            lv.start(loginViewclass);
            primaryStage.close();
    }
    /**
     * Hae tietokannasta kaikki Paketti oliot
     * @return Lista kaikista tietokannassa olevista Paketti olioista
     */
     public ArrayList<Paketti> getAllComputerNames() {
        ArrayList<Paketti> paketit = new ArrayList<>();
        
        for (Paketti paketti : dao.readPaketit()) {
            paketit.add(paketti);
        }
        return paketit;
    }
     
     public ArrayList<Osa> getAllOsat() {
        ArrayList<Osa> osat = new ArrayList<>();
        
        for (Osa osa : dao.readOsat()) {
            osat.add(osa);
        }
        return osat;
    }

    /**
     * Funktio hakee käyttöliittymässä olevista tietokentistä tarvittavat tiedot ja luo niiden perusteella tilauksen
     * @param hinta Tilaukselle myyntisivulla laskettu kokonaishinta
     */
    public void createOrder(Double hinta) {
        Tab1 tab1 = Tab1.getInstance();

        //Luo Tilaus_rivi lista productista
        List<Tilaus_rivi> tilaukset = tab1.getTilausrivit();
        Asiakas asiakas = tab1.getCustomer();
        Henkilosto henkilosto = user;
        //Tarkista että listassa on ainakin yksi tilaus
        if (tilaukset.isEmpty() == true) {
            //Ilmoita viewille että tilausrivejä ei ole yhtään
            tab1.lblWarning3.setText("Tilaus lista on tyhjä!");
            tab1.lblWarning3.setFill(Color.rgb(255, 0, 0));
        } else {
            dao.luoTilaus(tilaukset,asiakas, henkilosto,hinta);
            tab1.companyTxt.clear();
            tab1.addressTxt.clear();
            tab1.emailTxt.clear();
            tab1.lblWarning3.setText("Tilaus luotu onnistuneesti!");
            tab1.lblWarning3.setFill(Color.rgb(50, 205,50));
            tab1.lblWarning2.setText("");
            tab1.lblWarning.setText("");
            tab1.tableTemp.getItems().clear();
            tilaukset.removeAll(tilaukset);

        }
    }
    
    /**
     * Luo ohjelmistoon käyttäjä
     * @param henkilo  Henkilö, joka halutaan luoda
     */
    public void createUser(Henkilosto henkilo) {
        dao.luoHenkilo(henkilo);
    }

    /**
     * Hakee tietokannasta kaikki tiettyä tyyppiä vastaavat osat
     * @param tyyppi Osat jotka tietokannasta halutaan hakea
     * @return Lista halutuista osista
     */
    public ArrayList<Osa> getOsat(String tyyppi) {
        ArrayList<Osa> osat = new ArrayList<>();
        
        for (Osa osa : dao.getOsat(tyyppi)) {
            osat.add(osa);
        }
        
        return osat;
    }
    
    /**
     * Hae tilaukset tietokannasta
     * @return Lista kaikista tietokannassa olevista tilauksista
     */
    public ArrayList<Tilaus> getTilaukset() {
        ArrayList<Tilaus> tilaukset = new ArrayList<>();
        
        for (Tilaus tilaus : dao.readTilaukset()) {
            tilaukset.add(tilaus);
        }
        return tilaukset;
    }
    
    public ArrayList<Object> getObjectRows(Object obj) {
        return dao.getObjectRows(obj);
    }

    /**
     * Tallenna tai päivitä olio tietokantaan
     * @param obj Tallennettava tai päivitettävä olio
     */
    public void objectSaveOrUpdate(Object obj) {
        dao.objectSaveOrUpdate(obj, null);
    }

    /**
     * Päivitä tai tallenna tableviewssä valittu olio
     * @param obj Tallennettava olio
     * @param obj_rows Tallennettavat oliorivit
     */
    public void objectAndRowsSaveOrUpdate(Object obj, ArrayList<Object> obj_rows) {
        //dao.objectSaveOrUpdate(obj, obj_rows);
        dao.objectSaveOrUpdate(obj);
        dao.objectListSaveOrUpdate(obj_rows);
    }

    /**
     * Poista olio tietokannasta
     * @param obj Tietokannasta poistettava olio
     */
    public void objectDelete(Object obj) {
        dao.objectDelete(obj);
    }

    /**
     * Hae tietyn vuoden myyntitilastot tietokannasta
     * @param vuosi vuosi, jonka myyntitilastot haluat hakea
     * @return Array myyntilastoista
     */
    public double[] getSalesOfYear(Integer vuosi) {
        return dao.getSalesYear(vuosi);
    }
    
}
