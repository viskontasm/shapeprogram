package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ExitServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(String... items) {
        System.out.println("Bye. Have a nice day!");
        System.exit(0);
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return null;
    }
}
