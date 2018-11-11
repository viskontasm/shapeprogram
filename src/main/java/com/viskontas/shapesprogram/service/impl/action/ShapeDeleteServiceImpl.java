package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.PrintingService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShapeDeleteServiceImpl extends ShapeService {

    @Autowired
    public ShapeDeleteServiceImpl(ShapeValidatorService shapeValidatorService,
                                  ShapeRepository shapeRepository,
                                  PrintingService printingService) {
        super(shapeValidatorService, shapeRepository, printingService);
    }

    @Override
    public void doCommand(String... items) {

    }
}
