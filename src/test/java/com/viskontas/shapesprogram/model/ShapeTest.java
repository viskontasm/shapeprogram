package com.viskontas.shapesprogram.model;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class ShapeTest {

    protected Shape triangle;
    protected Shape circle;
    protected Shape donut;

    @Before
    public void setUpStreams() {
        triangle = new Triangle();
        triangle.setShapeName("triangle");
        double[] triangleData = {0, 0, 0, 5, 5, 0};
        triangle.addShapeValues(triangleData);
    }

    @Test
    public void get_shape_name() {
        assertEquals("triangle", triangle.getShapeName());
    }

    @Test
    public void get_shape_data() {
        double[] expectedResult = {0, 0, 0, 5, 5, 0};
        assertTrue(Arrays.equals(expectedResult, triangle.getShapeData().get(0)));
    }

    @Test
    public void get_triangle_values_count() {
        assertEquals(6, triangle.getShapeDataCount());
    }

    @Test
    public void add_shape_values() {
        double[] shapeData = {0, 0, 0, 5, 4, 0};
        triangle.addShapeValues(shapeData);
        assertEquals(2, triangle.getShapeData().size());
    }

    @Test
    public void setShapeName() {
        triangle.setShapeName("circle");
        assertEquals("circle", triangle.getShapeName());
    }
}