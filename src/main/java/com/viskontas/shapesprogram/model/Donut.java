package com.viskontas.shapesprogram.model;

import javax.persistence.Entity;

@Entity
public class Donut extends Shape {

    public Donut() {
        super("donut", 4);
    }

    @Override
    public void printShapeInformation(int shapeDataId) {
        double[] rawCoordinates = shapeData.get(shapeDataId);
        System.out.print("\tdonut-" + shapeDataId + " with centre o1("
                + rawCoordinates[0] + "," + rawCoordinates[1] + ") and radiuses "
                + rawCoordinates[2] + " and " + rawCoordinates[3] + ";");
    }

    @Override
    boolean insideCalculation(int shapeDataId, double... lookUpPoint) {
        double[] rawCoord = shapeData.get(shapeDataId);
        double calc = StrictMath.pow(lookUpPoint[0]-rawCoord[0], 2) +
                StrictMath.pow(lookUpPoint[1] - rawCoord[1], 2);
        boolean isSmallerCircle = calc < StrictMath.pow(rawCoord[2], 2);
        boolean isBiggerCircle = calc <= StrictMath.pow(rawCoord[3], 2);
        return isBiggerCircle && !isSmallerCircle;
    }

    @Override
    public void setSurfaceArea(int shapeDataId) {
        double[] rawCoord = extractRawCoordinates(shapeDataId);
        double smallCircleArea = circleSurfaceArea(rawCoord[2]);
        double bigCircleArea = circleSurfaceArea(rawCoord[3]);
        surfaceArea += bigCircleArea - smallCircleArea;
    }
}