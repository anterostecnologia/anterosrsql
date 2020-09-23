package br.com.anteros.rsql.query.builder.delegates.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.delegates.NumberPropertyDelegate;
import br.com.anteros.rsql.query.builder.properties.impl.IntegerProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class IntegerPropertyDelegate<T extends AnterosQBuilder<T>>
        extends NumberPropertyDelegate<T, Integer> implements IntegerProperty<T> {

    public IntegerPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

}
