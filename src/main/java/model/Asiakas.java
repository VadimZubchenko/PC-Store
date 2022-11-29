/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vadimzubchenko
 * Luodaan olion ASIAKAS
 * ja uudem tietokantataulun olio-relaatiomuunnoksen annotaatiolla.
 */
@Entity
@Table(name = "ASIAKAS")
public class Asiakas {
    /**
     * luodaan olion muuttujat
     */
    private int Id;
    private String nimi;
    private String osoite;
    private String email;
    /**
     * luodaan tynjän parametriton konstruktorin
     */
    public Asiakas() {
    }
    /**
     * luodaan konstrukorin 3-lla parametrilla
     * @param nimi luo asiakkaan antaman nimen    
     * @param osoite luo asiakkaan antaman osoitteen
     * @param email luo asiakkaan emailin
     */
    public Asiakas(String nimi, String osoite, String email) {
        
        this.nimi = nimi;
        this.osoite = osoite;
        this.email = email;
    }
    /**
     * luodaan tauluun perusavaimen ja id kentän
     * @return Id palauttaa taulun tietokannan generoiman avainarvon 
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id")
    public int getAsiakasID() {
        return Id;
    }
    /**
     * 
     * @param id asentaa tietokannan generoima id olioon
     */
    public void setAsiakasID(int id) {
        this.Id = id;
    }
    /**
     * luodaan tauluun Nimi kentän 
     * @return nimi palauttaa asiakkaan antaman nimen
     */
    @Column(name = "Nimi")
    public String getAsiakasNimi() {
        return nimi;
    }
    /**
     * 
     * @param nimi asentaa asiakkaan antaman nimen olioon
     */
    public void setAsiakasNimi(String nimi) {
        this.nimi = nimi;
    }
    /**
     * luodaan tauluun Osoite kentän 
     * @return asiakkaan antaman osoitteen
     */
    @Column(name = "Osoite")
    public String getOsoite() {
        return osoite;
    }
    /**
     * 
     * @param osoite asentaa asiakkaan antaman osoitteen olioon
     */
    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }
    /**
     * luodaan tauluun Email kentän 
     * @return palauttaa asiakkaan antaman email
     */
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email asentaa asiakkaan antaman email olioon
     */
    public void setEmail(String email) {
        this.email = email;
    }

     public String toString() {
        return this.nimi+ " , " + this.email +" , "+ this.osoite;
    }
}
