package br.com.anteros.rsql.query.builder.conditions;

import java.util.List;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.nodes.AndNode;
import br.com.anteros.rsql.query.builder.nodes.ComparisonNode;
import br.com.anteros.rsql.query.builder.nodes.OrNode;
import br.com.anteros.rsql.query.builder.visitors.ContextualNodeVisitor;

/**
 * A logically complete condition that can either be met or not met by an object.
 * Intended to be composed into more complex conditions, or built into a query
 * that can be executed against a set of objects to determine those things
 * which satisfy the criteria.
 *
 * @param <T> The final type of the builder, used for a fluid chaining interface.
 */
public interface Condition<T extends AnterosQBuilder<T>> {

    /**
     * Prepare to append another condition onto the current node in the condition tree
     * in such a way that both the preceeding condition AND the next condition
     * specified must be met in order to match an object.
     *
     * If more flexibility surrounding precedence is needed than what chaining provides,
     * please see {@link Partial#and(List)} and {@link Partial#or(List)}.
     *
     * @return The beginnings of another condition.
     */
    T and();

    /**
     * Prepare to append another condition onto the current node in the condition tree
     * in such a way that both the preceeding condition OR the next condition
     * specified must be met in order to match an object.
     *
     * If more flexibility surrounding precedence is needed than what chaining provides,
     * please see {@link Partial#and(List)} and {@link Partial#or(List)}.
     *
     * @return The beginnings of another condition.
     */
    T or();

    /**
     * Given this logically complete condition, execute a node visitor against the
     * underlying condition tree in order to build a query or predicate against which
     * objects can be queried / tested.
     *
     * @param visitor The visitor which specifies how to traverse the nodes in the visitor tree.
     *                Nodes can be {@link AndNode}s or {@link OrNode}s or {@link ComparisonNode}s.
     * @param <Q>     The type of the results returned from visiting any node in the tree.
     * @return The result of the visitor's execution.
     */
    <Q> Q query(ContextualNodeVisitor<Q, Void> visitor);

    /**
     * Given this logically complete condition, execute a node visitor against the
     * underlying condition tree in order to build a query or predicate against which
     * objects can be queried / tested.
     *
     * @param visitor The visitor which specifies how to traverse the nodes in the visitor tree.
     *                Nodes can be {@link AndNode}s or {@link OrNode}s or {@link ComparisonNode}s.
     * @param <Q>     The type of the results returned from visiting any node in the tree.
     * @return The result of the visitor's execution.
     */
    <Q, S> Q query(ContextualNodeVisitor<Q, S> visitor, S context);

}
