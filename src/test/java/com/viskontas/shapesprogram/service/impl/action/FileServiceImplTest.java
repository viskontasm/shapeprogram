package com.viskontas.shapesprogram.service.impl.action;

import com.viskontas.shapesprogram.service.ActionResolverServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileServiceImplTest extends ActionResolverServiceTest {

    private FileServiceImpl readFile;

    @Before
    public void setUp2() {
        readFile = new FileServiceImpl();
    }

    @Test
    public void read_resource_file() {
        List<String> lines = readFile.readFromFile("sample-data-for-tests.txt");
        assertEquals("1 2", lines.get(0));
    }
}