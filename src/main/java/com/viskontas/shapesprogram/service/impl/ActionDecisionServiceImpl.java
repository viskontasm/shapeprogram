package com.viskontas.shapesprogram.service.impl;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelpServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeFindServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeSaveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ActionDecisionServiceImpl implements ActionDecisionService {

    private final Map<String, ActionResolverService> availableCommands;

    @Autowired
    public ActionDecisionServiceImpl(ShapeSaveServiceImpl shapeSaveService,
                                     ShapeFindServiceImpl shapeFindService,
                                     HelpServiceImpl helperService,
                                     ExitServiceImpl exitServiceImpl) {
        availableCommands = new HashMap<>();
        availableCommands.put("help", helperService);
        availableCommands.put("exit", exitServiceImpl);
        availableCommands.put("find", shapeFindService);
        
        availableCommands.put("triangle", shapeSaveService);
        availableCommands.put("circle", shapeSaveService);
        availableCommands.put("donut", shapeSaveService);


    }

    @Override
    public void decide(Stream<String> lineStream) {
        lineStream.forEach(line -> {
            String[] items = line.split(" ");
            if (availableCommands.get(items[0]) == null) {
                availableCommands.get("find").doCommand(items);
            } else {
                availableCommands.get(items[0]).doCommand(items);
            }
        });
    }

    @Override
    public Map<String, ActionResolverService> getAvailableCommands() {
        return Collections.unmodifiableMap(availableCommands);
    }
}
