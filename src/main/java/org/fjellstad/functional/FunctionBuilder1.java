package org.fjellstad.functional;

public class FunctionBuilder1<OUT, IN> implements Function1<OUT, IN> {
    private final Function1<OUT, IN> original;

    public FunctionBuilder1(Function1<OUT, IN> original) {
        this.original = original;
    }

    @Override
    public OUT apply(IN input) {
        return original.apply(input);
    }

    public <OUT1> FunctionBuilder1<OUT1, IN> andThen(final Function1<? extends OUT1, ? super OUT> after) {
        return new FunctionBuilder1<>(new Function1<OUT1, IN>() {
            @Override
            public OUT1 apply(IN input) {
                OUT temp = original.apply(input);
                return temp != null ? after.apply(temp) : null;
            }
        });
    }

    public <OUT1> FunctionBuilder1<OUT, OUT1> compose(final Function1<? extends IN, ? super OUT1> before) {
        return new FunctionBuilder1<>(new Function1<OUT, OUT1>() {
            @Override
            public OUT apply(OUT1 input) {
                IN temp = before.apply(input);
                return temp != null ? original.apply(temp) : null;
            }
        });
    }

    public static <OUT, IN> FunctionBuilder1<OUT, IN> create(final Function1<OUT, IN> func) {
        return new FunctionBuilder1<>(func);
    }
}
