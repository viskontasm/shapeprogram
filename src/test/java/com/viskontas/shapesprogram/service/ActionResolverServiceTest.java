package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Donut;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.impl.PrintingServiceImpl;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelpServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeFindServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeSaveServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActionResolverServiceTest {

    protected ActionResolverService actionHelp;
    protected ActionResolverService actionExit;
    protected ActionResolverService saveShape;
    protected ActionResolverService findShape;

    protected List<Shape> noShapesFound;
    protected List<Shape> oneTriangleFound;
    protected List<Shape> oneCircleFound;
    protected List<Shape> oneDonutFound;

    @Mock
    protected ShapeRepository shapeRepository;
    protected ShapeValidatorService shapeValidatorService;
    protected PrintingService printingService;

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        noShapesFound = new ArrayList<>();

        Shape triangle = new Triangle();
        triangle.setShapeName("triangle");
        double[] shapeT = {0, 0, 0, 5, 5, 0};
        List<double[]> shapeDataT = new ArrayList<>();
        shapeDataT.add(shapeT);
        triangle.setShapeData(shapeDataT);
        oneTriangleFound = new ArrayList<>();
        oneTriangleFound.add(triangle);

        Shape circle = new Circle();
        circle.setShapeName("circle");
        double[] shapeC = {0, 0, 5};
        List<double[]> shapeDataC = new ArrayList<>();
        shapeDataC.add(shapeC);
        circle.setShapeData(shapeDataC);
        oneCircleFound = new ArrayList<>();
        oneCircleFound.add(circle);

        Shape donut = new Donut();
        donut.setShapeName("donut");
        double[] shapeD = {0, 0, 3, 5};
        List<double[]> shapeData = new ArrayList<>();
        shapeData.add(shapeD);
        donut.setShapeData(shapeData);
        oneDonutFound = new ArrayList<>();
        oneDonutFound.add(donut);

        shapeValidatorService = new ShapeValidatorServiceImpl();
        printingService = new PrintingServiceImpl();

        actionHelp = new HelpServiceImpl();
        actionExit = new ExitServiceImpl();
        saveShape = new ShapeSaveServiceImpl(shapeValidatorService, shapeRepository, printingService);
        findShape = new ShapeFindServiceImpl(shapeValidatorService, shapeRepository, printingService);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void no_test() {
    }
}