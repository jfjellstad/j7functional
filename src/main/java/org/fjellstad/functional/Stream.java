package org.fjellstad.functional;

import java.util.Collection;

public interface Stream<IN> {
    <NEXT> Stream<NEXT> map(final Function<? super IN, ? extends NEXT> mapper);
    Stream<IN> filter(final Predicate<? super IN> predicate);
    <COLLECT extends Collection<IN>> COLLECT collect(Supplier<COLLECT> supplier);
}
