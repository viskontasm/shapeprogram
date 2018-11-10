package com.viskontas.shapesprogram.model;

import javax.persistence.Entity;

@Entity
public class Circle extends Shape {

    public Circle() {
        super("circle", 3);
    }

    @Override
    public void printShapeInformation(int shapeDataId) {
        double[] rawCoordinates = shapeData.get(shapeDataId);
        System.out.println("\tcircle-" + shapeDataId + " with centre o1("
            + rawCoordinates[0] + "," + rawCoordinates[1] + ") and radius "
            + rawCoordinates[2] + ";");
    }

    @Override
    public void printInsideShapes(double... loopUpPoint) {

    }

    @Override
    protected boolean insideCalculation(int shapeDataId, double... lookUpPoint) {
        return false;
    }
}
