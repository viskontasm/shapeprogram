package com.viskontas.shapesprogram.service.validator;

public interface ShapeValidator {
   boolean isValidShapeName(String shapeName);
   boolean isValidShapeData(int valuesCount, String... shapeValues);
   boolean isValidLookUpCoordinates(int valuesCount, String... shapeValues);
}