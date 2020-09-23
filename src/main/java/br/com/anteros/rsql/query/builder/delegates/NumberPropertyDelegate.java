package br.com.anteros.rsql.query.builder.delegates;

import java.util.Collections;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.NumberProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public abstract class NumberPropertyDelegate<T extends AnterosQBuilder<T>, S extends Number>
        extends ListablePropertyDelegate<T, S> implements NumberProperty<T, S> {

    protected NumberPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    public final Condition<T> gt(S number) {
        return condition(getField(), ComparisonOperator.GT, Collections.singletonList(number));
    }

    public final Condition<T> lt(S number) {
        return condition(getField(), ComparisonOperator.LT, Collections.singletonList(number));
    }

    public final Condition<T> gte(S number) {
        return condition(getField(), ComparisonOperator.GTE, Collections.singletonList(number));
    }

    public final Condition<T> lte(S number) {
        return condition(getField(), ComparisonOperator.LTE, Collections.singletonList(number));
    }

}
