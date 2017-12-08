package com.mybaggage;

/**
 *
 * @author Rick de Vries (500758516)
 */
public class BagageToevoegen {

    private String naam;
    private String kleur;
    private String grootte;
    private String soortBagage;
    private String status;

    public BagageToevoegen(String naam, String kleur, String grootte, String soortBagage, String status) {
        this.naam = naam;
        this.kleur = kleur;
        this.grootte = grootte;
        this.soortBagage = soortBagage;
        this.status = status;
    }

    /**
     * @return the naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @param naam the naam to set
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * @return the kleur
     */
    public String getKleur() {
        return kleur;
    }

    /**
     * @param kleur the kleur to set
     */
    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    /**
     * @return the grootte
     */
    public String getGrootte() {
        return grootte;
    }

    /**
     * @param grootte the grootte to set
     */
    public void setGrootte(String grootte) {
        this.grootte = grootte;
    }

    /**
     * @return the soortBagage
     */
    public String getSoortBagage() {
        return soortBagage;
    }

    /**
     * @param soortBagage the soortBagage to set
     */
    public void setSoortBagage(String soortBagage) {
        this.soortBagage = soortBagage;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
