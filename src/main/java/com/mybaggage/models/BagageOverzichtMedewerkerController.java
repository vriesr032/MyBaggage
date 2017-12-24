/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.models;

import javafx.fxml.Initializable;

/**
 *
 * @author Ludo Bak (500760041)
 */
public class BagageOverzichtMedewerkerController {
    
    private String registratieNummer;
    private String naam;
    private String kleur;
    private String bagageGrootte;
    private String soortBagage;
    private String status;

    public BagageOverzichtMedewerkerController(String registratieNummer, String naam, String kleur, String bagageGrootte, String soortBagage, String status) {
        this.registratieNummer = registratieNummer;
        this.naam = naam;
        this.kleur = kleur;
        this.bagageGrootte = bagageGrootte;
        this.soortBagage = soortBagage;
        this.status = status;
    }

    public String getRegistratieNummer() {
        return registratieNummer;
    }

    public void setRegistratieNummer(String registratieNummer) {
        this.registratieNummer = registratieNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public String getBagageGrootte() {
        return bagageGrootte;
    }

    public void setBagageGrootte(String bagageGrootte) {
        this.bagageGrootte = bagageGrootte;
    }

    public String getSoortBagage() {
        return soortBagage;
    }

    public void setSoortBagage(String soortBagage) {
        this.soortBagage = soortBagage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
