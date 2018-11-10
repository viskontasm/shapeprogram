package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShapeService {

    ShapeRepository shapeRepository;
    ShapeValidatorServiceImpl shapeValidator;

    @Autowired
    public ShapeService(ShapeRepository shapeRepository, ShapeValidatorServiceImpl shapeValidator) {
        this.shapeRepository = shapeRepository;
        this.shapeValidator = shapeValidator;
    }

    public Shape createOrUpdateShape(Shape newShape) {
        Optional<Shape> existingShapeOptional = getExistingShapeOptional(newShape.getShapeName());
        if (existingShapeOptional.isPresent()) {
            Shape existingShape = existingShapeOptional.get();
            return save(prepareExistingShape(existingShape, newShape));
        } else {
            return save(newShape);
        }
    }

    private Optional<Shape> getExistingShapeOptional(String shapeName) {
        return findAll().stream()
                .filter(sh -> shapeName.equals(sh.getShapeName()))
                .findFirst();
    }

    private Shape prepareExistingShape(Shape existingShape, Shape newShape) {
        int lastIndex = newShape.getShapeData().size()-1;
        existingShape.addShapeValues(newShape.getShapeData().get(lastIndex));
        return existingShape;
    }

    public Shape save(Shape shape) {
        return shapeRepository.save(shape);
    }

    public List<Shape> findAll() {
        return shapeRepository.findAll();
    }

    public void lookUpAllShapes(String... line) throws ShapeException {
        double[] shapeData = Arrays.stream(line)
            .mapToDouble(Double::parseDouble).toArray();
        List<Shape> shapes = findAll();
        shapeValidator.validateShapesAvailability(shapes);
        shapes.forEach(shape -> shape.printInsideShapes(shapeData));
    }
}
