package com.viskontas.shapesprogram.service.impl;

import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import com.viskontas.shapesprogram.service.PrintingService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrintingServiceImpl implements PrintingService {

    @Override
    public void printInsideShapes(ShapeUsecase shapeUsecase, Double totalArea, double... lookUpPoint) {
        List<Integer> shapeDataIds = shapeUsecase.getShapeIdsWhichInsideShape(lookUpPoint);
        if (shapeDataIds.isEmpty()) {
            System.out.println("No such " + shapeUsecase.getShape().getShapeName() + "s.");
        } else {
            System.out.println("All " + shapeUsecase.getShape().getShapeName() + "s, which have inside coordinate x("
                    + lookUpPoint[0] + "," + lookUpPoint[1] + "):");

            for (int id : shapeDataIds) {
                printShapeInformation(shapeUsecase, id);
                printShapeArea(shapeUsecase, id);
                totalArea += shapeUsecase.getSurfaceArea(id);
            }
            //System.out.println("Total area: " + totalArea);
        }
    }

    @Override
    public void printShapeInformation(ShapeUsecase shapeUsecase, int id) {
        System.out.print(shapeUsecase.getShapeInformation(id));
    }

    @Override
    public void printShapeArea(ShapeUsecase shapeUsecase, int shapeId) {
        System.out.println(" Surface area:" + shapeUsecase.getSurfaceArea(shapeId));
    }

    @Override
    public void printTotalArea(double totalArea) {
        System.out.println("Total area:" + totalArea);
    }
}
