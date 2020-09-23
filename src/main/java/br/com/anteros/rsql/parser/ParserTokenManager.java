/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
package br.com.anteros.rsql.parser;


/** Token Manager. */
class ParserTokenManager implements ParserConstants {

	/** Debug output. */
	public java.io.PrintStream debugStream = System.out;

	/** Set debug output. */
	public void setDebugStream(java.io.PrintStream ds) {
		debugStream = ds;
	}

	private final int jjStopStringLiteralDfa_0(int pos, long active0) {
		switch (pos) {
		default:
			return -1;
		}
	}

	private final int jjStartNfa_0(int pos, long active0) {
		return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
	}

	private int jjStopAtPos(int pos, int kind) {
		jjmatchedKind = kind;
		jjmatchedPos = pos;
		return pos + 1;
	}

	private int jjMoveStringLiteralDfa0_0() {
		switch (curChar) {
		case 9:
			return jjStartNfaWithStates_0(0, 2, 0);
		case 32:
			return jjStartNfaWithStates_0(0, 1, 23);
		case 40:
			return jjStopAtPos(0, 10);
		case 41:
			return jjStopAtPos(0, 11);
		default:
			return jjMoveNfa_0(1, 0);
		}
	}

	private int jjStartNfaWithStates_0(int pos, int kind, int state) {
		jjmatchedKind = kind;
		jjmatchedPos = pos;
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			return pos + 1;
		}
		return jjMoveNfa_0(state, pos + 1);
	}

	static final long[] jjbitVec0 = { 0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL,
			0xffffffffffffffffL };
	static final long[] jjbitVec2 = { 0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL };

	private int jjMoveNfa_0(int startState, int curPos) {
		int startsAt = 0;
		jjnewStateCnt = 27;
		int i = 1;
		jjstateSet[0] = startState;
		int kind = 0x7fffffff;
		for (;;) {
			if (++jjround == 0x7fffffff)
				ReInitRounds();
			if (curChar < 64) {
				long l = 1L << curChar;
				do {
					switch (jjstateSet[--i]) {
					case 1:
						if ((0x87ffec78ffffffffL & l) != 0L) {
							if (kind > 5)
								kind = 5;
							jjCheckNAdd(0);
						} else if ((0x5000000000000000L & l) != 0L) {
							if (kind > 13)
								kind = 13;
							jjstateSet[jjnewStateCnt++] = 18;
						} else if (curChar == 32)
							jjAddStates(0, 1);
						else if (curChar == 33)
							jjCheckNAdd(15);
						else if (curChar == 61)
							jjCheckNAddTwoStates(14, 15);
						else if (curChar == 44) {
							if (kind > 9)
								kind = 9;
						} else if (curChar == 59) {
							if (kind > 8)
								kind = 8;
						} else if (curChar == 34)
							jjCheckNAddStates(2, 4);
						else if (curChar == 39)
							jjCheckNAddStates(5, 7);
						break;
					case 0:
						if ((0x87ffec78ffffffffL & l) == 0L)
							break;
						if (kind > 5)
							kind = 5;
						jjCheckNAdd(0);
						break;
					case 3:
						jjCheckNAddStates(5, 7);
						break;
					case 4:
						if ((0xffffff7fffffffffL & l) != 0L)
							jjCheckNAddStates(5, 7);
						break;
					case 5:
						if (curChar == 39 && kind > 6)
							kind = 6;
						break;
					case 6:
						if (curChar == 34)
							jjCheckNAddStates(2, 4);
						break;
					case 8:
						jjCheckNAddStates(2, 4);
						break;
					case 9:
						if ((0xfffffffbffffffffL & l) != 0L)
							jjCheckNAddStates(2, 4);
						break;
					case 10:
						if (curChar == 34 && kind > 7)
							kind = 7;
						break;
					case 11:
						if (curChar == 59 && kind > 8)
							kind = 8;
						break;
					case 12:
						if (curChar == 44 && kind > 9)
							kind = 9;
						break;
					case 13:
						if (curChar == 61)
							jjCheckNAddTwoStates(14, 15);
						break;
					case 15:
						if (curChar == 61 && kind > 12)
							kind = 12;
						break;
					case 16:
						if (curChar == 33)
							jjCheckNAdd(15);
						break;
					case 17:
						if ((0x5000000000000000L & l) == 0L)
							break;
						if (kind > 13)
							kind = 13;
						jjstateSet[jjnewStateCnt++] = 18;
						break;
					case 18:
						if (curChar == 61 && kind > 13)
							kind = 13;
						break;
					case 19:
						if (curChar == 32)
							jjAddStates(0, 1);
						break;
					case 20:
						if (curChar == 32 && kind > 8)
							kind = 8;
						break;
					case 24:
						if (curChar == 32 && kind > 9)
							kind = 9;
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else if (curChar < 128) {
				long l = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					case 23:
						if (curChar == 111)
							jjstateSet[jjnewStateCnt++] = 25;
						else if (curChar == 97)
							jjstateSet[jjnewStateCnt++] = 22;
						break;
					case 1:
					case 0:
						if ((0xbfffffffffffffffL & l) == 0L)
							break;
						if (kind > 5)
							kind = 5;
						jjCheckNAdd(0);
						break;
					case 2:
						if (curChar == 92)
							jjstateSet[jjnewStateCnt++] = 3;
						break;
					case 3:
						jjCheckNAddStates(5, 7);
						break;
					case 4:
						if ((0xffffffffefffffffL & l) != 0L)
							jjCheckNAddStates(5, 7);
						break;
					case 7:
						if (curChar == 92)
							jjstateSet[jjnewStateCnt++] = 8;
						break;
					case 8:
						jjCheckNAddStates(2, 4);
						break;
					case 9:
						if ((0xffffffffefffffffL & l) != 0L)
							jjCheckNAddStates(2, 4);
						break;
					case 14:
						if ((0x7fffffe07fffffeL & l) != 0L)
							jjAddStates(8, 9);
						break;
					case 21:
						if (curChar == 100)
							jjstateSet[jjnewStateCnt++] = 20;
						break;
					case 22:
						if (curChar == 110)
							jjstateSet[jjnewStateCnt++] = 21;
						break;
					case 25:
						if (curChar == 114)
							jjstateSet[jjnewStateCnt++] = 24;
						break;
					case 26:
						if (curChar == 111)
							jjstateSet[jjnewStateCnt++] = 25;
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else {
				int hiByte = (int) (curChar >> 8);
				int i1 = hiByte >> 6;
				long l1 = 1L << (hiByte & 077);
				int i2 = (curChar & 0xff) >> 6;
				long l2 = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					case 1:
					case 0:
						if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
							break;
						if (kind > 5)
							kind = 5;
						jjCheckNAdd(0);
						break;
					case 3:
					case 4:
						if (jjCanMove_0(hiByte, i1, i2, l1, l2))
							jjCheckNAddStates(5, 7);
						break;
					case 8:
					case 9:
						if (jjCanMove_0(hiByte, i1, i2, l1, l2))
							jjCheckNAddStates(2, 4);
						break;
					default:
						break;
					}
				} while (i != startsAt);
			}
			if (kind != 0x7fffffff) {
				jjmatchedKind = kind;
				jjmatchedPos = curPos;
				kind = 0x7fffffff;
			}
			++curPos;
			if ((i = jjnewStateCnt) == (startsAt = 27 - (jjnewStateCnt = startsAt)))
				return curPos;
			try {
				curChar = input_stream.readChar();
			} catch (java.io.IOException e) {
				return curPos;
			}
		}
	}

	static final int[] jjnextStates = { 23, 26, 7, 9, 10, 2, 4, 5, 14, 15, };

	private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
		switch (hiByte) {
		case 0:
			return ((jjbitVec2[i2] & l2) != 0L);
		default:
			if ((jjbitVec0[i1] & l1) != 0L)
				return true;
			return false;
		}
	}

	/** Token literal values. */
	public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null, null, null, null, "\50",
			"\51", null, null, };

	/** Lexer state names. */
	public static final String[] lexStateNames = { "DEFAULT", };
	static final long[] jjtoToken = { 0x3fe1L, };
	static final long[] jjtoSkip = { 0x6L, };
	protected SimpleCharStream input_stream;
	private final int[] jjrounds = new int[27];
	private final int[] jjstateSet = new int[54];
	protected char curChar;

	/** Constructor. */
	public ParserTokenManager(SimpleCharStream stream) {
		if (SimpleCharStream.staticFlag)
			throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
		input_stream = stream;
	}

	/** Constructor. */
	public ParserTokenManager(SimpleCharStream stream, int lexState) {
		this(stream);
		SwitchTo(lexState);
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream) {
		jjmatchedPos = jjnewStateCnt = 0;
		curLexState = defaultLexState;
		input_stream = stream;
		ReInitRounds();
	}

	private void ReInitRounds() {
		int i;
		jjround = 0x80000001;
		for (i = 27; i-- > 0;)
			jjrounds[i] = 0x80000000;
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream, int lexState) {
		ReInit(stream);
		SwitchTo(lexState);
	}

	/** Switch to specified lex state. */
	public void SwitchTo(int lexState) {
		if (lexState >= 1 || lexState < 0)
			throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.",
					TokenMgrError.INVALID_LEXICAL_STATE);
		else
			curLexState = lexState;
	}

	protected Token jjFillToken() {
		final Token t;
		final String curTokenImage;
		final int beginLine;
		final int endLine;
		final int beginColumn;
		final int endColumn;
		String im = jjstrLiteralImages[jjmatchedKind];
		curTokenImage = (im == null) ? input_stream.GetImage() : im;
		beginLine = input_stream.getBeginLine();
		beginColumn = input_stream.getBeginColumn();
		endLine = input_stream.getEndLine();
		endColumn = input_stream.getEndColumn();
		t = Token.newToken(jjmatchedKind, curTokenImage);

		t.beginLine = beginLine;
		t.endLine = endLine;
		t.beginColumn = beginColumn;
		t.endColumn = endColumn;

		return t;
	}

	int curLexState = 0;
	int defaultLexState = 0;
	int jjnewStateCnt;
	int jjround;
	int jjmatchedPos;
	int jjmatchedKind;

	/** Get the next Token. */
	public Token getNextToken() {
		Token matchedToken;
		int curPos = 0;

		EOFLoop: for (;;) {
			try {
				curChar = input_stream.BeginToken();
			} catch (java.io.IOException e) {
				jjmatchedKind = 0;
				matchedToken = jjFillToken();
				return matchedToken;
			}

			jjmatchedKind = 0x7fffffff;
			jjmatchedPos = 0;
			curPos = jjMoveStringLiteralDfa0_0();
			if (jjmatchedKind != 0x7fffffff) {
				if (jjmatchedPos + 1 < curPos)
					input_stream.backup(curPos - jjmatchedPos - 1);
				if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
					matchedToken = jjFillToken();
					return matchedToken;
				} else {
					continue EOFLoop;
				}
			}
			int error_line = input_stream.getEndLine();
			int error_column = input_stream.getEndColumn();
			String error_after = null;
			boolean EOFSeen = false;
			try {
				input_stream.readChar();
				input_stream.backup(1);
			} catch (java.io.IOException e1) {
				EOFSeen = true;
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
				if (curChar == '\n' || curChar == '\r') {
					error_line++;
					error_column = 0;
				} else
					error_column++;
			}
			if (!EOFSeen) {
				input_stream.backup(1);
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
			}
			throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar,
					TokenMgrError.LEXICAL_ERROR);
		}
	}

	private void jjCheckNAdd(int state) {
		if (jjrounds[state] != jjround) {
			jjstateSet[jjnewStateCnt++] = state;
			jjrounds[state] = jjround;
		}
	}

	private void jjAddStates(int start, int end) {
		do {
			jjstateSet[jjnewStateCnt++] = jjnextStates[start];
		} while (start++ != end);
	}

	private void jjCheckNAddTwoStates(int state1, int state2) {
		jjCheckNAdd(state1);
		jjCheckNAdd(state2);
	}

	private void jjCheckNAddStates(int start, int end) {
		do {
			jjCheckNAdd(jjnextStates[start]);
		} while (start++ != end);
	}

}
