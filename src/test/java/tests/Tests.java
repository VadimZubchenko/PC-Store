/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Date;
import model.Asiakas;
import model.Henkilosto;
import model.Osa;
import model.Paketti;
import model.Tilaus;
import model.Tilaus_rivi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zigis
 */
public class Tests {
//    View gui;
    Asiakas asiakas;
    Tilaus tilaus;
//    
//    
//    public Tests() {
//        gui = new View();
//    }
//    
    
    @Test
    public void testaaPaketinNimenHaku() {
        Paketti paketti = new Paketti();
        paketti.setPaketinNimi("PekkaPC");
        paketti.setPaketinHinta(200);
        assertEquals("PekkaPC", paketti.getPaketinNimi(), "Nimenhaku epäonnistui");
    }
    
    
    @Test
    public void testaaPaketinHinnanHaku() {
        Paketti paketti = new Paketti("PekkaPC", 200.0);
        assertEquals(200, paketti.getPaketinHinta(), "Hinnan hakeminen epäonnistui");
    }
    
    @Test
    public void testaaPaketinId() {
        Paketti paketti = new Paketti("PekkaPC", 200.0);
        paketti.setPakettiId(666);
        assertEquals(666, paketti.getPakettiId(), "Paketin Id:n asetus ja haku epäonnistui");
    }
    
    @Test
    public void testaaPaketinHinnanMuutos() {
        Paketti paketti = new Paketti("PekkaPC", 200.0);
        paketti.setPaketinHinta(100);
        assertEquals(100, paketti.getPaketinHinta(), "Paketin hinnan muuttaminen epäonnistui");
    }
    
    @Test
    public void testaaPaketinNimenMuutos() {
        Paketti paketti = new Paketti("PekkaPC", 200.0);
        paketti.setPaketinNimi("JukkaPC");
        assertEquals("JukkaPC", paketti.getPaketinNimi(), "Paketin nimen muuttaminen epäonnistui");
    }
    
    // Asiakas-luokan testaaminen
    
    @Test
    public void testaaAsiakkaanNimenHaku() {
        Asiakas asiakas = new Asiakas();
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        assertEquals("Martti", asiakas.getAsiakasNimi(), "Asiakkaan nimen hakeminen epäonnistui");
    }
    
    @Test
    public void testaaAsiakkaanOsoitteenHaku() {
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        assertEquals("Laakso", asiakas.getOsoite(), "Asiakkaan osoitteen hakeminen epäonnistui");
    }
    
    @Test
    public void testaaAsiakkaanEmailHaku() {
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        assertEquals("testi@email.com", asiakas.getEmail(), "Asiakkaan sähköpostiosoitteen hakeminen epäonnistui");
    }
    
    @Test
    public void testaaAsiakkaanNimenMuutos() {
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        asiakas.setAsiakasNimi("Vainaa");
        assertEquals("Vainaa", asiakas.getAsiakasNimi(), "Asiakkaan muuttaminen hakeminen epäonnistui");
    }
    
    @Test
    public void testaaAsiakkaanOsoitteenMuutos() {
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        asiakas.setOsoite("Valley");
        assertEquals("Valley", asiakas.getOsoite(), "Asiakkaan osoitteen muuttaminen epäonnistui");
    }
    
    @Test
    public void testaaAsiakkaanEmailMuutos() {
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        asiakas.setEmail("pekka@email.com");
        assertEquals("pekka@email.com", asiakas.getEmail(), "Asiakkaan sähköpostiosoitteen muutos epäonnistui");
    }
    
    @Test
    public void testaaAsiakkaanId() {
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        asiakas.setAsiakasID(666);
        assertEquals(666, asiakas.getAsiakasID(), "Asiakkaan Id:n asettaminen ja haku epäonnistui");
    }
    
    // Tilaus-luokan testaus
    @Test
    public void testaaTilauksenAsiakkaanHaku() {
        Tilaus testeri = new Tilaus();
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        testeri = new Tilaus(asiakas, henkilosto, new Date());
        assertEquals(asiakas, testeri.getAsiakas(), "Asiakkaan haku epäonnistui");
    }
    
    @Test
    public void testaaTilauksenAsiakkaanMuutos() {
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        tilaus.setAsiakas(new Asiakas("Pekka", "Valley", "tirehtoori"));
        assertNotEquals(asiakas, tilaus.getAsiakas(), "Asiakkaan muutos epäonnistui");
    }
    
    @Test
    public void testaaTilauksenHenkilostoHaku() {
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        assertEquals(henkilosto, tilaus.getHenkilosto(), "Henkilöstön haku epäonnistui");
    }
    
    @Test
    public void testaaTilauksenHenkilostoMuutos() {
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto("Pekka", "Varastomies");
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        tilaus.setHenkilosto(new Henkilosto("Jukka", "Myyjä"));
        assertNotEquals(henkilosto, tilaus.getHenkilosto(), "Asiakkaan muutos epäonnistui");
    }
    
