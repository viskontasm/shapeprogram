package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ShapeService implements ActionResolverService {
    protected final ShapeValidatorService shapeValidatorService;
    protected final ShapeRepository shapeRepository;
    protected final Map<String, Shape> availableShapes;

    protected ShapeService(ShapeValidatorService shapeValidatorService,
                           ShapeRepository shapeRepository) {
        this.shapeValidatorService = shapeValidatorService;
        this.shapeRepository = shapeRepository;

        availableShapes = new HashMap<>();
        availableShapes.put("triangle", new Triangle());
        availableShapes.put("circle", new Circle());
    }

    @Override
    public Map<String, Shape> getAvailableShapes() {
        return Collections.unmodifiableMap(availableShapes);
    }
}
