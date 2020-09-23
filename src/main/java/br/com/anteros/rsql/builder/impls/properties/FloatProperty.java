package br.com.anteros.rsql.builder.impls.properties;

import br.com.anteros.rsql.builder.impls.AbstractField;

public class FloatProperty<B> extends AbstractNumberProperty<B, Float> {

    public FloatProperty(String field, AbstractField<B> fieldBuilder) {
        super(field, fieldBuilder);
    }

    @Override
    protected String toString(Float value) {
        return value != null ? value.toString() : "";
    }
}