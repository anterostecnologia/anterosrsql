package br.com.anteros.rsql.builder.impls;

import br.com.anteros.rsql.builder.AnterosRsqlBuilder;

public class CloseableLogicalCloseBuilder<B> extends AbstractLogical<OpennedFieldOpenBuilder<B>> {

    private final B previousBuilder;

    CloseableLogicalCloseBuilder(AnterosRsqlBuilder rsqlBuilder, B previousBuilder, StringBuilder context) {
        super(rsqlBuilder, context);

        this.previousBuilder = previousBuilder;
    }

    @Override
    protected OpennedFieldOpenBuilder<B> nextBuilder() {
        return new OpennedFieldOpenBuilder<>(rsqlBuilder, previousBuilder, context);
    }

    /**
     * Close a group ")"
     */
    public B closeGroup() {
        context.append(rsqlBuilder.getCloseGroupSymbol());
        return previousBuilder;
    }
}