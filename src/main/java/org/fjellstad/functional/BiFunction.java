package org.fjellstad.functional;

public interface BiFunction<IN1, ARG2, RET> {
    RET apply(IN1 arg1, ARG2 arg2);
}
