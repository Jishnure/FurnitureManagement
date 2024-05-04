package com.example.Project.Repository;

import com.example.Project.Entiry.Login;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Login,Long> {
    Login findByUsername(String username);

}
