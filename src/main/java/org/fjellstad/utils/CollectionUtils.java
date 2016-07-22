package org.fjellstad.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class CollectionUtils {
    public static <E> List<E> nullSafeList(List<E> list) {
        return list != null ? list : Collections.<E>emptyList();
    }

    public static <E> Set<E> nullSafeSet(Set<E> set) {
        return set != null ? set : Collections.<E>emptySet();
    }

    public static <E> Collection<E> nullSafeCollection(Collection<E> coll) {
    	return coll != null ? coll : Collections.<E>emptyList();
    }
}
