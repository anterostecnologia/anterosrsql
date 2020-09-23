package br.com.anteros.rsql.query.builder.delegates;

import java.util.Collections;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.ExistentialProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public abstract class ExistentialPropertyDelegate<T extends AnterosQBuilder<T>> extends PropertyDelegate<T>
        implements ExistentialProperty<T> {

    protected ExistentialPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    public final Condition<T> exists() {
        return condition(getField(), ComparisonOperator.EX, Collections.singletonList(true));
    }

    public final Condition<T> doesNotExist() {
        return condition(getField(), ComparisonOperator.EX, Collections.singletonList(false));
    }

}
