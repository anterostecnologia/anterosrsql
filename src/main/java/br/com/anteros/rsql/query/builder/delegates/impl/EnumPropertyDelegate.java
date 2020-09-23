package br.com.anteros.rsql.query.builder.delegates.impl;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.delegates.ListablePropertyDelegate;
import br.com.anteros.rsql.query.builder.properties.impl.EnumProperty;
import br.com.anteros.rsql.query.builder.structures.FieldPath;

public final class EnumPropertyDelegate<T extends AnterosQBuilder<T>, S extends Enum<S>>
        extends ListablePropertyDelegate<T,S> implements EnumProperty<T,S> {

    public EnumPropertyDelegate(FieldPath field, T canonical) {
        super(field, canonical);
    }

}
