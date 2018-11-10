package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.impl.ActionDecisionServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelperServiceImpl;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionDecisionServiceTest {

    private ActionDecisionService actionDecisionService;
    @Mock
    private ShapesResolverService shapesResolverService;
    @Mock
    HelperServiceImpl helperService;
    @Mock
    ExitServiceImpl exitService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        ShapeValidatorServiceImpl shapeValidator = new ShapeValidatorServiceImpl();
        actionDecisionService = new ActionDecisionServiceImpl(shapesResolverService, shapeValidator,
                helperService, exitService);
    }

    @Test
    public void decicion_to_save_shape() throws ShapeException {
        //when(shapeValidator.isOkShapeName("triangle")).thenReturn(true);

        String line = "triangle 0 0 0 5 5 0";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(lineList.stream());
        verify(shapesResolverService, times(1))
                .saveShape(actionDecisionService.getAvailableShapes().get("triangle"),line.split(" "));
    }

    @Test
    public void decicion_to_lookup_shapes() throws ShapeException {
        String line = "1 2";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(lineList.stream());
        verify(shapesResolverService, times(1)).lookUpAllShapes(line.split(" "));
    }

    @Test
    public void decicion_to_ask_help() {
        String line = "help";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(lineList.stream());
        verify(helperService, times(1)).doCommand();
    }

    @Test
    public void decicion_to_exit() throws ShapeException {
        String line = "exit";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(lineList.stream());
        verify(exitService, times(1)).doCommand();
    }

    /*@Test
    public void decicion_bad_frist_value() {
        String line = "notExist 1 1 1 1 1 1";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(lineList.stream());
        String expectedString = "First value is not shape and not coordinate: [notExist, 1, 1, 1, 1, 1, 1]";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test //TODO ???
    public void decicion_bad_values_count() {
        String line = "triangle 1 1 1 1 1 0 0 0 0 0";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(lineList.stream());
        String expectedString = "kaka";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void decicion_in_values_not_number() {
        String line = "notExist 1 1 1 1 1";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        //exceptionRule.expect(ShapeException.class);
        actionDecisionService.decide(lineList.stream());
        String expectedString = "First value is not shape and not coordinate: [notExist, 1, 1, 1, 1, 1, 1]";
        assertEquals(expectedString, outContent.toString().trim());
    }*/
}