/*
 * Copyright 2020 Christian Winkler.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.cwkr.util;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/**
 * Provides utility methods for {@link Map} instances.
 *
 * @author Christian Winkler
 * @since 1.2.0
 */
public final class Maps {
    private Maps() {
    }

    /**
     * Creates an empty {@link LinkedHashMap}.
     *
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     */
    public static <K, V> Map<K, V> emptyOrderedMap() {
        return new LinkedHashMap<>();
    }

    /**
     * Creates a {@link LinkedHashMap} containing one entry.
     *
     * @param key entry key
     * @param value entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if key is {@code null}
     */
    public static <K, V> Map<K, V> orderedMapOf(final K key, final V value) {
        Validate.notNull(key);
        Map<K, V> map = new LinkedHashMap<>(1);
        map.put(key, value);
        return map;
    }

    /**
     * Creates a {@link LinkedHashMap} containing two entries keeping insertion-order.
     *
     * @param k1 first entry key
     * @param v1 first entry value
     * @param k2 second entry key
     * @param v2 second entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if a key is {@code null}
     */
    public static <K, V> Map<K, V> orderedMapOf(final K k1, final V v1, final K k2, final V v2) {
        Validate.notNull(k1);
        Validate.notNull(k2);
        Map<K, V> map = new LinkedHashMap<>(2);
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    /**
     * Creates a {@link LinkedHashMap} containing three entries keeping insertion-order.
     *
     * @param k1 first entry key
     * @param v1 first entry value
     * @param k2 second entry key
     * @param v2 second entry value
     * @param k3 third entry key
     * @param v3 third entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if a key is {@code null}
     */
    public static <K, V> Map<K, V> orderedMapOf(final K k1, final V v1,
                                                final K k2, final V v2,
                                                final K k3, final V v3) {
        Validate.notNull(k1);
        Validate.notNull(k2);
        Validate.notNull(k3);
        Map<K, V> map = new LinkedHashMap<>(3);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    /**
     * Creates a {@link LinkedHashMap} containing all entries as key-value pairs keeping insertion-order.
     *
     * @param kv key-value pairs
     * @param <T> key and value type
     * @return map instance
     * @throws NullPointerException if kv is {@code null}
     * @throws IllegalArgumentException if a key is {@code null} or the number of key-value elements is not symmetric
     */
    @SafeVarargs
    public static <T> Map<T, T> orderedMapOf(final T... kv) {
        Validate.noNullElements(kv);
        Validate.isTrue(kv.length % 2 == 0, "Number of key-value elements must be symmetric");
        Map<T, T> map = new LinkedHashMap<>(kv.length / 2);
        for (int i = 0; i < kv.length; i += 2) {
            map.put(kv[i], kv[i + 1]);
        }
        return map;
    }

    /**
     * Creates a {@link LinkedHashMap} containing one entry keeping insertion-order.
     *
     * @param entry entry
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if entry or the key is {@code null}
     */
    public static <K, V> Map<K, V> orderedMapOfEntries(final Map.Entry<K, V> entry) {
        Validate.notNull(entry);
        Validate.notNull(entry.getKey());
        Map<K, V> map = new LinkedHashMap<>(1);
        map.put(entry.getKey(), entry.getValue());
        return map;
    }

    /**
     * Creates a {@link LinkedHashMap} containing all entries keeping insertion-order.
     *
     * @param entries entries
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if entry or the key is {@code null}
     */
    @SafeVarargs
    public static <K, V> Map<K, V> orderedMapOfEntries(final Map.Entry<K, V>... entries) {
        Validate.noNullElements(entries);
        Map<K, V> map = new LinkedHashMap<>(entries.length);
        for (Map.Entry<K, V> entry: entries) {
            Validate.notNull(entry.getKey());
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    /**
     * Creates an {@link Map.Entry} containing key and value.
     *
     * @param key entry key
     * @param value entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if key is {@code null}
     */
    public static <K, V> Map.Entry<K, V> entryOf(final K key, final V value) {
        Validate.notNull(key);
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    /**
     * Creates an immutable {@link Map.Entry} containing key and value.
     *
     * @param key entry key
     * @param value entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if key is {@code null}
     */
    public static <K, V> Map.Entry<K, V> unmodifiableEntryOf(final K key, final V value) {
        Validate.notNull(key);
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    /**
     * Creates an immutable {@link Map} containing one entry.
     *
     * @param key entry key
     * @param value entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if key is {@code null}
     */
    public static <K, V> Map<K, V> unmodifiableMapOf(final K key, final V value) {
        Validate.notNull(key);
        return Collections.singletonMap(key, value);
    }

    /**
     * Creates an immutable {@link Map} containing two entries.
     *
     * @param k1 first entry key
     * @param v1 first entry value
     * @param k2 second entry key
     * @param v2 second entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if a key is {@code null}
     */
    public static <K, V> Map<K, V> unmodifiableMapOf(final K k1, final V v1, final K k2, final V v2) {
        return Collections.unmodifiableMap(orderedMapOf(k1, v1, k2, v2));
    }

    /**
     * Creates a {@link LinkedHashMap} containing three entries keeping insertion-order.
     *
     * @param k1 first entry key
     * @param v1 first entry value
     * @param k2 second entry key
     * @param v2 second entry value
     * @param k3 third entry key
     * @param v3 third entry value
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if a key is {@code null}
     */
    public static <K, V> Map<K, V> unmodifiableMapOf(final K k1, final V v1,
                                                     final K k2, final V v2,
                                                     final K k3, final V v3) {
        return Collections.unmodifiableMap(orderedMapOf(k1, v1, k2, v2, k3, v3));
    }

    /**
     * Creates an immutable {@link Map} containing all entries as key-value pairs.
     *
     * @param kv key-value pairs
     * @param <T> key and value type
     * @return map instance
     * @throws NullPointerException if kv is {@code null}
     * @throws IllegalArgumentException if a key is {@code null} or the number of key-value elements is not symmetric
     */
    @SafeVarargs
    public static <T> Map<T, T> unmodifiableMapOf(final T... kv) {
        return Collections.unmodifiableMap(orderedMapOf(kv));
    }

    /**
     * Creates an immutable {@link Map} containing one entry keeping insertion-order.
     *
     * @param entry entry
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if entry or the key is {@code null}
     */
    public static <K, V> Map<K, V> unmodifiableMapOfEntries(final Map.Entry<K, V> entry) {
        Validate.notNull(entry.getKey());
        return Collections.singletonMap(entry.getKey(), entry.getValue());
    }

    /**
     * Creates an immutable {@link Map} containing all entries keeping insertion-order.
     *
     * @param entries entries
     * @param <K> key type
     * @param <V> value type
     * @return map instance
     * @throws NullPointerException if entry or the key is {@code null}
     */
    @SafeVarargs
    public static <K, V> Map<K, V> unmodifiableMapOfEntries(final Map.Entry<K, V>... entries) {
        return Collections.unmodifiableMap(orderedMapOfEntries(entries));
    }
}
