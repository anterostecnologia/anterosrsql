package br.com.anteros.rsql.builder.impls.properties;

import br.com.anteros.rsql.builder.impls.AbstractField;

public class DoubleProperty<B> extends AbstractNumberProperty<B, Double> {

    public DoubleProperty(String field, AbstractField<B> fieldBuilder) {
        super(field, fieldBuilder);
    }

    @Override
    protected String toString(Double value) {
        return value != null ? value.toString() : "";
    }
}