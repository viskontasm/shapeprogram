package com.viskontas.shapesprogram.service.validator.impl;

import com.viskontas.shapesprogram.service.validator.ShapeValidatorService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.service.validator.genericvalidator.ValidatorUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ShapeValidatorImpl implements ShapeValidatorService {

    @Override
    public boolean isOkShapeName(String shapeName) {
        return ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
            .and(ValidatorUtil.isShape).test(shapeName).isValid();
    }

    @Override
    public void  validateDouble(String value) throws ShapeException {
        String error = ValidatorUtil.isDouble.test(value)
            .getErrorMessageIfInvalid("First value is not shape and not coordinate: ").orElse("");
        if (!error.isEmpty()) {
            throw new ShapeException(error);
        }
    }

    @Override
    public void validateShapeData(int valuesCount, String... shapeValues) throws ShapeException {
        String errors = ValidatorUtil.hasEnoughValues(valuesCount).test(shapeValues.length)
                .getErrorMessageIfInvalid("Not correct data values count.").orElse("");
        errors += Arrays.stream(shapeValues)
                .allMatch(s -> ValidatorUtil.isDouble.test(s).isValid()) ? "" : "Not number coordinate exists";
        if (!errors.isEmpty()) {
            throw new ShapeException(errors+": ");
        }
    }

    @Override
    public void validateLookUpCoordinates(int valuesCount, String... shapeValues) throws ShapeException {
        validateShapeData(valuesCount,shapeValues);
    }
}