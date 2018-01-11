package com.mybaggage.models;

import com.mybaggage.Utilities;
import java.time.LocalDate;

/**
 * Het doel van deze class is om de bagage registratie formulieren functioneel te maken
 *
 * @author Mitchell Gordon (500775386)
 */
public class Bagageregistratie {

    // Resort to a default integer when the string input is empty
    final int DEFAULT_INTEGER = 0;

    private String[] lijstVanMerken;

    private int formuliernummer;
    private int lostAndFoundID;
    private int labelnummer;
    private int vluchtnummer;
    private int klantnummer;
    private String tijd;
    private String datum;
    private String naam;
    private String adres;
    private String woonplaats;
    private String postcode;
    private String land;
    private String telefoonnummer;
    private String type;
    private String merk;
    private String kleur;
    private String kenmerken;
    private String bestemming;
    private String luchthaven;

    public int getFormuliernummer() {
        return formuliernummer;
    }

    public int getLabelnummer() {
        return labelnummer;
    }

    public void setLabelnummer(String labelnummer) {
        labelnummer.trim();
        this.labelnummer = (!"".equals(labelnummer)) ? Integer.parseInt(labelnummer.trim()) : DEFAULT_INTEGER;
    }

    public int getVluchtnummer() {
        return vluchtnummer;
    }

    public void setVluchtnummer(String vluchtnummer) {
        vluchtnummer.trim();
        this.vluchtnummer = (!"".equals(vluchtnummer)) ? Integer.parseInt(vluchtnummer) : DEFAULT_INTEGER;
    }

    public int getKlantnummer() {
        return klantnummer;
    }

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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
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

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public String getKenmerken() {
        return kenmerken;
    }

    public void setKenmerken(String kenmerken) {
        this.kenmerken = kenmerken;
    }

    public String getBestemming() {
        return bestemming;
    }

    public void setBestemming(String bestemming) {
        this.bestemming = bestemming;
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

}
