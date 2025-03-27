package com.workersAnywhere.WorkersAnywhere.RepositoryDAO;

import com.workersAnywhere.WorkersAnywhere.Models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
}
