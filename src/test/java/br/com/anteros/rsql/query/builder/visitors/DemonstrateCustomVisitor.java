package br.com.anteros.rsql.query.builder.visitors;

import static org.junit.Assert.assertEquals;

import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;

import br.com.anteros.rsql.query.builder.builders.AnterosQBuilder;
import br.com.anteros.rsql.query.builder.conditions.Condition;
import br.com.anteros.rsql.query.builder.nodes.ComparisonNode;
import br.com.anteros.rsql.query.builder.structures.FieldPath;
import br.com.anteros.rsql.query.builder.visitors.AnterosElasticsearchVisitor;

public class DemonstrateCustomVisitor {

    public static class DemonstrateTypeThing extends AnterosQBuilder<DemonstrateTypeThing> {
    }


    public static class VariableFieldElasticsearchVisitor extends AnterosElasticsearchVisitor {

        @Override
        protected QueryBuilder visit(ComparisonNode node, Context context) {

            if ("value".equals(node.getField().asKey())) {
                node.setField(new FieldPath(node.getField().asKey() + "_" + determineSuffix(single(node.getValues()))));
            }

            return super.visit(node, context);
        }

        private String determineSuffix(Object value) {
            if (value instanceof Number) {
                return "number";
            } else if (value instanceof String) {
                return "text";
            } else if (value instanceof Boolean) {
                return "bool";
            }

            return "";
        }

    }


    @Test
    public void test() {
        Condition<DemonstrateTypeThing> numQuery = new DemonstrateTypeThing().intNum("value").eq(2);
        Condition<DemonstrateTypeThing> textQuery = new DemonstrateTypeThing().string("value").eq("dadad");
        Condition<DemonstrateTypeThing> boolQuery = new DemonstrateTypeThing().bool("value").isTrue();

        
        
        assertEquals("{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"value_number\" : {\n" + 
        		"      \"value\" : 2,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}", numQuery.query(new VariableFieldElasticsearchVisitor(), new AnterosElasticsearchVisitor.Context()).toString());


        assertEquals("{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"value_text\" : {\n" + 
        		"      \"value\" : \"dadad\",\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}", textQuery.query(new VariableFieldElasticsearchVisitor(), new AnterosElasticsearchVisitor.Context()).toString());

        assertEquals("{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"value_bool\" : {\n" + 
        		"      \"value\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}", boolQuery.query(new VariableFieldElasticsearchVisitor(), new AnterosElasticsearchVisitor.Context()).toString());

    }

}
