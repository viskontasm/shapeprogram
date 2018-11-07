package com.viskontas.shapesprogram.service.validator;

import com.viskontas.shapesprogram.service.validator.genericvalidator.ValidatorUtil;
import org.springframework.stereotype.Service;

@Service
public class ShapeValidatorImpl implements ShapeValidator {
    @Override
    public void validate(String shapeName) throws ShapeException {
        /*errorFields.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
                .and(ValidatorUtil.stringBetween(1, 100)).test(employee.getLastName())
                .getFieldNameIfInvalid(" Please specify valid lastname ").orElse(""));
        errorFields.append(
                ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString).and(ValidatorUtil.stringBetween(3, 100))
                        .test(employee.getEmail()).getFieldNameIfInvalid(" Please specify valid email ").orElse(""));
        errorFields.append(ValidatorUtil.notNullInteger.and(ValidatorUtil.greaterThanZero)
                .and(ValidatorUtil.integerBetween(18, 60)).test(employee.getAge())
                .getFieldNameIfInvalid(" Please specify valid age ").orElse(""));*/
        String errors = ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString)
            .and(ValidatorUtil.isShape).test(shapeName)
            .getFieldNameIfInvalid(" There is no shape " + shapeName).orElse("");
        if (!errors.isEmpty()) {
            throw new ShapeException(errors);
        }
    }
}