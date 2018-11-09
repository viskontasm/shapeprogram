package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.AvailableShapes;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.validator.impl.ShapeValidatorImpl;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class ActionDecideService {

    @Autowired
    ShapeValidatorImpl shapeValidator;
    @Autowired
    AvailableShapes availableShapes;
    @Autowired
    ShapeService shapeService;

    public void decide(Stream<String> lineStream) {
        lineStream.map(line -> line.split(" "))
            .forEach(line -> {
                String firstValue = line[0];
                try {
                    if (shapeValidator.isOkShapeName(firstValue)) {
                        Shape shape = availableShapes.getShape(firstValue);
                        shapeValidator.validateShapeData(shape.getShapeDataCount(),
                            Arrays.copyOfRange(line, 1, line.length));

                        double[] shapeData = Arrays.stream(line)
                            .skip(1)
                            .mapToDouble(Double::parseDouble).toArray();
                        shape.addShapeValues(shapeData);

                        shapeService.createOrUpdateShape(shape);
                        System.out.println(shape.getShapeInformation());
                    } else {
                        shapeValidator.validateDouble(firstValue);
                        shapeValidator.validateLookUpCoordinates(2, line);
                        shapeService.lookUpAllShapes(line);
                    }
                } catch (ShapeException e) {
                    System.out.println(e.getMessage() + Arrays.toString(line));
                }
            });
    }

}
