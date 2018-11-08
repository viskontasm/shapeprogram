package com.viskontas.shapesprogram.service.validator;

import com.viskontas.shapesprogram.service.validator.genericvalidator.ValidatorUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ShapeValidatorImpl implements ShapeValidator {

    @Override
    public boolean isValidShapeName(String shapeName) {
        return ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
            .and(ValidatorUtil.isShape).test(shapeName)
            //.getFieldNameIfInvalid(" First value is not shape or not coordinate: " + shapeName).orElse("");
            .isValid();
    }

    @Override
    public boolean isValidShapeData(int valuesCount, String... shapeValues) {
        return ValidatorUtil.hasEnoughValues(valuesCount).test(shapeValues.length).isValid()
            && Arrays.stream(shapeValues).allMatch(s -> ValidatorUtil.isDouble.test(s).isValid());
    }

    @Override
    public boolean isValidLookUpCoordinates(int valuesCount, String... shapeValues) {
        return isValidShapeData(valuesCount, shapeValues);
    }
}