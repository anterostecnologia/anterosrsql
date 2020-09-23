package br.com.anteros.rsql.query.builder.delegates.impl;

import java.time.Instant;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.delegates.InstantLikePropertyDelegate;
import br.com.anteros.rsql.query.builder.properties.impl.InstantProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class InstantPropertyDelegate<T extends AnterosQBuilder<T>>
        extends InstantLikePropertyDelegate<T, Instant> implements InstantProperty<T> {

    public InstantPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    @Override
    protected Instant normalize(Instant instant) {
        return instant;
    }

}
