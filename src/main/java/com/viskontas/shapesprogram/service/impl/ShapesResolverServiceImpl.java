package com.viskontas.shapesprogram.service.impl;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ShapesResolverService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShapesResolverServiceImpl implements ShapesResolverService {

    private final ShapeValidatorServiceImpl shapeValidator;
    private final ShapeRepository shapeRepository;

    @Autowired
    public ShapesResolverServiceImpl(ShapeValidatorServiceImpl shapeValidator,
                                     ShapeRepository shapeRepository) {
        this.shapeValidator = shapeValidator;
        this.shapeRepository = shapeRepository;
    }

    @Override
    public void saveShape(Shape shape, String... line) throws ShapeException {
        String firstValue = line[0];
         shapeValidator.validateShapeData(shape.getShapeDataCount(),
                Arrays.copyOfRange(line, 1, line.length));

        double[] shapeData = Arrays.stream(line)
                .skip(1)
                .mapToDouble(Double::parseDouble).toArray();
        shape.addShapeValues(shapeData);

        createOrUpdateShape(shape);
        shape.printShapeInformation(shape.getShapeData().size()-1);
    }

    @Override
    public void lookUpAllShapes(String... line) throws ShapeException {
        String firstValue = line[0];
        lookUpShapes(line);
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
        return findAll().stream()
                .filter(sh -> shapeName.equals(sh.getShapeName()))
                .findFirst();
    }

    private Shape prepareExistingShape(Shape existingShape, Shape newShape) {
        int lastIndex = newShape.getShapeData().size()-1;
        existingShape.addShapeValues(newShape.getShapeData().get(lastIndex));
        return existingShape;
    }

    public List<Shape> findAll() {
        return shapeRepository.findAll();
    }

    private void lookUpShapes(String... line) throws ShapeException {
        double[] shapeData = Arrays.stream(line)
                .mapToDouble(Double::parseDouble).toArray();
        List<Shape> shapes = findAll();
        shapeValidator.validateShapesAvailability(shapes);
        shapes.forEach(shape -> shape.printInsideShapes(shapeData));
    }

    @Override
    public void deleteShape(String shapeId) {
        //TODO
    }
}
