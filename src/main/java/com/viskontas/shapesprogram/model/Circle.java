package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Circle extends Shape {

    public Circle(int shapeDataCount) {
        super(shapeDataCount);
    }

    @Override
    public String getShapeInformation() {
        int identifier = shapeData.size()-1;
        double[] rawCoordinates = shapeData.get(identifier);
        return "circle-" + identifier + " with centre o1("
            + rawCoordinates[0] + "," + rawCoordinates[1] + ") and radius "
            + rawCoordinates[2] + ";";
    }

    @Override
    public boolean isInsideShape(double... shapeData) {
        return false;
    }
}
