package org.fjellstad.functional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Collectors {
    public static <E> Supplier<List<E>> toList(@SuppressWarnings("UnusedParameters") Class<E> ignore) {
        return new Supplier<List<E>>() {
            @Override
            public List<E> get() {
                return new ArrayList<>();
            }
        };
    }

    public static <E> Supplier<List<E>> toList() {
        return new Supplier<List<E>>() {
            @Override
            public List<E> get() {
                return new ArrayList<>();
            }
        };
    }

    public static <E> Supplier<Set<E>> toSet(@SuppressWarnings("UnusedParameters") Class<E> ignore) {
        return new Supplier<Set<E>>() {
            @Override
            public Set<E> get() {
                return new HashSet<>();
            }
        };
    }
}
