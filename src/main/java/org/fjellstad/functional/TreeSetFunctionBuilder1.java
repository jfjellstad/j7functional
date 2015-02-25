package org.fjellstad.functional;

import java.util.TreeSet;

public class TreeSetFunctionBuilder1<OUT, IN> implements CollectionFunctionBuilder1<OUT, IN> {
    private TreeSet<IN> set;

    public TreeSetFunctionBuilder1(TreeSet<IN> set) {
        this.set = set;
    }

    @Override
    public <NEXT_OUT> TreeSetFunctionBuilder1<NEXT_OUT, NEXT_OUT> map(Function1<NEXT_OUT, IN> func) {
        return new TreeSetFunctionBuilder1<>(Functional.forEach(set, func));
    }

    @Override
    public OUT reduce(Function2<OUT, OUT, IN> func, OUT initialValue) {
        return Functional.reduce(set,func, initialValue);
    }

    @Override
    public TreeSetFunctionBuilder1<IN, IN> filter(Predicate<IN> predicate) {
        return new TreeSetFunctionBuilder1<>(Functional.filter(set, predicate));
    }

    @Override
    public TreeSetFunctionBuilder1<IN, IN> reject(Predicate<IN> predicate) {
        return new TreeSetFunctionBuilder1<>(Functional.reject(set, predicate));
    }

    @Override
    public TreeSet<IN> get() {
        return set;
    }

    @Override
    public TreeSet<OUT> apply(Function1<OUT, IN> input) {
        return Functional.forEach(set, input);
    }

    public static <IN> TreeSetFunctionBuilder1<IN, IN> create(TreeSet<IN> set) {
        return new TreeSetFunctionBuilder1<>(set);
    }
}
