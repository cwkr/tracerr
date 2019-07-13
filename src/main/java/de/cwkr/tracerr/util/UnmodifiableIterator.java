/*
 * Copyright 2019 Christian Winkler.
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

import java.util.Iterator;
import java.util.Objects;

public final class UnmodifiableIterator<E> implements Iterator<E> {
    private final Iterator<? extends E> iterator;

    private UnmodifiableIterator(final Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public static <E> Iterator<E> unmodifiableIterator(final Iterator<? extends E> iterator) {
        Objects.requireNonNull(iterator, "iterator must not be null");
        return new UnmodifiableIterator<>(iterator);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
