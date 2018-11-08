package com.viskontas.shapesprogram.service.validator.genericvalidator;


@FunctionalInterface
public interface Validation<K> {

    GenericValidationResult test(K param);

    default Validation <K> and(Validation <K> other) {
        return param -> {
            GenericValidationResult result = test(param);
            return !result.isValid() ? result : other.test(param);
        };
    }
    default Validation <K> or(Validation <K> other) {
        return param -> {
            GenericValidationResult result = test(param);
            return result.isValid() ? result : other.test(param);
        };
    }

}