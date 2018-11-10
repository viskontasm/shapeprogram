package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;

import java.util.Map;

public interface ActionResolverService {
    void doCommand(String... items);
    Map<String, Shape> getAvailableShapes();
}
