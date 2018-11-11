package com.viskontas.shapesprogram.usecase;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircleUsecaseTest extends ShapeUsecaseTest{

    @Test
    public void inside_calculation_circle_success() {
        double[] lookUpPoint = {1, 2};
        assertTrue(circleUsecase.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_circle_success_on_line() {
        double[] lookUpPoint = {0, 5};
        assertTrue(circleUsecase.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_circle_fail() {
        double[] lookUpPoint = {5, 5};
        assertFalse(circleUsecase.insideCalculation(0, lookUpPoint));
    }
}