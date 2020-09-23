package br.com.anteros.rsql.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Set;

import br.com.anteros.rsql.parser.ast.*;

/**
 * Parser of the RSQL (RESTful Service Query Language).
 *
 * <p>
 * RSQL is a query language for parametrized filtering of entries in RESTful
 * APIs. It's a superset of the <a href=
 * "http://tools.ietf.org/html/draft-nottingham-atompub-fiql-00">FIQL</a> (Feed
 * Item Query Language), so it can be used for parsing FIQL as well.
 * </p>
 *
 * <p>
 * <b>Grammar in EBNF notation:</b>
 * 
 * <pre>
 * {@code
 * input          = or, EOF;
 * or             = and, { ( "," | " or " ) , and };
 * and            = constraint, { ( ";" | " and " ), constraint };
 * constraint     = ( group | comparison );
 * group          = "(", or, ")";
 *
 * comparison     = selector, comparator, arguments;
 * selector       = unreserved-str;
 *
 * comparator     = comp-fiql | comp-alt;
 * comp-fiql      = ( ( "=", { ALPHA } ) | "!" ), "=";
 * comp-alt       = ( ">" | "<" ), [ "=" ];
 *
 * arguments      = ( "(", value, { "," , value }, ")" ) | value;
 * value          = unreserved-str | double-quoted | single-quoted;
 *
 * unreserved-str = unreserved, { unreserved }
 * single-quoted  = "'", { ( escaped | all-chars - ( "'" | "\" ) ) }, "'";
 * double-quoted  = '"', { ( escaped | all-chars - ( '"' | "\" ) ) }, '"';
 *
 * reserved       = '"' | "'" | "(" | ")" | ";" | "," | "=" | "!" | "~" | "<" | ">" | " ";
 * unreserved     = all-chars - reserved;
 * escaped        = "\", all-chars;
 * all-chars      = ? all unicode characters ?;
 * }
 * </pre>
 *
 * @version 2.1
 */

public final class RSQLParser {

	private static final Charset ENCODING = Charset.forName("UTF-8");

	private final NodesFactory nodesFactory;

	/**
	 * Creates a new instance of {@code RSQLParser} with the default set of
	 * comparison operators.
	 */
	public RSQLParser() {
		this.nodesFactory = new NodesFactory(RSQLOperators.defaultOperators());
	}

	/**
	 * Creates a new instance of {@code RSQLParser} that supports only the specified
	 * comparison operators.
	 *
	 * @param operators A set of supported comparison operators. Must not be
	 *                  <tt>null</tt> or empty.
	 */
	public RSQLParser(Set<ComparisonOperator> operators) {
		if (operators == null || operators.isEmpty()) {
			throw new IllegalArgumentException("operators must not be null or empty");
		}
		this.nodesFactory = new NodesFactory(operators);
	}

	/**
	 * Parses the RSQL expression and returns AST.
	 *
	 * @param query The query expression to parse.
	 * @return A root of the parsed AST.
	 *
	 * @throws RSQLParserException      If some exception occurred during parsing,
	 *                                  i.e. the {@code query} is syntactically
	 *                                  invalid.
	 * @throws IllegalArgumentException If the {@code query} is <tt>null</tt>.
	 */
	public Node parse(String query) throws RSQLParserException {
		if (query == null) {
			throw new IllegalArgumentException("query must not be null");
		}
		InputStream is = new ByteArrayInputStream(query.getBytes(ENCODING));
		Parser parser = new Parser(is, ENCODING.name(), nodesFactory);

		try {
			return parser.Input();

		} catch (Exception ex) {
			throw new RSQLParserException(ex);
		}
	}
	
	
	public static void main(String[] args) {
		Node rootNode = new RSQLParser().parse("((firstName==john;lastName==doe),(firstName==aaron;lastName==carter));((age==21;height==90),(age==30;height==100))");

		System.out.println(rootNode);
	}
}
