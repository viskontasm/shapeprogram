package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(ActionDecisionService actionDecisionService, String... items) {
       decide(actionDecisionService, readFromFile("sample-data.txt"));
    }

    public List<String> readFromFile(String fileName) {
        InputStream in = getClass().getResourceAsStream("/"+fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        return reader.lines().collect(Collectors.toList());
    }

    private void decide(ActionDecisionService actionDecisionService, List<String> lines){
        lines.forEach(actionDecisionService::decide);
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return null;
    }
}
