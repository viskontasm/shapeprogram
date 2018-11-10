package com.viskontas.shapesprogram.service.impl;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.ShapesResolverService;
import com.viskontas.shapesprogram.service.ShapeService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ShapesResolverServiceImpl implements ShapesResolverService {

    private final ShapeValidatorServiceImpl shapeValidator;
    private final ShapeService shapeService;

    @Autowired
    public ShapesResolverServiceImpl(ShapeValidatorServiceImpl shapeValidator, ShapeService shapeService) {
        this.shapeValidator = shapeValidator;
        this.shapeService = shapeService;
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

        shapeService.createOrUpdateShape(shape);
        shape.printShapeInformation(shape.getShapeData().size()-1);
    }

    @Override
    public void lookUpAllShapes(String... line) throws ShapeException {
        String firstValue = line[0];
        //shapeValidator.validateDouble(firstValue);        ;
        shapeService.lookUpAllShapes(line);
    }
}
