package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Circle extends Shape {

    public Circle(int shapeDataCount) {
        super(shapeDataCount);
    }

    @Override
    public void printShapeInformation(int shapeDataId) {
        double[] rawCoordinates = shapeData.get(shapeDataId);
        System.out.println("circle-" + shapeDataId + " with centre o1("
            + rawCoordinates[0] + "," + rawCoordinates[1] + ") and radius "
            + rawCoordinates[2] + ";");
    }

    @Override
    public void printInsideShapes(double... loopUpPoint) {

    }
}
