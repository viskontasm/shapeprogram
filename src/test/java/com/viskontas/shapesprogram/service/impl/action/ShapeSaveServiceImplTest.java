package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShapeSaveServiceImplTest extends ActionResolverServiceTest {

    @Test
    public void do_comamnd_save_triangle() {
        ShapeUsecase shapeUsecase = saveShape.getAvailableShapes().get("triangle");

        String line = "triangle 0 0 0 5 5 0";
        saveShape.doCommand(null, line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_update_triangle() {
        ShapeUsecase triangleUsecase = saveShape.getAvailableShapes().get("triangle");
        double[] shapeValues = {0, 1, 0, 1, 0, 1};
        triangleUsecase.getShape().addShapeValues(shapeValues);

        String line = "triangle 0 0 0 5 5 0";
        saveShape.doCommand(null, line.split(" "));

        verify(shapeRepository, times(1)).save(triangleUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_save_circle() {
        ShapeUsecase shapeUsecase = saveShape.getAvailableShapes().get("circle");

        String line = "circle 0 0 5";
        saveShape.doCommand(null, line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tcircle-0 with centre o1(0.0,0.0) and radius 5.0; Surface area:78.53981633974483\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_update_circle() {
        ShapeUsecase circleUsecase = saveShape.getAvailableShapes().get("circle");
        double[] shapeValues = {0, 1, 0, 1, 0, 1};
        circleUsecase.getShape().addShapeValues(shapeValues);

        String line = "circle 0 0 5";
        saveShape.doCommand(null, line.split(" "));

        verify(shapeRepository, times(1)).save(circleUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tcircle-1 with centre o1(0.0,0.0) and radius 5.0; Surface area:78.53981633974483\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_save_donut() {
        ShapeUsecase shapeUsecase = saveShape.getAvailableShapes().get("donut");

        String line = "donut 0 0 3 5";
        saveShape.doCommand(null, line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_update_donut() {
        ShapeUsecase donutUsecase = saveShape.getAvailableShapes().get("donut");
        double[] shapeValues = {0, 0, 1, 5};
        donutUsecase.getShape().addShapeValues(shapeValues);

        String line = "donut 0 0 5 10";
        saveShape.doCommand(null, line.split(" "));

        verify(shapeRepository, times(1)).save(donutUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tdonut-1 with centre o1(0.0,0.0) and radiuses 5.0 and 10.0; Surface area:235.61944901923448\r\n";
        assertEquals(expectedString, outContent.toString());
    }
}