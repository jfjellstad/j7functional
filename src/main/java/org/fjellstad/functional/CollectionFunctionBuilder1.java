package org.fjellstad.functional;

import java.util.Collection;

public interface CollectionFunctionBuilder1<IN, OUT> extends Function<Function<IN, OUT>, Collection<OUT>> {
    <NEXT_OUT> CollectionFunctionBuilder1<NEXT_OUT, NEXT_OUT> map(Function<IN, NEXT_OUT> func);

    OUT reduce(BiFunction<OUT, IN, OUT> func, OUT initialValue);

    CollectionFunctionBuilder1<IN, IN> filter(Predicate<IN> predicate);

    CollectionFunctionBuilder1<IN, IN> reject(Predicate<IN> predicate);

    Collection<IN> get();
}
