package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public abstract class Shape {

    @Id
    @GeneratedValue
    protected Long id;
    protected String shapeName;
    protected double[] points;

    protected Shape(String shapeName, double... points) {
        this.shapeName = shapeName;
        this.points = points.clone();
    }

    abstract void getData(double... points);
}
