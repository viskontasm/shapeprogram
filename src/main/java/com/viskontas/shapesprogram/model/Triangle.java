package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Triangle extends Shape {

    public Triangle(String shapeName, double... points) {
        super(shapeName, points);
    }

    @Override
    void getData(double... points) {

    }
}
