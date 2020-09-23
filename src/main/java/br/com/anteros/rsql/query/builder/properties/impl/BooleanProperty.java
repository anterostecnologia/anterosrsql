package br.com.anteros.rsql.query.builder.properties.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.properties.ExistentialProperty;

/**
 * A property view for fields with {@link Boolean} values.
 *
 * @param <T> The type of the final builder.
 */
public interface BooleanProperty<T extends AnterosQBuilder<T>> extends ExistentialProperty<T> {

    /**
     * Mandates that the boolean field must be true to match the query.
     * @return The logically complete condition.
     */
    Condition<T> isTrue();

    /**
     * Mandates that the boolean field must be false to match the query.
     * @return The logically complete condition.
     */
    Condition<T> isFalse();

}
