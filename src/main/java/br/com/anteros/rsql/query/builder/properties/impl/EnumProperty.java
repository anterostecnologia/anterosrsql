package br.com.anteros.rsql.query.builder.properties.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.properties.EquitableProperty;
import br.com.anteros.rsql.query.builder.properties.ListableProperty;

/**
 * A property view for fields with {@link Enum} values.
 *
 * @param <T> The type of the final builder.
 */
public interface EnumProperty<T extends AnterosQBuilder<T>, S extends Enum<S>>
        extends ListableProperty<T,S>, EquitableProperty<T, S> {}
