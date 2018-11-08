package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

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
            shapeFromDb.setShapeData(DoubleStream.concat(Arrays.stream(shapeFromDb.getShapeData()),
                        Arrays.stream(shape.getShapeData())).toArray());
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
}
