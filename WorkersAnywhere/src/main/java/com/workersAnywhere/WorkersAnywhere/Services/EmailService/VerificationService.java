package com.workersAnywhere.WorkersAnywhere.Services.EmailService;

import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.Models.VerificationToken;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationService {

    @Autowired
    VerificationTokenRepo tokenRepo;
    @Autowired
    UsersRepo usersRepo;

    public String VerifyToken(String token)
    {
        System.out.println("Verification");
        VerificationToken verificationToken = tokenRepo.findByToken(token);

        if (verificationToken.getToken().equals(null)){
            return "Invalid Token";
        } else if (!verificationToken.getToken().equals(token)){
            return "Something went wrong";
        } else if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "Token expired";
        }

        for (Users u : usersRepo.findAll()){
            if (u.getUsername().equals(verificationToken.getUserUsername())){
                u.setVerified(true);
                usersRepo.save(u);
                return "Verified";
            }
        }
        return "Something went wrong";

    }
}
