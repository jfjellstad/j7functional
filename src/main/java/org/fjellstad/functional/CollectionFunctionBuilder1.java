package org.fjellstad.functional;

import java.util.Collection;

public interface CollectionFunctionBuilder1<OUT, IN> extends Function1<Collection<OUT>, Function1<OUT,IN>> {
    <NEXT_OUT> CollectionFunctionBuilder1<NEXT_OUT, NEXT_OUT> map(Function1<NEXT_OUT, IN> func);

    OUT reduce(Function2<OUT, OUT, IN> func, OUT initialValue);

    CollectionFunctionBuilder1<IN, IN> filter(Predicate<IN> predicate);

    CollectionFunctionBuilder1<IN, IN> reject(Predicate<IN> predicate);

    Collection<IN> get();
}
