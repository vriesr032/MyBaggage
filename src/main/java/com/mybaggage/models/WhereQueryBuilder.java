package com.mybaggage.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Het doel van deze class is om een WHERE query dynamisch op te bouwen, zo kan je namelijk de Where query overzichtelijk en simpel houden.
 *
 * @author Mitchell Gordon (500775386)
 */
public abstract class WhereQueryBuilder<T> {

    public final int QUERY_VOORBEREIDING_LIMIET = 1;

    public ArrayList<WhereQueryBuilder> whereConditions;
    public String veld;
    public Object waarde;

    public WhereQueryBuilder() {

    }

    public WhereQueryBuilder(String field, Object txtValue) {
        this.veld = field;
        this.waarde = txtValue;
    }

    protected void prepareWhereQueryCondities(T formulier) {
        whereConditions = new ArrayList<>();
    }

    protected String buildWhereQuery(String query, T formulier) {
        return query;
    }

    public void setWhereQueryWaardes(PreparedStatement statement) throws SQLException {

    }
}
