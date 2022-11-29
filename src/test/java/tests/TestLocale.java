/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author hannu.korhonen
 */

public class TestLocale {
    public static void main(String[] args) {
        try {
            
            Locale.setDefault(new Locale("en", "US"));
            
            System.out.println("Current Locale: " + Locale.getDefault());
            ResourceBundle mybundle = ResourceBundle.getBundle("MyLabels");
            
            System.out.println("lbl_username: " + mybundle.getString("lbl_username"));
            
            /*
            // en_US
            System.out.println("Current Locale: " + Locale.getDefault());
            ResourceBundle mybundle = ResourceBundle.getBundle("MyLabels");

            // read MyLabels_en_US.properties
            System.out.println("Say how are you in US English: " + mybundle.getString("how_are_you"));

            Locale.setDefault(new Locale("ms", "MY"));

            // read MyLabels_ms_MY.properties
            System.out.println("Current Locale: " + Locale.getDefault());
            mybundle = ResourceBundle.getBundle("MyLabels");
            System.out.println("Say how are you in Malaysian Malaya language: " + mybundle.getString("how_are_you"));
            */
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}