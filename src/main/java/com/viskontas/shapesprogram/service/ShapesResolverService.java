package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;

public interface ShapesResolverService {
    void saveShape(Shape shape, String... line) throws ShapeException;
    void deleteShape(String shapeId);
    void lookUpAllShapes(String... line) throws ShapeException;
}
