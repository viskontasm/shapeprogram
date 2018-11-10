package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.ShapesResolverService;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class ShapeSaveServiceImpl extends ShapeService implements ActionResolverService {

    @Autowired
    public ShapeSaveServiceImpl(ShapesResolverService shapesResolverService,
                                ShapeValidatorService shapeValidatorService) {
        super(shapesResolverService, shapeValidatorService);
    }

    @Override
    public void doCommand(String... items) {
        try {
            String firstItem = items[0];
            if (availableShapes.keySet().contains(firstItem) && shapeValidatorService.isOkShapeName(firstItem)) {
                shapesResolverService.saveShape(availableShapes.get(firstItem), items);
            }
        } catch (ShapeException e) {
            System.out.print(e.getMessage() + Arrays.toString(items));
        }
    }
}