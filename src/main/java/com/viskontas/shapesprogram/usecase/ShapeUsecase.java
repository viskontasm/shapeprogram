package com.viskontas.shapesprogram.usecase;

import com.viskontas.shapesprogram.model.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ShapeUsecase {

    protected Shape shape;
    protected ShapeUsecase(Shape shape) {
        this.shape = shape;
    }

    public abstract boolean insideCalculation(int shapeDataId, double... lookUpPoint);
    public abstract String getShapeInformation(int shapeDataId);
    public abstract double getSurfaceArea(int shapeDataId);

    public List<Integer> getShapeIdsWhichInsideShape(double... lookUpPoint) {
        return IntStream.range(0, shape.getShapeData().size())
                .filter(shapeDataId -> insideCalculation(shapeDataId, lookUpPoint))
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    protected double[] extractRawCoordinates(int shapeDataId) {
        return shape.getShapeData().get(shapeDataId);
    }

    protected double calculateCircleSurfaceArea(double r) {
        return Math.PI * StrictMath.pow(r, 2);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
