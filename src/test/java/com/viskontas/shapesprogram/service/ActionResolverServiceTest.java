package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.impl.PrintingServiceImpl;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelpServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeSaveServiceImpl;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActionResolverServiceTest {

    ActionResolverService actionHelp;
    ActionResolverService actionExit;
    ActionResolverService saveTriangle;

    @Mock
    ShapeRepository shapeRepository;
    ShapeValidatorService shapeValidatorService;
    PrintingService printingService;

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        shapeValidatorService = new ShapeValidatorServiceImpl();
        printingService = new PrintingServiceImpl();

        actionHelp = new HelpServiceImpl();
        actionExit = new ExitServiceImpl();
        saveTriangle = new ShapeSaveServiceImpl(shapeValidatorService, shapeRepository, printingService);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void do_comamnd_help() {
        actionHelp.doCommand();
        String expectedString = "help text";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_exit() {
        //TODO
    }

    @Test
    public void do_comamnd_save_triangle() {
        //may not work on OS not Windows
        List<Shape> noShapesFound = new ArrayList<>();
        when(shapeRepository.findAll()).thenReturn(noShapesFound);

        ShapeUsecase shapeUsecase = saveTriangle.getAvailableShapes().get("triangle");

        String line = "triangle 0 0 0 5 5 0";
        saveTriangle.doCommand(line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_update_triangle() {
        //may not work on OS not Windows
        ShapeUsecase shapeUsecase = saveTriangle.getAvailableShapes().get("triangle");
        double[] shapeValues = {0, 1, 0, 1, 0, 1};
        shapeUsecase.getShape().addShapeValues(shapeValues);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shapeUsecase.getShape());

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "triangle 0 0 0 5 5 0";
        saveTriangle.doCommand(line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_save_circle() {
        //may not work on OS not Windows
        List<Shape> noShapesFound = new ArrayList<>();
        when(shapeRepository.findAll()).thenReturn(noShapesFound);

        ShapeUsecase shapeUsecase = saveTriangle.getAvailableShapes().get("circle");

        String line = "circle 0 0 5";
        saveTriangle.doCommand(line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tcircle-0 with centre o1(0.0,0.0) and radius 5.0; Surface area:78.53981633974483\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_update_circle() {
        //may not work on OS not Windows
        ShapeUsecase shapeUsecase = saveTriangle.getAvailableShapes().get("circle");
        double[] shapeValues = {0, 1, 0, 1, 0, 1};
        shapeUsecase.getShape().addShapeValues(shapeValues);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shapeUsecase.getShape());

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "circle 0 0 5";
        saveTriangle.doCommand(line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tcircle-2 with centre o1(0.0,0.0) and radius 5.0; Surface area:78.53981633974483\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_save_donut() {
        //may not work on OS not Windows
        List<Shape> noShapesFound = new ArrayList<>();
        when(shapeRepository.findAll()).thenReturn(noShapesFound);

        ShapeUsecase shapeUsecase = saveTriangle.getAvailableShapes().get("donut");

        String line = "donut 0 0 3 5";
        saveTriangle.doCommand(line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    public void do_comamnd_update_donut() {
        //may not work on OS not Windows
        ShapeUsecase shapeUsecase = saveTriangle.getAvailableShapes().get("donut");
        double[] shapeValues = {0, 0, 1, 5};
        shapeUsecase.getShape().addShapeValues(shapeValues);
        List<Shape> oneShapeFound = new ArrayList<>();
        oneShapeFound.add(shapeUsecase.getShape());

        when(shapeRepository.findAll()).thenReturn(oneShapeFound);

        String line = "donut 0 0 5 10";
        saveTriangle.doCommand(line.split(" "));

        verify(shapeRepository, times(1)).save(shapeUsecase.getShape());

        String expectedString = "Shape added:\r\n" +
                "\tdonut-2 with centre o1(0.0,0.0) and radiuses 5.0 and 10.0; Surface area:235.61944901923448\r\n";
        assertEquals(expectedString, outContent.toString());
    }

    //TODO other shapes if will be
}