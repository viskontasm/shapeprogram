package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShapeService {

    @Autowired
    ShapeRepository shapeRepository;

    public Shape createOrUpdateShape(Shape shape) {
        Optional<Shape> shapeOptional = findAll().stream()
                .filter(s -> shape.getShapeName().equals(s.getShapeName()))
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

    public void lookUpAllShapes( String... line) {
        double[] shapeData = Arrays.stream(line)
            .mapToDouble(Double::parseDouble).toArray();
        findAll().stream()
            .forEach(shape -> shape.printInsideShapes(shapeData));
    }
}
