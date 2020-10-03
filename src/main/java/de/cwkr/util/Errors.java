/*
 * Copyright 2019-2020 Christian Winkler.
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

import static de.cwkr.util.UnmodifiableIterator.unmodifiableIterator;
import static java.util.Collections.unmodifiableList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

/**
 * Error collecting container; Thread-safe using {@link CopyOnWriteArrayList}.
 *
 * @author Christian Winkler
 */
public final class Errors implements Iterable<String> {
    private static final Logger logger = LoggerFactory.getLogger(Errors.class);
    private final List<String> errors = new CopyOnWriteArrayList<>();

    public Errors() {
    }

    public Errors(Iterable<String> errors) {
        addErrors(errors);
    }

    public List<String> getErrors() {
        logger.trace("getErrors()");
        return unmodifiableList(errors);
    }

    public void reset() {
        logger.trace("reset()");
        errors.clear();
    }

    public void addError(String error) {
        logger.trace("addError(error = {})", error);
        errors.add(error);
    }

    public void addErrors(Iterable<String> errors) {
        logger.trace("addErrors(errors = {})", errors);
        Objects.requireNonNull(errors, "errors must not be null");
        errors.forEach(this::addError);
    }

    public void addErrors(String... errors) {
        if(logger.isTraceEnabled()) {
            logger.trace("addErrors(errors = {})", Arrays.toString(errors));
        }
        Objects.requireNonNull(errors, "errors must not be null");
        Arrays.stream(errors).forEach(this::addError);
    }

    public int countErrors() {
        logger.trace("countErrors()");
        return errors.size();
    }

    public boolean hasErrors() {
        logger.trace("hasErrors()");
        return !errors.isEmpty();
    }

    public void logErrors(Logger logger) {
        Errors.logger.trace("logErrors(logger = {})", logger);
        Objects.requireNonNull(logger, "logger must not be null");
        errors.forEach(logger::error);
    }

    public void logErrors() {
        logger.trace("logErrors()");
        logErrors(logger);
    }

    public void throwErrors(ExceptionProducer<? extends RuntimeException> exceptionProducer, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("throwErrors(exceptionProducer = {}, msg = {}, params = {})", exceptionProducer, msg, Arrays.toString(params));
        }
        if(hasErrors()) {
            throw exceptionProducer.produce(
                MessageFormatter.arrayFormat(msg, params).getMessage(),
                getErrors()
            );
        }
    }

    @Override
    public Iterator<String> iterator() {
        logger.trace("iterator()");
        return unmodifiableIterator(errors.iterator());
    }

    @Override
    public String toString() {
        logger.trace("toString()");
        return new ToStringBuilder(this).append("errors", errors)
                                        .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Errors that = (Errors) o;

        return new EqualsBuilder()
            .append(errors, that.errors)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(errors)
            .toHashCode();
    }

    public void isNull(Object obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isNull(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(!Objects.isNull(obj)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isNotNull(Object obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isNotNull(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(Objects.isNull(obj)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isEmpty(CharSequence obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isEmpty(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(StringUtils.isNotEmpty(obj)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isNotEmpty(CharSequence obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isNotEmpty(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(StringUtils.isEmpty(obj)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isEmpty(Collection<?> obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isEmpty(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(!obj.isEmpty()) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isNotEmpty(Collection<?> obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isNotEmpty(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(obj.isEmpty()) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isBlank(CharSequence obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isBlank(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(StringUtils.isNotBlank(obj)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isNotBlank(CharSequence obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isNotBlank(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(StringUtils.isBlank(obj)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isContainingDuplicates(Collection<?> obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isContainingDuplicates(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(obj.stream().noneMatch(e -> Collections.frequency(obj, e) > 1)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isNotContainingDuplicates(Collection<?> obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isNotContainingDuplicates(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(obj.stream().anyMatch(e -> Collections.frequency(obj, e) > 1)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isBetween(int num, int min, int max, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isBetween(num = {}, min = {}, max = {}, msg = {}, params = {})", num, min, max, msg, Arrays.toString(params));
        }
        if(num < min || num > max) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isTrue(boolean obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isTrue(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(!obj) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isFalse(boolean obj, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isFalse(obj = {}, msg = {}, params = {})", obj, msg, Arrays.toString(params));
        }
        if(obj) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isEqual(Object obj, Object other, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isEqual(obj = {}, other = {}, msg = {}, params = {})", obj, other, msg, Arrays.toString(params));
        }
        if(!Objects.equals(obj, other)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }

    public void isNotEqual(Object obj, Object other, String msg, Object ...params) {
        if(logger.isTraceEnabled()) {
            logger.trace("isNotEqual(obj = {}, other = {}, msg = {}, params = {})", obj, other, msg, Arrays.toString(params));
        }
        if(Objects.equals(obj, other)) {
            errors.add(MessageFormatter.arrayFormat(msg, params).getMessage());
        }
    }
}
