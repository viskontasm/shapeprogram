package com.viskontas.shapesprogram.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DonutTest extends ShapeTest {

    @Test
    public void print_shape_information_donut() {
        donut.printShapeInformation(0);
        String expectedString = "donut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0;";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_success_donut() {
        //may not work on OS not Windows
        double[] lookUpPoint = {0, 4};
        donut.printInsideShapes(lookUpPoint);
        String expectedString = "All donuts, which have inside coordinate x(0.0,4.0):\r\n" +
                "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0;";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_fail_donut() {
        double[] lookUpPoint = {5, 5};
        donut.printInsideShapes(lookUpPoint);
        String expectedString = "No such donuts.";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void inside_calculation_donut_success() {
        double[] lookUpPoint = {0, 4};
        assertTrue(donut.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_donut_success_on_line1() {
        double[] lookUpPoint = {0, 3};
        assertTrue(circle.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_donut_success_on_line2() {
        double[] lookUpPoint = {0, 5};
        assertTrue(circle.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_donut_fail() {
        double[] lookUpPoint = {5, 5};
        assertFalse(donut.insideCalculation(0, lookUpPoint));
    }
}