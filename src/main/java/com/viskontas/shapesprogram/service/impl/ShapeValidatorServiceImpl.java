package com.viskontas.shapesprogram.service.impl;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.service.validator.genericvalidator.ValidatorUtil;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
public class ShapeValidatorServiceImpl implements ShapeValidatorService {

    @Override
    public boolean isOkShapeName(String shapeName) {
        return ValidatorUtil.NOT_NULL_STRING.and(ValidatorUtil.NOT_EMPTY_STRING)
            .test(shapeName).isValid();
    }

    @Override
    public void  validateDouble(String value) throws ShapeException {
        String error = ValidatorUtil.IS_DOUBLE.test(value)
            .getErrorMessageIfInvalid("First value is not shape and not coordinate: ").orElse("");
        if (!error.isEmpty()) {
            throw new ShapeException(error);
        }
    }

    @Override
    public void validateShapeData(int valuesCount, String... shapeValues) throws ShapeException {
        String errors = ValidatorUtil.enoughValues(valuesCount).test(shapeValues.length)
                .getErrorMessageIfInvalid("Not correct data values count").orElse("");
        if (!Arrays.stream(shapeValues).allMatch(s -> ValidatorUtil.IS_DOUBLE.test(s).isValid())) {
             errors += " Not number coordinate exists";
        }
        if (!errors.isEmpty()) {
            throw new ShapeException(errors+": ");
        }
    }

    @Override
    public void validateLookUpCoordinates(int valuesCount, String... shapeValues) throws ShapeException {
        validateDouble(shapeValues[0]);
        validateShapeData(valuesCount, shapeValues);
    }

    @Override
    public void validateShapesAvailability(List<Shape> shape) throws ShapeException {
        String error = ValidatorUtil.NOT_EMPTY_LIST.test(shape)
                .getErrorMessageIfInvalid("No shapes found: ").orElse("");
        if (!error.isEmpty()) {
            throw new ShapeException(error);
        }
    }
}