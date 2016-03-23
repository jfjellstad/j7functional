package org.fjellstad.functional;

public interface Function0<IN> extends Function<IN, Void> {
    Void apply(IN input);
}
