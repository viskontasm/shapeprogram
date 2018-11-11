package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Donut;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import com.viskontas.shapesprogram.service.impl.ActionDecisionServiceImpl;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ShapeDeleteServiceImplTest extends ActionResolverServiceTest {

    private ActionDecisionService actionDecisionService;

    @Before
    public void setUp2() {
        actionDecisionService = new ActionDecisionServiceImpl(
                new ShapeSaveServiceImpl(null, null, null),
                new ShapeFindServiceImpl(null, null, null),
                new ShapeDeleteServiceImpl(null, null, null),
                new HelpServiceImpl(),
                new ExitServiceImpl(),
                new FileServiceImpl());
    }

    @Test
    public void do_comamnd_delete_shape_fail_no_shapes() {
        List<Shape> noShapesFound = new ArrayList<>();

        when(shapeRepository.findAll()).thenReturn(noShapesFound);

        ShapeUsecase shapeUsecase = deleteShape.getAvailableShapes().get("triangle");
        String line = "delete triangle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));
        String expectedString = "ERROR: shape not found: [delete, triangle-0]";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_shape_fail_existshape_but_id_wrong() {
        Shape shape = new Triangle();
        shape.setShapeName("triangle");
        double[] shapeD = {0, 0, 0, 5, 5, 0};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        shape.setShapeData(shapeData);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shape);

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "delete triangle-111";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "ERROR: triangle not found: [delete, triangle-111]";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_shape() {
        String line = "delete notexist-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "ERROR: Not correct delete command: [delete, notexist-0]";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_shape_success() {
        Shape shape = new Triangle();
        shape.setShapeName("triangle");
        double[] shapeD = {0, 0, 0, 5, 5, 0};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        shape.setShapeData(shapeData);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shape);

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "delete triangle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "Shape deleted: triangle-0";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_circle_success() {
        Shape shape = new Circle();
        shape.setShapeName("circle");
        double[] shapeD = {0, 0, 5};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        shape.setShapeData(shapeData);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shape);

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "delete circle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "Shape deleted: circle-0";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_donut_success() {
        Shape shape = new Donut();
        shape.setShapeName("donut");
        double[] shapeD = {0, 0, 5};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        shape.setShapeData(shapeData);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shape);

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "delete circle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "ERROR: shape not found: [delete, circle-0]";
        assertEquals(expectedString, outContent.toString().trim());
    }
}