package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(ActionDecisionService actionDecisionService, String... items) {
       readFromFile(actionDecisionService);
    }

    public void readFromFile(ActionDecisionService actionDecisionService) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sample-data.txt").getFile());
        try (Stream<String> lineStream = Files.lines(Paths.get(file.getPath()))) {
            lineStream.forEach(actionDecisionService::decide);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return null;
    }
}
