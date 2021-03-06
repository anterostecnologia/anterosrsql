package br.com.anteros.rsql.query.builder.properties;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;

/**
 * For numerical fields.
 *
 * @param <T> The final type of the builder.
 * @param <S> The type of the numbers that the property supports.
 */
public interface NumberProperty<T extends AnterosQBuilder<T>, S extends Number>
        extends ListableProperty<T, S>, EquitableProperty<T, S> {

    /**
     * Specifies that the field's value must be greater than the provided value.
     * @param number The value that the field's value must be greater than.
     * @return The logically complete condition.
     */
    Condition<T> gt(S number);

    /**
     * Specifies that the field's value must be less than the provided value.
     * @param number The value that the field's value must be less than.
     * @return The logically complete condition.
     */
    Condition<T> lt(S number);

    /**
     * Specifies that the field's value must be greater than or equal to the provided value.
     * @param number The value that the field's value must be greater than or equal to.
     * @return The logically complete condition.
     */
    Condition<T> gte(S number);

    /**
     * Specifies that the field's value must be less than or equal to the provided value.
     * @param number The value that the field's value must be less than or equal to.
     * @return The logically complete condition.
     */
    Condition<T> lte(S number);

}
