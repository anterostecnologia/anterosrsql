package br.com.anteros.rsql.builder;

import br.com.anteros.rsql.builder.impls.FieldOpenBuilder;

public class AnterosRsqlBuilder {

    private static final String OPEN_GROUP = "(";
    private static final String CLOSE_GROUP = ")";
    private static final String AND_LONG_SYMBOL = " and ";
    private static final String OR_LONG_SYMBOL = " or ";
    private static final String AND_SHORT_SYMBOL = ";";
    private static final String OR_SHORT_SYMBOL = ",";

    private final String andSymbol;
    private final String orSymbol;

    private AnterosRsqlBuilder(String andSymbol, String orSymbol) {
        super();

        this.andSymbol = andSymbol;
        this.orSymbol = orSymbol;
    }

    /**
     * Create a new rsql nextBuilder with "and" and "or" symbol
     */
    public static FieldOpenBuilder newCompletBuilder() {
        return new AnterosRsqlBuilder(AND_LONG_SYMBOL, OR_LONG_SYMBOL).build();
    }

    /**
     * Create a new rsql nextBuilder with ";" and "," symbol
     */
    public static FieldOpenBuilder rsql() {
        return new AnterosRsqlBuilder(AND_SHORT_SYMBOL, OR_SHORT_SYMBOL).build();
    }

    public String getOpenGroupSymbol() {
        return OPEN_GROUP;
    }

    public String getCloseGroupSymbol() {
        return CLOSE_GROUP;
    }

    public String getAndSymbol() {
        return andSymbol;
    }

    public String getOrSymbol() {
        return orSymbol;
    }

    private FieldOpenBuilder build() {
        return new FieldOpenBuilder(this, new StringBuilder());
    }
}
