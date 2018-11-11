package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.PrintingService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.service.validator.exception.ShapeInfoException;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class ShapeFindServiceImpl extends ShapeService {

    private double totalArea;

    @Autowired
    public ShapeFindServiceImpl(ShapeValidatorService shapeValidatorService,
                                ShapeRepository shapeRepository,
                                PrintingService printingService) {
        super(shapeValidatorService, shapeRepository, printingService);
    }

    @Override
    public void doCommand(ActionDecisionService actionDecisionService, String... items) {
        try {
            shapeValidatorService.validateLookUpCoordinates(2, items);
            find(items);
        } catch (ShapeInfoException e) {
            System.out.println(e.getMessage() + Arrays.toString(items));
        } catch (ShapeException e) {
            System.out.println("ERROR: " + e.getMessage() + Arrays.toString(items));
        }
    }

    private void find(String... line) throws ShapeException {
        totalArea = 0;
        List<Shape> shapes = shapeRepository.findAll();
        shapeValidatorService.validateShapesAvailability(shapes);

        double[] lookUpPoint = Arrays.stream(line)
                .mapToDouble(Double::parseDouble).toArray();
        shapes.forEach(shape -> printingService.printInsideShapes(this,
                getConcreteAvailableShape(shape), lookUpPoint));
        printingService.printTotalArea(totalArea);
    }

    private ShapeUsecase getConcreteAvailableShape(Shape shape) {
        ShapeUsecase shapeUsecase = getAvailableShapes().get(shape.getShapeName());
        shapeUsecase.setShape(shape);
        return shapeUsecase;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }
}
