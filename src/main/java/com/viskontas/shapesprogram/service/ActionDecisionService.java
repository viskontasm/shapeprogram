package com.viskontas.shapesprogram.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Stream;

@Service
public interface ActionDecisionService {
    void decide(Stream<String> lineStream);
    Map<String, ActionResolverService> getAvailableCommands();
}
