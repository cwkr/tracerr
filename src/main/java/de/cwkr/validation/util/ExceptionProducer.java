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

import java.util.List;

/**
 * Specification of functional interface used by {@link Errors#throwErrors(ExceptionProducer, String, Object...)}.
 *
 * @param <T> runtime exception type
 * @author Christian Winkler
 */
@FunctionalInterface
public interface ExceptionProducer<T extends RuntimeException> {
    T produce(String message, List<String> errors);
}
