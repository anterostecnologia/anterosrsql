package br.com.anteros.rsql.query.builder.properties;

import java.util.Collection;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;

/**
 * For acting against a field via a list of values.
 */
public interface ListableProperty<T extends AnterosQBuilder<T>, S> extends Property<T> {

    /**
     * Specifies that the field's value must occur at least once in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must occur in.
     * @return The logically complete condition.
     */
    Condition<T> in(S... values);

    /**
     * Specifies that the field's value must occur at least once in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must occur in.
     * @return The logically complete condition.
     */
    Condition<T> in(Collection<S> values);

    /**
     * Specifies that the field's value must never occur in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must never occur in.
     * @return The logically complete condition.
     */
    Condition<T> nin(S... values);

    /**
     * Specifies that the field's value must never occur in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must never occur in.
     * @return The logically complete condition.
     */
    Condition<T> nin(Collection<S> values);

}
