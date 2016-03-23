package org.fjellstad.functional;

public class ConsumerBuilder<IN> implements Consumer<IN> {
    private final Consumer<IN> original;

    public ConsumerBuilder(Consumer<IN> original) {
        this.original = original;
    }

    @Override
    public void accept(IN argument) {
        original.accept(argument);
    }

    public ConsumerBuilder<IN> andThen(final Consumer<? super IN> after) {
        return new ConsumerBuilder<>(new Consumer<IN>() {
            @Override
            public void accept(IN argument) {
                original.accept(argument);
                after.accept(argument);
            }
        });
    }
}
