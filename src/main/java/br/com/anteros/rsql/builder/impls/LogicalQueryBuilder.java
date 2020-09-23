package br.com.anteros.rsql.builder.impls;

import br.com.anteros.rsql.builder.AnterosRsqlBuilder;

public class LogicalQueryBuilder extends AbstractLogical<FieldOpenBuilder> {

    LogicalQueryBuilder(AnterosRsqlBuilder rsqlBuilder, StringBuilder context) {
        super(rsqlBuilder, context);
    }

    @Override
    protected FieldOpenBuilder nextBuilder() {
        return new FieldOpenBuilder(rsqlBuilder, context);
    }

    /**
     * @return rsql query
     */
    public String query() {
        return context.toString();
    }
}