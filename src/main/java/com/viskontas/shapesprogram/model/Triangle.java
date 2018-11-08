package com.viskontas.shapesprogram.model;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Triangle extends Shape {

    public Triangle(String shapeName, double... shapeData) {
        super(shapeName, shapeData);
    }

    @Override
    public String getShapeInformation() {
        String a =Arrays.stream(shapeData).mapToObj(Double::toString)
            .collect( Collectors.joining( " "));
        return "Shape "+ id + ": triangle with coordinates (" + a +  ")";
    }

    @Override
    boolean isInsideShape(double... shapeData) {
        //https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/
        /*double x1 =

        float A = area (x1, y1, x2, y2, x3, y3);

        *//* Calculate area of triangle PBC *//*
        float A1 = area (x, y, x2, y2, x3, y3);

        *//* Calculate area of triangle PAC *//*
        float A2 = area (x1, y1, x, y, x3, y3);

        *//* Calculate area of triangle PAB *//*
        float A3 = area (x1, y1, x2, y2, x, y);

        *//* Check if sum of A1, A2 and A3 is same as A *//*
        return (A == A1 + A2 + A3); ;*/
        return false;
    }

    /*double area(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
    }*/
}
