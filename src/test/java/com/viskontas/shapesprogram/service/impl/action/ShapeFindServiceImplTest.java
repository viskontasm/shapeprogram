package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Donut;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ShapeFindServiceImplTest extends ActionResolverServiceTest {

    @Test
    public void do_command_find_shape_fail() {
        List<Shape> oneShapeFound = new ArrayList<>();

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "1 2";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "ERROR: No shapes found: [1, 2]";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_command_find_triangle() {
        Shape shape = new Triangle();
        shape.setShapeName("triangle");
        double[] shapeD = {0, 0, 0, 5, 5, 0};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        shape.setShapeData(shapeData);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shape);

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "1 2";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "All triangles, which have inside coordinate x(1.0,2.0):\r\n" +
                "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n" +
                "Total area:12.5";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_command_find_circle() {
        Shape shape = new Circle();
        shape.setShapeName("circle");
        double[] shapeD = {0, 0, 5};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        shape.setShapeData(shapeData);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shape);

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "1 2";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "All circles, which have inside coordinate x(1.0,2.0):\r\n" +
                "\tcircle-0 with centre o1(0.0,0.0) and radius 5.0; Surface area:78.53981633974483\r\n" +
                "Total area:78.53981633974483";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_command_find_donut() {
        Shape shape = new Donut();
        shape.setShapeName("donut");
        double[] shapeD = {0, 0, 3, 5};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        shape.setShapeData(shapeData);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shape);

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "0 4";
        findShape.doCommand(null, line.split(" "));

        String expectedString = "All donuts, which have inside coordinate x(0.0,4.0):\r\n" +
                "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n" +
                "Total area:50.26548245743669";
        assertEquals(expectedString, outContent.toString().trim());
    }
}