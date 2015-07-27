package org.zenframework.z8.server.base.model.sql;

import java.sql.SQLException;

import org.zenframework.z8.server.db.FieldType;
import org.zenframework.z8.server.db.sql.FormatOptions;
import org.zenframework.z8.server.types.integer;

public class CountingSelect extends Select {
    public CountingSelect() {
        super();
    }

    public CountingSelect(Select select) {
        super(select);
    }

    @Override
    protected String sql(FormatOptions options) {
        setFields(null);
        setOrderBy(null);

        if(isGrouped()) {
            setSubselect(new Select(this));

            setRootQuery(null);
            setLinks(null);
            setWhere(null);
            setHaving(null);

            setGroupBy(null);
        }

        return super.sql(options);
    }

    public int count() {
        try {
            open();
            int result = next() ? ((integer)get(1, FieldType.Integer)).getInt() : 0;
            close();
            return result;
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}