package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverService;
import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileServiceImplTest extends ActionResolverServiceTest {

    private ActionResolverService readFile;

    @Before
    public void setUp2() {
        readFile = new FileServiceImpl();
    }

    @Test
    public void read_resource_file() {
        //TODO
    }
}