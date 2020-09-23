package br.com.anteros.rsql.query.builder.properties.impl;

import java.time.Instant;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.properties.InstantLikeProperty;

/**
 * A property view for fields with {@link Instant} values.
 *
 * @param <T> The type of the final builder.
 */
public interface InstantProperty<T extends AnterosQBuilder<T>> extends InstantLikeProperty<T, Instant> {}
