package br.com.anteros.rsql.builder.impls;

import br.com.anteros.rsql.builder.AnterosRsqlBuilder;

public abstract class AbstractLogical<B> {

    protected final AnterosRsqlBuilder rsqlBuilder;
    protected final StringBuilder context;

    AbstractLogical(AnterosRsqlBuilder rsqlBuilder, StringBuilder context) {
        super();

        this.rsqlBuilder = rsqlBuilder;
        this.context = context;
    }

    /**
     * @return next builder
     */
    protected abstract B nextBuilder();

    /**
     * AND operation
     */
    public B and() {
        context.append(rsqlBuilder.getAndSymbol());
        return nextBuilder();
    }

    /**
     * OR operation
     */
    public B or() {
        context.append(rsqlBuilder.getOrSymbol());
        return nextBuilder();
    }
}