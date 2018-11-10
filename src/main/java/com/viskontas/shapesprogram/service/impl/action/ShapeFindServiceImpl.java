package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.ShapesResolverService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ShapeFindServiceImpl extends ShapeService implements ActionResolverService {

    @Autowired
    public ShapeFindServiceImpl(ShapesResolverService shapesResolverService,
                                ShapeValidatorService shapeValidatorService) {
        super(shapesResolverService, shapeValidatorService);
    }

    @Override
    public void doCommand(String... items) {
        try {
            shapeValidatorService.validateLookUpCoordinates(2, items);
            shapesResolverService.lookUpAllShapes(items);
        } catch (ShapeException e) {
            System.out.println(e.getMessage() + Arrays.toString(items));
        }
    }
}
