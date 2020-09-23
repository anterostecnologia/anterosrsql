package br.com.anteros.rsql.query.builder.delegates.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.delegates.NumberPropertyDelegate;
import br.com.anteros.rsql.query.builder.properties.impl.DoubleProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class DoublePropertyDelegate<T extends AnterosQBuilder<T>> extends NumberPropertyDelegate<T, Double> implements DoubleProperty<T> {

    public DoublePropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

}
