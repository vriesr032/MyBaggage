/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.old.jordy;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lenovo
 */
public class Record {

//Assume each record have 6 elements, all String
    private SimpleStringProperty luchthaven, datum, tijd, labelNummer, vluchtNummer, bestemming, naam, adres, woonplaats, postcode, telefoonNummer, type, merk, bijzonder;

    private Record(String field, String field0, String field1, String field2, String field3, String field4, String field5, String field6, String field7, String field8) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getLuchthaven() {
        return luchthaven.get();
    }

    public String getDatum() {
        return datum.get();
    }

    public String getTijd() {
        return tijd.get();
    }

    public String getLabelNummer() {
        return labelNummer.get();
    }

    public String getVluchtNummer() {
        return vluchtNummer.get();
    }

    public String getBestemming() {
        return bestemming.get();
    }

    public String getNaam() {
        return naam.get();
    }

    public String getAdres() {
        return adres.get();
    }

    public String getWoonplaats() {
        return woonplaats.get();
    }

    public String getPostcode() {
        return postcode.get();
    }

    public String getTelefoonNummer() {
        return telefoonNummer.get();
    }

    public String getType() {
        return type.get();
    }

    public String getMerk() {
        return merk.get();
    }

    public String getBijzonder() {
        return bijzonder.get();
    }

    Record(String Luchthaven, String Datum, String Tijd, String VluchtNummer, String LabelNummer, String Bestemming, String Naam, String Adres, String Woonplaats, String Postcode, String TelefoonNummer, String Type, String Merk, String Bijzonder) {

        this.luchthaven = new SimpleStringProperty(Luchthaven);
        this.datum = new SimpleStringProperty(Datum);
        this.tijd = new SimpleStringProperty(Tijd);
        this.vluchtNummer = new SimpleStringProperty(VluchtNummer);
        this.labelNummer = new SimpleStringProperty(LabelNummer);
        this.bestemming = new SimpleStringProperty(Bestemming);
        this.naam = new SimpleStringProperty(Naam);
        this.adres = new SimpleStringProperty(Adres);
        this.woonplaats = new SimpleStringProperty(Woonplaats);
        this.postcode = new SimpleStringProperty(Postcode);
        this.telefoonNummer = new SimpleStringProperty(TelefoonNummer);
        this.type = new SimpleStringProperty(Type);
        this.merk = new SimpleStringProperty(Merk);
        this.bijzonder = new SimpleStringProperty(Bijzonder);
    }

}
