package org.fjellstad.functional;

import java.util.*;

public class Functional {
    public static <S, T> List<S> forEach(final List<T> list, final Function1<S, T> func) {
        return forEach(list, func, new ArrayList<S>(list.size()));
    }

    public static <S, T> List<S> forEach(final Collection<T> coll, final Function1<S, T> func, List<S> newList) {
        for (T value : coll) {
            if (value != null) {
                newList.add(func.apply(value));
            }
        }
        return newList;
    }

    public static <S, T> HashSet<S> forEach(final Set<T> set, final Function1<S, T> func) {
        return (HashSet<S>)forEach(set, func, new HashSet<S>(set.size()));
    }

    public static <S, T> TreeSet<S> forEach(final TreeSet<T> set, final Function1<S, T> func) {
        return (TreeSet<S>)forEach(set, func, new TreeSet<S>());
    }

    public static <S, T> Set<S> forEach(final Collection<T> coll, final Function1<S, T> func, Set<S> newSet) {
        for (T value : coll) {
            if (value != null) {
                newSet.add(func.apply(value));
            }
        }
        return newSet;
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
        return filter(coll, predicate, new ArrayList<T>());
    }

    public static <T> List<T> filter(final Collection<T> coll, final Predicate<T> predicate, List<T> newList) {
        for (T value : coll) {
            if (predicate.apply(value)) {
                newList.add(value);
            }
        }

        return newList;
    }

    public static <T> List<T> reject(final List<T> coll, final Predicate<T> predicate) {
        return reject(coll, predicate, new ArrayList<T>());
    }

    public static <T> List<T> reject(final Collection<T> coll, final Predicate<T> predicate, List<T> newList) {
        for (T value : coll) {
            if (!predicate.apply(value)) {
                newList.add(value);
            }
        }

        return newList;
    }

    public static <T> Set<T> filter(final Set<T> coll, final Predicate<T> predicate) {
        return filter(coll, predicate, new HashSet<T>());
    }

    public static <T> Set<T> filter(final Collection<T> coll, final Predicate<T> predicate, Set<T> newSet) {
        for (T value : coll) {
            if (predicate.apply(value)) {
                newSet.add(value);
            }
        }

        return newSet;
    }

    public static <T> Set<T> reject(final Set<T> coll, final Predicate<T> predicate) {
        return reject(coll, predicate, new HashSet<T>());
    }

    public static <T> Set<T> reject(final Collection<T> coll, final Predicate<T> predicate, Set<T> newSet) {
        for (T value : coll) {
            if (!predicate.apply(value)) {
                newSet.add(value);
            }
        }

        return newSet;
    }

    public static <T> TreeSet<T> filter(final TreeSet<T> coll, final Predicate<T> predicate) {
        return filter(coll, predicate, new TreeSet<T>());
    }

    public static <T> TreeSet<T> filter(final Collection<T> coll, final Predicate<T> predicate, TreeSet<T> newSet) {
        for (T value : coll) {
            if (predicate.apply(value)) {
                newSet.add(value);
            }
        }

        return newSet;
    }

    public static <T> TreeSet<T> reject(final TreeSet<T> coll, final Predicate<T> predicate) {
        return reject(coll, predicate, new TreeSet<T>());
    }

    public static <T> TreeSet<T> reject(final Collection<T> coll, final Predicate<T> predicate, TreeSet<T> newSet) {
        for (T value : coll) {
            if (!predicate.apply(value)) {
                newSet.add(value);
            }
        }

        return newSet;
    }
}
