package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(ActionDecisionService actionDecisionService, String... items) {
       readFromFile(actionDecisionService);
    }

    public void readFromFile(ActionDecisionService actionDecisionService) {
        InputStream in = getClass().getResourceAsStream("/sample-data.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        List<String> list = reader.lines().collect(Collectors.toList());
        list.forEach(actionDecisionService::decide);
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return null;
    }
}
