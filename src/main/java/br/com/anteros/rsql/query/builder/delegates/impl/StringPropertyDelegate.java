package br.com.anteros.rsql.query.builder.delegates.impl;

import java.util.Collections;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.delegates.ListablePropertyDelegate;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.impl.StringProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class StringPropertyDelegate<T extends AnterosQBuilder<T>>
        extends ListablePropertyDelegate<T, String> implements StringProperty<T> {

    public StringPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    public final Condition<T> lexicallyAfter(String value) {
        return condition(getField(), ComparisonOperator.GT, Collections.singletonList(value));
    }

    public final Condition<T> lexicallyBefore(String value) {
        return condition(getField(), ComparisonOperator.LT, Collections.singletonList(value));
    }

    public final Condition<T> lexicallyNotAfter(String value) {
        return condition(getField(), ComparisonOperator.LTE, Collections.singletonList(value));
    }

    public final Condition<T> lexicallyNotBefore(String value) {
        return condition(getField(), ComparisonOperator.GTE, Collections.singletonList(value));
    }

    public Condition<T> pattern(String pattern) {
        return condition(getField(), ComparisonOperator.RE, Collections.singletonList(pattern));
    }

}
