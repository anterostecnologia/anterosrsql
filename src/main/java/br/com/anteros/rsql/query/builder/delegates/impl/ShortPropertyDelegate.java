package br.com.anteros.rsql.query.builder.delegates.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.delegates.NumberPropertyDelegate;
import br.com.anteros.rsql.query.builder.properties.impl.ShortProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class ShortPropertyDelegate<T extends AnterosQBuilder<T>>
        extends NumberPropertyDelegate<T, Short> implements ShortProperty<T> {

    public ShortPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

}
