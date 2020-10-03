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

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SetsTests {
    @Test
    public void orderedSetOf1() {
        Set<String> set = Sets.orderedSetOf(Collections.singleton("one"));
        set.add("two");
        assertEquals(2, set.size());
    }

    @Test
    public void orderedSetOf2() {
        Set<String> set = Sets.orderedSetOf("one", "two");
        set.add("three");
        assertEquals(3, set.size());
    }

    @Test
    public void orderedSetOf3() {
        Set<String> set = Sets.orderedSetOf("one");
        set.add("two");
        set.add("three");
        assertEquals(3, set.size());
    }

    @Test
    public void orderedSetOfNull() {
        assertThrows(NullPointerException.class, () -> {
            Sets.orderedSetOf((String)null);
        });
    }

    @Test
    public void orderedSetOfNulls() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sets.orderedSetOf(null, null);
        });
    }

    @Test
    public void unmodifiableSetOf1() {
        Set<String> set = Sets.unmodifiableSetOf(Collections.singleton("one"));
        assertEquals(1, set.size());
    }

    @Test
    public void unmodifiableSetOf2() {
        Set<String> set = Sets.unmodifiableSetOf("one", "two");
        assertEquals(2, set.size());
    }

    @Test
    public void unmodifiableSetOf3() {
        Set<String> set = Sets.unmodifiableSetOf("one", "two");
        assertThrows(UnsupportedOperationException.class, () -> set.add("three"));
    }

    @Test
    public void unmodifiableSetOf4() {
        Set<String> set = Sets.unmodifiableSetOf("one");
        assertThrows(UnsupportedOperationException.class, () -> set.add("three"));
    }

    @Test
    public void unmodifiableSetOfNull() {
        assertThrows(NullPointerException.class, () -> {
            Sets.unmodifiableSetOf((String)null);
        });
    }

    @Test
    public void unmodifiableSetOfNulls() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sets.unmodifiableSetOf(null, null);
        });
    }
}
