package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;
import java.util.stream.IntStream;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Triangle extends Shape {

    public Triangle(int shapeDataCount) {
        super(shapeDataCount);
    }

    @Override
    public void printShapeInformation(int shapeDataId) {
        double[] rawCoordinates = shapeData.get(shapeDataId);
        System.out.println("triangle-" + shapeDataId + " with coordinates x1("
            + rawCoordinates[0] + "," + rawCoordinates[1] + "), x2("
            + rawCoordinates[2] + "," + rawCoordinates[3] + "), x3("
            + rawCoordinates[4] + "," + rawCoordinates[5] + ");");
    }

    @Override
    public void printInsideShapes(double... lookUpPoint) {
        System.out.println("All shapes, which have inside coordinate x("
            + lookUpPoint[0] + "," + lookUpPoint[1] + "):");

        IntStream.range(0, shapeData.size())
            .filter(shapeDataId -> insideCalculation(shapeDataId, lookUpPoint))
            .forEach(this::printShapeInformation);
    }

    private boolean insideCalculation(int shapeDataId, double... lookUpPoint) {
        double[] rawCoord = shapeData.get(shapeDataId);
        double mainArea = surfaceArea(rawCoord[0], rawCoord[1], rawCoord[2],
            rawCoord[3], rawCoord[4], rawCoord[5]);

        double smal1Area1 = surfaceArea(lookUpPoint[0], lookUpPoint[1], rawCoord[2], rawCoord[3], rawCoord[4], rawCoord[5]);
        double smal1Area2 = surfaceArea(rawCoord[0], rawCoord[1], lookUpPoint[0], lookUpPoint[1], rawCoord[4], rawCoord[5]);
        double smal1Area3 = surfaceArea(rawCoord[0], rawCoord[1], rawCoord[2], rawCoord[3], lookUpPoint[0], lookUpPoint[1]);

        return mainArea == smal1Area1 + smal1Area2 + smal1Area3;
    }

    private double surfaceArea(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2);
    }
}
//triangle calculation info:
//https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/