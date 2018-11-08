package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Triangle extends Shape {

    public Triangle(int shapeDataCount) {
        super(shapeDataCount);
    }

    @Override
    public String getShapeInformation() {
        int identifier = shapeData.size()-1;
        double[] rawCoordinates = shapeData.get(identifier);
        return "triangle-" + identifier + " with coordinates - x1("
                + rawCoordinates[0] + "," + rawCoordinates[1] + "), x2("
                + rawCoordinates[2] + "," + rawCoordinates[3] + "), x3("
                + rawCoordinates[4] + "," + rawCoordinates[5] + ");";
    }

    //https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/
}
