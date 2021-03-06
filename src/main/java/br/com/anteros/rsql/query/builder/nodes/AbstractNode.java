package br.com.anteros.rsql.query.builder.nodes;

import br.com.anteros.rsql.query.builder.visitors.ContextualNodeVisitor;

public abstract class AbstractNode implements Visitable {

    private LogicalNode parent;

    public AbstractNode() {}

    public AbstractNode(LogicalNode parent) {
        this.parent = parent;
    }

    public LogicalNode getParent() {
        return parent;
    }

    public void setParent(LogicalNode parent) {
        this.parent = parent;
    }

    @Override
    public <T, S> T visit(ContextualNodeVisitor<T, S> visitor, S context) {
        return visitor.visitAny(this, context);
    }

}
