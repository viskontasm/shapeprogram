package com.viskontas.shapesprogram.service.validator;

import com.viskontas.shapesprogram.service.validator.exception.ShapeException;

public interface ShapeValidatorService {
   boolean isOkShapeName(String shapeName) throws ShapeException;
   void validateShapeData(int valuesCount, String... shapeValues) throws ShapeException;
   void validateLookUpCoordinates(int valuesCount, String... shapeValues) throws ShapeException;
   void  validateDouble(String shapeName) throws ShapeException;
}