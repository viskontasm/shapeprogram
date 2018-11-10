package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShapeDeleteServiceImpl extends ShapeService {

    @Autowired
    public ShapeDeleteServiceImpl(ShapeValidatorService shapeValidatorService,
                                  ShapeRepository shapeRepository) {
        super(shapeValidatorService, shapeRepository);
    }

    @Override
    public void doCommand(String... items) {

    }
}
