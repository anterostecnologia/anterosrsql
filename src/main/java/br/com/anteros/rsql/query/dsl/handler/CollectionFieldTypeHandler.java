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

import java.util.Collection;
import java.util.List;

import br.com.anteros.persistence.dsl.osql.alias.DefaultTypeSystem;
import br.com.anteros.persistence.dsl.osql.alias.TypeSystem;
import br.com.anteros.persistence.dsl.osql.support.Expressions;
import br.com.anteros.persistence.dsl.osql.types.Path;
import br.com.anteros.persistence.dsl.osql.types.PathMetadata;
import br.com.anteros.persistence.dsl.osql.types.PathMetadataFactory;
import br.com.anteros.persistence.dsl.osql.types.expr.BooleanExpression;
import br.com.anteros.persistence.dsl.osql.types.expr.SimpleExpression;
import br.com.anteros.persistence.dsl.osql.types.path.CollectionPath;
import br.com.anteros.rsql.query.dsl.FieldMetadata;
import br.com.anteros.rsql.query.dsl.AnterosRSQLQueryConfig;
import br.com.anteros.rsql.query.dsl.exception.TypeNotSupportedException;
import br.com.anteros.rsql.query.dsl.operator.Operator;
import br.com.anteros.rsql.query.dsl.operator.QrsqlOperator;

/**
 * @author Balint Rudas
 */
public class CollectionFieldTypeHandler extends BaseFieldTypeHandler implements FieldTypeHandler {


    @Override
    public Boolean supportsType(Class type) {
        TypeSystem typeSystem = new DefaultTypeSystem();
        return typeSystem.isCollectionType(type);
    }

    @Override
    public Path getPath(FieldMetadata fieldMetadata, Path parentPath, AnterosRSQLQueryConfig qrsqlConfig) {
        PathMetadata listMetadata = PathMetadataFactory.forProperty(parentPath, fieldMetadata.getFieldSelector());
        CollectionPath collectionPath = Expressions.collectionPath(fieldMetadata.getCollectionType(), (Class) fieldMetadata.getPathType(), listMetadata);
        return (Path) collectionPath.any();
    }

    @Override
    public Object getValue(List<String> values, FieldMetadata fieldMetadata, AnterosRSQLQueryConfig qrsqlConfig) {
        if (fieldMetadata.getParameterizedType() == null) {
            return values.size() > 1 ? values : values.get(0);
        }
        FieldTypeHandler fieldType = null;
        try {
            fieldType = qrsqlConfig.getFieldTypeHandler(fieldMetadata.getCollectionType());
        } catch (TypeNotSupportedException e) {
            e.printStackTrace();
        }
        return fieldType.getValue(values, new FieldMetadata(fieldMetadata.getCollectionType(), fieldMetadata), qrsqlConfig);
    }

    @Override
    public BooleanExpression getExpression(Path path, FieldMetadata fieldMetadata, Object value, QrsqlOperator operator, AnterosRSQLQueryConfig qrsqlConfig) {
        if (operator.equals(Operator.IN)) {
            return ((SimpleExpression) path).in((Collection) value);
        } else if (operator.equals(Operator.NOTIN)) {
            return ((SimpleExpression) path).notIn((Collection) value);
        } else {
            return super.getExpression(path, fieldMetadata, value, operator, qrsqlConfig);
        }
    }

}
