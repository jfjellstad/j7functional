package org.fjellstad.functional;

public class Identity<IN> implements Function1<IN, IN> {
    @Override
    public IN apply(IN input) {
        return input;
    }
}
