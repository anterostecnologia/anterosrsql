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
package br.com.anteros.rsql.query.dsl;

import java.util.ArrayList;
import java.util.List;

import br.com.anteros.persistence.dsl.osql.support.Expressions;
import br.com.anteros.persistence.dsl.osql.types.Path;
import br.com.anteros.persistence.dsl.osql.types.expr.BooleanExpression;
import br.com.anteros.rsql.query.dsl.exception.TypeNotSupportedException;
import br.com.anteros.rsql.query.dsl.handler.FieldTypeHandler;
import br.com.anteros.rsql.query.dsl.operator.QrsqlOperator;
import br.com.anteros.rsql.query.dsl.util.QrsqlUtil;

/**
 * @author Balint Rudas
 */
public class PredicateBuilder {

    private AnterosRSQLQueryConfig qrsqlConfig;

    public PredicateBuilder(AnterosRSQLQueryConfig qrsqlConfig) {
        this.qrsqlConfig = qrsqlConfig;
    }

    public AnterosRSQLQueryConfig getQrsqlConfig() {
        return qrsqlConfig;
    }

    /**
     *
     * @param rootClass The base class
     * @param fieldSelector Field name
     * @param values The value of the field
     * @param operator The operator between the field and the value.
     * @return {@link BooleanExpression}
     * @throws TypeNotSupportedException If the requested field type is not supported
     */
    public BooleanExpression getExpression(Class rootClass, String fieldSelector, List<String> values, QrsqlOperator operator) throws TypeNotSupportedException {
        List<FieldMetadata> fieldMetadataList = QrsqlUtil.parseFieldSelector(rootClass, fieldSelector);
        Path rootPath = Expressions.path(rootClass, rootClass.getSimpleName().toLowerCase());
        List<Path> processedPaths = new ArrayList<>();
        FieldTypeHandler fieldType = null;
        for (int i = 0; i < fieldMetadataList.size(); i++) {
            fieldType = this.qrsqlConfig.getFieldTypeHandler(fieldMetadataList.get(i).getType());
            processedPaths.add(fieldType.getPath(fieldMetadataList.get(i), i == 0 ? rootPath : processedPaths.get(i - 1), this.qrsqlConfig));
        }
        Object value = fieldType.getValue(values, fieldMetadataList.get(fieldMetadataList.size() - 1), this.qrsqlConfig);
        return fieldType.getExpression(processedPaths.get(processedPaths.size() - 1), fieldMetadataList.get(fieldMetadataList.size() - 1), value, operator, this.qrsqlConfig);
    }

}
