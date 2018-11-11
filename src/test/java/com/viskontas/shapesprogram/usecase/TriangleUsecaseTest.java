package com.viskontas.shapesprogram.usecase;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleUsecaseTest extends ShapeUsecaseTest{

    @Test
    public void inside_calculation_triangle_success() {
        double[] lookUpPoint = {1, 2};
        assertTrue(triangleUsecase.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_triangle_success_on_line() {
        double[] lookUpPoint = {0, 3};
        assertTrue(triangleUsecase.insideCalculation(0, lookUpPoint));
    }

    @Test
    public void inside_calculation_triangle_fail() {
        double[] lookUpPoint = {5, 5};
        assertFalse(triangleUsecase.insideCalculation(0, lookUpPoint));
    }
}