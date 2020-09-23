package br.com.anteros.rsql.query.builder.structures;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.anteros.rsql.query.builder.structures.FieldPath;

public class FieldPathTest {

    private FieldPath it = new FieldPath("br.com.anteros.rsql.myList");

    @Test
    public void iterator() throws Exception {
        List<String> segments = it.stream().flatMap(FieldPath.FieldNamespace::stream).collect(toList());
        assertEquals("br", segments.get(0));
        assertEquals("com", segments.get(1));
        assertEquals("anteros", segments.get(2));
        assertEquals("rsql", segments.get(3));
        assertEquals("myList", segments.get(4));
    }

    @Test
    public void append_String() throws Exception {
        FieldPath oneMore = it.append("name");
        assertEquals("br.com.anteros.rsql.myList.name", oneMore.asFullyQualifiedKey());
        assertEquals("name", oneMore.asKey());

        FieldPath twoMore = it.append("name", "again");
        assertEquals("br.com.anteros.rsql.myList.name.again", twoMore.asFullyQualifiedKey());
        assertEquals("again", twoMore.asKey());
    }

    @Test
    public void append_Path() throws Exception {
        FieldPath oneMore = it.append(new FieldPath("name"));
        assertEquals("br.com.anteros.rsql.myList.name", oneMore.asFullyQualifiedKey());
        assertEquals("name", oneMore.asKey());
    }

    @Test
    public void prepend_String() throws Exception {
        FieldPath oneMore = it.prepend("name");
        assertEquals("name.br.com.anteros.rsql.myList", oneMore.asFullyQualifiedKey());
        assertEquals("br.com.anteros.rsql.myList", oneMore.asKey());
    }

    @Test
    public void prepend_Path() throws Exception {
        FieldPath oneMore = it.prepend(new FieldPath("name"));
        assertEquals("name.br.com.anteros.rsql.myList", oneMore.asFullyQualifiedKey());
        assertEquals("br.com.anteros.rsql.myList", oneMore.asKey());
    }

    @Test
    public void asFullyQualifiedPrefix() throws Exception {
        FieldPath oneMore = it.append("name");
        assertEquals("br.com.anteros.rsql.myList.", it.asFullyQualifiedPrefix());
        assertEquals("br.com.anteros.rsql.myList.name.", oneMore.asFullyQualifiedPrefix());
    }

    @Test
    public void asFullyQualifiedKey() throws Exception {
        FieldPath oneMore = it.append("name");
        assertEquals("br.com.anteros.rsql.myList", it.asFullyQualifiedKey());
        assertEquals("br.com.anteros.rsql.myList.name", oneMore.asFullyQualifiedKey());
    }

    @Test
    public void asShortKey() throws Exception {
        FieldPath oneMore = it.append("name");
        assertEquals("name", oneMore.asKey());
    }

    @Test
    public void asShortPrefix() throws Exception {
        FieldPath oneMore = it.append("name");
        assertEquals("name.", oneMore.asPrefix());
    }

    @Test
    public void test_toString() throws Exception {
        FieldPath oneMore = it.append("name");
        assertEquals("br.com.anteros.rsql.myList.name", oneMore.toString());
    }

    @Test
    public void testEquals() {
        FieldPath path = new FieldPath("test");
        assertTrue(path.equals(path));
        assertFalse(path.equals("cats"));
        assertFalse(path.equals(new FieldPath("bats")));
        assertTrue(path.equals(new FieldPath("test")));
    }

    @Test
    public void testHashCode() {
        FieldPath path = new FieldPath("test");
        assertEquals(path.hashCode(), path.hashCode());
        assertNotEquals(path.hashCode(), new FieldPath("badgers").hashCode());
    }


    @Test
    public void testGetParentPath() {
        FieldPath path = it.append("name");
        assertTrue(path.getParentPath().isPresent());
        assertEquals("br.com.anteros.rsql.myList", path.getParentPath().get().asFullyQualifiedKey());
    }

}
