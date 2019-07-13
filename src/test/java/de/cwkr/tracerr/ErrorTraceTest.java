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
package de.cwkr.tracerr;

import java.util.List;
import static de.cwkr.tracerr.util.Lists.listOf;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

@ExtendWith(MockitoExtension.class)
public class ErrorTraceTest {
    @Test
    public void getErrors() {
        ErrorTrace errorTrace = new ErrorTrace(listOf("one", "two"));
        List<String> errors = errorTrace.getErrors();
        assertTrue(errorTrace.hasErrors());
        assertNotNull(errors);
        assertEquals(2, errors.size());
    }

    @Test
    public void reset() {
        ErrorTrace errorTrace = new ErrorTrace(listOf("one", "two"));
        assertTrue(errorTrace.hasErrors());
        errorTrace.reset();
        assertEquals(0, errorTrace.countErrors());
    }

    @Test
    public void addErrors() {
        ErrorTrace errorTrace = new ErrorTrace();
        errorTrace.addErrors("one", "two", "three");
        assertTrue(errorTrace.hasErrors());
        assertEquals(3, errorTrace.countErrors());
    }

    @Test
    public void logErrors(@Mock Logger logger) {
        ErrorTrace errorTrace = new ErrorTrace(listOf("one", "two", "three"));
        errorTrace.logErrors(logger);
        verify(logger, times(3)).error(anyString());
    }

    @Test
    public void throwErros() {
        ErrorTrace errorTrace = new ErrorTrace(listOf("one", "two", "three"));
        assertThrows(CustomException.class, () -> errorTrace.throwErrors(CustomException::new, "There were {} errors", errorTrace.countErrors()));
    }

    @Test
    public void interator() {
        int count = 0;
        ErrorTrace errorTrace = new ErrorTrace(listOf("one", "two", "three"));
        for(String error: errorTrace) {
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    public void isNull() {
        ErrorTrace errorTrace = new ErrorTrace();
        Object obj = null;
        errorTrace.isNull(obj, "obj must be null: {}", obj);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isNull_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        Object obj = "Test";
        errorTrace.isNull(obj, "obj must be null: {}", obj);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isNotNull() {
        ErrorTrace errorTrace = new ErrorTrace();
        Object obj = "Test";
        errorTrace.isNotNull(obj, "obj must not be null: {}", obj);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isNotNull_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        Object obj = null;
        errorTrace.isNotNull(obj, "obj must not be null: {}", obj);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isEmpty() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "";
        errorTrace.isEmpty(str, "obj must be empty: {}", str);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isEmpty_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "Test";
        errorTrace.isEmpty(str, "obj must be empty: {}", str);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isEmpty_coll() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = emptyList();
        errorTrace.isEmpty(list, "obj must be empty: {}", list);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isEmpty_coll_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = singletonList("Test");
        errorTrace.isEmpty(list, "obj must be empty: {}", list);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isNotEmpty() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "Test";
        errorTrace.isNotEmpty(str, "obj must not be empty: {}", str);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isNotEmpty_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "";
        errorTrace.isNotEmpty(str, "obj must not be empty: {}", str);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isNotEmpty_coll() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = singletonList("Test");
        errorTrace.isNotEmpty(list, "obj must not be empty: {}", list);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isNotEmpty_coll_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = emptyList();
        errorTrace.isNotEmpty(list, "obj must not be empty: {}", list);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isBlank() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "    ";
        errorTrace.isBlank(str, "obj must be blank: {}", str);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isBlank_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "Test";
        errorTrace.isBlank(str, "obj must be blank: {}", str);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isNotBlank() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "Test";
        errorTrace.isNotBlank(str, "obj must not be blank: {}", str);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isNotBlank_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        String str = "    ";
        errorTrace.isNotBlank(str, "obj must not be blank: {}", str);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isTrue() {
        ErrorTrace errorTrace = new ErrorTrace();
        boolean bool = true;
        errorTrace.isTrue(bool, "bool must be true: {}", bool);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isTrue_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        boolean bool = false;
        errorTrace.isTrue(bool, "bool must be true: {}", bool);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isFalse() {
        ErrorTrace errorTrace = new ErrorTrace();
        boolean bool = false;
        errorTrace.isFalse(bool, "bool must be false: {}", bool);
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isFalse_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        boolean bool = true;
        errorTrace.isFalse(bool, "bool must be false: {}", bool);
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isContainingDuplicates() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = listOf("one", "two", "one");
        errorTrace.isContainingDuplicates(list, "must contain duplicates");
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isContainingDuplicates_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = listOf("one", "two", "three");
        errorTrace.isContainingDuplicates(list, "must contain duplicates");
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isNotContainingDuplicates() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = listOf("one", "two", "three");
        errorTrace.isNotContainingDuplicates(list, "must not contain duplicates");
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isNotContainingDuplicates_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        List<String> list = listOf("one", "two", "one");
        errorTrace.isNotContainingDuplicates(list, "must not contain duplicates");
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isBetween() {
        ErrorTrace errorTrace = new ErrorTrace();
        errorTrace.isBetween(5, 3, 12, "must be in range 3-12");
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isBetween_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        errorTrace.isBetween(21, 3, 12, "must be in range 3-12");
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isEqual() {
        ErrorTrace errorTrace = new ErrorTrace();
        errorTrace.isEqual("Test", "Test", "must be equal");
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isEqual_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        errorTrace.isEqual("Test", "foobar", "must be equal");
        assertTrue(errorTrace.hasErrors());
    }

    @Test
    public void isNotEqual() {
        ErrorTrace errorTrace = new ErrorTrace();
        errorTrace.isNotEqual("Test", "foobar", "must not be equal");
        assertFalse(errorTrace.hasErrors());
    }

    @Test
    public void isNotEqual_error() {
        ErrorTrace errorTrace = new ErrorTrace();
        errorTrace.isNotEqual("Test", "Test", "must not be equal");
        assertTrue(errorTrace.hasErrors());
    }
}
