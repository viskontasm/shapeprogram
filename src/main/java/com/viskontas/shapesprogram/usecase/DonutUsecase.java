package com.viskontas.shapesprogram.usecase;

import com.viskontas.shapesprogram.model.Shape;

public class DonutUsecase extends ShapeUsecase {

    public DonutUsecase(Shape shape) {
        super(shape);
    }

    @Override
    public String getShapeInformation(int shapeDataId) {
        double[] rawCoordinates = shape.getShapeData().get(shapeDataId);
        return "\tdonut-" + shapeDataId + " with centre o1("
                + rawCoordinates[0] + "," + rawCoordinates[1] + ") and radiuses "
                + rawCoordinates[2] + " and " + rawCoordinates[3] + ";";
    }

    @Override
    public boolean insideCalculation(int shapeDataId, double... lookUpPoint) {
        double[] rawCoord = shape.getShapeData().get(shapeDataId);
        double calc = StrictMath.pow(lookUpPoint[0]-rawCoord[0], 2) +
                StrictMath.pow(lookUpPoint[1] - rawCoord[1], 2);
        boolean isSmallerCircle = calc < StrictMath.pow(rawCoord[2], 2);
        boolean isBiggerCircle = calc <= StrictMath.pow(rawCoord[3], 2);
        return isBiggerCircle && !isSmallerCircle;
    }

    @Override
    public double getSurfaceArea(int shapeDataId) {
        double[] rawCoord = extractRawCoordinates(shapeDataId);
        double smallCircleArea = calculateCircleSurfaceArea(rawCoord[2]);
        double bigCircleArea = calculateCircleSurfaceArea(rawCoord[3]);
        return bigCircleArea - smallCircleArea;
    }
}
