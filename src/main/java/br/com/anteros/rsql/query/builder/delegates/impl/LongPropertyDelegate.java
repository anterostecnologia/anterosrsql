package br.com.anteros.rsql.query.builder.delegates.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.delegates.NumberPropertyDelegate;
import br.com.anteros.rsql.query.builder.properties.impl.LongProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class LongPropertyDelegate<T extends AnterosQBuilder<T>> extends NumberPropertyDelegate<T, Long> implements LongProperty<T> {

    public LongPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

}
