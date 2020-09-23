package br.com.anteros.rsql.query.builder.builders;

import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.nodes.ComparisonNode;

public class GeneralQueryBuilder extends AnterosQBuilder<GeneralQueryBuilder> {

    public Condition<GeneralQueryBuilder> passThrough(ComparisonNode node) {
        return condition(node.getField(), node.getOperator(), node.getValues());
    }

}
