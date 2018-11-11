package com.viskontas.shapesprogram.service;

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
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ActionResolverServiceTest {

    private ActionResolverService actionHelp;
    private ActionResolverService actionExit;
    protected ActionResolverService saveShape;
    protected ActionResolverService findShape;

    @Mock
    protected ShapeRepository shapeRepository;
    private ShapeValidatorService shapeValidatorService;
    private PrintingService printingService;

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

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
    public void do_comamnd_help() {
        actionHelp.doCommand(null);
        String expectedString = "help text";
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void do_comamnd_exit() {
        //TODO
    }


}