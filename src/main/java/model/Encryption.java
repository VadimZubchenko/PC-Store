package model;

import java.util.Base64;

/**
 * Merkkijonojen salaukseen käytettävä olio
 */
public class Encryption {
    /**
     * Encryption olioin ilmentymä
     */
    private static Encryption INSTANCE = null;

    /**
     * Encryption olion konstruktori
     */
    private Encryption() {

    }

    /**
     * Luo Encryption olion, jos sellaista ei vielä aikaisemmin olla luotu
     * @return Encryption olio
     */
    public static Encryption getInstance() {
        if (INSTANCE == null) {
            return new Encryption();
        }
        return INSTANCE;
    }

    /**
     * Salaa merkkijonon
     * @param syote Merkkijono joka halutaan salata
     * @return Palauttaa syötetyn merkkijonon salatussa muodossa
     */
    public String encrypt(String syote) {
        return Base64.getEncoder().encodeToString(syote.getBytes());
    }

}
