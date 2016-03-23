package org.fjellstad.functional;

public class Identity<E> implements Function<E, E> {
    @Override
    public E apply(E e) {
        return e;
    }
}
