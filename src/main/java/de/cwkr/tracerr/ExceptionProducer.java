package de.cwkr.tracerr;

import java.util.List;

@FunctionalInterface
public interface ExceptionProducer<T extends RuntimeException> {
    T produce(String message, List<String> errors);
}
