package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Circle extends Shape {

    public Circle(int shapeDataCount) {
        super(shapeDataCount);
    }

    @Override
    public String getShapeInformation() {
        return "bb";
    }

    //@Override
    //public boolean isInsideShape(double... shapeData) {
    //    return false;
    //}
}
