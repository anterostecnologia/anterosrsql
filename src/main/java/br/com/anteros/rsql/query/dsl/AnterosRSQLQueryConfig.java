package br.com.anteros.rsql.query.dsl;


import java.util.ArrayList;
import java.util.List;


import br.com.anteros.persistence.session.SQLSessionFactory;
import br.com.anteros.rsql.query.dsl.exception.QrsqlException;
import br.com.anteros.rsql.query.dsl.exception.TypeNotSupportedException;
import br.com.anteros.rsql.query.dsl.handler.FieldTypeHandler;
import br.com.anteros.rsql.query.dsl.operator.QrsqlOperator;
import br.com.anteros.rsql.query.dsl.util.QrsqlUtil;

/**
 * A class to build an initialization configuration to {@link AnterosRSQLQuery.Builder}
 *
 * @param <E> Target type
 */
public class AnterosRSQLQueryConfig<E> {

    private SQLSessionFactory sqlSessionFactory;
    private List<QrsqlOperator> operators;
    private List<FieldTypeHandler> fieldTypeHandlers;
    private String dateFormat;

    private AnterosRSQLQueryConfig(Builder<E> builder) {
        this.sqlSessionFactory = builder.sqlSessionFactory;
        this.operators = builder.operators;
        this.dateFormat = builder.dateFormat;
        this.fieldTypeHandlers = QrsqlUtil.getDefaultFieldTypeHandlers();
        if (builder.fieldTypeHandlers != null) {
            this.fieldTypeHandlers.addAll(0, builder.fieldTypeHandlers);
        }
    }

    public SQLSessionFactory getSessionFactory() {
        return sqlSessionFactory;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setSessionFactory(SQLSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<QrsqlOperator> getOperators() {
        return this.operators;
    }

    public void setOperators(List<QrsqlOperator> operators) {
        this.operators = operators;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setFieldTypeHandlers(List<FieldTypeHandler> fieldTypeHandlers) {
        this.fieldTypeHandlers = QrsqlUtil.getDefaultFieldTypeHandlers();
        if (fieldTypeHandlers != null) {
            this.fieldTypeHandlers.addAll(0,fieldTypeHandlers);
        }
    }

    /**
     * Determine the FieldType of the given class.
     *
     * @param type Field class type
     * @return {@link FieldTypeHandler}
     * @throws TypeNotSupportedException If the requested field type is not supported
     */
    public FieldTypeHandler getFieldTypeHandler(Class type) {
        for (FieldTypeHandler fieldType : this.fieldTypeHandlers) {
            if (fieldType.supportsType(type)) {
                return fieldType;
            }
        }
        throw new TypeNotSupportedException("Type is not supported: " + type.toString());
    }

    /**
     * Help to create and configure {@link AnterosRSQLQueryConfig}
     *
     * @param <E> Target type
     */
    public static class Builder<E> {
        private SQLSessionFactory sqlSessionFactory;
        private List<QrsqlOperator> operators;
        private List<FieldTypeHandler> fieldTypeHandlers;
        private String dateFormat = null;

        public Builder(SQLSessionFactory sqlSessionFactory) {
            this.sqlSessionFactory = sqlSessionFactory;
        }

        private Builder(AnterosRSQLQueryConfig.Builder<E> builder) {
            this.sqlSessionFactory = builder.sqlSessionFactory;
            this.operators = builder.operators;
            this.fieldTypeHandlers = builder.fieldTypeHandlers;
            this.dateFormat = builder.dateFormat;
        }

        public AnterosRSQLQueryConfig.Builder<E> sessionFactory(SQLSessionFactory sqlSessionFactory) {
            this.sqlSessionFactory = sqlSessionFactory;
            return this;
        }


        public AnterosRSQLQueryConfig.Builder<E> operators(List<QrsqlOperator> operators) {
            this.operators = operators;
            return this;
        }

        public AnterosRSQLQueryConfig.Builder<E> operator(QrsqlOperator operator) {
            this.operators = new ArrayList<>();
            this.operators.add(operator);
            return this;
        }

        public AnterosRSQLQueryConfig.Builder<E> fieldTypeHandlers(List<FieldTypeHandler> fieldTypeHandlers) {
            this.fieldTypeHandlers = fieldTypeHandlers;
            return this;
        }

        public AnterosRSQLQueryConfig.Builder<E> fieldTypeHandler(FieldTypeHandler fieldTypeHandler) {
            this.fieldTypeHandlers = new ArrayList<>();
            this.fieldTypeHandlers.add(fieldTypeHandler);
            return this;
        }

        public AnterosRSQLQueryConfig.Builder<E> dateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
            return this;
        }

        public AnterosRSQLQueryConfig<E> build() throws QrsqlException {
            try {
                return new AnterosRSQLQueryConfig<E>(this);
            } catch (Exception ex) {
                throw new QrsqlException(ex);
            }
        }


    }
}
