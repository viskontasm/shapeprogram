package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
public abstract class Shape {

    @Id
    @GeneratedValue
    protected long id;
    @ElementCollection
    protected List<double[]> shapeData;
    protected int shapeDataCount;
    protected String shapeName;

    protected Shape(int shapeDataCount) {
        this.shapeDataCount = shapeDataCount;
        shapeData = new ArrayList<>();
    }

    //public abstract boolean isInsideShape(double... shapeData);
    public abstract String getShapeInformation();

    public String getShapeName() {
        return shapeName;
    }

    public List<double[]> getShapeData() {
       return Collections.unmodifiableList(shapeData);
    }

    public int getShapeDataCount() {
        return shapeDataCount;
    }

    public void addShapeValues(double... shapeValues) {
        shapeData.add(shapeValues);
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }
}
