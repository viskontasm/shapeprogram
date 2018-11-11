package com.viskontas.shapesprogram.service.impl;

import com.viskontas.shapesprogram.service.impl.action.ShapeFindServiceImpl;
import com.viskontas.shapesprogram.usecase.ShapeUsecase;
import com.viskontas.shapesprogram.service.PrintingService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrintingServiceImpl implements PrintingService {

    @Override
    public void printInsideShapes(ShapeFindServiceImpl shapeFindService, ShapeUsecase shapeUsecase, double... lookUpPoint) {
        List<Integer> shapeDataIds = shapeUsecase.getShapeIdsWhichInsideShape(lookUpPoint);
        if (shapeDataIds.isEmpty()) {
            System.out.println("No such " + shapeUsecase.getShape().getShapeName() + "s.");
        } else {
            System.out.println("All " + shapeUsecase.getShape().getShapeName() + "s, which have inside coordinate x("
                    + lookUpPoint[0] + "," + lookUpPoint[1] + "):");

            for (int id : shapeDataIds) {
                printShapeInformation(shapeUsecase, id);
                printShapeArea(shapeUsecase, id);
                shapeFindService.setTotalArea(shapeFindService.getTotalArea()+shapeUsecase.getSurfaceArea(id));
            }
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
        if (totalArea > 0) {
            System.out.println("Total area:" + totalArea);
        }
    }
}
