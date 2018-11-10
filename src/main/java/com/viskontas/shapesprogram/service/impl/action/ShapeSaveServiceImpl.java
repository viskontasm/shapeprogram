package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ShapeSaveServiceImpl extends ShapeService {

    @Autowired
    public ShapeSaveServiceImpl(ShapeValidatorService shapeValidatorService,
                                ShapeRepository shapeRepository) {
        super(shapeValidatorService, shapeRepository);
    }

    @Override
    public void doCommand(String... items) {
        try {
            String firstItem = items[0];
            if (availableShapes.keySet().contains(firstItem) && shapeValidatorService.isOkShapeName(firstItem)) {
                Shape shape = availableShapes.get(firstItem);
                validateShapeForSave(shape, items);
                fillShapeWithData(shape, items);
                createOrUpdateShape(shape);
                printSavedShape(shape);
            }
        } catch (ShapeException e) {
            System.out.println("ERROR: " + e.getMessage() + Arrays.toString(items));
        }
    }

    private void printSavedShape(Shape shape) {
        int shapeId = shape.getShapeData().size()-1;
        System.out.println("Shape added:");
        shape.printShapeInformation(shapeId);
        shape.printShapeArea(shapeId);
    }

    private void validateShapeForSave(Shape shape, String... line) throws ShapeException {
        shapeValidatorService.validateShapeData(shape.getShapeDataCount(),
                Arrays.copyOfRange(line, 1, line.length));
    }

    private void fillShapeWithData(Shape shape, String... line) {
        double[] shapeData = Arrays.stream(line)
                .skip(1)
                .mapToDouble(Double::parseDouble).toArray();
        shape.addShapeValues(shapeData);
    }

    private Shape createOrUpdateShape(Shape newShape) {
        Optional<Shape> existingShapeOptional = getExistingShapeOptional(newShape.getShapeName());
        if (existingShapeOptional.isPresent()) {
            Shape existingShape = existingShapeOptional.get();
            return shapeRepository.save(prepareExistingShape(existingShape, newShape));
        } else {
            return shapeRepository.save(newShape);
        }
    }

    private Optional<Shape> getExistingShapeOptional(String shapeName) {
        return shapeRepository.findAll().stream()
                .filter(sh -> shapeName.equals(sh.getShapeName()))
                .findFirst();
    }

    private Shape prepareExistingShape(Shape existingShape, Shape newShape) {
        int lastIndex = newShape.getShapeData().size()-1;
        existingShape.addShapeValues(newShape.getShapeData().get(lastIndex));
        return existingShape;
    }
}