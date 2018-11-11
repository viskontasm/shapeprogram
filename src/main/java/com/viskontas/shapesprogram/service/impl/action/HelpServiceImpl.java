package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@NoArgsConstructor
public class HelpServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(String... items) {
        System.out.println("help text");
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return null;
    }
}
