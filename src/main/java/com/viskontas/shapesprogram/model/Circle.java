package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Circle extends Shape {

    public Circle(String shapeName, double... shapeData) {
        super(shapeName, shapeData);
    }

    @Override
    public String getShapeInformation() {
        return "bb";
    }

    @Override
    boolean isInsideShape(double... shapeData) {
        return false;
    }
}
