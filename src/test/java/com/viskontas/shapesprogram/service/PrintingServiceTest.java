package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.impl.PrintingServiceImpl;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import com.viskontas.shapesprogram.usecase.TriangleUsecase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class PrintingServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private PrintingService printingService;
    private ShapeUsecase triangleUsecase;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        printingService = new PrintingServiceImpl();

        Triangle triangle = new Triangle();
        triangle.setShapeName("triangle");
        double[] triangleData = {0, 0, 0, 5, 5, 0};
        triangle.addShapeValues(triangleData);
        triangleUsecase = new TriangleUsecase(triangle);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void print_shape_information_triangle() {
        printingService.printShapeInformation(triangleUsecase,0);
        String expectedString = "triangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0);";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_success_triangle() {
        //may not work on OS not Windows
        double[] lookUpPoint = {1, 2};
        printingService.printInsideShapes(triangleUsecase, lookUpPoint);
        String expectedString = "All triangles, which have inside coordinate x(1.0,2.0):\r\n" +
                "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_inside_fail_triangle() {
        double[] lookUpPoint = {5, 5};
        printingService.printInsideShapes(triangleUsecase, lookUpPoint);
        String expectedString = "No such triangles.";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void print_shape_area() {
        printingService.printShapeArea(triangleUsecase, 0);
        String expectedString = "Surface area:12.5";
        assertEquals(expectedString, outContent.toString().trim());
    }
}