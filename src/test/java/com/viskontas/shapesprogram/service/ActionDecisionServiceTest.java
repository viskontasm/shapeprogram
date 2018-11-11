package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.service.impl.ActionDecisionServiceImpl;
import com.viskontas.shapesprogram.service.impl.ShapeValidatorServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ExitServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.FileServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.HelpServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeFindServiceImpl;
import com.viskontas.shapesprogram.service.impl.action.ShapeSaveServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionDecisionServiceTest {

    private ActionDecisionService actionDecisionService;

    @Mock
    private ShapeSaveServiceImpl shapeSaveService;
    @Mock
    private ShapeFindServiceImpl shapeFindService;
    @Mock
    private HelpServiceImpl helperService;
    @Mock
    private ExitServiceImpl exitServiceImpl;
    @Mock
    private FileServiceImpl fileService;
    @Mock
    ShapeValidatorService shapeValidatorService;

    @Before
    public void setUp() {
        ShapeValidatorServiceImpl shapeValidator = new ShapeValidatorServiceImpl();
        actionDecisionService = new ActionDecisionServiceImpl(shapeSaveService, shapeFindService,
                helperService, exitServiceImpl, fileService);
    }

    @Test
    public void decicion_to_save_triangle()  {
        String line = "triangle 0 0 0 5 5 0";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(line);
        verify(shapeSaveService, times(1))
                .doCommand(actionDecisionService, line.split(" "));
    }

    @Test
    public void decicion_to_save_circle() {
        String line = "circle 0 0 5";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(line);
        verify(shapeSaveService, times(1))
                .doCommand(actionDecisionService, line.split(" "));
    }

    @Test
    public void decicion_to_save_donut()  {
        String line = "donut 0 0 3 5";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(line);
        verify(shapeSaveService, times(1))
                .doCommand(actionDecisionService, line.split(" "));
    }

    //TODO other shapes if will be

    @Test
    public void decicion_find_inside_shapes() {
        String line = "1 2";
        List <String> lineList = new ArrayList<>();
        lineList.add(line); //TODO
        actionDecisionService.decide(line);
        verify(shapeFindService, times(1))
                .doCommand(actionDecisionService, line.split(" "));
    }

    @Test
    public void decicion_to_find_shapes()  {
        String line = "1 2";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(line);
        verify(shapeFindService, times(1))
                .doCommand(actionDecisionService, line.split(" "));
    }

    @Test
    public void decicion_to_ask_help() {
        String line = "help";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(line);
        verify(helperService, times(1)).doCommand(actionDecisionService, line);
    }

    @Test
    public void decicion_to_exit() {
        String line = "exit";
        List <String> lineList = new ArrayList<>();
        lineList.add(line);
        actionDecisionService.decide(line);
        verify(exitServiceImpl, times(1)).doCommand(actionDecisionService, line);
    }
}