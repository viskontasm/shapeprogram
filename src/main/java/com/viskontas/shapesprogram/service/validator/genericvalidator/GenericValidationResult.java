package com.viskontas.shapesprogram.service.validator.genericvalidator;

import java.util.Optional;

public class GenericValidationResult {
    private final boolean valid;
    public boolean isValid() {
        return valid;
    }
    public static GenericValidationResult ok() {
        return new GenericValidationResult(true);
    }
    private GenericValidationResult(boolean valid) {
        this.valid = valid;
    }
    public static GenericValidationResult fail() {
        return new GenericValidationResult(false);
    }
    public Optional< String > getErrorMessageIfInvalid(String field) {
        return valid ? Optional.empty() : Optional.of(field);
    }
}