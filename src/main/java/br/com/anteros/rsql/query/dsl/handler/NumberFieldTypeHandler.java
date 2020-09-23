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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.anteros.persistence.dsl.osql.support.Expressions;
import br.com.anteros.persistence.dsl.osql.types.Path;
import br.com.anteros.persistence.dsl.osql.types.expr.BooleanExpression;
import br.com.anteros.persistence.dsl.osql.types.expr.SimpleExpression;
import br.com.anteros.persistence.dsl.osql.types.path.NumberPath;
import br.com.anteros.persistence.dsl.osql.util.MathUtils;
import br.com.anteros.rsql.query.dsl.FieldMetadata;
import br.com.anteros.rsql.query.dsl.AnterosRSQLQueryConfig;
import br.com.anteros.rsql.query.dsl.operator.Operator;
import br.com.anteros.rsql.query.dsl.operator.QrsqlOperator;

/**
 * @author Balint Rudas
 */
public class NumberFieldTypeHandler extends BaseFieldTypeHandler implements FieldTypeHandler {

    @Override
    public Boolean supportsType(Class type) {
        return Integer.class.equals(type) || int.class.equals(type) ||
                Byte.class.equals(type) || byte.class.equals(type) ||
                Long.class.equals(type) || long.class.equals(type) ||
                Short.class.equals(type) || short.class.equals(type) ||
                Double.class.equals(type) || double.class.equals(type) ||
                Float.class.equals(type) || float.class.equals(type) ||
                BigInteger.class.equals(type) || BigDecimal.class.equals(type) ||
                Number.class.isAssignableFrom(type);
    }

    @Override
    public Path getPath(FieldMetadata fieldMetadata, Path parentPath, AnterosRSQLQueryConfig qrsqlConfig) {
        return Expressions.numberPath(fieldMetadata.getType(), parentPath, fieldMetadata.getFieldSelector());
    }

    @Override
    public Object getValue(List<String> values, FieldMetadata fieldMetadata, AnterosRSQLQueryConfig qrsqlConfig) {
        List<Number> converted = new ArrayList<>();
        for (String item : values) {
            Number number = item.isEmpty() ? null : NumberUtils.createNumber(item);
            converted.add(number != null ? MathUtils.cast(number, fieldMetadata.getType()) : number);
        }
        return values.size() > 1 ? converted : converted.get(0);
    }

    @Override
    public BooleanExpression getExpression(Path path, FieldMetadata fieldMetadata, Object value, QrsqlOperator
            operator, AnterosRSQLQueryConfig qrsqlConfig) {
        if (operator.equals(Operator.GREATER)) {
            return ((NumberPath) path).gt((Number) value);
        } else if (operator.equals(Operator.GREATER_OR_EQUALS)) {
            return ((NumberPath) path).goe((Number) value);
        } else if (operator.equals(Operator.LESS_THAN)) {
            return ((NumberPath) path).lt((Number) value);
        } else if (operator.equals(Operator.LESS_THAN_OR_EQUALS)) {
            return ((NumberPath) path).loe((Number) value);
        } else if (operator.equals(Operator.IN)) {
            return ((SimpleExpression) path).in((List<Number>) value);
        } else if (operator.equals(Operator.NOTIN)) {
            return ((SimpleExpression) path).notIn((List<Number>) value);
        } else {
            return super.getExpression(path, fieldMetadata, value, operator, qrsqlConfig);
        }
    }
}
