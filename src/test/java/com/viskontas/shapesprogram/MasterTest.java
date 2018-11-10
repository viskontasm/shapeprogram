package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.impl.ActionDecisionServiceImpl;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelpServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeFindServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeSaveServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class MasterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private ActionDecisionService actionDecisionService;
    @Mock
    private ShapeRepository shapeRepository;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        ShapeValidatorService shapeValidatorService = new ShapeValidatorServiceImpl();
        ShapeSaveServiceImpl shapeSaveService = new ShapeSaveServiceImpl(shapeValidatorService, shapeRepository);
        ShapeFindServiceImpl shapeFindService = new ShapeFindServiceImpl(shapeValidatorService, shapeRepository);
        HelpServiceImpl helperService = new HelpServiceImpl();
        ExitServiceImpl exitServiceImpl = new ExitServiceImpl();

        actionDecisionService =  new ActionDecisionServiceImpl(shapeSaveService, shapeFindService,
                helperService, exitServiceImpl);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void master_test()  {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sample-data-for-tests.txt").getFile());
        try (Stream<String> lineStream = Files.lines(Paths.get(file.getPath()))) {
            actionDecisionService.decide(lineStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expectedString = "ERROR: No shapes found: [1, 2]\r\n" +
                "help text\r\n" +
                "Shape added:\r\n" +
                "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0);\r\n" +
                "ERROR: Not correct data values count: [triangle, 0, 0, 0, 5, -2, 0, 0]\r\n" +
                "Shape added:\r\n" +
                "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(-2.0,0.0);\r\n" +
                "ERROR: First value is not shape and not coordinate: [nera, 1, 1, 1, 1]\r\n" +
                "Shape added:\r\n" +
                "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0);\r\n" +
                "ERROR: Not correct data values count: [circle, 0, 0]\r\n" +
                "Shape added:\r\n" +
                "\tcircle-0 with centre o1(0.0,0.0) and radius 5.0;\r\n" +
                "Shape added:\r\n" +
                "\tcircle-1 with centre o1(0.0,0.0) and radius 3.0;\r\n" +
                "Shape added:\r\n" +
                "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0);\r\n" +
                "Shape added:\r\n" +
                "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0;\r\n" +
                "ERROR: No shapes found: [1, 2]\r\n" +
                "ERROR: No shapes found: [0, 3]\r\n" +
                "ERROR: No shapes found: [0, 5]\r\n" +
                "ERROR: No shapes found: [0, 4]\r\n" +
                "ERROR: Not correct data values count: [donut, 0, 0, 5]";
        assertEquals(expectedString, outContent.toString().trim());
    }
}
