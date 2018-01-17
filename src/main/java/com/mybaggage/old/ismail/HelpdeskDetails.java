package com.mybaggage.old.ismail;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class voor alle getters en setters om gegevens in de tabel van de helpdesk te laden.
 *
 * @author Ismail Bahar (500783727)
 */
public class HelpdeskDetails {

    private final StringProperty idTicket;
    private final StringProperty voornaam;
    private final StringProperty achternaam;
    private final StringProperty datum;
    private final StringProperty toegewezenAan;
    private final StringProperty beschrijving;

    //Default constructor
    public HelpdeskDetails(String idTicket, String voornaam, String achternaam, String datum, String toegewezenAan, String beschrijving) {
        this.idTicket = new SimpleStringProperty(idTicket);
        this.voornaam = new SimpleStringProperty(voornaam);
        this.achternaam = new SimpleStringProperty(achternaam);
        this.datum = new SimpleStringProperty(datum);
        this.toegewezenAan = new SimpleStringProperty(toegewezenAan);
        this.beschrijving = new SimpleStringProperty(beschrijving);
    }

    //Getters
    public String getIdTicket() {
        return idTicket.get();
    }

    public String getVoornaam() {
        return voornaam.get();
    }

    public String getAchternaam() {
        return achternaam.get();
    }

    public String getDatum() {
        return datum.get();
    }

    public String getToegewezenAan() {
        return toegewezenAan.get();
    }

    public String getBeschrijving() {
        return beschrijving.get();
    }

    //Setters
    public void setIdTicket(String value) {
        idTicket.set(value);
    }

    public void setVoornaam(String value) {
        voornaam.set(value);
    }

    public void setAchternaam(String value) {
        achternaam.set(value);
    }

    public void setDatum(String value) {
        datum.set(value);
    }

    public void setToegewezenAan(String value) {
        toegewezenAan.set(value);
    }

    public void setBeschrijving(String value) {
        beschrijving.set(value);
    }

    //Property values
    public StringProperty idTicketPropety() {
        return idTicket;
    }

    public StringProperty voornaamProperty() {
        return voornaam;
    }

    public StringProperty achternaamProperty() {
        return achternaam;
    }

    public StringProperty datumProperty() {
        return datum;
    }

    public StringProperty toegewezenAanProperty() {
        return toegewezenAan;
    }

    public StringProperty beschrijving() {
        return beschrijving;
    }
}
