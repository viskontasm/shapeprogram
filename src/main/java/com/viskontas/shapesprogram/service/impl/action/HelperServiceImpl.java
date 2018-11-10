package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class HelperServiceImpl implements ActionResolverService {

    @Override
    public void doCommand() {
        System.out.println("help text");
    }
}
