package org.fjellstad.functional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;
import static org.fjellstad.functional.ListFunctionBuilder1.create;

public class TestListFunctionBuilder1 {

    private final Logger logger = LoggerFactory.getLogger(TestListFunctionBuilder1.class);
    BiFunction<Integer, Integer, Integer> PLUS = new BiFunction<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer input0, Integer input1) {
            return input0 + input1;
        }
    };
    Function<String, String> PRINTER = new Function<String, String>() {
        @Override
        public String apply(String input) {
            logger.info("{}:{}", input.getClass().getSimpleName(), input);
            return input;
        }
    };

    @Test
    public void reduceShouldReturnCorrectedValue() {
        int expected = 6;

        List<Integer> input = newArrayList(1,2,3);

        ListFunctionBuilder1<Integer, Integer> listBuilder = create(input);
        int result = listBuilder.reduce(PLUS, 0);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void mapAndReduction() {
        Integer expected = 6;

        Function<String, Integer> STRING_TO_INT = new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.valueOf(input);
            }
        };
        Function<Integer, String> INTEGER_TO_STRING = new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return input.toString();
            }
        };
        Function<Double, Integer> DOUBLE_TO_INT = new Function<Double, Integer>() {
            @Override
            public Integer apply(Double input) {
                return input.intValue();
            }
        };

        List<Double> list = newArrayList(1.54, 2.2342, 3.4565);

        Integer result = create(list)
                .map(DOUBLE_TO_INT)
                .map(INTEGER_TO_STRING)
                .map(PRINTER)
                .map(STRING_TO_INT)
                .reduce(PLUS, 0);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void filterShouldRemoveNonMatching() {
        List<Integer> expected = newArrayList(2,4,6);

        Predicate<Integer> EVENS = new Predicate<Integer>() {
            @Override
            public boolean test(Integer input) {
                return input != null && input % 2 == 0;
            }
        };

        List<Integer> list = newArrayList(1,2,3,4,5,6);

        List<Integer> result = create(list).filter(EVENS).get();

        logger.info(result.toString());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void rejectShouldRemoveMatching() {
        List<Integer> expected = newArrayList(1,3,5);

        Predicate<Integer> EVENS = new Predicate<Integer>() {
            @Override
            public boolean test(Integer input) {
                return input == null || input % 2 == 0;
            }
        };

        List<Integer> list = newArrayList(1,2,3,null,4,5,6);

        List<Integer> result = create(list).reject(EVENS).get();

        logger.info(result.toString());

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldRemoveDuplicates() {
        List<Integer> expected = newArrayList(1,2,3,4);

        List<Integer> list = newArrayList(1, 2, 2, 3, 4, 3);

        List<Integer> result = create(list).unique().get();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldSortCorrectly() {
        List<Integer> expected = newArrayList(1,2,3,4);
        Comparator<Integer> COMP = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        List<Integer> list = newArrayList(1,4,3,2);
        List<Integer> result = create(list).sort(COMP).get();

        assertThat(result).isEqualTo(expected);
        assertThat(result).isNotEqualTo(list);
    }
}
