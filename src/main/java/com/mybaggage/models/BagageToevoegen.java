package com.mybaggage.models;

/**
 *
 * @author Rick de Vries (500758516)
 */
public class BagageToevoegen {

    private String formuliernummer;
    private String type;
    private String lostandfoundID;
    private String kenmerken;
    private String labelnummer;
    private String luchthaven;

    public BagageToevoegen(String formuliernummer, String type, String lostandfoundID, String kenmerken, String labelnummer, String luchthaven) {
        this.formuliernummer = formuliernummer;
        this.type = type;
        this.lostandfoundID = lostandfoundID;
        this.kenmerken = kenmerken;
        this.labelnummer = labelnummer;
        this.luchthaven = luchthaven;
    }

    /**
     * @return the kleur
     */
    public String getFormuliernummer() {
        return formuliernummer;
    }

    /**
     * @param formuliernummer
     * @param kleur the kleur to set
     */
    public void setFormuliernummer(String formuliernummer) {
        this.formuliernummer = formuliernummer;
    }

    /**
     * @return the naam
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     * @param naam the naam to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the grootte
     */
    public String getLostandfoundID() {
        return lostandfoundID;
    }

    /**
     * @param lostandfoundID
     * @param grootte the grootte to set
     */
    public void setLostandfoundID(String lostandfoundID) {
        this.lostandfoundID = lostandfoundID;
    }

    /**
     * @return the soortBagage
     */
    public String getKenmerken() {
        return kenmerken;
    }

    /**
     * @param kenmerken
     * @param soortBagage the soortBagage to set
     */
    public void setKenmerken(String kenmerken) {
        this.kenmerken = kenmerken;
    }

    public String getLabelnummer() {
        return labelnummer;
    }

    /**
     * @param labelnummer
     * @param soortBagage the soortBagage to set
     */
    public void setLabelnummer(String labelnummer) {
        this.labelnummer = labelnummer;
    }

    /**
     * @return the status
     */
    public String getLuchthaven() {
        return luchthaven;
    }

    /**
     * @param luchthaven
     * @param status the status to set
     */
    public void setLuchthaven(String luchthaven) {
        this.luchthaven = luchthaven;
    }

}
