package org.fjellstad.functional;

import java.util.HashSet;
import java.util.Set;

public class SetFunctionBuilder1<OUT, IN> implements CollectionFunctionBuilder1<OUT, IN> {
    private Set<IN> set;

    public SetFunctionBuilder1(Set<IN> set) {
        this.set = set;
    }

    @Override
    public <NEXT_OUT> SetFunctionBuilder1<NEXT_OUT, NEXT_OUT> map(Function1<NEXT_OUT, IN> func) {
        return new SetFunctionBuilder1<>(Functional.forEach(set, func));
    }

    @Override
    public OUT reduce(Function2<OUT, OUT, IN> func, OUT initialValue) {
        return Functional.reduce(set, func, initialValue);
    }

    @Override
    public SetFunctionBuilder1<IN, IN> filter(Predicate<IN> predicate) {
        return new SetFunctionBuilder1<>(Functional.filter(set, predicate));
    }

    @Override
    public SetFunctionBuilder1<IN, IN> reject(Predicate<IN> predicate) {
        return new SetFunctionBuilder1<>(Functional.reject(set, predicate));
    }

    @Override
    public Set<IN> get() {
        return set;
    }

    @Override
    public Set<OUT> apply(Function1<OUT, IN> input) {
        return Functional.forEach(set, input, new HashSet<OUT>());
    }

    public static <IN> SetFunctionBuilder1<IN, IN> create(Set<IN> list) {
        return new SetFunctionBuilder1<>(list);
    }
}
