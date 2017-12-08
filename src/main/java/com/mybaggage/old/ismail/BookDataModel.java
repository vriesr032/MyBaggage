package com.mybaggage.old.ismail;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ismail
 */
public class BookDataModel {

    private SimpleStringProperty idTicket;
    private SimpleStringProperty voornaam;
    private SimpleStringProperty achternaam;
    private SimpleStringProperty datum;
    private SimpleStringProperty toegewezenAan;
    private SimpleStringProperty beschrijving;

    protected BookDataModel() {
        this.idTicket = new SimpleStringProperty();
        this.voornaam = new SimpleStringProperty();
        this.achternaam = new SimpleStringProperty();
        this.datum = new SimpleStringProperty();
        this.toegewezenAan = new SimpleStringProperty();
        this.beschrijving = new SimpleStringProperty();
    }

    public String getIdTicket() {
        return idTicket.get();
    }

    public void setId(String idTicket) {
        this.idTicket.set(idTicket);
    }

    public String getVoornaam() {
        return voornaam.get();
    }

    public void setVoornaam(String voornaam) {
        this.voornaam.set(voornaam);
    }

    public String getAchternaam() {
        return achternaam.get();
    }

    public void setAchternaam(String achternaam) {
        this.achternaam.set(achternaam);
    }

    public String getDatum() {
        return datum.get();
    }

    public void setDatum(String datum) {
        this.datum.set(datum);
    }

    public String getToegewezenAan() {
        return toegewezenAan.get();
    }

    public void setToegewezenAan(String toegewezenAan) {
        this.toegewezenAan.set(toegewezenAan);
    }

    public String getBeschrijving() {
        return beschrijving.get();
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving.set(beschrijving);
    }

    public void add(BookDataModel book) {

    }

    public void add(String string) {

    }

}
