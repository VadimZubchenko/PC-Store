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
 */
/**
 * Luodaan olion PAKETTI_RIVI ja uuden tietokantataulun olio-relaatiomuunnoksen
 * annotaatiolla.
 */
@Entity
@Table(name = "PAKETTI_RIVI")
public class Paketti_rivi {

    /**
     * luodaan olion instanssimuuttujat
     */
    private int id;
    private Paketti paketti;
    private Osa osa;
    

    /**
     * luodaan parametriton konstruktorin
     */
    public Paketti_rivi() {
    }

    /**
     * luodaan konstruktorin 2-parametrilla
     *
     * @param paketti luo paketin parametrit Paketti-oliosta
     * @param osa luo osan parametrit Osa-oliosta
     */
    public Paketti_rivi( Paketti paketti, Osa osa) {
        this.paketti = paketti;
        this.osa = osa;
    }

    /**
     * luodaan Paketti_rivi tauluun perusavaimen ja sen kentän
     *
     * @return id palauttaa taulun tietokannan generoiman avainarvon
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getPakettiId() {
        return id;
    }

    /**
     *
     * @param id asentaa tietokannan generoiman id olioon
     */
    public void setPakettiId(int id) {
        this.id = id;
    }

    /**
     * luodaan monen suhde yhteen-yhteyden, joka tuottaa viiteavaimen Osa-olioon
     *
     * @return osa Osa-oliosta
     */
    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn(name = "Osa")
    public Osa getOsa() {
        return osa;
    }

    /**
     *
     * @param osa asentaa tuotteen parametrit Osa-oliosta Paketti_rivi olioon
     */
    public void setOsa(Osa osa) {
        this.osa = osa;
    }

    /**
     * luodaan monen suhde yhteen-yhteyden, joka tuottaa viiteavaimen
     * Paketti-olioon
     *
     * @return paketti palautta paketin parametrit
     */
    @ManyToOne
    @JoinColumn(name = "Paketti")
    public Paketti getPaketti() {
        return paketti;
    }

    /**
     *
     * @param paketti asentaa paketin parametrit Paketti-oliosta Paketti_rivi
     * olioon
     */
    public void setPaketti(Paketti paketti) {
        this.paketti = paketti;
    }

}
