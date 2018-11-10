package com.viskontas.shapesprogram.repository;

import com.viskontas.shapesprogram.model.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapeRepository extends JpaRepository<Shape, Integer> {
}
