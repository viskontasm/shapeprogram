package com.viskontas.shapesprogram.service.validator.genericvalidator;

import com.viskontas.shapesprogram.AvailableShapes;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class ValidatorUtil {

//https://dzone.com/articles/server-side-validator-using-functional-interfaces


    public static final Validation <String> notNullString = GenericValidation.from(Objects::nonNull);
    public static final Validation <String> notEmptyString = GenericValidation.from(s -> !s.isEmpty());
    public static final Validation <String> isShape = GenericValidation.from(
        AvailableShapes::shapeExists);

    public static final Validation <String> isDouble = GenericValidation.from(
        ValidatorUtil::canParseDouble);

    public static Validation<Integer> hasEnoughValues(int size){
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