package org.fjellstad.functional;

import java.util.Arrays;
import java.util.Collection;

public class Stream<ORIG, NEXT> {
    private final Collection<ORIG> collection;
    private final FunctionBuilder<ORIG, NEXT> original;

    public Stream(Collection<ORIG> collection, FunctionBuilder<ORIG, NEXT> original) {
        this.collection = collection;
        this.original = original;
    }

    public static <ORIG> Stream<ORIG, ORIG> of(Collection<ORIG> collection) {
        return new Stream<>(collection, new FunctionBuilder<>(new Identity<ORIG>()));
    }

    @SafeVarargs
    public static <ORIG> Stream<ORIG, ORIG> of(ORIG... elements) {
        return new Stream<>(Arrays.asList(elements), new FunctionBuilder<>(new Identity<ORIG>()));
    }

    public Stream<ORIG, NEXT> filter(final Predicate<? super NEXT> predicate) {
        return new Stream<>(collection, original.map(new Function<NEXT, NEXT>() {
            @Override
            public NEXT apply(NEXT next) {
                return predicate.test(next) ? next : null;
            }
        }));
    }

    public <NNEXT> Stream<ORIG, NNEXT> map(final Function<? super NEXT, ? extends NNEXT> mapper) {
        return new Stream<>(collection, original.map(mapper));
    }

    public Stream<ORIG, NEXT> peek(final Consumer<NEXT> consumer) {
        return new Stream<>(collection, original.map(new Function<NEXT, NEXT>() {
            @Override
            public NEXT apply(NEXT next) {
                consumer.accept(next);
                return next;
            }
        }));
    }

    public <R extends Collection<NEXT>> R collect(Supplier<R> supplier) {
        R coll = supplier.get();
        for (ORIG elem : collection) {
            NEXT temp = original.apply(elem);
            if (temp != null) {
                coll.add(temp);
            }
        }
        return coll;
    }

    public long count() {
        long c = 0L;
        for (ORIG elem : collection) {
            NEXT temp = original.apply(elem);
            if (temp != null) {
                c++;
            }
        }
        return c;
    }

    public Optional<NEXT> findAny() {
        for (ORIG elem : collection) {
            NEXT temp = original.apply(elem);
            if (temp != null) {
                return Optional.of(temp);
            }
        }
        return Optional.empty();
    }

    public NEXT reduce(NEXT initialValue, BiFunction<NEXT, NEXT, NEXT> accumulator) {
        NEXT value = initialValue;
        for (ORIG elem : collection) {
            NEXT temp = original.apply(elem);
            if (temp != null) {
                value = accumulator.apply(value, temp);
            }
        }
        return value;
    }

    public boolean allMatch(Predicate<? super NEXT> predicate) {
        for (ORIG elem : collection) {
            NEXT temp = original.apply(elem);
            if (temp == null || !predicate.test(temp)) {
                return false;
            }
        }
        return true;
    }

    public boolean anyMatch(Predicate<? super NEXT> predicate) {
        for (ORIG elem : collection) {
            NEXT temp = original.apply(elem);
            if (temp != null && predicate.test(temp)) {
                return true;
            }
        }
        return false;
    }
}
