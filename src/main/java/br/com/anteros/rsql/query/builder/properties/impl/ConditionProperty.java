package br.com.anteros.rsql.query.builder.properties.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.properties.Property;


/**
 * A property view for multi-value fields containing objects who themselves
 * may have additional fields.
 *
 * @param <T> The type of the final builder.
 */
public interface ConditionProperty<T extends AnterosQBuilder<T>, S extends AnterosQBuilder<S>> extends Property<T> {

    /**
     * Mandates that at least one of the elements of the multi-valued fields must match the
     * provided condition exactly.
     *
     * @param condition The condition that should be imposed individually against each element
     *                  in the multi valued field.
     *
     * @return The logically complete condition.
     */
    Condition<T> any(Condition<S> condition);

}
