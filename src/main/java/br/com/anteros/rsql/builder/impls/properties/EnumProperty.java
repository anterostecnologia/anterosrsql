package br.com.anteros.rsql.builder.impls.properties;

import br.com.anteros.rsql.builder.impls.AbstractField;

public class EnumProperty<B, E extends Enum<E>> extends AbstractListableProperty<B, E> {

    public EnumProperty(String field, AbstractField<B> fieldBuilder) {
        super(field, fieldBuilder);
    }

    @Override
    protected String toString(E value) {
        return "'" + (value != null ? value.name() : "") + "'";
    }
}