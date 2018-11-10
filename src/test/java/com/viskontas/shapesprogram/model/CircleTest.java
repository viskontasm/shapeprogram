package com.viskontas.shapesprogram.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CircleTest extends ShapeTest {

    @Test
    public void print_shape_information_circle() {
        circle.printShapeInformation(0);
        String expectedString = "circle-0 with centre o1(0.0,0.0) and radius 5.0;";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_success_circle() {
        //may not work on OS not Windows
        double[] lookUpPoint = {1, 2};
        circle.printInsideShapes(lookUpPoint);
        String expectedString = "All circles, which have inside coordinate x(1.0,2.0):\r\n" +
                "\tcircle-0 with centre o1(0.0,0.0) and radius 5.0;";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_fail_circle() {
        double[] lookUpPoint = {5, 5};
        circle.printInsideShapes(lookUpPoint);
        String expectedString = "No such circles.";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void inside_calculation_circle_success() {
        double[] lookUpPoint = {1, 2};
        assertTrue(circle.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_circle_success_on_line() {
        double[] lookUpPoint = {0, 5};
        assertTrue(circle.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_circle_fail() {
        double[] lookUpPoint = {5, 5};
        assertFalse(circle.insideCalculation(0, lookUpPoint));
    }
}