package com.mybaggage.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Het doel van deze class is om de zoekbaggage query dynamisch op te bouwen, aan de hand van waar de gebruiker naar wilt zoeken. Zo blijven de zoekresultaten relevant
 *
 * @author Mitchell Gordon (500775386)
 */
public class BagageFormulierQueryBuilder extends WhereQueryBuilder<Bagageregistratie> {

    public BagageFormulierQueryBuilder() {

    }

    public BagageFormulierQueryBuilder(String field, Object txtValue) {
        super(field, txtValue);
    }

    @Override
    public void prepareWhereQueryCondities(Bagageregistratie vermisteBagageFormulier) {
        super.prepareWhereQueryCondities(vermisteBagageFormulier);
        for (int index = 0; index < QUERY_VOORBEREIDING_LIMIET; index++) {
            if (!vermisteBagageFormulier.getLuchthaven().equals("")) {
                whereConditions.add(new BagageFormulierQueryBuilder("luchthaven", vermisteBagageFormulier.getLuchthaven()));
            }
            if (vermisteBagageFormulier.getLabelnummer() != 0) {
                whereConditions.add(new BagageFormulierQueryBuilder("labelnummer", vermisteBagageFormulier.getLabelnummer()));
            }
            if (vermisteBagageFormulier.getVluchtnummer() != 0) {
                whereConditions.add(new BagageFormulierQueryBuilder("vluchtnummer", vermisteBagageFormulier.getVluchtnummer()));
            }
            if (!vermisteBagageFormulier.getBestemming().equals("")) {
                whereConditions.add(new BagageFormulierQueryBuilder("bestemming", vermisteBagageFormulier.getBestemming()));
            }
            if (!vermisteBagageFormulier.getType().equals("")) {
                whereConditions.add(new BagageFormulierQueryBuilder("type", vermisteBagageFormulier.getType()));
            }
            if (!vermisteBagageFormulier.getMerk().equals("")) {
                whereConditions.add(new BagageFormulierQueryBuilder("merk", vermisteBagageFormulier.getMerk()));
            }
            if (!vermisteBagageFormulier.getKleur().equals("")) {
                whereConditions.add(new BagageFormulierQueryBuilder("kleur", vermisteBagageFormulier.getKleur()));
            }
        }
    }

    @Override
    public String buildWhereQuery(String query, Bagageregistratie vermisteBagageFormulier) {
        prepareWhereQueryCondities(vermisteBagageFormulier);

        // Breid de WHERE query dynamisch uit
        for (int index = 0; index < whereConditions.size(); index++) {
            if (index == 0) {
                // Start WHERE conditie
                query += String.format(" WHERE %s = ?", whereConditions.get(index).veld);
            } else {
                // Verleng WHERE conditie
                query += String.format(" AND %s = ?", whereConditions.get(index).veld);
            }
        }
        return query;
    }

    @Override
    public void setWhereQueryWaardes(PreparedStatement statement) throws SQLException {
        for (int index = 0; index < whereConditions.size(); index++) {
            if (whereConditions.get(index).waarde instanceof String) {
                statement.setString((index + 1), (String) whereConditions.get(index).waarde);
            } else if (whereConditions.get(index).waarde instanceof Integer) {
                statement.setInt((index + 1), (Integer) whereConditions.get(index).waarde);
            } else if (whereConditions.get(index).waarde instanceof Double) {
                statement.setDouble((index + 1), (Double) whereConditions.get(index).waarde);
            } else if (whereConditions.get(index).waarde instanceof Float) {
                statement.setFloat((index + 1), (Float) whereConditions.get(index).waarde);
            }
        }
    }
}
