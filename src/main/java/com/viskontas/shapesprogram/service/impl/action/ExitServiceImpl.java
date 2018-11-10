package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.ActionResolverService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExitServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(String... items) {
        System.exit(0);
    }

    @Override
    public Map<String, Shape> getAvailableShapes() {
        return null;
    }
}
