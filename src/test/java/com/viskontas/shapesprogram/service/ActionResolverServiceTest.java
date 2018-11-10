package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelpServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class ActionResolverServiceTest {

    ActionResolverService actionHelp;
    ActionResolverService actionExit;

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        actionHelp = new HelpServiceImpl();
        actionExit = new ExitServiceImpl();
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
}