package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.service.validator.impl.ShapeValidatorServiceImpl;
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

    public Shape createOrUpdateShape(Shape shape) {
        Optional<Shape> shapeOptional = findAll().stream()
                .filter(sh -> shape.getShapeName().equals(sh.getShapeName()))
                .findFirst();

        if (shapeOptional.isPresent()) {
            Shape shapeFromDb = shapeOptional.get();
            int lastIndex = shape.getShapeData().size()-1;
            shapeFromDb.addShapeValues(shape.getShapeData().get(lastIndex));
            return save(shapeFromDb);
        } else {
            return save(shape);
        }
    }

    public Shape save(Shape shape) {
        return shapeRepository.save(shape);
    }

    public List<Shape> findAll() {
        return shapeRepository.findAll();
    }

    public void lookUpAllShapes( String... line) throws ShapeException {
        double[] shapeData = Arrays.stream(line)
            .mapToDouble(Double::parseDouble).toArray();
        List<Shape> shapes = findAll();
        shapeValidator.validateShapesAvailability(shapes);
        shapes.forEach(shape -> shape.printInsideShapes(shapeData));
    }
}
