package com.viskontas.shapesprogram.usecase;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Donut;
import com.viskontas.shapesprogram.model.Triangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeUsecaseTest {

    protected ShapeUsecase triangleUsecase;
    protected Triangle triangle;

    protected ShapeUsecase circleUsecase;
    protected Circle circle;

    protected ShapeUsecase donutUsecase;
    protected Donut donut;

    @Before
    public void setUpStreams() {
        triangle = new Triangle();
        triangle.setShapeName("triangle");
        double[] triangleData = {0, 0, 0, 5, 5, 0};
        triangle.addShapeValues(triangleData);
        triangleUsecase = new TriangleUsecase(triangle);

        circle = new Circle();
        circle.setShapeName("circle");
        double[] circleData = {0, 0, 5};
        circle.addShapeValues(circleData);
        circleUsecase = new CircleUsecase(circle);

        donut = new Donut();
        donut.setShapeName("donut");
        double[] donutData = {0, 0, 3, 5};
        donut.addShapeValues(donutData);
        donutUsecase = new DonutUsecase(donut);
    }

    @Test
    public void get_shape_ids_which_inside_shape_success() {
        double[] lookUpPoint = {1, 2};
        assertEquals(1, triangleUsecase.getShapeIdsWhichInsideShape(lookUpPoint).size());
    }

    @Test
    public void get_shape_ids_which_inside_shape_fail() {
        double[] lookUpPoint = {5, 5};
        assertEquals(0, triangleUsecase.getShapeIdsWhichInsideShape(lookUpPoint).size());
    }


    @Test
    public void get_shape() {
        assertEquals(triangle, triangleUsecase.getShape());
    }

    @Test
    public void extract_raw_coordinates() {
        double[] triangleData = {0, 0, 0, 5, 5, 0};
        //TODO array ?? assertEquals(triangleData, shapeUsecase.extractRawCoordinates(0));
    }

    @Test
    public void circle_surface_area() {
        assertEquals(78.53981633974483, circleUsecase.calculateCircleSurfaceArea(5), 0);
    }

}