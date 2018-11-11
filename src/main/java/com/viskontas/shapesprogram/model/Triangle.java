package com.viskontas.shapesprogram.model;

import javax.persistence.Entity;

@Entity
public class Triangle extends Shape {
    public Triangle() {
        super("triangle", 6);
    }
}