package br.com.anteros.rsql.query.builder.delegates.impl;

import java.util.Collections;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.delegates.ExistentialPropertyDelegate;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.impl.BooleanProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class BooleanPropertyDelegate<T extends AnterosQBuilder<T>> extends ExistentialPropertyDelegate<T> implements BooleanProperty<T> {

    public BooleanPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    public final Condition<T> isTrue() {
        return condition(getField(), ComparisonOperator.EQ, Collections.singletonList(true));
    }

    public final Condition<T> isFalse() {
        return condition(getField(), ComparisonOperator.EQ, Collections.singletonList(false));
    }

}
