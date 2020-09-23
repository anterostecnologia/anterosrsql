package br.com.anteros.rsql.query.builder.delegates;

import java.time.Instant;
import java.util.Collections;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.InstantLikeProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

@SuppressWarnings("unchecked")
public abstract class InstantLikePropertyDelegate<T extends AnterosQBuilder<T>, S>
        extends EquitablePropertyDelegate<T, S> implements InstantLikeProperty<T, S> {

    public InstantLikePropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    @Override
    public final Condition<T> before(S dateTime, boolean exclusive) {
        return condition(getField(), exclusive ? ComparisonOperator.LT : ComparisonOperator.LTE,
                Collections.singletonList(normalize(dateTime)));
    }

    @Override
    public final Condition<T> after(S dateTime, boolean exclusive) {
        return condition(getField(), exclusive ? ComparisonOperator.GT : ComparisonOperator.GTE,
                Collections.singletonList(normalize(dateTime)));
    }

    @Override
    public final Condition<T> between(S after, boolean exclusiveAfter, S before, boolean exclusiveBefore) {
        Condition<T> afterCondition = new AnterosQBuilder().instant(getField().asKey()).after(after, exclusiveAfter);
        Condition<T> beforeCondition = new AnterosQBuilder().instant(getField().asKey()).before(before, exclusiveBefore);
        return and(afterCondition, beforeCondition);
    }

    protected abstract Instant normalize(S dateTime);

}
