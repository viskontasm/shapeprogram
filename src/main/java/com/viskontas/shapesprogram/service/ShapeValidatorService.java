package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;

import java.util.List;

public interface ShapeValidatorService {
   boolean isOkShapeName(String shapeName) throws ShapeException;
   void validateShapeData(int valuesCount, String... shapeValues) throws ShapeException;
   void validateLookUpCoordinates(int valuesCount, String... shapeValues) throws ShapeException;
   void  validateDouble(String shapeName) throws ShapeException;
   void validateShapesAvailability(List<Shape> shape) throws ShapeException;
   void validateDeteCommand(List<String> availableShapes, String... values) throws ShapeException;
}