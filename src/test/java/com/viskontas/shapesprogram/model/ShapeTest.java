package com.viskontas.shapesprogram.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.Assert.*;

public class ShapeTest {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;
    protected Shape triangle;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

        triangle = new Triangle(6);
        triangle.setShapeName("triangle");
        double[] shapeData = {0, 0, 0, 5, 5, 0};
        triangle.addShapeValues(shapeData);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
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

    @Test
    public void getShapeIdsWhichInsideShape() {
        double[] lookUpPoint = {1, 2};
        assertEquals(1, triangle.getShapeIdsWhichInsideShape(lookUpPoint).size());
    }
}