package br.com.anteros.rsql.builder.impls;

public enum Operator {
    /**
     * Equal to
     */
    Equal("=="), /**
     * Not equal to
     */
    NotEqual("!="), /**
     * Less than
     */
    Less("=lt="), /**
     * Less than or equal to
     */
    LessEqual("=le="), /**
     * Greater than
     */
    Greater("=gt="), /**
     * Greater than or equal to
     */
    GreaterEqual("=ge="), /**
     * In
     */
    In("=in="), /**
     * Not in
     */
    NotIn("=out=");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
