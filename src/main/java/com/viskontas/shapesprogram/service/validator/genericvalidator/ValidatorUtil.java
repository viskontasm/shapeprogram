package com.viskontas.shapesprogram.service.validator.genericvalidator;

import com.viskontas.shapesprogram.AvailableShapes;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

@Component
public class ValidatorUtil {

//https://dzone.com/articles/server-side-validator-using-functional-interfaces


    public static final Validation <String> notNullString = GenericValidation.from(Objects::nonNull);
    public static final Validation <String> notEmptyString = GenericValidation.from(s -> !s.isEmpty());
    public static final Validation <String> isShape = GenericValidation.from(
        s -> AvailableShapes.fromValue(s.toUpperCase(Locale.ENGLISH)) != null);

    public static final Validation <String> stringMoreThan(int size) {
        return GenericValidation.from(s -> ((String) s).length() > size);
    };
    public static final Validation <String> stringLessThan(int size) {
        return GenericValidation.from(s -> ((String) s).length() < size);
    };
    public static final Validation <String> stringBetween(int morethan, int lessThan) {
        return stringMoreThan(morethan).and(stringLessThan(lessThan));
    };
    public static final Validation <Integer> integerMoreThan(int limit) {
        return GenericValidation.from(s -> s > limit);
    };
    public static final Validation <Integer> integerLessThan(int size) {
        return GenericValidation.from(s -> s < size);
    };
    public static final Validation <Integer> integerBetween(int morethan, int lessThan) {
        return integerMoreThan(morethan).and(integerLessThan(lessThan));
    };
}