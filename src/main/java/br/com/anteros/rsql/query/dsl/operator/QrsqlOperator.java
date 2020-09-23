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
package br.com.anteros.rsql.query.dsl.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import br.com.anteros.core.utils.ListUtils;

/**
 * @author Balint Rudas
 */
public class QrsqlOperator {

    private String name;
    private String[] symbols;

    public QrsqlOperator(String symbol) {
        this.symbols = new String[]{symbol};
    }

    public QrsqlOperator(String[] symbols) {
        this.symbols = symbols;
    }

    public QrsqlOperator(String name, String[] symbols) {
        this.name = name;
        this.symbols = symbols;
    }

    public String getName() {
        return name;
    }

    public String[] getSymbols() {
        return symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Operator){
            Operator operator = (Operator) o;
            return !intersection(Arrays.asList(operator.getRsqlOperator()), Arrays.asList(this.symbols)).isEmpty();
        } else if(o instanceof QrsqlOperator){
            QrsqlOperator qrsqlOperator = (QrsqlOperator) o;
            return !intersection(Arrays.asList(qrsqlOperator.getSymbols()), Arrays.asList(this.symbols)).isEmpty();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(symbols);
    }
    
    /**
     * Returns a new list containing all elements that are contained in
     * both given lists.
     *
     * @param <E> the element type
     * @param list1  the first list
     * @param list2  the second list
     * @return  the intersection of those two lists
     * @throws NullPointerException if either list is null
     */
    public static <E> List<E> intersection(final List<? extends E> list1, final List<? extends E> list2) {
        final List<E> result = new ArrayList<>();

        List<? extends E> smaller = list1;
        List<? extends E> larger = list2;
        if (list1.size() > list2.size()) {
            smaller = list2;
            larger = list1;
        }

        final HashSet<E> hashSet = new HashSet<>(smaller);

        for (final E e : larger) {
            if (hashSet.contains(e)) {
                result.add(e);
                hashSet.remove(e);
            }
        }
        return result;
    }
}
