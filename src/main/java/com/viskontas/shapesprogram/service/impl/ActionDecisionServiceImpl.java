package com.viskontas.shapesprogram.service.impl;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ShapesResolverService;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelperServiceImpl;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Data
@Service
public class ActionDecisionServiceImpl implements ActionDecisionService {

    private final ShapesResolverService shapesResolverService;
    private final ShapeValidatorServiceImpl shapeValidator;

    private final Map<String, ActionResolverService> availableCommands;
    private final Map<String, Shape> availableShapes;

    @Autowired
    public ActionDecisionServiceImpl(ShapesResolverService shapesResolverService,
                                     ShapeValidatorServiceImpl shapeValidator,
                                     HelperServiceImpl helperService,
                                     ExitServiceImpl exitService) {
        this.shapesResolverService = shapesResolverService;
        this.shapeValidator = shapeValidator;

        availableCommands = new HashMap<>();
        availableCommands.put("help", helperService);
        availableCommands.put("exit", exitService);

        availableShapes = new HashMap<>();
        availableShapes.put("triangle", new Triangle());
        availableShapes.put("circle", new Circle());
    }

    @Override
    public void decide(Stream<String> lineStream) {
        lineStream.forEach(line -> {
                String[] items = line.split(" ");
                try {
                    String firstItem = items[0];
                    if (availableCommands.keySet().contains(firstItem) && items.length == 1) {
                        availableCommands.get(firstItem).doCommand();
                    } else if (availableShapes.keySet().contains(firstItem) && shapeValidator.isOkShapeName(firstItem)) {
                        shapesResolverService.saveShape(availableShapes.get(firstItem), items);
                    } else {
                        shapeValidator.validateLookUpCoordinates(2, items);
                        shapesResolverService.lookUpAllShapes(items);
                    }
                } catch (ShapeException e) {
                    System.out.print(e.getMessage() + Arrays.toString(items));
                }
            });
    }

    @Override
    public Map<String, ActionResolverService> getAvailableCommands() {
        return Collections.unmodifiableMap(availableCommands);
    }

    @Override
    public Map<String, Shape> getAvailableShapes() {
        return Collections.unmodifiableMap(availableShapes);
    }
}
