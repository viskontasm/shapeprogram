package com.viskontas.shapesprogram;

import com.viskontas.shapesprogram.model.Circle;
import com.viskontas.shapesprogram.model.Shape;
import com.viskontas.shapesprogram.model.Triangle;
import org.apache.commons.lang3.EnumUtils;


public enum AvailableShapes {
    TRIANGLE {
        @Override
        public Shape createShape(String shapeName, double... points) {
            return new Triangle(shapeName, points);
        }
    },
    CIRCLE {
        @Override
        public Shape createShape(String shapeName, double... points) {
            return new Circle(shapeName, points);
        }
    };
    public abstract Shape createShape(String shapeName, double... points);

    public static AvailableShapes fromValue(String shapeName) {
        if (shapeName != null && EnumUtils.isValidEnum(AvailableShapes.class, shapeName)) {
            return valueOf(shapeName);
        }
        return null;
    }
}