/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.CascadeType;
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
 * 
 * Luodaan olion TILAUS_RIVI
 * ja uuden tietokantataulun olio-relaatiomuunnoksen annotaatiolla.
 */
@Entity
@Table(name = "TILAUS_RIVI")
public class Tilaus_rivi {
    /**
     * luodaan oion instanssimuuttujat 
     */
    private int id;
    private Paketti paketti;
    private Osa osa;
    private int maara;
    private Tilaus tilaus;
    private double hinta;
    private Object tavara;
    /**
     * luodaan konstruktorin 5-lla parametrilla
     * @param tilaus luo tilauksen parametrit Tilaus oliosta
     * @param paketti luo paketin parametrit Paketti oliosta
     * @param osa luo osan parametrit Osa oliosta
     * @param maara lou tilauksen pakettien ja osien yhteisen määrän  
     * @param hinta  luo tilauksen yhteishinnan 
     */
    public Tilaus_rivi(Tilaus tilaus, Paketti paketti, Osa osa, int maara, double hinta) {
        this.tilaus = tilaus;
        this.paketti = paketti;
        this.osa = osa;
        this.maara = maara;
        this.hinta = hinta;
        
    }
    /**
     * luodaan parametriton konstruktorin 
     */
    public Tilaus_rivi() {
    }
    /**
     * luodaan konstruktorin 2-parametrilla 
     * @param paketti luo paketin parametrit Paketti oliosta
     * @param orderAmount lou tilauksen pakettien ja osien yhteisen määrän
     */
    public Tilaus_rivi(Object objekti, int orderAmount, double price) {
        if (objekti instanceof Paketti) {
            this.paketti = (Paketti) objekti;
        }
        if (objekti instanceof Osa) {
            this.osa = (Osa) objekti;
        }
        this.maara = orderAmount;
        this.hinta = price;
    }
    /**
     * luodaan Tilaus_rivi tauluun perusavaimen ja sen kenttä Id
     * @return id palauttaa taulun tietokannan generoiman avainarvon
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getTilaus_riviId() {
        return id;
    }
    /**
     * 
     * @param id asentaa tietokannan generoiman "Tilaus_rivi" taulun id
     * ja sen kentän 
     */
    public void setTilaus_riviId(int id) {
        this.id = id;
    }
    /**
     * luodaan monen suhde yhteen -yhteyden, joka 
     * tuottaa viiteavaimen Paketti-olioon
     * @return paketin parametrit Paketti-oliosta
     */
    @ManyToOne (cascade=CascadeType.DETACH)
    @JoinColumn(name = "Paketti")
    public Paketti getPaketti() {
        return paketti;
    }
    /**
     * 
     * @param paketti asentaa paketin parametrit Paketti-oliosta
     * Tilaus_rivi olioon
     */
    public void setPaketti(Paketti paketti) {
        this.paketti = paketti;
    }
    /**
     * luodaan monen suhde yhteen-yhteyden, joka 
     * tuottaa viiteavaimen Osa-olioon
     * @return osan parametrit Osa-oliosta
     */
    @ManyToOne (cascade = CascadeType.DETACH)
    @JoinColumn(name = "Osa")
    public Osa getOsa() {
        return osa;
    }
    /**
     * 
     * @param osa asentaa osan parametrit Osa-oliosta 
     * Tilaus_rivi olioon
     */
    public void setOsa(Osa osa) {
        this.osa = osa;
    }
    /**
     * luodaan tauluun kenttä "Maara"
     * @return maara palauttaa tilauksen yhteisen määrän
     */
    @Column(name = "Maara")
    public int getMaara() {
        return maara;
    }
    /**
     * 
     * @param maara asentaaa tilauksen yhteisen määrän 
     * Tilaus_rivi olioon
     */
    public void setMaara(int maara) {
        this.maara = maara;
    }
    /**
     * luodaan monen suhde yhteen-yhteyden, joka 
     * tuottaa viiteavaimen Tilaus-olioon
     * @return tilaus palauttaa tilauksen parametrit Tilaus-oliosta
     */
    @ManyToOne (cascade=CascadeType.REMOVE)
    @JoinColumn(name = "Tilaus")
    public Tilaus getTilaus() {
        return tilaus;
    }
    /**
     * 
     * @param tilaus asentaa tilauksen parametrit Tilaus-oliosta
     * Tilaus_rivi olioon
     */
    public void setTilaus(Tilaus tilaus) {
        this.tilaus = tilaus;
    }
    /**
     * luodaan tauluun kenttä "Hinta"
     * @return hinta palauttaa tilauksen hinnan
     */
    @Column(name = "Hinta")
    public double getHinta() {
        return hinta;
    }
    /**
     * 
     * @param hinta asentaa tilauksen hinnan Tilaus_rivi olioon
     */
    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

}
