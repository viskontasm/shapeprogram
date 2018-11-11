package com.viskontas.shapesprogram.usecase;

import com.viskontas.shapesprogram.model.Shape;

public class CircleUsecase extends ShapeUsecase {

    public CircleUsecase(Shape shape) {
        super(shape);
    }

    @Override
    public String getShapeInformation(int shapeDataId) {
        double[] rawCoordinates = shape.getShapeData().get(shapeDataId);
        return "\tcircle-" + shapeDataId + " with centre o1("
                + rawCoordinates[0] + "," + rawCoordinates[1] + ") and radius "
                + rawCoordinates[2] + ";";
    }

    @Override
    public boolean insideCalculation(int shapeDataId, double... lookUpPoint) {
        double[] rawCoord = shape.getShapeData().get(shapeDataId);
        return StrictMath.pow(lookUpPoint[0]-rawCoord[0], 2) +
                StrictMath.pow(lookUpPoint[1] - rawCoord[1], 2) <= StrictMath.pow(rawCoord[2], 2);
    }

    @Override
    public double getSurfaceArea(int shapeDataId) {
        return calculateCircleSurfaceArea(extractRawCoordinates(shapeDataId)[2]);
    }
}
