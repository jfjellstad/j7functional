package org.fjellstad.functional;

import java.util.*;

public class Functional {
    public static <S, T> List<S> forEach(final List<T> list, final Function1<S, T> func) {
        return (List<S>)forEach(list, func, new ArrayList<S>(list.size()));
    }

    private static <S, T> Collection<S> forEach(final Collection<T> coll, final Function1<S, T> func, Collection<S> newCollection) {
        for (T value : coll) {
            if (value != null) {
                newCollection.add(func.apply(value));
            }
        }
        return newCollection;
    }

    public static <S, T> HashSet<S> forEach(final Set<T> set, final Function1<S, T> func) {
        return (HashSet<S>)forEach(set, func, new HashSet<S>(set.size()));
    }

    public static <S, T> TreeSet<S> forEach(final TreeSet<T> set, final Function1<S, T> func) {
        return (TreeSet<S>)forEach(set, func, new TreeSet<S>());
    }

    public static <S, T> S reduce(final Collection<T> coll, final Function2<S, S, T> func, S initialValue) {
        S result = initialValue;
        for (T value : coll) {
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
            if (predicate.apply(value)) {
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
            if (!predicate.apply(value)) {
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
            public boolean apply(T input) {
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