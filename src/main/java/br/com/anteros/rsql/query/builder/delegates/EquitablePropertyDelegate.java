package br.com.anteros.rsql.query.builder.delegates;

import java.util.Collections;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.EquitableProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public abstract class EquitablePropertyDelegate<T extends AnterosQBuilder<T>, S>
        extends ExistentialPropertyDelegate<T> implements EquitableProperty<T,S> {

    protected EquitablePropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    public final Condition<T> eq(S value) {
        if(value == null) {
            return condition(getField(), ComparisonOperator.EX, Collections.singleton(false));
        }
        return condition(getField(), ComparisonOperator.EQ, Collections.singletonList(value));
    }

    public final Condition<T> ne(S value) {
        if(value == null) {
            return condition(getField(), ComparisonOperator.EX, Collections.singleton(true));
        }
        return condition(getField(), ComparisonOperator.NE, Collections.singletonList(value));
    }

}
