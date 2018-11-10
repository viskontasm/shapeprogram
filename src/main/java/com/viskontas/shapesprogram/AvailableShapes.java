package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AvailableShapes {

    private static Map<String, Shape> shapes;

    public AvailableShapes() {
        shapes = new HashMap<>();
        shapes.put("triangle", new Triangle(6));
        shapes.put("circle", new Circle(3));
    }

    public Shape getShape(String shapeName) {
        Shape shape = shapes.get(shapeName);
        shape.setShapeName(shapeName);
        return shape;
    }

    public static boolean shapeExists(String shapeName) {
        return shapes.get(shapeName) != null;
    }
}
