package br.com.anteros.rsql.query.builder.nodes;

import br.com.anteros.rsql.query.builder.visitors.ContextualNodeVisitor;

public interface Visitable {

    default <T> T visit(ContextualNodeVisitor<T,Void> visitor) {
        return visit(visitor, null);
    }

     <T,S> T visit(ContextualNodeVisitor<T,S> visitor, S context);

}
