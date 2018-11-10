package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class ShapeFindServiceImpl extends ShapeService {

    @Autowired
    public ShapeFindServiceImpl(ShapeValidatorService shapeValidatorService,
                                ShapeRepository shapeRepository) {
        super(shapeValidatorService, shapeRepository);
    }

    @Override
    public void doCommand(String... items) {
        try {
            shapeValidatorService.validateLookUpCoordinates(2, items);
            lookUpInsideShapes(items);
        } catch (ShapeException e) {
            System.out.println("ERROR: " + e.getMessage() + Arrays.toString(items));
        }
    }

    private void lookUpInsideShapes(String... line) throws ShapeException {
        List<Shape> shapes = shapeRepository.findAll();
        shapeValidatorService.validateShapesAvailability(shapes);

        double[] shapeData = Arrays.stream(line)
                .mapToDouble(Double::parseDouble).toArray();
        shapes.forEach(shape -> shape.printInsideShapes(shapeData));
    }
}
