package com.mybaggage.old.ismail;

import com.mybaggage.Utilities;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class voor alle getters en setters van de gegevens in de database om tickets toe te voegen.
 *
 * @author Ismail Bahar (500783727)
 */
public class AddTickets {

    private SimpleStringProperty idTicket;
    private SimpleStringProperty voornaam;
    private SimpleStringProperty achternaam;
    private String datum;
    private SimpleStringProperty toegewezenAan;
    private SimpleStringProperty beschrijving;

    protected AddTickets() {
        this.idTicket = new SimpleStringProperty();
        this.voornaam = new SimpleStringProperty();
        this.achternaam = new SimpleStringProperty();
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
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = Utilities.convertLocalDateToString(datum);
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

    public void add(AddTickets book) {

    }

    public void add(String string) {

    }

}
