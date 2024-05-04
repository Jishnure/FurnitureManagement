package com.example.Project.Repository;

import com.example.Project.Entiry.User;
import com.example.Project.Entiry.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
    Vendor findByEmail(String email);
}
