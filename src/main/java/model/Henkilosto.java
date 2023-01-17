package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import  javax.persistence.*;
/**
 *
 * @author vadimzubchenko
 * 
 */
/**
 * Luodaan olion HENKILOSTO
 * ja uusi tietokantataulun olio-relaatiomuunnoksen annotaatiolla.
 */
@Entity
@Table (name="HENKILOSTO")
public class Henkilosto {
    /**
     * luodaan olion muuttujat
     */
    private int id;
    private String nimi;
    private String rooli;
    private String salasana;
    private String kirjautumistunnus;
    
     /**
     * luodaan tyhjän parametriton konstruktorin
     */
    public Henkilosto(){
    }
    /**
     *  luodaan konstruktori 4-lla parametrilla
     * @param nimi luo työntekijan nimi
     * @param rooli luo työntekijan käyttöoikeuet toimistossa
     * @param salasana luo työntekijan järjestelemään sisäänpääsyn salasanan 
     * @param kirjautumistunnus luo työntekijan järjestelemään sisäänpääsyn tunnuksen
     */
    public Henkilosto(String nimi, String rooli, String salasana, String kirjautumistunnus) {
        
        this.nimi = nimi;
        this.rooli = rooli;
        this.salasana = salasana;
        this.kirjautumistunnus = kirjautumistunnus;
    }
    /**
     *  luodaan konstruktorin 2-lla parametrilla
     * @param henkiloNimi luo työntekijan nimen
     * @param rooli luo työntekijan käyttöoikeudet toimistossa
     */
    public Henkilosto(String henkiloNimi, String rooli) {
        this.nimi = henkiloNimi;
        this.rooli = rooli;
    }
    /**
     * luodaan tauluun perusavaimen ja sen kentän
     * @return Id palauttaa taulun tietokannan generoiman avainarvon;
     */
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    public int getHenkiloId() {
        return id;
    }
    /**
     * 
     * @param id asentaa tietokannan generoiman id olioon
     */
    public void setHenkiloId(int id) {
        this.id = id;
    }
     /**
     * luodaan tauluun kentän Nimi
     * @return nimi palauttaa työntekijän nimen
     */
    @Column(name="Nimi")
    public String getHenkiloNimi() {
        return nimi;
    }
    /**
     * 
     * @param nimi asentaa työntekijän nimen olioon
     */
    public void setHenkiloNimi(String nimi) {
        this.nimi = nimi;
    }
    /**
     * luodaan tauluun kentän Rooli
     * @return rooli palauttaa työntekijan käyttöoikeudet toimistossa
     */
    @Column(name="Rooli")
    public String getRooli() {
        return rooli;
    }
    /**
     * 
     * @param rooli asentaa työntekijän käyttöoikeudet toimistossa olioon
     */
    public void setRooli(String rooli) {
        this.rooli = rooli;
    }
    /**
     * luodaan tauluun kenttä Salasana
     * @return salasana palauttaa työntekjän järjestelmään sisäänpääsyn salasanan
     */
    @Column(name="Salasana")
    public String getSalasana() {
        return salasana;
    }
    /**
     * 
     * @param salasana asentaa työntekjän järjestelmään sisäänpääsyn salasanan
     */
    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }
    /**
     * luodaan tauluun kentän Kirjautumistunnuksen
     * @return kirjautumistunnus palauttaa työntekijan järjestelemään sisäänpääsyn tunnuksen
     */
    @Column(name="Kirjautumistunnus")
    public String getKirjoitumistunnus() {
        return kirjautumistunnus;
    }
    /**
     * 
     * @param kirjautumistunnus asentaa työntekijan järjestelemään sisäänpääsyn tunnuksen olioon
     */
    public void setKirjoitumistunnus(String kirjautumistunnus) {
        this.kirjautumistunnus = kirjautumistunnus;
    }
    
    
}
