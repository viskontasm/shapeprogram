package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
@NoArgsConstructor
public class HelpServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(ActionDecisionService actionDecisionService, String... items) {
        FileServiceImpl fileService = new FileServiceImpl();
        fileService.readFromFile("help.txt").forEach(System.out::println);
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return null;
    }
}
