package br.com.anteros.rsql.query.builder.properties;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;

/**
 * For properties that may or may not be equal.
 *
 * @param <T> The final type of the builder.
 */
public interface EquitableProperty<T extends AnterosQBuilder<T>, S> extends ExistentialProperty<T> {

    /**
     * Specifies that the value of the field must be equal to the provided value.
     * @param value The value that the field must be equal to.
     * @return The logically complete condition.
     */
    Condition<T> eq(S value);

    /**
     * Specifies that the value of the field must not be equal to the provided value.
     * @param value The value that the field must not be equal to.
     * @return The logically complete condition.
     */
    Condition<T> ne(S value);

}
