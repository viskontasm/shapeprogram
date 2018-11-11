package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.PrintingService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class ShapeFindServiceImpl extends ShapeService {

    @Autowired
    public ShapeFindServiceImpl(ShapeValidatorService shapeValidatorService,
                                ShapeRepository shapeRepository,
                                PrintingService printingService) {
        super(shapeValidatorService, shapeRepository, printingService);
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

        double[] lookUpPoint = Arrays.stream(line)
                .mapToDouble(Double::parseDouble).toArray();
        //shapes.forEach(shape -> printingService.printInsideShapes(
        //        getConcreteAvailableShape(shape), lookUpPoint, double totalArea));
        Double totalArea = (double) 0;
        for (Shape shape : shapes) {
            printingService.printInsideShapes(
                    getConcreteAvailableShape(shape), totalArea, lookUpPoint);
        }
        printingService.printTotalArea(totalArea);
    }

    private ShapeUsecase getConcreteAvailableShape(Shape shape) {
        ShapeUsecase shapeUsecase = getAvailableShapes().get(shape.getShapeName());
        shapeUsecase.setShape(shape);
        return shapeUsecase;
    }
}
