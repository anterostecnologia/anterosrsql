package br.com.anteros.rsql.builder.impls.properties;

import br.com.anteros.rsql.builder.impls.AbstractField;

public class BooleanProperty<B> extends AbstractProperty<B> {

    public BooleanProperty(String field, AbstractField<B> fieldBuilder) {
        super(field, fieldBuilder);
    }

    public final B isTrue() {
        return fieldBuilder.eq(field, "true");
    }

    public final B isFalse() {
        return fieldBuilder.eq(field, "false");
    }
}
