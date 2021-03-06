package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import java.util.Map;

public interface ActionResolverService {
    void doCommand(ActionDecisionService actionDecisionService, String... items);
    Map<String, ShapeUsecase> getAvailableShapes();
}
