package org.fjellstad.functional;

import java.util.Arrays;
import java.util.Collection;

public class StreamBuilder<ORIG, NEXT> {
    private final Collection<ORIG> collection;
    private final Function<ORIG, NEXT> original;

    public StreamBuilder(Collection<ORIG> collection, Function<ORIG, NEXT> original) {
        this.collection = collection;
        this.original = original;
    }

    public static <ORIG> StreamBuilder<ORIG, ORIG> of(Collection<ORIG> collection) {
        return new StreamBuilder<>(collection, new Identity<ORIG>());
    }

    @SafeVarargs
    public static <ORIG> StreamBuilder<ORIG, ORIG> of(ORIG... elements) {
        return new StreamBuilder<>(Arrays.asList(elements), new Identity<ORIG>());
    }

    public StreamBuilder<ORIG, NEXT> filter(final Predicate<? super NEXT> predicate) {
        return new StreamBuilder<>(collection, new Function<ORIG, NEXT>() {
            @Override
            public NEXT apply(ORIG arg) {
                NEXT temp = original.apply(arg);
                return temp != null ? predicate.test(temp) ? temp : null : null;
            }
        });
    }

    public <NNEXT> StreamBuilder<ORIG, NNEXT> map(final Function<? super NEXT, ? extends NNEXT> mapper) {
        return new StreamBuilder<>(collection, new Function<ORIG, NNEXT>() {
            @Override
            public NNEXT apply(ORIG orig) {
                NEXT temp = original.apply(orig);
                return temp != null ? mapper.apply(temp) : null;
            }
        });
    }

    public StreamBuilder<ORIG, NEXT> peek(final Consumer<NEXT> consumer) {
        return new StreamBuilder<>(collection, new Function<ORIG, NEXT>() {
            @Override
            public NEXT apply(ORIG arg) {
                NEXT temp = original.apply(arg);
                if (temp != null)
                    consumer.accept(temp);
                return temp;
            }
        });
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
