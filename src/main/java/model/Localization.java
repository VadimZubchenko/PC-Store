/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Locale;
import java.util.ResourceBundle;
import view.Tab1;
import view.Tab2;
import view.Tab3;
import view.Tab4;

/**
 *
 * @author hannu.korhonen
 */
public class Localization {
    private static Localization INSTANCE = null;
    
    ResourceBundle mybundle;
    Locale locale;
    
    /**
     * Konstruktori
     */
    private Localization() {
        //Suomi oletuskieli
        Locale.setDefault(new Locale("fi", "FI"));
        mybundle = ResourceBundle.getBundle("MyLabels");
    }
    
    /**
     * Singleton
     * 
     * @return jos Localization on jo luotu niin palauta se
     */
    public static synchronized Localization getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Localization();
        }
        return INSTANCE;
    }
    
    /**
     * Vaihtaa järjestelmälle asetetun kielen.
     * Ottaa vastaan "FI", "US" tai "RUS" parametrin
     * 
     * @param language valitun kielen parametri
     */
    public void changeLocale(String language) {
        switch(language) { 
            case "FI": 
                Locale.setDefault(new Locale("fi", "FI"));
                break; 
            case "US": 
                Locale.setDefault(new Locale("en", "US"));
                break; 
            case "RUS": 
                Locale.setDefault(new Locale("ru", "RU"));
                break;
        }
        mybundle = ResourceBundle.getBundle("MyLabels");
    }
    
    /**
     * Palauttaa asetetun kielipaketin
     * 
     * @return palauttaa kielipaketin
     */
    public ResourceBundle getBundle() {
        return mybundle;
    }
    
    /**
     * Vaihtaa View luokan jokaisen tabin kielen valituksi
     * 
     * @param tab1 ensimmäinen tab
     * @param tab2 toinen tab
     * @param tab3 kolmas tab
     * @param tab4 neljäs tab
     */
    public void translateAll(Tab1 tab1, Tab2 tab2, Tab3 tab3, Tab4 tab4) {
        tab1.localizationSetText();
        tab2.localizationSetText();
        tab3.localizationSetText();
        tab4.localizationSetText();
        
    }

}
