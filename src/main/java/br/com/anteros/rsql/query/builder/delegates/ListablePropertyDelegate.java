package br.com.anteros.rsql.query.builder.delegates;

import static java.util.Arrays.asList;

import java.util.Collection;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.ListableProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public abstract class ListablePropertyDelegate<T extends AnterosQBuilder<T>, S>
        extends EquitablePropertyDelegate<T, S> implements ListableProperty<T, S> {

    protected ListablePropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    @SafeVarargs
    public final Condition<T> in(S... values) {
        return condition(getField(), ComparisonOperator.IN, asList(values));
    }

    public final Condition<T> in(Collection<S> values) {
        return condition(getField(), ComparisonOperator.IN, values);
    }

    @SafeVarargs
    public final Condition<T> nin(S... values) {
        return condition(getField(), ComparisonOperator.NIN, asList(values));
    }

    public final Condition<T> nin(Collection<S> values) {
        return condition(getField(), ComparisonOperator.NIN, values);
    }

}
