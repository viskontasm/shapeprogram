package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Donut;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShapeFindServiceImplTest extends ActionResolverServiceTest {

    @Test
    public void do_command_find_shape_fail() {
        when(shapeRepository.findAll()).thenReturn(noShapesFound);

        String line = "1 2";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "ERROR: No shapes found: [1, 2]";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_command_find_triangle() {
        when(shapeRepository.findAll()).thenReturn(oneTriangleFound);

        String line = "1 2";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "All triangles, which have inside coordinate x(1.0,2.0):\r\n" +
                "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n" +
                "\tTotal area:12.5";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_command_find_circle() {
        when(shapeRepository.findAll()).thenReturn(oneCircleFound);

        String line = "1 2";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "All circles, which have inside coordinate x(1.0,2.0):\r\n" +
                "\tcircle-0 with centre o1(0.0,0.0) and radius 5.0; Surface area:78.53981633974483\r\n" +
                "\tTotal area:78.53981633974483";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_command_find_donut() {
        when(shapeRepository.findAll()).thenReturn(oneDonutFound);

        String line = "0 4";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "All donuts, which have inside coordinate x(0.0,4.0):\r\n" +
                "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n" +
                "\tTotal area:50.26548245743669";
        assertEquals(expectedString, outContent.toString().trim());
    }
}