package com.workersAnywhere.WorkersAnywhere.Services.AdminService;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.Models.VerificationToken;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.AdminRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.VerificationTokenRepo;
import com.workersAnywhere.WorkersAnywhere.Services.EmailService.UsersEmailService;
import com.workersAnywhere.WorkersAnywhere.Services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class RegisterAdminService {
    private String role = "ADMIN";
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UserService userService;
    @Autowired
    VerificationTokenRepo tokenRepo;
    @Autowired
    UsersEmailService emailService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String RegisterAdmin(Admin admin) {

        for (Users u : usersRepo.findAll())
        {
            if (u.getUsername().equals(admin.getUsername()))
            {
                return "Username already exist try new one";
            }
        }
        admin.setPassword(encoder.encode(admin.getPassword()));
        if (userService.RegisterUser(admin.getUsername(),admin.getPassword(),role))
        {
            adminRepo.save(admin);

            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken();
            verificationToken.setToken(token);
            verificationToken.setUserUsername(admin.getUsername());
            verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));
            tokenRepo.save(verificationToken);
            CompletableFuture.runAsync(() -> {
                emailService.sendVerificationMail(admin.getEmail(), token);
            });

            return "We Send verification link to your email verify it";
        }
        else
        {
            return "Error to saving in User";
        }
    }
}
