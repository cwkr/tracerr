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

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListsTests {
    @Test
    public void listOf1() {
        List<String> list = Lists.listOf(Collections.singleton("one"));
        list.add("two");
        assertEquals(2, list.size());
    }

    @Test
    public void listOf2() {
        List<String> list = Lists.listOf("one", "two");
        list.add("three");
        assertEquals(3, list.size());
    }

    @Test
    public void listOf3() {
        List<String> list = Lists.listOf("one", "two", "three");
        list.add("four");
        assertEquals(4, list.size());
    }

    @Test
    public void listOfNulls() {
        assertThrows(IllegalArgumentException.class, () -> {
            Lists.listOf(null, null);
        });
    }

    @Test
    public void unmodifiableListOf1() {
        List<String> list = Lists.unmodifiableListOf(Collections.singletonList("one"));
        assertEquals(1, list.size());
    }

    @Test
    public void unmodifiableListOf2() {
        List<String> list = Lists.unmodifiableListOf("one", "two");
        assertEquals(2, list.size());
    }

    @Test
    public void unmodifiableListOf3() {
        List<String> list = Lists.unmodifiableListOf("one", "two");
        assertThrows(UnsupportedOperationException.class, () -> list.add("three"));
    }

    @Test
    public void unmodifiableListOfNulls() {
        assertThrows(IllegalArgumentException.class, () -> {
            Lists.unmodifiableListOf(null, null);
        });
    }
}
