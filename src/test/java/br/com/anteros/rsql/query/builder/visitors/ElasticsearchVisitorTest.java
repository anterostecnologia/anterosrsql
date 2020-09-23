package br.com.anteros.rsql.query.builder.visitors;

import org.elasticsearch.index.query.QueryBuilder;

import br.com.anteros.rsql.query.builder.testsupport.QBuilderTestBase;
import br.com.anteros.rsql.query.builder.visitors.AnterosElasticsearchVisitor;

import static org.junit.Assert.assertEquals;

public class ElasticsearchVisitorTest extends QBuilderTestBase<AnterosElasticsearchVisitor, QueryBuilder, AnterosElasticsearchVisitor.Context> {

    public ElasticsearchVisitorTest() {

        Enum_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myEnum\" : {\n" + 
        		"      \"value\" : \"VALUE1\",\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Enum_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myEnum\" : {\n" + 
        		"            \"value\" : \"VALUE1\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Enum_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myEnum\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Enum_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myEnum\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Enum_IN = "{\n" + 
        		"  \"terms\" : {\n" + 
        		"    \"myEnum\" : [\n" + 
        		"      \"VALUE1\",\n" + 
        		"      \"VALUE2\",\n" + 
        		"      \"VALUE3\"\n" + 
        		"    ],\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Enum_NIN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myEnum\" : [\n" + 
        		"            \"VALUE1\",\n" + 
        		"            \"VALUE2\",\n" + 
        		"            \"VALUE3\"\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        String_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myString\" : {\n" + 
        		"      \"value\" : \"abcdefg\",\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        String_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myString\" : {\n" + 
        		"            \"value\" : \"abcdefg\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        String_LT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myString\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : \"abcdefg\",\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        String_LTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myString\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : \"abcdefg\",\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        String_GT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myString\" : {\n" + 
        		"      \"from\" : \"abcdefg\",\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : false,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        String_GTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myString\" : {\n" + 
        		"      \"from\" : \"abcdefg\",\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        String_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myString\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        String_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myString\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        String_IN = "{\n" + 
        		"  \"terms\" : {\n" + 
        		"    \"myString\" : [\n" + 
        		"      \"a\",\n" + 
        		"      \"b\",\n" + 
        		"      \"c\"\n" + 
        		"    ],\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        String_NIN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myString\" : [\n" + 
        		"            \"d\",\n" + 
        		"            \"e\",\n" + 
        		"            \"f\"\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        String_RE = "{\n" + 
        		"  \"regexp\" : {\n" + 
        		"    \"myString\" : {\n" + 
        		"      \"value\" : \"(abc|def)\",\n" + 
        		"      \"flags_value\" : 65535,\n" + 
        		"      \"max_determinized_states\" : 10000,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Boolean_TRUE = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myBoolean\" : {\n" + 
        		"      \"value\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Boolean_FALSE = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myBoolean\" : {\n" + 
        		"      \"value\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Boolean_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myBoolean\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Boolean_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myBoolean\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Short_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myShort\" : {\n" + 
        		"      \"value\" : 100,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Short_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myShort\" : {\n" + 
        		"            \"value\" : 100,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Short_LT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myShort\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Short_GT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myShort\" : {\n" + 
        		"      \"from\" : 100,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : false,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Short_LTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myShort\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Short_GTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myShort\" : {\n" + 
        		"      \"from\" : 100,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Short_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myShort\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Short_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myShort\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Short_IN = "{\n" + 
        		"  \"terms\" : {\n" + 
        		"    \"myShort\" : [\n" + 
        		"      98,\n" + 
        		"      99,\n" + 
        		"      100\n" + 
        		"    ],\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Short_NIN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myShort\" : [\n" + 
        		"            101,\n" + 
        		"            102,\n" + 
        		"            103\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Integer_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myInteger\" : {\n" + 
        		"      \"value\" : 100,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Integer_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myInteger\" : {\n" + 
        		"            \"value\" : 100,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Integer_LT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myInteger\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Integer_GT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myInteger\" : {\n" + 
        		"      \"from\" : 100,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : false,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Integer_LTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myInteger\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Integer_GTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myInteger\" : {\n" + 
        		"      \"from\" : 100,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Integer_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myInteger\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Integer_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myInteger\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Integer_IN = "{\n" + 
        		"  \"terms\" : {\n" + 
        		"    \"myInteger\" : [\n" + 
        		"      98,\n" + 
        		"      99,\n" + 
        		"      100\n" + 
        		"    ],\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Integer_NIN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myInteger\" : [\n" + 
        		"            101,\n" + 
        		"            102,\n" + 
        		"            103\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Long_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myLong\" : {\n" + 
        		"      \"value\" : 100,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Long_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myLong\" : {\n" + 
        		"            \"value\" : 100,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Long_LT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myLong\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Long_GT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myLong\" : {\n" + 
        		"      \"from\" : 100,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : false,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Long_LTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myLong\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Long_GTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myLong\" : {\n" + 
        		"      \"from\" : 100,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Long_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myLong\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Long_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myLong\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Long_IN = "{\n" + 
        		"  \"terms\" : {\n" + 
        		"    \"myLong\" : [\n" + 
        		"      98,\n" + 
        		"      99,\n" + 
        		"      100\n" + 
        		"    ],\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Long_NIN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myLong\" : [\n" + 
        		"            101,\n" + 
        		"            102,\n" + 
        		"            103\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Float_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myFloat\" : {\n" + 
        		"      \"value\" : 100.0,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Float_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myFloat\" : {\n" + 
        		"            \"value\" : 100.0,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Float_LT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myFloat\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100.0,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Float_GT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myFloat\" : {\n" + 
        		"      \"from\" : 100.0,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : false,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Float_LTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myFloat\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100.0,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Float_GTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myFloat\" : {\n" + 
        		"      \"from\" : 100.0,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Float_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myFloat\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Float_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myFloat\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Float_IN = "{\n" + 
        		"  \"terms\" : {\n" + 
        		"    \"myFloat\" : [\n" + 
        		"      98.0,\n" + 
        		"      99.0,\n" + 
        		"      100.0\n" + 
        		"    ],\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Float_NIN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myFloat\" : [\n" + 
        		"            101.0,\n" + 
        		"            102.0,\n" + 
        		"            103.0\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Double_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myDouble\" : {\n" + 
        		"      \"value\" : 100.0,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Double_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myDouble\" : {\n" + 
        		"            \"value\" : 100.0,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Double_LT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDouble\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100.0,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Double_GT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDouble\" : {\n" + 
        		"      \"from\" : 100.0,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : false,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Double_LTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDouble\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : 100.0,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Double_GTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDouble\" : {\n" + 
        		"      \"from\" : 100.0,\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        Double_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myDouble\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Double_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myDouble\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Double_IN = "{\n" + 
        		"  \"terms\" : {\n" + 
        		"    \"myDouble\" : [\n" + 
        		"      98.0,\n" + 
        		"      99.0,\n" + 
        		"      100.0\n" + 
        		"    ],\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        Double_NIN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myDouble\" : [\n" + 
        		"            101.0,\n" + 
        		"            102.0,\n" + 
        		"            103.0\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        DateTime_EQ = "{\n" + 
        		"  \"term\" : {\n" + 
        		"    \"myDateTime\" : {\n" + 
        		"      \"value\" : \"1970-01-01T00:00:00.000Z\",\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";


        DateTime_NE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myDateTime\" : {\n" + 
        		"            \"value\" : \"1970-01-01T00:00:00.000Z\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        DateTime_LT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDateTime\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : \"1971-01-01T00:00:00.000Z\",\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : false,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        DateTime_LTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDateTime\" : {\n" + 
        		"      \"from\" : null,\n" + 
        		"      \"to\" : \"1971-01-01T00:00:00.000Z\",\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        DateTime_GT = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDateTime\" : {\n" + 
        		"      \"from\" : \"1970-01-01T00:00:00.000Z\",\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : false,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        DateTime_GTE = "{\n" + 
        		"  \"range\" : {\n" + 
        		"    \"myDateTime\" : {\n" + 
        		"      \"from\" : \"1970-01-01T00:00:00.000Z\",\n" + 
        		"      \"to\" : null,\n" + 
        		"      \"include_lower\" : true,\n" + 
        		"      \"include_upper\" : true,\n" + 
        		"      \"boost\" : 1.0\n" + 
        		"    }\n" + 
        		"  }\n" + 
        		"}";

        DateTime_EX = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myDateTime\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        DateTime_DNE = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myDateTime\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        DateTime_BETWEEN = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myDateTime\" : {\n" + 
        		"            \"from\" : \"1970-01-01T00:00:00.000Z\",\n" + 
        		"            \"to\" : null,\n" + 
        		"            \"include_lower\" : true,\n" + 
        		"            \"include_upper\" : true,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myDateTime\" : {\n" + 
        		"            \"from\" : null,\n" + 
        		"            \"to\" : \"1971-01-01T00:00:00.000Z\",\n" + 
        		"            \"include_lower\" : true,\n" + 
        		"            \"include_upper\" : true,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        INLINE_ANDING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myString\" : {\n" + 
        		"            \"value\" : \"Thing\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myLong\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        INLINE_ORING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"should\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myString\" : {\n" + 
        		"            \"value\" : \"Thing\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myLong\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        LIST_ANDING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myString\" : {\n" + 
        		"            \"value\" : \"Thing\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myLong\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        LIST_ORING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"should\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myString\" : {\n" + 
        		"            \"value\" : \"Thing\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myLong\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";


        LIST_ORING_OF_INLINE_ANDING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"should\" : [\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must\" : [\n" + 
        		"            {\n" + 
        		"              \"term\" : {\n" + 
        		"                \"myString\" : {\n" + 
        		"                  \"value\" : \"Thing\",\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must_not\" : [\n" + 
        		"                  {\n" + 
        		"                    \"exists\" : {\n" + 
        		"                      \"field\" : \"myLong\",\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must_not\" : [\n" + 
        		"                  {\n" + 
        		"                    \"term\" : {\n" + 
        		"                      \"myString\" : {\n" + 
        		"                        \"value\" : \"Cats\",\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"range\" : {\n" + 
        		"                \"myLong\" : {\n" + 
        		"                  \"from\" : 30,\n" + 
        		"                  \"to\" : null,\n" + 
        		"                  \"include_lower\" : false,\n" + 
        		"                  \"include_upper\" : true,\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        LIST_ANDING_OF_INLINE_ORING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"should\" : [\n" + 
        		"            {\n" + 
        		"              \"term\" : {\n" + 
        		"                \"myString\" : {\n" + 
        		"                  \"value\" : \"Thing\",\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must_not\" : [\n" + 
        		"                  {\n" + 
        		"                    \"exists\" : {\n" + 
        		"                      \"field\" : \"myLong\",\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"should\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must_not\" : [\n" + 
        		"                  {\n" + 
        		"                    \"term\" : {\n" + 
        		"                      \"myString\" : {\n" + 
        		"                        \"value\" : \"Cats\",\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"range\" : {\n" + 
        		"                \"myLong\" : {\n" + 
        		"                  \"from\" : 30,\n" + 
        		"                  \"to\" : null,\n" + 
        		"                  \"include_lower\" : false,\n" + 
        		"                  \"include_upper\" : true,\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        LIST_ANDING_OR_LIST_ORING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"should\" : [\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"should\" : [\n" + 
        		"                  {\n" + 
        		"                    \"term\" : {\n" + 
        		"                      \"myString\" : {\n" + 
        		"                        \"value\" : \"Thing\",\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"exists\" : {\n" + 
        		"                            \"field\" : \"myLong\",\n" + 
        		"                            \"boost\" : 1.0\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"should\" : [\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"term\" : {\n" + 
        		"                            \"myString\" : {\n" + 
        		"                              \"value\" : \"Cats\",\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"range\" : {\n" + 
        		"                      \"myLong\" : {\n" + 
        		"                        \"from\" : 30,\n" + 
        		"                        \"to\" : null,\n" + 
        		"                        \"include_lower\" : false,\n" + 
        		"                        \"include_upper\" : true,\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"should\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must\" : [\n" + 
        		"                  {\n" + 
        		"                    \"term\" : {\n" + 
        		"                      \"myString\" : {\n" + 
        		"                        \"value\" : \"Thing\",\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"exists\" : {\n" + 
        		"                            \"field\" : \"myLong\",\n" + 
        		"                            \"boost\" : 1.0\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must\" : [\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"term\" : {\n" + 
        		"                            \"myString\" : {\n" + 
        		"                              \"value\" : \"Cats\",\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"range\" : {\n" + 
        		"                      \"myLong\" : {\n" + 
        		"                        \"from\" : 30,\n" + 
        		"                        \"to\" : null,\n" + 
        		"                        \"include_lower\" : false,\n" + 
        		"                        \"include_upper\" : true,\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        LIST_ORING_AND_LIST_ANDING = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"should\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must\" : [\n" + 
        		"                  {\n" + 
        		"                    \"term\" : {\n" + 
        		"                      \"myString\" : {\n" + 
        		"                        \"value\" : \"Thing\",\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"exists\" : {\n" + 
        		"                            \"field\" : \"myLong\",\n" + 
        		"                            \"boost\" : 1.0\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must\" : [\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"term\" : {\n" + 
        		"                            \"myString\" : {\n" + 
        		"                              \"value\" : \"Cats\",\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"range\" : {\n" + 
        		"                      \"myLong\" : {\n" + 
        		"                        \"from\" : 30,\n" + 
        		"                        \"to\" : null,\n" + 
        		"                        \"include_lower\" : false,\n" + 
        		"                        \"include_upper\" : true,\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"should\" : [\n" + 
        		"                  {\n" + 
        		"                    \"term\" : {\n" + 
        		"                      \"myString\" : {\n" + 
        		"                        \"value\" : \"Thing\",\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"exists\" : {\n" + 
        		"                            \"field\" : \"myLong\",\n" + 
        		"                            \"boost\" : 1.0\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"should\" : [\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must_not\" : [\n" + 
        		"                        {\n" + 
        		"                          \"term\" : {\n" + 
        		"                            \"myString\" : {\n" + 
        		"                              \"value\" : \"Cats\",\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"range\" : {\n" + 
        		"                      \"myLong\" : {\n" + 
        		"                        \"from\" : 30,\n" + 
        		"                        \"to\" : null,\n" + 
        		"                        \"include_lower\" : false,\n" + 
        		"                        \"include_upper\" : true,\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        CHAINED_ORS = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"should\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myString\" : {\n" + 
        		"            \"value\" : \"thing\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myInteger\" : {\n" + 
        		"            \"from\" : 0,\n" + 
        		"            \"to\" : null,\n" + 
        		"            \"include_lower\" : false,\n" + 
        		"            \"include_upper\" : true,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myInteger\" : {\n" + 
        		"            \"from\" : null,\n" + 
        		"            \"to\" : 5,\n" + 
        		"            \"include_lower\" : true,\n" + 
        		"            \"include_upper\" : false,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myLong\" : [\n" + 
        		"            0,\n" + 
        		"            1,\n" + 
        		"            2\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myDouble\" : {\n" + 
        		"            \"from\" : null,\n" + 
        		"            \"to\" : 2.9,\n" + 
        		"            \"include_lower\" : true,\n" + 
        		"            \"include_upper\" : true,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myBoolean\" : {\n" + 
        		"            \"value\" : false,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myDateTime\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        CHAINED_ANDS_AND_ORS = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"should\" : [\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"should\" : [\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"must\" : [\n" + 
        		"                        {\n" + 
        		"                          \"term\" : {\n" + 
        		"                            \"myString\" : {\n" + 
        		"                              \"value\" : \"thing\",\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        },\n" + 
        		"                        {\n" + 
        		"                          \"range\" : {\n" + 
        		"                            \"myInteger\" : {\n" + 
        		"                              \"from\" : 0,\n" + 
        		"                              \"to\" : null,\n" + 
        		"                              \"include_lower\" : false,\n" + 
        		"                              \"include_upper\" : true,\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"range\" : {\n" + 
        		"                      \"myInteger\" : {\n" + 
        		"                        \"from\" : null,\n" + 
        		"                        \"to\" : 5,\n" + 
        		"                        \"include_lower\" : true,\n" + 
        		"                        \"include_upper\" : false,\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"terms\" : {\n" + 
        		"                      \"myLong\" : [\n" + 
        		"                        0,\n" + 
        		"                        1,\n" + 
        		"                        2\n" + 
        		"                      ],\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"range\" : {\n" + 
        		"                \"myDouble\" : {\n" + 
        		"                  \"from\" : null,\n" + 
        		"                  \"to\" : 2.9,\n" + 
        		"                  \"include_lower\" : true,\n" + 
        		"                  \"include_upper\" : true,\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"term\" : {\n" + 
        		"                \"myBoolean\" : {\n" + 
        		"                  \"value\" : false,\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myDateTime\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        CHAINED_ANDS = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myString\" : {\n" + 
        		"            \"value\" : \"thing\",\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myInteger\" : {\n" + 
        		"            \"from\" : 0,\n" + 
        		"            \"to\" : null,\n" + 
        		"            \"include_lower\" : false,\n" + 
        		"            \"include_upper\" : true,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myInteger\" : {\n" + 
        		"            \"from\" : null,\n" + 
        		"            \"to\" : 5,\n" + 
        		"            \"include_lower\" : true,\n" + 
        		"            \"include_upper\" : false,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"terms\" : {\n" + 
        		"          \"myLong\" : [\n" + 
        		"            0,\n" + 
        		"            1,\n" + 
        		"            2\n" + 
        		"          ],\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"range\" : {\n" + 
        		"          \"myDouble\" : {\n" + 
        		"            \"from\" : null,\n" + 
        		"            \"to\" : 2.9,\n" + 
        		"            \"include_lower\" : true,\n" + 
        		"            \"include_upper\" : true,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myBoolean\" : {\n" + 
        		"            \"value\" : false,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myDateTime\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        CHAINED_ORS_AND_ANDS = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"should\" : [\n" + 
        		"            {\n" + 
        		"              \"bool\" : {\n" + 
        		"                \"must\" : [\n" + 
        		"                  {\n" + 
        		"                    \"bool\" : {\n" + 
        		"                      \"should\" : [\n" + 
        		"                        {\n" + 
        		"                          \"term\" : {\n" + 
        		"                            \"myString\" : {\n" + 
        		"                              \"value\" : \"thing\",\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        },\n" + 
        		"                        {\n" + 
        		"                          \"range\" : {\n" + 
        		"                            \"myInteger\" : {\n" + 
        		"                              \"from\" : 0,\n" + 
        		"                              \"to\" : null,\n" + 
        		"                              \"include_lower\" : false,\n" + 
        		"                              \"include_upper\" : true,\n" + 
        		"                              \"boost\" : 1.0\n" + 
        		"                            }\n" + 
        		"                          }\n" + 
        		"                        }\n" + 
        		"                      ],\n" + 
        		"                      \"adjust_pure_negative\" : true,\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"range\" : {\n" + 
        		"                      \"myInteger\" : {\n" + 
        		"                        \"from\" : null,\n" + 
        		"                        \"to\" : 5,\n" + 
        		"                        \"include_lower\" : true,\n" + 
        		"                        \"include_upper\" : false,\n" + 
        		"                        \"boost\" : 1.0\n" + 
        		"                      }\n" + 
        		"                    }\n" + 
        		"                  },\n" + 
        		"                  {\n" + 
        		"                    \"terms\" : {\n" + 
        		"                      \"myLong\" : [\n" + 
        		"                        0,\n" + 
        		"                        1,\n" + 
        		"                        2\n" + 
        		"                      ],\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                ],\n" + 
        		"                \"adjust_pure_negative\" : true,\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"range\" : {\n" + 
        		"                \"myDouble\" : {\n" + 
        		"                  \"from\" : null,\n" + 
        		"                  \"to\" : 2.9,\n" + 
        		"                  \"include_lower\" : true,\n" + 
        		"                  \"include_upper\" : true,\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            },\n" + 
        		"            {\n" + 
        		"              \"term\" : {\n" + 
        		"                \"myBoolean\" : {\n" + 
        		"                  \"value\" : false,\n" + 
        		"                  \"boost\" : 1.0\n" + 
        		"                }\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"bool\" : {\n" + 
        		"          \"must_not\" : [\n" + 
        		"            {\n" + 
        		"              \"exists\" : {\n" + 
        		"                \"field\" : \"myDateTime\",\n" + 
        		"                \"boost\" : 1.0\n" + 
        		"              }\n" + 
        		"            }\n" + 
        		"          ],\n" + 
        		"          \"adjust_pure_negative\" : true,\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";


        SUB_QUERY = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must\" : [\n" + 
        		"      {\n" + 
        		"        \"nested\" : {\n" + 
        		"          \"query\" : {\n" + 
        		"            \"bool\" : {\n" + 
        		"              \"must\" : [\n" + 
        		"                {\n" + 
        		"                  \"term\" : {\n" + 
        		"                    \"mySubList.myString\" : {\n" + 
        		"                      \"value\" : \"Thing\",\n" + 
        		"                      \"boost\" : 1.0\n" + 
        		"                    }\n" + 
        		"                  }\n" + 
        		"                },\n" + 
        		"                {\n" + 
        		"                  \"bool\" : {\n" + 
        		"                    \"must_not\" : [\n" + 
        		"                      {\n" + 
        		"                        \"exists\" : {\n" + 
        		"                          \"field\" : \"mySubList.myLong\",\n" + 
        		"                          \"boost\" : 1.0\n" + 
        		"                        }\n" + 
        		"                      }\n" + 
        		"                    ],\n" + 
        		"                    \"adjust_pure_negative\" : true,\n" + 
        		"                    \"boost\" : 1.0\n" + 
        		"                  }\n" + 
        		"                }\n" + 
        		"              ],\n" + 
        		"              \"adjust_pure_negative\" : true,\n" + 
        		"              \"boost\" : 1.0\n" + 
        		"            }\n" + 
        		"          },\n" + 
        		"          \"path\" : \"mySubList\",\n" + 
        		"          \"ignore_unmapped\" : false,\n" + 
        		"          \"score_mode\" : \"none\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      },\n" + 
        		"      {\n" + 
        		"        \"term\" : {\n" + 
        		"          \"myBoolean\" : {\n" + 
        		"            \"value\" : true,\n" + 
        		"            \"boost\" : 1.0\n" + 
        		"          }\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";


        NULL_EQUALITY = "{\n" + 
        		"  \"bool\" : {\n" + 
        		"    \"must_not\" : [\n" + 
        		"      {\n" + 
        		"        \"exists\" : {\n" + 
        		"          \"field\" : \"myString\",\n" + 
        		"          \"boost\" : 1.0\n" + 
        		"        }\n" + 
        		"      }\n" + 
        		"    ],\n" + 
        		"    \"adjust_pure_negative\" : true,\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";

        NULL_INEQUALITY = "{\n" + 
        		"  \"exists\" : {\n" + 
        		"    \"field\" : \"myString\",\n" + 
        		"    \"boost\" : 1.0\n" + 
        		"  }\n" + 
        		"}";
    }


    @Override
    protected AnterosElasticsearchVisitor getVisitor() {
        return new AnterosElasticsearchVisitor();
    }

    @Override
    protected AnterosElasticsearchVisitor.Context getContext() {
        return new AnterosElasticsearchVisitor.Context();
    }

    @Override
    protected void compare(String expected, org.elasticsearch.index.query.QueryBuilder converted) {
        assertEquals(expected, converted.toString());
    }

}
