package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelpServiceImplTest extends ActionResolverServiceTest {

    @Test
    public void do_comamnd_help() {
        actionHelp.doCommand(null);
        String expectedString = "" +
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
                "==========================================================================================================";
        assertEquals(expectedString, outContent.toString().trim());
    }

}