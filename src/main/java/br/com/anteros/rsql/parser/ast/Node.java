package br.com.anteros.rsql.parser.ast;

/**
 * Common interface of the AST nodes. Implementations must be immutable.
 */
public interface Node {

    /**
     * Accepts the visitor, calls its <tt>visit()</tt> method and returns a result.
     *
     * <p>Each implementation must implement this methods exactly as listed:
     * <pre>{@code
     * public <R, A> R accept(RSQLVisitor<R, A> visitor, A param) {
     *     return visitor.visit(this, param);
     * }
     * }</pre>
     *
     * @param visitor The visitor whose appropriate method will be called.
     * @param param An optional parameter to pass to the visitor.
     * @param <R> Return type of the visitor's method.
     * @param <A> Type of an optional parameter passed to the visitor's method.
     * @return An object returned by the visitor (may be <tt>null</tt>).
     */
    <R, A> R accept(RSQLVisitor<R, A> visitor, A param);

    /**
     * Accepts the visitor, calls its <tt>visit()</tt> method and returns the result.
     *
     * This method should just call {@link #accept(RSQLVisitor, Object)} with
     * <tt>null</tt> as the second argument.
     */
    <R, A> R accept(RSQLVisitor<R, A> visitor);
}
