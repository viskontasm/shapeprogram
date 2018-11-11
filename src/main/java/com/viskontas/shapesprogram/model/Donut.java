package com.viskontas.shapesprogram.model;

import javax.persistence.Entity;

@Entity
public class Donut extends Shape {
    public Donut() {
        super("donut", 4);
    }
}