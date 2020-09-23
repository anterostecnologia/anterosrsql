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
package br.com.anteros.rsql.query.dsl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import br.com.anteros.persistence.dsl.osql.types.Expression;
import br.com.anteros.persistence.dsl.osql.types.Order;
import br.com.anteros.persistence.dsl.osql.types.Path;
import br.com.anteros.persistence.dsl.osql.types.path.PathBuilder;
import br.com.anteros.persistence.metadata.EntityCache;
import br.com.anteros.persistence.session.SQLSessionFactory;
import br.com.anteros.rsql.parser.ast.ComparisonOperator;
import br.com.anteros.rsql.query.dsl.FieldMetadata;
import br.com.anteros.rsql.query.dsl.handler.BaseFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.BooleanFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.CharacterFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.CollectionFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.DateFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.EnumFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.FieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.ListFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.NumberFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.SetFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.handler.StringFieldTypeHandler;
import br.com.anteros.rsql.query.dsl.operator.Operator;
import br.com.anteros.rsql.query.dsl.operator.QrsqlOperator;

/**
 * @author Balint Rudas
 */
public class QrsqlUtil {

    public static List<Path> convertStringFieldsToPath(Class type, List<String> fields) {
        List<Path> paths = new ArrayList<>();
        PathBuilder targetPath = new PathBuilder(type, type.getSimpleName().toLowerCase());
        for (String field : fields) {
            paths.add(targetPath.get(field));
        }
        return paths;
    }

    public static Class getClassForEntityString(String entityName, SQLSessionFactory sessionFactory) {
        if (sessionFactory != null) {
            for (EntityCache entity : sessionFactory.getEntityCacheManager().getEntities().values()) {
                if (entityName.equals(entity.getEntityClass().getSimpleName())) {
                    return entity.getEntityClass();
                }
            }
        }
        return null;
    }

    public static Expression[] convertPathToExpression(List<Path> paths) {
        Expression[] expressions = new Expression[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            expressions[i] = paths.get(i);
        }
        return expressions;
    }

    /**
     * Merge default operators and the given custom operators.
     * Convert {@code List<QrsqlOperator>} to {@code Set<ComparisonOperator>}
     * @param customOperators  Additional operators
     * @return {@code Set<ComparisonOperator>}
     */
    public static Set<ComparisonOperator> getOperators(List<QrsqlOperator> customOperators) {
        Set<ComparisonOperator> operators = new HashSet<>();
        for (String operator : Operator.getLookup().keySet()) {
            operators.add(new ComparisonOperator(operator, true));
        }
        if(customOperators!=null && !customOperators.isEmpty()){
            for(QrsqlOperator qrsqlOperator : customOperators){
                for(String operator : qrsqlOperator.getSymbols()){
                    operators.add(new ComparisonOperator(operator, true));
                }
            }
        }
        return operators;
    }

    /**
     * Validate the format of the operators.
     * @param operators Must match <tt>=[a-zA-Z]*=|[&gt;&lt;]=?|!=</tt>.
     */
    public static void validateOperators(List<QrsqlOperator> operators) {
        for (QrsqlOperator qrsqlOperator : operators) {
            for(String operator : qrsqlOperator.getSymbols()) {
                try {
                    new ComparisonOperator(operator, true);
                } catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Invalid operator symbol: '" + operator + "' Operator " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Parse and split string select expression to {@code List<String>}
     * @param selectString Select expression
     * @return {@code List<String>}
     */
    public static List<String> parseSelectExpression(String selectString) {
        if (selectString == null || selectString.isEmpty()) {
            return null;
        }
        selectString = selectString.replaceAll("\\s+", "");
        if (selectString.startsWith("(")) {
            selectString = selectString.substring(1, selectString.length() - 1);
        }
        return Arrays.asList(selectString.split(","));
    }

    /**
     * Get {@code List<Path>} for string select expression.
     * @param selectString Example: "(field1,field2)"
     * @param entityClass Target type
     * @return {@code List<String>}
     */
    public static List<Path> parseSelect(String selectString, Class entityClass) {
        List<String> selectFields = parseSelectExpression(selectString);
        List<Path> selectPath = null;
        if (selectFields != null) {
            selectPath = convertStringFieldsToPath(entityClass, selectFields);
        }
        return selectPath;
    }

    /**
     * Get {@code List<Long>} for two params string expression.
     * @param limit Example: "(0,10)"
     * @return {@code List<String>}
     */
    public static List<Long> parseTwoParamExpression(String limit) {
        List<String> limitParams = parseSelectExpression(limit);
        if (limitParams == null || limitParams.isEmpty() || limitParams.size() > 2) {
            throw new IllegalArgumentException("Invalid expression");
        }
        List<Long> result = new ArrayList<>();
        for (String param : limitParams) {
            result.add(Long.valueOf(param));
        }
        return result;
    }

    public static List<FieldMetadata> parseFieldSelector(Class rootClass, String fieldSelector) {
        String[] nestedFields = StringUtils.split(fieldSelector, ".");
        List<FieldMetadata> fieldMetadataList = new ArrayList<>();
        for (int i = 0; i < nestedFields.length; i++) {
            if (i == 0) {
                fieldMetadataList.add(new FieldMetadata(nestedFields[i], rootClass));
            } else {
                fieldMetadataList.add(new FieldMetadata(nestedFields[i], fieldMetadataList.get(i - 1)));
            }
        }
        return fieldMetadataList;
    }

    /**
     * Parse sort expression.
     * @param sort Example: "(field1.asc)"
     * @return {@code Map<String, Order>}
     */
    public static Map<String, Order> parseSortExpression(String sort) {
        Map<String, Order> result = new HashMap<>();
        List<String> sortParams = parseSelectExpression(sort);
        if (sortParams == null || sortParams.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }
        for (String param : sortParams) {
            int dotLastIndex = param.lastIndexOf(".");
            if (dotLastIndex == -1) {
                throw new IllegalArgumentException("Invalid expression");
            }
            String[] params = {param.substring(0, dotLastIndex), param.substring(dotLastIndex + 1)};
            result.put(params[0], Order.valueOf(params[1].toUpperCase()));
        }
        return result;
    }

    /**
     * Return all qrsql built in field type handlers
     * @return {@code List<FieldTypeHandler>}
     */
    public static List<FieldTypeHandler> getDefaultFieldTypeHandlers(){
        List<FieldTypeHandler> defaultFieldTypes = new ArrayList<>();
        defaultFieldTypes.add(new StringFieldTypeHandler());
        defaultFieldTypes.add(new CharacterFieldTypeHandler());
        defaultFieldTypes.add(new NumberFieldTypeHandler());
        defaultFieldTypes.add(new DateFieldTypeHandler());
        defaultFieldTypes.add(new BooleanFieldTypeHandler());
        defaultFieldTypes.add(new ListFieldTypeHandler());
        defaultFieldTypes.add(new SetFieldTypeHandler());
        defaultFieldTypes.add(new CollectionFieldTypeHandler());
        defaultFieldTypes.add(new EnumFieldTypeHandler());
        defaultFieldTypes.add(new BaseFieldTypeHandler());
        return defaultFieldTypes;
    }

}
