package org.fjellstad.functional;

import java.util.List;

import static org.fjellstad.functional.Functional.forEach;

public class ListFunctionBuilder1<OUT, IN> implements CollectionFunctionBuilder1<OUT, IN> {
    private List<IN> list;

    public ListFunctionBuilder1(List<IN> list) {
        this.list = list;
    }

    @Override
    public List<OUT> apply(Function1<OUT, IN> input) {
        return forEach(list, input);
    }

    @Override
    public <NEXT_OUT> ListFunctionBuilder1<NEXT_OUT, NEXT_OUT> map(final Function1<NEXT_OUT, IN> func) {
        return new ListFunctionBuilder1<>(forEach(list, func));
    }

    @Override
    public OUT reduce(final Function2<OUT, OUT, IN> func, OUT initialValue) {
        return Functional.reduce(list, func, initialValue);
    }

    @Override
    public ListFunctionBuilder1<IN, IN> filter(Predicate<IN> predicate) {
        return new ListFunctionBuilder1<>(Functional.filter(list, predicate));
    }

    @Override
    public ListFunctionBuilder1<IN, IN> reject(Predicate<IN> predicate) {
        return new ListFunctionBuilder1<>(Functional.reject(list, predicate));
    }

    @Override
    public List<IN> get() {
        return list;
    }

    public static <IN> ListFunctionBuilder1<IN, IN> create(List<IN> list) {
        return new ListFunctionBuilder1<>(list);
    }

}
