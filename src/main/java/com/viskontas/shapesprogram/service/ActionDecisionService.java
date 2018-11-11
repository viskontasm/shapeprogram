package com.viskontas.shapesprogram.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public interface ActionDecisionService {
    void decide(String line);
    Map<String, ActionResolverService> getAvailableCommands();
}
