package br.com.anteros.rsql.builder.impls.properties;

import br.com.anteros.rsql.builder.impls.AbstractField;

public abstract class AbstractProperty<B> {

    protected final String field;
    protected final AbstractField<B> fieldBuilder;

    AbstractProperty(String field, AbstractField<B> fieldBuilder) {
        super();

        this.field = field;
        this.fieldBuilder = fieldBuilder;
    }
}
