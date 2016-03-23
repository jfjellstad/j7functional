package org.fjellstad.functional;

public class FunctionBuilder<IN, OUT> implements Function<IN, OUT> {

    private final Function<IN, OUT> original;

    public FunctionBuilder(Function<IN, OUT> original) {
        this.original = original;
    }

    @Override
    public OUT apply(IN arg) {
        return arg != null ? original.apply(arg) : null;
    }

    public <NEXT> FunctionBuilder<IN, NEXT> map(final Function<? super OUT, ? extends NEXT> after) {
        return new FunctionBuilder<>(new Function<IN, NEXT>() {
            @Override
            public NEXT apply(IN arg) {
                OUT tmp = original.apply(arg);
                return tmp != null ? after.apply(tmp) : null;
            }
        });
    }
}
