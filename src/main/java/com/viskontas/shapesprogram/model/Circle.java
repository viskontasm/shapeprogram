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
        System.out.print("\tcircle-" + shapeDataId + " with centre o1("
            + rawCoordinates[0] + "," + rawCoordinates[1] + ") and radius "
            + rawCoordinates[2] + ";");
     }

    @Override
    boolean insideCalculation(int shapeDataId, double... lookUpPoint) {
        double[] rawCoord = shapeData.get(shapeDataId);
        return StrictMath.pow(lookUpPoint[0]-rawCoord[0], 2) +
                StrictMath.pow(lookUpPoint[1] - rawCoord[1], 2) <= StrictMath.pow(rawCoord[2], 2);
    }

    @Override
    public double getSurfaceArea(int shapeDataId) {
        return circleSurfaceArea(extractRawCoordinates(shapeDataId)[2]);
    }
}
