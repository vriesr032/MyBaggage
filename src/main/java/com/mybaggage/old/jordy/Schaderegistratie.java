package com.mybaggage.old.jordy;

import com.mybaggage.Utilities;
import java.time.LocalDate;

/**
 * Het doel van deze class is om het schade registratie formulier functioneel
 * te maken
 *
 * @author Jordy Pouw (500783513)
 */
public class Schaderegistratie {

    // Resort to a default integer when the string input is empty
    final int DEFAULT_INTEGER = 0;
    final String DEFAULT_STRING = "";

    private int lostAndFoundID;
    private String tijd;
    private String datum;
    private String naam;
    private String type;
    private String merk;
    private String luchthaven;
    private String schade;
    private String vergoeding;
    private String bankrekening;

    public String getTijd() {
        return tijd;
    }

    public void setTijd() {
        this.tijd = Utilities.getCurrentTimeString();
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = Utilities.convertLocalDateToString(datum);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getLuchthaven() {
        return luchthaven;
    }

    public void setLuchthaven(String luchthaven) {
        this.luchthaven = luchthaven;
    }

    public int getLostAndFoundID() {
        return lostAndFoundID;
    }

    public void setLostAndFoundID(String lostAndFoundID) {
        lostAndFoundID.trim();
        this.lostAndFoundID = (!"".equals(lostAndFoundID)) ? Integer.parseInt(lostAndFoundID) : DEFAULT_INTEGER;
    }

    public String getSchade() {
        return schade;
    }

    public void setSchade(String schade) {
        this.schade = schade;
    }

    public String getVergoeding() {
        return vergoeding;
    }

    public void setVergoeding(String vergoeding) {
        this.vergoeding = vergoeding;
    }

    public String getBankrekening() {
        return bankrekening;
    }

    public void setBankrekening(String bankrekening) {
        this.bankrekening = bankrekening;
    }
}
