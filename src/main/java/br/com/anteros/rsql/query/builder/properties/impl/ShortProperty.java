package br.com.anteros.rsql.query.builder.properties.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.properties.NumberProperty;

/**
 * A property view for fields with {@link Short} values.
 *
 * @param <T> The type of the final builder.
 */
public interface ShortProperty<T extends AnterosQBuilder<T>> extends NumberProperty<T, Short> {}
