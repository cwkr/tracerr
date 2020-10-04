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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class MapsTests {
    @Test
    public void emptyOrderedMap() {
        Map<String, Object> strings = Maps.emptyOrderedMap();
        assertNotNull(strings);
        assertTrue(strings.isEmpty());
        strings.put("k1", "one");
        assertEquals(1, strings.size());
    }

    @Test
    public void orderedMapOf1() {
        Map<String, Object> map = Maps.orderedMapOf("k1", "one");
        map.put("k2", "two");
        assertEquals(2, map.size());
        assertEquals("one", map.get("k1"));
        assertEquals("two", map.get("k2"));
    }

    @Test
    public void orderedMapOf2() {
        Map<String, LocalTime> map = Maps.orderedMapOf(
            "k1", LocalTime.of(8, 0),
            "k2", LocalTime.of(12, 0)
        );
        map.put("k3", LocalTime.of(15, 0));
        assertEquals(3, map.size());
        assertEquals(LocalTime.of(8, 0), map.get("k1"));
        assertEquals(LocalTime.of(12, 0), map.get("k2"));
    }

    @Test
    public void orderedMapOf3() {
        Map<String, LocalTime> map = Maps.orderedMapOf(
            "k1", LocalTime.of(8, 0),
            "k2", LocalTime.of(12, 0),
            "k3", LocalTime.of(15, 0)
        );
        map.put("k4", LocalTime.of(19, 0));
        assertEquals(4, map.size());
        assertEquals(LocalTime.of(8, 0), map.get("k1"));
        assertEquals(LocalTime.of(12, 0), map.get("k2"));
        assertEquals(LocalTime.of(15, 0), map.get("k3"));
    }

    @Test
    public void orderedMapOfN() {
        Map<String, String> map = Maps.orderedMapOf(
            "k1", "one",
            "k2", "two",
            "k3", "three",
            "k4", "four",
            "k5", "five"
        );
        map.put("k6", "six");
        assertEquals(6, map.size());
        assertEquals("two", map.get("k2"));
    }

    @Test
    public void orderedMapOfEntries1() {
        Map<String, String> map = Maps.orderedMapOfEntries(Maps.entryOf("k1", "one"));
        map.put("k2", "two");
        assertEquals(2, map.size());
        assertEquals("one", map.get("k1"));
        assertEquals("two", map.get("k2"));
    }

    @Test
    public void orderedMapOfEntriesN() {
        Map<String, String> map = Maps.orderedMapOfEntries(
            Maps.entryOf("k1", "one"),
            Maps.entryOf("k2", "two"),
            Maps.entryOf("k3", "three"),
            Maps.entryOf("k4", "four"),
            Maps.entryOf("k5", "five")
        );
        map.put("k6", "six");
        assertEquals(6, map.size());
        assertEquals("two", map.get("k2"));
        assertEquals("four", map.get("k4"));
    }

    @Test
    public void entryOf() {
        LocalDate now = LocalDate.now();
        Map.Entry<String, LocalDate> entry = Maps.entryOf("now", now);
        assertEquals("now", entry.getKey());
        assertEquals(now, entry.getValue());
    }

    @Test
    public void unmodifiableEntryOf() {
        LocalDate now = LocalDate.now();
        Map.Entry<String, LocalDate> entry = Maps.unmodifiableEntryOf("now", now);
        assertEquals("now", entry.getKey());
        assertEquals(now, entry.getValue());
        assertThrows(UnsupportedOperationException.class, () -> {
            entry.setValue(LocalDate.now());
        });
    }

    @Test
    public void unmodifiableMapOf1() {
        Map<String, Object> map = Maps.unmodifiableMapOf("k1", "one");

        assertEquals(1, map.size());
        assertEquals("one", map.get("k1"));
        assertThrows(UnsupportedOperationException.class, () -> {
            map.put("k2", "two");
        });
    }

    @Test
    public void unmodifiableMapOf2() {
        Map<String, LocalTime> map = Maps.unmodifiableMapOf(
            "k1", LocalTime.of(8, 0),
            "k2", LocalTime.of(12, 0)
        );

        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals(LocalTime.of(8, 0), map.get("k1"));
        assertEquals(LocalTime.of(12, 0), map.get("k2"));
        assertThrows(UnsupportedOperationException.class, () -> {
            map.put("k3", LocalTime.of(15, 0));
        });
    }

    @Test
    public void unmodifiableMapOf() {
        Map<String, LocalTime> map = Maps.unmodifiableMapOf(
            "k1", LocalTime.of(8, 0),
            "k2", LocalTime.of(12, 0),
            "k3", LocalTime.of(15, 0)
        );
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(LocalTime.of(8, 0), map.get("k1"));
        assertEquals(LocalTime.of(12, 0), map.get("k2"));
        assertEquals(LocalTime.of(15, 0), map.get("k3"));
        assertThrows(UnsupportedOperationException.class, () -> {
            map.put("k4", LocalTime.of(19, 0));
        });
    }

    @Test
    public void unmodifiableMapOfN() {
        Map<String, String> map = Maps.unmodifiableMapOf(
            "k1", "one",
            "k2", "two",
            "k3", "three",
            "k4", "four",
            "k5", "five"
        );
        assertNotNull(map);
        assertEquals(5, map.size());
        assertEquals("two", map.get("k2"));
        assertThrows(UnsupportedOperationException.class, () -> {
            map.put("k6", "six");
        });
    }

    @Test
    public void unmodifiableMapOfEntries1() {
        Map<String, String> map = Maps.unmodifiableMapOfEntries(Maps.entryOf("k1", "one"));
        assertNotNull(map);
        assertEquals(1, map.size());
        assertEquals("one", map.get("k1"));
        assertThrows(UnsupportedOperationException.class, () -> {
            map.put("k2", "two");
        });
    }

    @Test
    public void unmodifiableMapOfEntriesN() {
        Map<String, String> map = Maps.unmodifiableMapOfEntries(
            Maps.entryOf("k1", "one"),
            Maps.entryOf("k2", "two"),
            Maps.entryOf("k3", "three"),
            Maps.entryOf("k4", "four"),
            Maps.entryOf("k5", "five")
        );

        assertNotNull(map);
        assertEquals(5, map.size());
        assertEquals("two", map.get("k2"));
        assertThrows(UnsupportedOperationException.class, () -> {
            map.put("k6", "six");
        });
    }
}
