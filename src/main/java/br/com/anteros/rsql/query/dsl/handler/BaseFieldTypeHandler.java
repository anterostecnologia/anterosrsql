/*
 * MIT License
 *
 * Copyright (c) 2019 Balint Rudas
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package br.com.anteros.rsql.query.dsl.handler;

import java.util.List;

import br.com.anteros.persistence.dsl.osql.support.Expressions;
import br.com.anteros.persistence.dsl.osql.types.Path;
import br.com.anteros.persistence.dsl.osql.types.expr.BooleanExpression;
import br.com.anteros.persistence.dsl.osql.types.expr.SimpleExpression;
import br.com.anteros.rsql.query.dsl.FieldMetadata;
import br.com.anteros.rsql.query.dsl.AnterosRSQLQueryConfig;
import br.com.anteros.rsql.query.dsl.operator.Operator;
import br.com.anteros.rsql.query.dsl.operator.QrsqlOperator;

/**
 * @author Balint Rudas
 */
public class BaseFieldTypeHandler implements FieldTypeHandler {

    @Override
    public Boolean supportsType(Class type) {
        return true;
    }

    @Override
    public Path getPath(FieldMetadata fieldMetadata, Path parentPath, AnterosRSQLQueryConfig qrsqlConfig) {
        return Expressions.path(fieldMetadata.getType(), parentPath, fieldMetadata.getFieldSelector());
    }

    @Override
    public Object getValue(List<String> values, FieldMetadata fieldMetadata, AnterosRSQLQueryConfig qrsqlConfig) {
        return values.size() > 1 ? values : values.get(0);
    }

    @Override
    public BooleanExpression getExpression(Path path, FieldMetadata fieldMetadata, Object value, QrsqlOperator operator, AnterosRSQLQueryConfig qrsqlConfig) {
        if (operator.equals(Operator.EQUALS)) {
            return ((SimpleExpression) path).eq(value);
        } else if (operator.equals(Operator.NOTEQUALS)) {
            return ((SimpleExpression) path).ne(value);
        } else if (operator.equals(Operator.IN)) {
            return ((SimpleExpression) path).in(value);
        } else if (operator.equals(Operator.NOTIN)) {
            return ((SimpleExpression) path).notIn(value);
        } else if (operator.equals(Operator.ISNULL)) {
            return ((SimpleExpression) path).isNull();
        } else if (operator.equals(Operator.ISNOTNULL)) {
            return ((SimpleExpression) path).isNotNull();
        }
        return null;
    }
}
