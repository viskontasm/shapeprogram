package com.viskontas.shapesprogram.service;


import com.viskontas.shapesprogram.model.Shape;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@Service
public interface ActionDecisionService {
    void decide(Stream<String> lineStream);
    Map<String, ActionResolverService> getAvailableCommands();
    Map<String, Shape> getAvailableShapes();
}
