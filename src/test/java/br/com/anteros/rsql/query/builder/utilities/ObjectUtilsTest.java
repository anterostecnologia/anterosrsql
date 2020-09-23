package br.com.anteros.rsql.query.builder.utilities;

import org.junit.Test;

import br.com.anteros.rsql.query.builder.utilities.ObjectUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ObjectUtilsTest {

    private static class WithNoVisibleConstructor {

        private WithNoVisibleConstructor(){}

    }


    public static class SinglePlainArgument {

        public SinglePlainArgument(String value) {

        }
    }

    @Test
    public void noVisibleConstructor() throws Exception {
        expect("Could not instantiate class for provided arguments.", ()
                -> ObjectUtils.init(WithNoVisibleConstructor.class));
    }

    @Test
    public void noArgumentsProvided() throws Exception {
        expect("Could not instantiate class for provided arguments.", ()
                -> ObjectUtils.init(SinglePlainArgument.class));
    }

    @Test
    public void wrongArgumentTypesProvided() throws Exception {
        expect("Could not instantiate class for provided arguments.", ()
                -> ObjectUtils.init(SinglePlainArgument.class, (Integer) 55));
    }

    @Test
    public void correctTypeProvided() throws Exception {
        SinglePlainArgument instance = ObjectUtils.init(SinglePlainArgument.class, "stuff");
        assertNotNull(instance);
    }

    private void expect(String message, Runnable runnable) {
        try {
            runnable.run();
        } catch(Exception e) {
            assertTrue(e instanceof RuntimeException);
            assertEquals(message, e.getMessage());
        }
    }
}
