package com.viskontas.shapesprogram.service.validator.genericvalidator;

import java.util.function.Predicate;

public final class GenericValidation <K> implements Validation<K> {
    private final Predicate <K> predicate;

    public static <K> GenericValidation <K> from(Predicate <K> predicate) {
        return new GenericValidation <K> (predicate);
    }

    private GenericValidation(Predicate <K> predicate) {
        this.predicate = predicate;
    }

    @Override
    public GenericValidationResult test(K param) {
        return predicate.test(param) ? GenericValidationResult.ok() : GenericValidationResult.fail();
    }
}