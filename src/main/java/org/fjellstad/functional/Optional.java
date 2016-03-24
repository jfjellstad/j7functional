package org.fjellstad.functional;

import java.util.Objects;

public final class Optional<T> {
    private final T value;

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }

    public Optional(T value) {
        this.value = value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        if (isPresent()) {
            return value;
        }
        throw new NullPointerException("value is null");
    }

    public T orElse(T other) {
        if (isPresent()) {
            return value;
        } else {
            return other;
        }
    }

    public T orElseGet(Supplier<T> supplier) {
        if (isPresent()) {
            return value;
        } else {
            return supplier.get();
        }
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        if (isPresent()) {
            return of(mapper.apply(value));
        } else {
            return empty();
        }
    }

    public Optional<T> filter(Predicate<T> predicate) {
        if (isPresent() && predicate.test(value)) {
            return of(value);
        } else {
            return empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Optional<?> optional = (Optional<?>) o;
        return Objects.equals(value, optional.value);
    }

    @Override
    public int hashCode() {
        if (isPresent()) {
            return value.hashCode();
        } else {
            return 0;
        }
    }
}
