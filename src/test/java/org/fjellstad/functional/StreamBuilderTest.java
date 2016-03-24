package org.fjellstad.functional;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamBuilderTest {

    private final Logger logger = LoggerFactory.getLogger(StreamBuilderTest.class);

    private Supplier<List<Integer>> IntegerSupplier = new Supplier<List<Integer>>() {
        @Override
        public List<Integer> get() {
            return newArrayList();
        }
    };
    private Supplier<List<String>> StringSupplier = new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            return newArrayList();
        }
    };
    private Consumer<String> PRINTER = new Consumer<String>() {
        @Override
        public void accept(String input) {
            logger.info("{}:{}", input.getClass().getSimpleName(), input);
        }
    };

    @Before
    public void setUp() throws Exception {

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
        Function<Integer, String> TWO = new Function<Integer, String>() {
            @Override
            public String apply(Integer element) {
                return element.toString();
            }
        };

        List<Integer> list = newArrayList(1,2,3,4,5,6);

        List<Integer> result = StreamBuilder.of(list).filter(EVENS).collect(IntegerSupplier);

        logger.info(result.toString());
        assertThat(result).isNotNull().isEqualTo(expected);
    }

    @Test
    public void mapShouldMapToString() {
        List<String> expected = newArrayList("2", "4", "6");

        Predicate<Integer> EVENS = new Predicate<Integer>() {
            @Override
            public boolean test(Integer input) {
                return input != null && input % 2 == 0;
            }
        };

        Function<Integer, String> INT2STR = new Function<Integer, String>() {
            @Override
            public String apply(Integer element) {
                return element.toString();
            }
        };

        List<Integer> list = newArrayList(1,2,3,4,5,6);
        List<String> result = StreamBuilder.of(list).filter(EVENS).map(INT2STR).peek(PRINTER).collect(StringSupplier);

        logger.info("{}", result);
        assertThat(result).isNotNull().isEqualTo(expected);
    }
}
