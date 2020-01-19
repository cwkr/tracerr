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
package de.cwkr.validation.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import static de.cwkr.validation.util.ValidCollectionUtils.listOf;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ErrorsTest {
    @Test
    public void getErrors() {
        Errors errorTrace = new Errors(listOf("one", "two"));
        List<String> errors = errorTrace.getErrors();
        assertTrue(errorTrace.hasErrors());
        assertNotNull(errors);
        assertEquals(2, errors.size());
    }

    @Test
    public void reset() {
        Errors errors = new Errors(listOf("one", "two"));
        assertTrue(errors.hasErrors());
        errors.reset();
        assertEquals(0, errors.countErrors());
    }

    @Test
    public void addErrors() {
        Errors errors = new Errors();
        errors.addErrors("one", "two", "three");
        assertTrue(errors.hasErrors());
        assertEquals(3, errors.countErrors());
    }

    @Test
    public void logErrors(@Mock Logger logger) {
        Errors errors = new Errors(listOf("one", "two", "three"));
        errors.logErrors(logger);
        verify(logger, times(3)).error(anyString());
    }

    @Test
    public void throwErrors() {
        Errors errors = new Errors(listOf("one", "two", "three"));
        assertThrows(CustomException.class, () -> errors.throwErrors(CustomException::new, "There were {} errors", errors
            .countErrors()));
    }

    @Test
    public void interator() {
        int count = 0;
        Errors errors = new Errors(listOf("one", "two", "three"));
        for(String error: errors) {
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    public void toStringTest() {
        Errors errors = new Errors(listOf("one", "two"));
        assertTrue(StringUtils.contains(errors.toString(), "[one, two]"));
    }

    @Test
    public void equalsTest() {
        Errors errorsOne = new Errors(listOf("one", "two"));
        Errors errorsTwo = new Errors(listOf("one", "two"));
        Errors errors = new Errors();
        errors.addErrors(errorsOne);
        errors.addError("three");

        Set<Errors> errorsSet = new HashSet<>();
        errorsSet.add(errorsOne);
        errorsSet.add(errorsTwo);
        errorsSet.add(errors);

        assertEquals(errorsOne, errorsTwo);
        assertNotEquals(errorsSet, errorsOne);
        assertEquals(2, errorsSet.size());
    }

    @Test
    public void isNull() {
        Errors errors = new Errors();
        Object obj = null;
        errors.isNull(obj, "obj must be null: {}", obj);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isNull_error() {
        Errors errors = new Errors();
        Object obj = "Test";
        errors.isNull(obj, "obj must be null: {}", obj);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isNotNull() {
        Errors errors = new Errors();
        Object obj = "Test";
        errors.isNotNull(obj, "obj must not be null: {}", obj);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isNotNull_error() {
        Errors errors = new Errors();
        Object obj = null;
        errors.isNotNull(obj, "obj must not be null: {}", obj);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isEmpty() {
        Errors errors = new Errors();
        String str = "";
        errors.isEmpty(str, "obj must be empty: {}", str);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isEmpty_error() {
        Errors errors = new Errors();
        String str = "Test";
        errors.isEmpty(str, "obj must be empty: {}", str);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isEmpty_coll() {
        Errors errors = new Errors();
        List<String> list = emptyList();
        errors.isEmpty(list, "obj must be empty: {}", list);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isEmpty_coll_error() {
        Errors errors = new Errors();
        List<String> list = singletonList("Test");
        errors.isEmpty(list, "obj must be empty: {}", list);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isNotEmpty() {
        Errors errors = new Errors();
        String str = "Test";
        errors.isNotEmpty(str, "obj must not be empty: {}", str);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isNotEmpty_error() {
        Errors errors = new Errors();
        String str = "";
        errors.isNotEmpty(str, "obj must not be empty: {}", str);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isNotEmpty_coll() {
        Errors errors = new Errors();
        List<String> list = singletonList("Test");
        errors.isNotEmpty(list, "obj must not be empty: {}", list);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isNotEmpty_coll_error() {
        Errors errors = new Errors();
        List<String> list = emptyList();
        errors.isNotEmpty(list, "obj must not be empty: {}", list);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isBlank() {
        Errors errors = new Errors();
        String str = "    ";
        errors.isBlank(str, "obj must be blank: {}", str);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isBlank_error() {
        Errors errors = new Errors();
        String str = "Test";
        errors.isBlank(str, "obj must be blank: {}", str);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isNotBlank() {
        Errors errors = new Errors();
        String str = "Test";
        errors.isNotBlank(str, "obj must not be blank: {}", str);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isNotBlank_error() {
        Errors errors = new Errors();
        String str = "    ";
        errors.isNotBlank(str, "obj must not be blank: {}", str);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isTrue() {
        Errors errors = new Errors();
        boolean bool = true;
        errors.isTrue(bool, "bool must be true: {}", bool);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isTrue_error() {
        Errors errors = new Errors();
        boolean bool = false;
        errors.isTrue(bool, "bool must be true: {}", bool);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isFalse() {
        Errors errors = new Errors();
        boolean bool = false;
        errors.isFalse(bool, "bool must be false: {}", bool);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isFalse_error() {
        Errors errors = new Errors();
        boolean bool = true;
        errors.isFalse(bool, "bool must be false: {}", bool);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isContainingDuplicates() {
        Errors errors = new Errors();
        List<String> list = listOf("one", "two", "one");
        errors.isContainingDuplicates(list, "must contain duplicates");
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isContainingDuplicates_error() {
        Errors errors = new Errors();
        List<String> list = listOf("one", "two", "three");
        errors.isContainingDuplicates(list, "must contain duplicates");
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isNotContainingDuplicates() {
        Errors errors = new Errors();
        List<String> list = listOf("one", "two", "three");
        errors.isNotContainingDuplicates(list, "must not contain duplicates");
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isNotContainingDuplicates_error() {
        Errors errors = new Errors();
        List<String> list = listOf("one", "two", "one");
        errors.isNotContainingDuplicates(list, "must not contain duplicates");
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isBetween() {
        Errors errors = new Errors();
        errors.isBetween(5, 3, 12, "must be in range 3-12");
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isBetween_error() {
        Errors errors = new Errors();
        errors.isBetween(21, 3, 12, "must be in range 3-12");
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isEqual() {
        Errors errors = new Errors();
        errors.isEqual("Test", "Test", "must be equal");
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isEqual_error() {
        Errors errors = new Errors();
        errors.isEqual("Test", "foobar", "must be equal");
        assertTrue(errors.hasErrors());
    }

    @Test
    public void isNotEqual() {
        Errors errors = new Errors();
        errors.isNotEqual("Test", "foobar", "must not be equal");
        assertFalse(errors.hasErrors());
    }

    @Test
    public void isNotEqual_error() {
        Errors errors = new Errors();
        errors.isNotEqual("Test", "Test", "must not be equal");
        assertTrue(errors.hasErrors());
    }
}
