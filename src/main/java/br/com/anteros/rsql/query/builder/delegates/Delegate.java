package br.com.anteros.rsql.query.builder.delegates;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;

public abstract class Delegate<T extends AnterosQBuilder<T>> extends AnterosQBuilder<T> {

    private T canonical;

    protected Delegate(T canonical) {
        this.canonical = canonical;
    }

    @Override
    protected final T self() {
        return canonical;
    }

}
