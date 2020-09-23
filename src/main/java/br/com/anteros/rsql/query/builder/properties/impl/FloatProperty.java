package br.com.anteros.rsql.query.builder.properties.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.properties.NumberProperty;

/**
 * A property view for fields with {@link Float} values.
 *
 * @param <T> The type of the final builder.
 */
public interface FloatProperty<T extends AnterosQBuilder<T>> extends NumberProperty<T, Float> {}
