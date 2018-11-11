package com.viskontas.shapesprogram.service;

import com.viskontas.shapesprogram.usecase.ShapeUsecase;

public interface PrintingService {
    void printInsideShapes(ShapeUsecase shapeUsecase, Double totalArea, double... lookUpPoint);
    void printShapeInformation(ShapeUsecase shapeUsecase, int id);
    void printShapeArea(ShapeUsecase shapeUsecase, int shapeId);
    void printTotalArea(double totalArea); //TODO
}
