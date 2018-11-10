package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    protected Shape(String shapeName, int shapeDataCount) {
        this.shapeDataCount = shapeDataCount;
        this.shapeName = shapeName;
        shapeData = new ArrayList<>();
    }

    public abstract void printInsideShapes(double... loopUpPoint);
    public abstract void printShapeInformation(int shapeDataId);
    abstract boolean insideCalculation(int shapeDataId, double... lookUpPoint);

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

    protected List<Integer> getShapeIdsWhichInsideShape(double... lookUpPoint) {
        return IntStream.range(0, shapeData.size())
                .filter(shapeDataId -> insideCalculation(shapeDataId, lookUpPoint))
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
