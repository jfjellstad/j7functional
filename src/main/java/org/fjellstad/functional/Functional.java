package org.fjellstad.functional;

import java.util.*;

public class Functional {
    public static <S, T> List<T> forEach(final List<S> list, final Function<? super S, ? extends T> func) {
        return (List<T>)forEach(list, func, new ArrayList<T>(list.size()));
    }

    private static <S, T> Collection<T> forEach(final Collection<S> coll, final Function<? super S, ? extends T> func, Collection<T> newCollection) {
        for (S value : coll) {
            if (value != null) {
                newCollection.add(func.apply(value));
            }
        }
        return newCollection;
    }

    public static <S, T> HashSet<T> forEach(final Set<S> set, final Function<? super S, ? extends T> func) {
        return (HashSet<T>)forEach(set, func, new HashSet<T>(set.size()));
    }

    public static <S, T> TreeSet<T> forEach(final TreeSet<S> set, final Function<? super S, ? extends T> func) {
        return (TreeSet<T>)forEach(set, func, new TreeSet<T>());
    }

    public static <S, T> T reduce(final Collection<S> coll, final BiFunction<T, S, T> func, T initialValue) {
        T result = initialValue;
        for (S value : coll) {
            if (value != null) {
                result = func.apply(result, value);
            }
        }
        return result;
    }

    public static <T> List<T> filter(final List<T> coll, final Predicate<T> predicate) {
        return (List<T>)filter(coll, predicate, new ArrayList<T>());
    }

    private static <T> Collection<T> filter(final Collection<T> coll, final Predicate<T> predicate, Collection<T> newCollection) {
        for (T value : coll) {
            if (predicate.test(value)) {
                newCollection.add(value);
            }
        }

        return newCollection;
    }

    public static <T> List<T> reject(final List<T> coll, final Predicate<T> predicate) {
        return (List<T>)reject(coll, predicate, new ArrayList<T>());
    }

    private static <T> Collection<T> reject(final Collection<T> coll, final Predicate<T> predicate, Collection<T> newCollection) {
        for (T value : coll) {
            if (!predicate.test(value)) {
                newCollection.add(value);
            }
        }

        return newCollection;
    }

    public static <T> Set<T> filter(final Set<T> coll, final Predicate<T> predicate) {
        return (Set<T>)filter(coll, predicate, new HashSet<T>());
    }

    public static <T> Set<T> reject(final Set<T> coll, final Predicate<T> predicate) {
        return (Set<T>)reject(coll, predicate, new HashSet<T>());
    }

    public static <T> TreeSet<T> filter(final TreeSet<T> coll, final Predicate<T> predicate) {
        return (TreeSet<T>)filter(coll, predicate, new TreeSet<T>());
    }

    public static <T> TreeSet<T> reject(final TreeSet<T> coll, final Predicate<T> predicate) {
        return (TreeSet<T>)reject(coll, predicate, new TreeSet<T>());
    }

    private static <T> Collection<T> unique(final Collection<T> coll, final Collection<T> newCollection) {
        return filter(coll, new Predicate<T>() {
            private final Collection<T> tmpColl = newCollection;

            @Override
            public boolean test(T input) {
                return input != null && !tmpColl.contains(input);
            }
        }, newCollection);
    }

    public static <T> List<T> unique(final List<T> coll) {
        return (List<T>)unique(coll, new ArrayList<T>());
    }

    public static <T> List<T> sort(final List<T> list, Comparator<T> comparator) {
        return sort(list, comparator, new ArrayList<T>());
    }

    private static <T> List<T> sort(final List<T> list, Comparator<T> comparator, List<T> newList) {
        newList.addAll(list);
        Collections.sort(newList, comparator);
        return newList;
    }
}