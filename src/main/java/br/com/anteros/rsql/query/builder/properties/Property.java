package br.com.anteros.rsql.query.builder.properties;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;

/**
 * A marker interface for properties that have been defined. Properties
 * represent queryable field types and expose mechanisms to define the
 * constraints against hte field for the query.
 *
 * @param <T> The final type of the builder.
 */
@SuppressWarnings("unused")
public interface Property<T extends AnterosQBuilder<T>> {
}
