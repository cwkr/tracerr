/*
 * Copyright 2017-2018 Christian Winkler.
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
package de.cwkr.tracerr.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Provides utility methods for {@link List} instances.
 *
 * @author Christian Winkler
 */
public final class Lists {
    private Lists() {
    }

    /**
     * Creates an {@link ArrayList} containing all elements.
     *
     * @param elements elements to add to list
     * @param <T> element type
     * @return list instance
     */
    @SafeVarargs
    public static <T> List<T> listOf(final T... elements) {
        List<T> list = new ArrayList<>(elements.length);
        for(T element: elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Creates an {@link ArrayList} from a {@link Collection}.
     *
     * @param collection elements
     * @param <T> element type
     * @return list instance
     */
    public static <T> List<T> listOf(final Collection<? extends T> collection) {
        return new ArrayList<>(collection);
    }

    /**
     * Creates an immutable {@link List} containing all elements.
     *
     * @param elements elements to add to list
     * @param <T> element type
     * @return list instance
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
     */
    public static <T> List<T> unmodifiableListOf(final Collection<? extends T> collection) {
        return Collections.unmodifiableList(listOf(collection));
    }
}
