package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.ShapesResolverService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShapeService {
    protected final ShapesResolverService shapesResolverService;
    protected final ShapeValidatorService shapeValidatorService;
    protected final Map<String, Shape> availableShapes;

    public ShapeService(ShapesResolverService shapesResolverService,
                                ShapeValidatorService shapeValidatorService) {
        this.shapeValidatorService = shapeValidatorService;
        this.shapesResolverService = shapesResolverService;

        availableShapes = new HashMap<>();
        availableShapes.put("triangle", new Triangle());
        availableShapes.put("circle", new Circle());
    }
    public Map<String, Shape> getAvailableShapes() {
        return Collections.unmodifiableMap(availableShapes);
    }
}
