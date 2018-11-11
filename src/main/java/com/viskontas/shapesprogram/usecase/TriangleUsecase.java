package com.viskontas.shapesprogram.usecase;

import com.viskontas.shapesprogram.model.Shape;

public class TriangleUsecase extends ShapeUsecase{

    public TriangleUsecase(Shape shape) {
        super(shape);
    }

    @Override
    public String getShapeInformation(int shapeDataId) {
        double[] rawCoordinates = shape.getShapeData().get(shapeDataId);
        return "\ttriangle-" + shapeDataId + " with coordinates x1("
                + rawCoordinates[0] + "," + rawCoordinates[1] + "), x2("
                + rawCoordinates[2] + "," + rawCoordinates[3] + "), x3("
                + rawCoordinates[4] + "," + rawCoordinates[5] + ");";
    }

    @Override
    public boolean insideCalculation(int shapeDataId, double... lookUpPoint) {
        double[] rawCoord = shape.getShapeData().get(shapeDataId);

        double allArea = getSurfaceArea(shapeDataId);
        double smal1Area1 = calculateSurfaceArea(lookUpPoint[0], lookUpPoint[1], rawCoord[2], rawCoord[3], rawCoord[4], rawCoord[5]);
        double smal1Area2 = calculateSurfaceArea(rawCoord[0], rawCoord[1], lookUpPoint[0], lookUpPoint[1], rawCoord[4], rawCoord[5]);
        double smal1Area3 = calculateSurfaceArea(rawCoord[0], rawCoord[1], rawCoord[2], rawCoord[3], lookUpPoint[0], lookUpPoint[1]);

        return  allArea == smal1Area1 + smal1Area2 + smal1Area3;
    }

    private double calculateSurfaceArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        return StrictMath.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2);
    }

    @Override
    public double getSurfaceArea(int shapeDataId) {
        double[] rawCoord = extractRawCoordinates(shapeDataId);
        return calculateSurfaceArea(rawCoord[0], rawCoord[1], rawCoord[2],
                rawCoord[3], rawCoord[4], rawCoord[5]);
    }
}
