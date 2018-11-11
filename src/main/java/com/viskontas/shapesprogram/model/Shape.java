package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Shape {

    @Id
    @GeneratedValue
    private long id;
    @ElementCollection
    private List<double[]> shapeData;
    private int shapeDataCount;
    private String shapeName;

    protected Shape(String shapeName, int shapeDataCount) {
        this.shapeDataCount = shapeDataCount;
        this.shapeName = shapeName;
        shapeData = new ArrayList<>();
    }

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
