package com.viskontas.shapesprogram.usecase;

import org.junit.Test;

import static org.junit.Assert.*;

public class DonutUsecaseTest extends ShapeUsecaseTest{

    @Test
    public void inside_calculation_donut_success() {
        double[] lookUpPoint = {0, 4};
        assertTrue(donutUsecase.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_donut_success_on_line1() {
        double[] lookUpPoint = {0, 3};
        assertTrue(donutUsecase.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_donut_success_on_line2() {
        double[] lookUpPoint = {0, 5};
        assertTrue(donutUsecase.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_donut_fail() {
        double[] lookUpPoint = {5, 5};
        assertFalse(donutUsecase.insideCalculation(0, lookUpPoint));
    }
}