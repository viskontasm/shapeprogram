package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelpServiceImplTest extends ActionResolverServiceTest {

    @Test
    public void do_comamnd_help() {
        actionHelp.doCommand(null);
        String expectedString = "" +
                "================================SHAPES PROGRAM===========================================================\r\n" +
                "\r\n" +
                " Available shapes to add:\r\n" +
                "\t'circle x y r' - where 'x' and 'y' center coordinates and 'r' radius\r\n" +
                "\t'donut x y r R' - where 'x' and 'y' center coordinates, 'r' smaller radius and 'R' bigger radius\r\n" +
                "\t'triangle x1 y1 x2 y2 x3 y3' - where 3 coordinates '(x1, y1)', '(x2, y2)' and '(x3, y3)'\r\n" +
                "\r\n" +
                " When a pair of numbers entered 'x y', the application should print out all the shapes that include\r\n" +
                " that point, i.e. it should print out shape S if the given point is inside S(line included).\r\n" +
                "\r\n" +
                " Other commands available:\r\n" +
                "\t'exit' - exit program,\r\n" +
                "\t'help' - ask for help\r\n" +
                "\t'file' - read from file located in resources 'sample-data.txt'\r\n" +
                "==========================================================================================================";
        assertEquals(expectedString, outContent.toString().trim());
    }
}