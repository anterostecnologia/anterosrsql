package br.com.anteros.rsql.query.builder.delegates;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.properties.Property;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public abstract class PropertyDelegate<T extends AnterosQBuilder<T>> extends Delegate<T> implements Property<T> {

    private FieldPath field;

    protected PropertyDelegate(FieldPath field, T canonical) {
        super(canonical);
        this.field = field;
    }

    protected final FieldPath getField() {
        return field;
    }


}
