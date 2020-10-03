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

import org.apache.commons.lang3.Validate;

import java.util.*;

/**
 * Provides utility methods for {@link List} instances.
 *
 * @author Christian Winkler
 * @since 1.1.0
 */
public final class Sets {
    private Sets() {
    }

    /**
     * Creates an {@link LinkedHashSet} containing all elements.
     *
     * @param elements elements to add to set
     * @param <T> element type
     * @return set instance
     * @throws NullPointerException if elements is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    @SafeVarargs
    public static <T> Set<T> setOf(final T... elements) {
        Validate.noNullElements(elements);
        Set<T> set = new LinkedHashSet<>(elements.length);
        set.addAll(Arrays.asList(elements));
        return set;
    }

    /**
     * Creates an {@link LinkedHashSet} from a {@link Collection}.
     *
     * @param collection elements
     * @param <T> element type
     * @return set instance
     * @throws NullPointerException if the collection is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    public static <T> Set<T> setOf(final Collection<? extends T> collection) {
        Validate.noNullElements(collection);
        return new LinkedHashSet<>(collection);
    }

    /**
     * Creates an immutable {@link Set} containing all elements.
     *
     * @param elements elements to add to set
     * @param <T> element type
     * @return set instance
     * @throws NullPointerException if elements is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    @SafeVarargs
    public static <T> Set<T> unmodifiableSetOf(final T... elements) {
        return Collections.unmodifiableSet(setOf(elements));
    }

    /**
     * Creates an immutable {@link Set} from a {@link Collection}.
     *
     * @param collection elements
     * @param <T> element type
     * @return set instance
     * @throws NullPointerException if the collection is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    public static <T> Set<T> unmodifiableSetOf(final Collection<? extends T> collection) {
        return Collections.unmodifiableSet(setOf(collection));
    }
}
