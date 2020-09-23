package br.com.anteros.rsql.query.builder.delegates.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.delegates.NumberPropertyDelegate;
import br.com.anteros.rsql.query.builder.properties.impl.FloatProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class FloatPropertyDelegate<T extends AnterosQBuilder<T>> extends NumberPropertyDelegate<T, Float> implements FloatProperty<T> {

    public FloatPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

}
