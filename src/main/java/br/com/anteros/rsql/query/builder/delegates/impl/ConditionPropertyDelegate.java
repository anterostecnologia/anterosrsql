package br.com.anteros.rsql.query.builder.delegates.impl;

import java.util.Collections;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.delegates.PropertyDelegate;
import br.com.anteros.rsql.query.builder.nodes.AndNode;
import br.com.anteros.rsql.query.builder.nodes.ComparisonNode;
import br.com.anteros.rsql.query.builder.nodes.LogicalNode;
import br.com.anteros.rsql.query.builder.nodes.OrNode;
import br.com.anteros.rsql.query.builder.operators.ComparisonOperator;
import br.com.anteros.rsql.query.builder.properties.impl.ConditionProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;
import br.com.anteros.rsql.query.builder.visitors.AbstractVoidContextNodeVisitor;

public final class ConditionPropertyDelegate<T extends AnterosQBuilder<T>, S extends AnterosQBuilder<S>>
        extends PropertyDelegate<T> implements ConditionProperty<T, S> {

    public ConditionPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

    @Override
    public Condition<T> any(Condition<S> condition) {

        final LogicalNode root = ((ConditionDelegate) condition).getRootNode();

        // prepend this field to all of the fields in the subtree
        root.visit(new NamespacingVisitor(getField()));

        return condition(getField(), ComparisonOperator.SUB_CONDITION_ANY, Collections.singleton(condition));
    }

    private class NamespacingVisitor extends AbstractVoidContextNodeVisitor<Void> {

        private FieldPath parent;

        public NamespacingVisitor(FieldPath parent) {
            this.parent = parent;
        }

        @Override
        protected Void visit(AndNode node) {
            node.getChildren().stream().forEach(this::visitAny);
            return null;
        }

        @Override
        protected Void visit(OrNode node) {
            node.getChildren().stream().forEach(this::visitAny);
            return null;
        }

        @Override
        protected Void visit(ComparisonNode node) {
            node.setField(node.getField().prepend(parent));
            return null;
        }
    }

}
