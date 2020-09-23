package br.com.anteros.rsql.builder.impls;

import br.com.anteros.rsql.builder.AnterosRsqlBuilder;

public class FieldOpenBuilder extends AbstractPropertyField<LogicalQueryBuilder> {

    public FieldOpenBuilder(AnterosRsqlBuilder rsqlBuilder, StringBuilder context) {
        super(rsqlBuilder, context);
    }

    @Override
    protected LogicalQueryBuilder nextBuilder() {
        return new LogicalQueryBuilder(rsqlBuilder, context);
    }

    /**
     * Open a new group "("
     */
    public OpennedFieldOpenBuilder<LogicalQueryBuilder> openGroup() {
        context.append(rsqlBuilder.getOpenGroupSymbol());
        return new OpennedFieldOpenBuilder<>(rsqlBuilder, new LogicalQueryBuilder(rsqlBuilder, context), context);
    }
}