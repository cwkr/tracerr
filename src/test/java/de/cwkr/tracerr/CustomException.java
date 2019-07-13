package de.cwkr.tracerr;

import java.util.Collections;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class CustomException extends RuntimeException {
    private final List<String> errors;

    public CustomException(String message, List<String> errors) {
        super(message);
        this.errors = unmodifiableList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}
