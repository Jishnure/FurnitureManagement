package com.example.Project.Repository;

import com.example.Project.Entiry.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture,Long> {
}
