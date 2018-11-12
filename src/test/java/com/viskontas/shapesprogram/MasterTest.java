package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.repository.ShapeRepository;
import com.viskontas.shapesprogram.service.ActionDecisionService;
import com.viskontas.shapesprogram.service.PrintingService;
import com.viskontas.shapesprogram.service.ShapeValidatorService;
import com.viskontas.shapesprogram.service.impl.ActionDecisionServiceImpl;
import com.viskontas.shapesprogram.service.impl.PrintingServiceImpl;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.FileServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelpServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeDeleteServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeFindServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeSaveServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
public class MasterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Autowired
    private ShapeRepository shapeRepository;
    private ActionDecisionService actionDecisionService;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        ShapeValidatorService shapeValidatorService = new ShapeValidatorServiceImpl();
        PrintingService printingService = new PrintingServiceImpl();
        ShapeSaveServiceImpl shapeSaveService = new ShapeSaveServiceImpl(shapeValidatorService, shapeRepository, printingService);
        ShapeFindServiceImpl shapeFindService = new ShapeFindServiceImpl(shapeValidatorService, shapeRepository, printingService);
        ShapeDeleteServiceImpl shapeDeleteService = new ShapeDeleteServiceImpl(shapeValidatorService, shapeRepository, printingService);
        HelpServiceImpl helperService = new HelpServiceImpl();
        ExitServiceImpl exitServiceImpl = new ExitServiceImpl();
        FileServiceImpl fileService = new FileServiceImpl();

        actionDecisionService =  new ActionDecisionServiceImpl(shapeSaveService, shapeFindService, shapeDeleteService,
            helperService, exitServiceImpl, fileService);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void master_test()  {
        FileServiceImpl fileService = new FileServiceImpl();
        List<String> lines = fileService.readFromFile("sample-data-for-tests.txt");
        lines.forEach(actionDecisionService::decide);

        String expectedString = "No shapes found: [1, 2]\r\n"
            + "Shape added:\r\n"
            + "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n"
            + "ERROR: Not correct data values count: [triangle, 0, 0, 0, 5, -2, 0, 0]\r\n"
            + "Shape added:\r\n"
            + "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(-2.0,0.0); Surface area:5.0\r\n"
            + "ERROR: First value is not shape and not coordinate: [nera, 1, 1, 1, 1]\r\n"
            + "Shape added:\r\n"
            + "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "ERROR: Not correct data values count: [circle, 0, 0]\r\n"
            + "Shape added:\r\n"
            + "\tcircle-0 with centre o1(0.0,0.0) and radius 1.0; Surface area:3.141592653589793\r\n"
            + "Shape added:\r\n"
            + "\tcircle-1 with centre o1(0.0,0.0) and radius 2.0; Surface area:12.566370614359172\r\n"
            + "Shape added:\r\n"
            + "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "Shape added:\r\n"
            + "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n"
            + "All triangles, which have inside coordinate x(1.0,2.0):\r\n"
            + "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n"
            + "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "No such circles.\r\n"
            + "No such donuts.\r\n"
            + "\n"
            + "\tTotal area:32.5\r\n"
            + "All triangles, which have inside coordinate x(0.0,3.0):\r\n"
            + "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n"
            + "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(-2.0,0.0); Surface area:5.0\r\n"
            + "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "No such circles.\r\n"
            + "All donuts, which have inside coordinate x(0.0,3.0):\r\n"
            + "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n"
            + "\n"
            + "\tTotal area:87.76548245743669\r\n"
            + "All triangles, which have inside coordinate x(0.0,5.0):\r\n"
            + "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n"
            + "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(-2.0,0.0); Surface area:5.0\r\n"
            + "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "No such circles.\r\n"
            + "All donuts, which have inside coordinate x(0.0,5.0):\r\n"
            + "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n"
            + "\n"
            + "\tTotal area:87.76548245743669\r\n"
            + "All triangles, which have inside coordinate x(0.0,4.0):\r\n"
            + "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n"
            + "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(-2.0,0.0); Surface area:5.0\r\n"
            + "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "No such circles.\r\n"
            + "All donuts, which have inside coordinate x(0.0,4.0):\r\n"
            + "\tdonut-0 with centre o1(0.0,0.0) and radiuses 3.0 and 5.0; Surface area:50.26548245743669\r\n"
            + "\n"
            + "\tTotal area:87.76548245743669\r\n"
            + "Shape added:\r\n"
            + "\tcircle-2 with centre o1(0.0,0.0) and radius 3.0; Surface area:28.274333882308138\r\n"
            + "Shape added:\r\n"
            + "\tcircle-3 with centre o1(0.0,0.0) and radius 4.0; Surface area:50.26548245743669\r\n"
            + "All triangles, which have inside coordinate x(0.0,0.0):\r\n"
            + "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n"
            + "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(-2.0,0.0); Surface area:5.0\r\n"
            + "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "All circles, which have inside coordinate x(0.0,0.0):\r\n"
            + "\tcircle-0 with centre o1(0.0,0.0) and radius 1.0; Surface area:3.141592653589793\r\n"
            + "\tcircle-1 with centre o1(0.0,0.0) and radius 2.0; Surface area:12.566370614359172\r\n"
            + "\tcircle-2 with centre o1(0.0,0.0) and radius 3.0; Surface area:28.274333882308138\r\n"
            + "\tcircle-3 with centre o1(0.0,0.0) and radius 4.0; Surface area:50.26548245743669\r\n"
            + "No such donuts.\r\n"
            + "\n"
            + "\tTotal area:131.74777960769381\r\n"
            + "Shape deleted: circle-2\r\n"
            + "All triangles, which have inside coordinate x(0.0,0.0):\r\n"
            + "\ttriangle-0 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(5.0,0.0); Surface area:12.5\r\n"
            + "\ttriangle-1 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(-2.0,0.0); Surface area:5.0\r\n"
            + "\ttriangle-2 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "\ttriangle-3 with coordinates x1(0.0,0.0), x2(0.0,5.0), x3(4.0,0.0); Surface area:10.0\r\n"
            + "All circles, which have inside coordinate x(0.0,0.0):\r\n"
            + "\tcircle-0 with centre o1(0.0,0.0) and radius 1.0; Surface area:3.141592653589793\r\n"
            + "\tcircle-1 with centre o1(0.0,0.0) and radius 2.0; Surface area:12.566370614359172\r\n"
            + "\tcircle-2 with centre o1(0.0,0.0) and radius 4.0; Surface area:50.26548245743669\r\n"
            + "No such donuts.\r\n"
            + "\n"
            + "\tTotal area:103.47344572538566\r\n"
            + "ERROR: circle not found: [delete, circle-111]\r\n"
            + "ERROR: triangle not found: [delete, triangle-111]\r\n";
        assertEquals(expectedString, outContent.toString());
    }
}