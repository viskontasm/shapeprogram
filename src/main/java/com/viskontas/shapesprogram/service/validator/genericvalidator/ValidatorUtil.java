package com.viskontas.shapesprogram.service.validator.genericvalidator;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;

@Component
public class ValidatorUtil {

    public static final Validation <String> NOT_NULL_STRING = GenericValidation.from(Objects::nonNull);
    public static final Validation <String> NOT_EMPTY_STRING = GenericValidation.from(s -> !s.isEmpty());

    public static final Validation <String> IS_DOUBLE = GenericValidation.from(
        ValidatorUtil::canParseDouble);

    public static final Validation <List<?>> NOT_EMPTY_LIST = GenericValidation.from(list -> !list.isEmpty());

    public static Validation<Integer> enoughValues(int size){
        return GenericValidation.from(s -> s == size);
    }

    private static boolean canParseDouble(String input){
        try {
            Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}