    @Test
    public void testaaTilauksenPvmHaku() {
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        assertEquals(new Date(), tilaus.getTilausPvm(), "Tilauspäivämäärän haku epäonnistui");
    }
    
    @Test
    public void testaaTilauksenPvmMuutos() {
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        tilaus.setTilausPvm(new Date());
        assertEquals(new Date(), tilaus.getTilausPvm(), "Tilauspäivämäärän muutos epäonnistui");
    }
    
    @Test
    public void testaaTilauksenId() {
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        tilaus.setTilausId(666);
        assertEquals(666, tilaus.getTilausId(), "TilausId:n haku epäonnistui");
    }
    
    @Test
    public void testaaTilauksenIdMuutos() {
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        tilaus.setTilausId(666);
        tilaus.setTilausId(69);
        assertEquals(69, tilaus.getTilausId(), "TilausId:n muutos epäonnistui");
    }
//    @Test
//    public void testaaSetTilausRivi() {
//        Tilaus tilaus = new Tilaus(new Asiakas("Herra Huu", "Korpi", "huu@mail.fi"), new Henkilosto("Vadim", "IT-insinööri"), new Date());
//        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
//        Paketti paketti = new Paketti("SupeGamer", 2000);
//        Tilaus_rivi rivi  = new Tilaus_rivi(tilaus, paketti, osa, 1, 2200);
//        Tilaus_rivi rivi2  = new Tilaus_rivi(tilaus, paketti, osa, 1, 2200);
//        tilaus.lisääTilausRivi(rivi);
//        tilaus.lisääTilausRivi(rivi2);
//        tilaus.getYhtHinta();
//        assertEquals(4400.0, tilaus.getYhtHinta(), "Paketin rakentaminen ei onnistunut");
//    }
//    
//    // Paketti_rivi.javan testaus
//    @Test
//    public void testaaPakettiRiviGetPaketti() {
//        Paketti_rivi pkt = new Paketti_rivi();
//        Paketti paketti = new Paketti("Paketti", 100);
//        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
//        pkt = new Paketti_rivi(paketti, osa);
//        assertEquals(paketti, pkt.getPaketti(), "Paketin haku epäonnistui");
//    }
//    @Test
//    public void testaaPakettiRiviSetPaketti() {
//        Paketti paketti = new Paketti("Paketti", 100);
//        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
//        Paketti_rivi pkt = new Paketti_rivi(paketti, osa);
//        pkt.setPaketti(new Paketti("testi", 900));
//        assertNotEquals(paketti, pkt.getPaketti(), "Paketin asetus epäonnistui");
//    }
//    @Test
//    public void testaaPakettiRiviGetOsa() {
//        Paketti paketti = new Paketti("Paketti", 100);
//        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
//        Paketti_rivi pkt = new Paketti_rivi(paketti, osa);
//        assertEquals(osa, pkt.getOsa(), "Osan haku epäonnistui");
//    }
//    
//    @Test
//    public void testaaPakettiRiviSetOsa() {
//        Paketti paketti = new Paketti("Paketti", 100);
//        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
//        Paketti_rivi pkt = new Paketti_rivi(paketti, osa);
//        pkt.setOsa(new Osa("testi", 900, 70, "Prosessori"));
//        assertNotEquals(osa, pkt.getOsa(), "Osan asetus epäonnistui");
//    }
//    
//    @Test
//    public void testaaPakettiRiviId() {
//        Paketti paketti = new Paketti("Paketti", 100);
//        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
//        Paketti_rivi pkt = new Paketti_rivi(paketti, osa);
//        pkt.setPakettiId(666);
//        assertEquals(666, pkt.getPakettiId(), "Paketin id asetus ja haku epäonnistui");
//    }
//    @Test
//    public void testaaSetPakettiRivit() {
//        Paketti paketti = new Paketti("Paketti", 100);
//        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
//        Paketti_rivi pakkaus  = new Paketti_rivi(paketti, osa);
//        paketti.lisaaPakettiRivi(pakkaus);
//        paketti.getYhtPakettiHinta();
//        assertEquals(200.0, paketti.getYhtPakettiHinta(), "Paketin rakentaminen ei onnistunut");
//    }
    // 
    
    @Test
    public void testaaOsaId() {
        Osa osa = new Osa();
        osa.setOsaNimi("Osa");
        osa.setOsaHinta(666);
        osa.setVarastoMaara(69);
        osa.setTyyppi("Prosessori");
        osa.setOsaId(1);
        assertEquals(1, osa.getOsaId(), "Osan id:n haku epäonnistui");
        
    }
    
