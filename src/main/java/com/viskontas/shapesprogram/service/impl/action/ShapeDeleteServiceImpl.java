package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.PrintingService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShapeDeleteServiceImpl extends ShapeService {

    @Autowired
    public ShapeDeleteServiceImpl(ShapeValidatorService shapeValidatorService,
                                  ShapeRepository shapeRepository,
                                  PrintingService printingService) {
        super(shapeValidatorService, shapeRepository, printingService);
    }

    @Override
    public void doCommand(ActionDecisionService actionDecisionService, String... items) {
        try {
            delete(extractAvailableShapes(actionDecisionService), items);
        } catch (ShapeException e) {
            System.out.println("ERROR: " + e.getMessage() + Arrays.toString(items));
        }
    }

    private void delete(List<String> availableShapes, String... items) throws ShapeException {
        String[] shapeIdInfo = items[1].split("-");
        shapeValidatorService.validateDeteCommand(availableShapes, items);
        Shape shape = collectShape(shapeIdInfo[0], items);
        if (shape == null) {
            throw new ShapeException("shape not found: ");
        }
        removeShapeData(shape, Integer.valueOf(shapeIdInfo[1]));
        shapeRepository.save(shape);
        System.out.println("Shape deleted: " + items[1]);
    }

    private Shape collectShape(String shapeName, String... items) {
        List<Shape> shapes = shapeRepository.findAll().stream()
                .filter(shape -> shape.getShapeName().equals(shapeName))
                .collect(Collectors.toList());
        if (!shapes.isEmpty()) {
            return shapes.get(0);
        }
        return null;
    }

    private void removeShapeData(Shape shape, int shapeId) throws ShapeException {
        if (shape.getShapeData().size()-1 < shapeId) {
            throw new ShapeException(shape.getShapeName() + " not found: ");
        }
        double[] shapeDataToDelete = shape.getShapeData().get(shapeId);
        shape.getShapeData().remove(shapeDataToDelete);
    }

    private List<String> extractAvailableShapes(ActionDecisionService actionDecisionService) {
        return actionDecisionService.getAvailableCommands().entrySet().stream()
                .filter(x -> x.getValue() instanceof ShapeSaveServiceImpl)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
