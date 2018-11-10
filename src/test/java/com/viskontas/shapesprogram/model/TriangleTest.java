package com.viskontas.shapesprogram.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest extends ShapeTest {

    @Test
    public void print_shape_information_triangle() {
        triangle.printShapeInformation(0);
        String expectedString = "triangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0);";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_success_triangle() {
        //may not work on OS not Windows
        double[] lookUpPoint = {1, 2};
        triangle.printInsideShapes(lookUpPoint);
        String expectedString = "All triangles, which have inside coordinate x(1.0,2.0):\r\n" +
                "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0);";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_fail_triangle() {
        double[] lookUpPoint = {5, 5};
        triangle.printInsideShapes(lookUpPoint);
        String expectedString = "No such shapes.";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void inside_calculation_triangle_success() {
        double[] lookUpPoint = {1, 2};
        assertTrue(triangle.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_triangle_fail() {
        double[] lookUpPoint = {5, 5};
        assertFalse(triangle.insideCalculation(0, lookUpPoint));
    }
}