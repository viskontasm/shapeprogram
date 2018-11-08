package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import com.viskontas.shapesprogram.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AvailableShapes {

    ShapeService shapeService;
    private static Map<String, Shape> shapes;

    @Autowired
    public AvailableShapes(ShapeService shapeService) {
        this.shapeService = shapeService;
        shapes = new HashMap<>();
        shapes.put("triangle", new Triangle());
        shapes.put("circle", new Circle());
    }

    public Shape getShapeWithData(String shapeName, double... shapeData)  {
        Shape shape = shapes.get(shapeName);
        shape.setShapeName(shapeName);
        shape.setShapeData(shapeData);
        return shape;
    }

    public static boolean shapeExists(String shapeName) {
        return shapes.get(shapeName) != null;
    }
}
