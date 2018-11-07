package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Circle extends Shape {

    public Circle(String shapeName, double... points) {
        super(shapeName, points);
    }

    @Override
    void getData(double... points) {

    }
}
