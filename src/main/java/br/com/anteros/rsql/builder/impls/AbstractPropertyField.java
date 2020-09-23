package br.com.anteros.rsql.builder.impls;

import java.time.temporal.Temporal;

import br.com.anteros.rsql.builder.AnterosRsqlBuilder;
import br.com.anteros.rsql.builder.impls.properties.BooleanProperty;
import br.com.anteros.rsql.builder.impls.properties.DoubleProperty;
import br.com.anteros.rsql.builder.impls.properties.EnumProperty;
import br.com.anteros.rsql.builder.impls.properties.FloatProperty;
import br.com.anteros.rsql.builder.impls.properties.IntegerProperty;
import br.com.anteros.rsql.builder.impls.properties.LongProperty;
import br.com.anteros.rsql.builder.impls.properties.ShortProperty;
import br.com.anteros.rsql.builder.impls.properties.StringProperty;
import br.com.anteros.rsql.builder.impls.properties.TemporalProperty;

public abstract class AbstractPropertyField<B> extends AbstractField<B> {

    AbstractPropertyField(AnterosRsqlBuilder rsqlBuilder, StringBuilder context) {
        super(rsqlBuilder, context);
    }

    /**
     * String field
     *
     * @param field a name
     */
    public StringProperty<B> string(String field) {
        return new StringProperty<>(field, this);
    }

    /**
     * String field
     *
     * @param field a name
     */
    public BooleanProperty<B> bool(String field) {
        return new BooleanProperty<B>(field, this);
    }

    /**
     * Short field
     *
     * @param field a name
     */
    public ShortProperty<B> shortNum(String field) {
        return new ShortProperty<B>(field, this);
    }

    /**
     * Int field
     *
     * @param field a name
     */
    public IntegerProperty<B> intNum(String field) {
        return new IntegerProperty<B>(field, this);
    }

    /**
     * Float field
     *
     * @param field a name
     */
    public FloatProperty<B> floatNum(String field) {
        return new FloatProperty<B>(field, this);
    }

    /**
     * Double field
     *
     * @param field a name
     */
    public DoubleProperty<B> doubleNum(String field) {
        return new DoubleProperty<B>(field, this);
    }

    /**
     * Long field
     *
     * @param field a name
     */
    public LongProperty<B> longNum(String field) {
        return new LongProperty<B>(field, this);
    }

    /**
     * Enum field
     *
     * @param field a name
     */
    public <E extends Enum<E>> EnumProperty<B, E> enumeration(String field) {
        return new EnumProperty<>(field, this);
    }

    /**
     * Temporal field
     *
     * @param field a name
     */
    public <E extends Temporal> TemporalProperty<B, E> temporal(String field) {
        return new TemporalProperty<>(field, this);
    }
}
