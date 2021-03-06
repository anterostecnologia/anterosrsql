package br.com.anteros.rsql.query.builder.visitors;


import br.com.anteros.rsql.query.builder.nodes.AbstractNode;
import br.com.anteros.rsql.query.builder.nodes.AndNode;
import br.com.anteros.rsql.query.builder.nodes.ComparisonNode;
import br.com.anteros.rsql.query.builder.nodes.OrNode;

@SuppressWarnings("ConstantConditions")
public abstract class AbstractVoidContextNodeVisitor<T> extends ContextualNodeVisitor<T, Void> {

    @Override
    protected final T visit(AndNode node, Void context) {
        return visit(node);
    }

    @Override
    protected final T visit(OrNode node, Void context) {
        return visit(node);
    }

    @Override
    protected final T visit(ComparisonNode node, Void context) {
        return visit(node);
    }

    protected abstract T visit(AndNode node);

    protected abstract T visit(OrNode node);

    protected abstract T visit(ComparisonNode node);

    protected T condition(ComparisonNode node) {
        return condition(node, null);
    }

    public final T visitAny(AbstractNode node) {
        return visitAny(node, null);
    }

}
