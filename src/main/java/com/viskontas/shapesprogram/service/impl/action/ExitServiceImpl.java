package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverService;
import org.springframework.stereotype.Service;

@Service
public class ExitServiceImpl implements ActionResolverService {

    @Override
    public void doCommand() {
        System.exit(0);
    }
}