    @Test
    public void testaaGetOsaNimi() {
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        assertEquals("Osa", osa.getOsaNimi(), "Osan nimen haku epäonnistui");
    }
    
    @Test
    public void testaaOsaSetNimi() {
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        osa.setOsaNimi("epäosa");
        assertNotEquals("Osa", osa.getOsaNimi(), "Osan nimen haku epäonnistui");
    }
    
    @Test
    public void testaaGetOsaTyyppi() {
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        assertEquals("Emolevy", osa.getTyyppi(), "Osan tyypin haku epäonnistui");
    }
    
    @Test
    public void testaOsaVarastomaara() {
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        assertEquals(69, osa.getVarastoMaara(), "Osan varastomäärän haku epäonnistui");
    }
    
    @Test
    public void testaaOsaHinta() {
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        assertEquals(200, osa.getOsaHinta(), "Osan hinnan haku epäonnistui");
    }
    
    //Henkilöstö-luokan testit
    @Test
    public void testaaHenkilostoGetNimi() {
        Henkilosto henk = new Henkilosto();
        henk.setHenkiloNimi("Jarmo");
        assertEquals("Jarmo", henk.getHenkiloNimi());
    }
    @Test
    public void testaaHenkilostoGetRooli() {
        Henkilosto henk = new Henkilosto("Aapeli", "Varastomies", "tilliliha", "sitkeä");
        henk.setRooli("rooli");
        assertEquals("rooli", henk.getRooli());
    }
    @Test
    public void testaaHenkilostoGetSalasana() {
        Henkilosto henk = new Henkilosto("Aapeli", "Varastomies", "tilliliha", "sitkeä");
        henk.setSalasana("kirahvi");
        assertEquals("kirahvi", henk.getSalasana());
    }
    @Test
    public void testaaHenkilostoGetTunnus() {
        Henkilosto henk = new Henkilosto("Aapeli", "Varastomies", "tilliliha", "sitkea");
        henk.setKirjoitumistunnus("dippadappa");
        assertEquals("dippadappa", henk.getKirjoitumistunnus());
    }
    @Test
    public void testaaHenkilostoId() {
        Henkilosto henk = new Henkilosto("Aapeli", "Varastomies", "tilliliha", "sitkeä");
        henk.setHenkiloId(666);
        assertEquals(666, henk.getHenkiloId());
    }
    
    //Tilaus_Rivin testit
    @Test
    public void testaaTilausRiviGetPaketti() {
        Tilaus_rivi rivi = new Tilaus_rivi();
        Paketti paketti = new Paketti("Paketti", 200);
        rivi.setPaketti(paketti);
        assertEquals(paketti, rivi.getPaketti(), "Paketin haku ei onnistunut");
    }
    @Test
    public void testaaTilausRiviGetId() {
        Paketti paketti = new Paketti("Paketti", 200);
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        Tilaus_rivi rivi = new Tilaus_rivi(tilaus, paketti, osa, 6, 600);
        rivi.setTilaus_riviId(666);
        assertEquals(666, rivi.getTilaus_riviId(), "Id haku epäonnistui");
    }
    
    public void testaaTilausRiviGetHinta() {
        Paketti paketti = new Paketti("Paketti", 200);
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        Tilaus_rivi rivi = new Tilaus_rivi(tilaus, paketti, osa, 6, 600);
        assertEquals(600, rivi.getHinta(), "Hinnan haku epäonnistui");
    }
    
    public void testaaTilausRiviGetMaara() {
        Paketti paketti = new Paketti("Paketti", 200);
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        Tilaus_rivi rivi = new Tilaus_rivi(tilaus, paketti, osa, 6, 600);
        assertEquals(6, rivi.getMaara(), "Määrän haku epäonnistui");
    }
    
    public void testaaTilausRiviGetTilaus() {
        Paketti paketti = new Paketti("Paketti", 200);
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        Asiakas asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        Tilaus tilaus = new Tilaus(asiakas, henkilosto, new Date());
        Tilaus_rivi rivi = new Tilaus_rivi(tilaus, paketti, osa, 6, 600);
        rivi.setTilaus(tilaus);
        assertEquals(tilaus, rivi.getTilaus(), "Tilauksen haku epäonnistui");
    }
    
    public void testaaTilausRiviGetOsa() {
        Paketti paketti = new Paketti("Paketti", 200);
        Osa osa = new Osa("Osa", 200.0, 69, "Emolevy");
        asiakas = new Asiakas("Martti", "Laakso", "testi@email.com");
        Henkilosto henkilosto = new Henkilosto();
        tilaus = new Tilaus(asiakas, henkilosto, new Date());
        Tilaus_rivi rivi = new Tilaus_rivi(tilaus, paketti, osa, 6, 600);
        assertEquals(osa, rivi.getOsa(), "Osan haku epäonnistui");
    }
    
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
