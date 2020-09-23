package br.com.anteros.rsql.builder;



import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MainRsqlBuilder {

    public static void main(String[] args) {
        System.out.println(AnterosRsqlBuilder.rsql().bool("name").isTrue().query());
        System.out.println(AnterosRsqlBuilder.rsql().string("name").neq("t'es là").query());
        System.out.println(AnterosRsqlBuilder.rsql().string("name").eq("gaby").and().openGroup().intNum("age").gt(20).or().intNum("age").lte(40).closeGroup().query());

        System.out.println(AnterosRsqlBuilder.rsql().temporal("date").after(LocalDate.now(), true).query());
        System.out.println(AnterosRsqlBuilder.rsql().temporal("date").before(LocalDateTime.now(), false).query());
        System.out.println(AnterosRsqlBuilder.rsql().temporal("date").after(LocalTime.now(), false).query());
        System.out.println(AnterosRsqlBuilder.rsql().temporal("date").after(Instant.now(), false).query());

        System.out.println(AnterosRsqlBuilder.rsql().string("name").eq("gaby").and().openGroup().openGroup().intNum("age").gt(20).or().intNum("age").lte(40).closeGroup().and().temporal("birthday")
                .after(LocalDate.parse("1970-01-01"), true).and().temporal("birthday").before(LocalDate.parse("1990-05-10"), true).closeGroup().query());

        System.out.println(AnterosRsqlBuilder.rsql().or(AnterosRsqlBuilder.rsql().string("name").eq("gaby"), AnterosRsqlBuilder.rsql().string("name").eq("sandra")).query());
    }
}
