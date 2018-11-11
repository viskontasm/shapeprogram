package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import com.viskontas.shapesprogram.service.validator.exception.ShapeException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ShapeValidatorServiceTest {

    private ShapeValidatorService shapeValidatorService;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        shapeValidatorService = new ShapeValidatorServiceImpl();
    }

    @Test
    public void validate_null_shape_name() throws ShapeException {
        assertFalse(shapeValidatorService.isOkShapeName(null));
    }

    @Test
    public void validate_empty_shape_name() throws ShapeException {
        assertFalse(shapeValidatorService.isOkShapeName(""));
    }

    @Test
    public void validate_ok_shape_name() throws ShapeException {
        assertTrue(shapeValidatorService.isOkShapeName("circle"));
    }

    @Test
    public void validate_not_double() throws ShapeException {
        exceptionRule.expect(ShapeException.class);
        exceptionRule.expectMessage("First value is not shape and not coordinate: ");
        shapeValidatorService.validateDouble("notdouble");
    }

    @Test
    public void validate_ok_double() throws ShapeException {
        shapeValidatorService.validateDouble("1.111");
    }

    @Test
    public void validate_shape_data_bad_count() throws ShapeException {
        exceptionRule.expect(ShapeException.class);
        exceptionRule.expectMessage("Not correct data values count");
        String[] shapeData = {"0", "0", "0", "5", "5"};
        shapeValidatorService.validateShapeData(6, shapeData);
    }

    @Test
    public void validate_shape_data_not_double() throws ShapeException {
        exceptionRule.expect(ShapeException.class);
        exceptionRule.expectMessage(" Not number coordinate exists");
        String[] shapeData = {"0", "0", "0", "5", "5", "a"};
        shapeValidatorService.validateShapeData(6, shapeData);
    }

    @Test
    public void validate_shape_data_ok() throws ShapeException {
        String[] shapeData = {"0", "0", "0", "5", "5", "1"};
        shapeValidatorService.validateShapeData(6, shapeData);
    }

    @Test
    public void validate_lookup_coordinates_bad_count() throws ShapeException {
        exceptionRule.expect(ShapeException.class);
        exceptionRule.expectMessage("Not correct data values count");
        String[] shapeData = {"0", "0"};
        shapeValidatorService.validateLookUpCoordinates(3, shapeData);
    }

    @Test
    public void validate_lookup_coordinates_not_double() throws ShapeException {
        exceptionRule.expect(ShapeException.class);
        exceptionRule.expectMessage(" Not number coordinate exists");
        String[] shapeData = {"0", "a"};
        shapeValidatorService.validateLookUpCoordinates(2, shapeData);
    }

    @Test
    public void validate_lookup_coordinates_ok() throws ShapeException {
        String[] shapeData = {"0", "0"};
        shapeValidatorService.validateLookUpCoordinates(2, shapeData);
    }

    @Test
    public void validate_shapes_availability_fail() throws ShapeException {
        exceptionRule.expect(ShapeException.class);
        exceptionRule.expectMessage("No shapes found:");
        shapeValidatorService.validateShapesAvailability(new ArrayList<>());
    }

    @Test
    public void validate_shapes_availability_ok() throws ShapeException {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Triangle());
        shapeValidatorService.validateShapesAvailability(shapes);
    }
}