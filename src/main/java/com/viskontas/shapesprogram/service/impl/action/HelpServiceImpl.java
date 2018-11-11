package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@NoArgsConstructor
public class HelpServiceImpl implements ActionResolverService {

    @Override
    public void doCommand(ActionDecisionService actionDecisionService, String... items) {
        System.out.println("\n" +
                "================================SHAPES PROGRAM===========================================================\n" +
                "\n" +
                "Available shapes to add:\n" +
                "\t'circle x y r' - where 'x' and 'y' center coordinates and 'r' radius\n" +
                "\t'donut x y r R' - where 'x' and 'y' center coordinates, 'r' smaller radius and 'R' bigger radius\n" +
                "\t'triangle x1 y1 x2 y2 x3 y3' - where 3 coordinates '(x1, y1)', '(x2, y2)' and '(x3, y3)'\n" +
                "\t\n" +
                "When a pair of numbers entered 'x y', the application should print out all the shapes that include that point, \n" +
                "i.e. it should print out shape S if the given point is inside S.\n" +
                "\n" +
                "Other commands available: 'exit', 'help'\n" +
                "==========================================================================================================");
    }

    @Override
    public Map<String, ShapeUsecase> getAvailableShapes() {
        return null;
    }
}
