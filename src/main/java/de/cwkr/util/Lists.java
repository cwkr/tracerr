/*
 * Copyright 2017-2020 Christian Winkler.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * Provides utility methods for {@link List} instances.
 *
 * @author Christian Winkler
 */
public final class Lists {
    private Lists() {
    }

    /**
     * Creates an {@link ArrayList} containing one element.
     *
     * @param element element to add to list
     * @param <T> element type
     * @return list instance
     * @throws NullPointerException if element is {@code null}
     * @since 1.1.0
     */
    public static <T> List<T> listOf(final T element) {
        Validate.notNull(element);
        List<T> list = new ArrayList<>(1);
        list.add(element);
        return list;
    }

    /**
     * Creates an {@link ArrayList} containing all elements.
     *
     * @param elements elements to add to list
     * @param <T> element type
     * @return list instance
     * @throws NullPointerException if elements is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    @SafeVarargs
    public static <T> List<T> listOf(final T... elements) {
        Validate.noNullElements(elements);
        List<T> list = new ArrayList<>(elements.length);
        list.addAll(Arrays.asList(elements));
        return list;
    }

    /**
     * Creates an {@link ArrayList} from a {@link Collection}.
     *
     * @param collection elements
     * @param <T> element type
     * @return list instance
     * @throws NullPointerException if the collection is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    public static <T> List<T> listOf(final Collection<? extends T> collection) {
        Validate.noNullElements(collection);
        return new ArrayList<>(collection);
    }

    /**
     * Creates an immutable {@link List} containing one element.
     *
     * @param element element to add to list
     * @param <T> element type
     * @return list instance
     * @throws NullPointerException if element is {@code null}
     * @since 1.1.0
     */
    public static <T> List<T> unmodifiableListOf(final T element) {
        Validate.notNull(element);
        return Collections.singletonList(element);
    }

    /**
     * Creates an immutable {@link List} containing all elements.
     *
     * @param elements elements to add to list
     * @param <T> element type
     * @return list instance
     * @throws NullPointerException if elements is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    @SafeVarargs
    public static <T> List<T> unmodifiableListOf(final T... elements) {
        return Collections.unmodifiableList(listOf(elements));
    }

    /**
     * Creates an immutable {@link List} from a {@link Collection}.
     *
     * @param collection elements
     * @param <T> element type
     * @return list instance
     * @throws NullPointerException if the collection is {@code null}
     * @throws IllegalArgumentException if an element is {@code null}
     */
    public static <T> List<T> unmodifiableListOf(final Collection<? extends T> collection) {
        return Collections.unmodifiableList(listOf(collection));
    }
}
