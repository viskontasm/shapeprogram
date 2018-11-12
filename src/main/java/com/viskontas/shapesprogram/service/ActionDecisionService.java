package com.viskontas.shapesprogram.service;

import java.util.Map;

public interface ActionDecisionService {
    void decide(String line);
    Map<String, ActionResolverService> getAvailableCommands();
}
