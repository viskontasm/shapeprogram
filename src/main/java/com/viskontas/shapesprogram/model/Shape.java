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
    protected double[] shapeData;

    protected Shape(String shapeName, double... shapeData) {
        this.shapeName = shapeName;
        this.shapeData = shapeData.clone();
    }

    abstract boolean isInsideShape(double... shapeData);
    public abstract String getShapeInformation();

    public String getShapeName() {
        return shapeName;
    }

    public double[] getShapeData() {
        if (shapeData != null) {
            return shapeData.clone();
        }
        return null;
    }

    public void setShapeData(double... shapeData) {
        if (shapeData != null) {
            this.shapeData = shapeData.clone();
        }
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }
}
