package com.viskontas.shapesprogram.model;

import javax.persistence.Entity;

@Entity
public class Circle extends Shape {
    public Circle() {
        super("circle", 3);
    }
}
