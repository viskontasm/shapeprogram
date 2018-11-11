package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Donut;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.PrintingService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.usecase.CircleUsecase;
import com.viskontas.shapesprogram.usecase.DonutUsecase;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import com.viskontas.shapesprogram.usecase.TriangleUsecase;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ShapeService implements ActionResolverService {
    protected final ShapeValidatorService shapeValidatorService;
    protected final ShapeRepository shapeRepository;
    protected final PrintingService printingService;
    protected final Map<String, ShapeUsecase> availableShapes;

    protected ShapeService(ShapeValidatorService shapeValidatorService,
                           ShapeRepository shapeRepository,
                           PrintingService printingService) {
        this.shapeValidatorService = shapeValidatorService;
        this.shapeRepository = shapeRepository;
        this.printingService = printingService;

        availableShapes = new HashMap<>();
        availableShapes.put("triangle", new TriangleUsecase(new Triangle()));
        availableShapes.put("circle", new CircleUsecase(new Circle()));
        availableShapes.put("donut", new DonutUsecase(new Donut()));
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return Collections.unmodifiableMap(availableShapes);
    }
}
