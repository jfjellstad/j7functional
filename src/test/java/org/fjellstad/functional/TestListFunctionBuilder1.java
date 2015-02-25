package org.fjellstad.functional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.fjellstad.functional.ListFunctionBuilder1.create;
import static org.junit.Assert.assertEquals;

public class TestListFunctionBuilder1 {

    private static final Logger LOG = LoggerFactory.getLogger(TestListFunctionBuilder1.class);
    Function2<Integer, Integer, Integer> PLUS = new Function2<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer input0, Integer input1) {
            return input0 + input1;
        }
    };
    Function1<String, String> PRINTER = new Function1<String, String>() {
        @Override
        public String apply(String input) {
            LOG.info("{}:{}", input.getClass().getSimpleName(), input);
            return input;
        }
    };

    @Test
    public void reduceShouldReturnCorrectedValue() {
        int expected = 6;

        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);

        ListFunctionBuilder1<Integer, Integer> listBuilder = create(input);
        int result = listBuilder.reduce(PLUS, 0);

        assertEquals(expected, result);
    }

    @Test
    public void mapAndReduction() {
        Integer expected = 6;

        Function1<Integer, String> TO_INT = new Function1<Integer, String>() {
            @Override
            public Integer apply(String input) {
                return Integer.valueOf(input);
            }
        };
        Function1<String, Integer> TO_STRING = new Function1<String, Integer>() {
            @Override
            public String apply(Integer input) {
                return input.toString();
            }
        };
        Function1<Integer, Double> DOUBLE_TO_INT = new Function1<Integer, Double>() {
            @Override
            public Integer apply(Double input) {
                return input.intValue();
            }
        };

        List<Double> list = newArrayList(1.54, 2.2342, 3.4565);

        Integer result = create(list).map(DOUBLE_TO_INT).map(TO_STRING).map(PRINTER).map(TO_INT).reduce(PLUS, 0);

        assertEquals(expected, result);
    }

    @Test
    public void filterShouldRemoveNonMatching() {
        List<Integer> expected = newArrayList(2,4,6);

        Predicate<Integer> EVENS = new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input != null && input % 2 == 0;
            }
        };

        List<Integer> list = newArrayList(1,2,3,4,5,6);

        List<Integer> result = create(list).filter(EVENS).get();

        LOG.info(result.toString());
        assertEquals(expected, result);
    }

    @Test
    public void rejectShouldRemoveMatching() {
        List<Integer> expected = newArrayList(1,3,5);

        Predicate<Integer> EVENS = new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input == null || input % 2 == 0;
            }
        };

        List<Integer> list = newArrayList(1,2,3,null,4,5,6);

        List<Integer> result = create(list).reject(EVENS).get();

        LOG.info(result.toString());

        assertEquals(expected, result);
    }
}
