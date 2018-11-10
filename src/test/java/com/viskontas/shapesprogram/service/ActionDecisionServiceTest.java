package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.AvailableShapes;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.model.TriangleTest;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import com.viskontas.shapesprogram.service.validator.impl.ShapeValidatorServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActionDecisionServiceTest extends TriangleTest {

    private ActionDecisionService actionDecisionService;
    @Mock
    private ShapeValidatorServiceImpl shapeValidator;
    @Mock
    private AvailableShapes availableShapes;
    @Mock
    private ShapeService shapeService;

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        actionDecisionService = new ActionDecisionService(shapeValidator, availableShapes, shapeService);
        AvailableShapes availableShapes = new AvailableShapes();
    }

    @After
    public void restore() {
        System.setOut(originalOut);
    }

    @Test
    public void decicion_to_save_shape() throws ShapeException {
        List<String> list = new ArrayList<>();
        list.add( "triangle 0 0 0 5 5 0");

        when(shapeValidator.isOkShapeName("triangle")).thenReturn(true);
        when(availableShapes.getShape("triangle")).thenReturn(new Triangle(6));
        doNothing().when(shapeValidator).validateShapeData(6,"0 0 0 5 5 0");

        actionDecisionService.decide(list.stream());
        String expectedString = "triangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0);";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void decicion_to_lookup_shapes() throws ShapeException {
        List<String> list = new ArrayList<>();
        list.add( "1 2");

        when(shapeValidator.isOkShapeName("1")).thenReturn(false);

        actionDecisionService.decide(list.stream());
        String expectedString = "triangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0);";
        assertEquals(expectedString, outContent.toString().trim());
    }
}