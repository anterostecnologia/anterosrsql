package br.com.anteros.rsql.builder.impls.properties;

import br.com.anteros.rsql.builder.impls.AbstractField;

public class IntegerProperty<B> extends AbstractNumberProperty<B, Integer> {

    public IntegerProperty(String field, AbstractField<B> fieldBuilder) {
        super(field, fieldBuilder);
    }

    @Override
    protected String toString(Integer value) {
        return value != null ? value.toString() : "";
    }
}