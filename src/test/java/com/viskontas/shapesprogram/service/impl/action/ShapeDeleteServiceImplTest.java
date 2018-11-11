package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import com.viskontas.shapesprogram.service.impl.ActionDecisionServiceImpl;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShapeDeleteServiceImplTest extends ActionResolverServiceTest {

    private ActionDecisionService actionDecisionService;
    private ActionResolverService deleteShape;

    @Before
    public void setUp2() {
        deleteShape = new ShapeDeleteServiceImpl(shapeValidatorService, shapeRepository, printingService);

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
        when(shapeRepository.findAll()).thenReturn(noShapesFound);

        ShapeUsecase shapeUsecase = deleteShape.getAvailableShapes().get("triangle");
        String line = "delete triangle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));
        String expectedString = "ERROR: shape not found: [delete, triangle-0]";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_shape_fail_existshape_but_id_wrong() {
        when(shapeRepository.findAll()).thenReturn(oneTriangleFound);

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
        when(shapeRepository.findAll()).thenReturn(oneTriangleFound);

        String line = "delete triangle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "Shape deleted: triangle-0";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_circle_success() {
        when(shapeRepository.findAll()).thenReturn(oneCircleFound);

        String line = "delete circle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "Shape deleted: circle-0";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_delete_donut_success() {
        when(shapeRepository.findAll()).thenReturn(oneDonutFound);

        String line = "delete circle-0";
        deleteShape.doCommand(actionDecisionService, line.split(" "));

        String expectedString = "ERROR: shape not found: [delete, circle-0]";
        assertEquals(expectedString, outContent.toString().trim());
    }
}