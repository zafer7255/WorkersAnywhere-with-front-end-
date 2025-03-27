package com.workersAnywhere.WorkersAnywhere.Services.WorkerServices;

import com.workersAnywhere.WorkersAnywhere.Models.Users;
<<<<<<< HEAD
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerRepo;
=======
import com.workersAnywhere.WorkersAnywhere.Models.VerificationToken;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.VerificationTokenRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerRepo;
import com.workersAnywhere.WorkersAnywhere.Services.EmailService.UsersEmailService;
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
import com.workersAnywhere.WorkersAnywhere.Services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
@Service
public class RegisterWorkerService {

    private final String role = "WORKER";
    @Autowired
    WorkerRepo workerRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UserService userService;
<<<<<<< HEAD
=======
    @Autowired
    VerificationTokenRepo tokenRepo;
    @Autowired
    UsersEmailService emailService;

>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String RegisterWorker(Worker worker)
    {
        for (Users u : usersRepo.findAll())
        {
            if (u.getUsername().equals(worker.getUsername()))
            {
                return "Username already exist try new one";
            }
        }
        worker.setPassword(encoder.encode(worker.getPassword()));
        if (userService.RegisterUser(worker.getUsername() , worker.getPassword(),role))
        {
            workerRepo.save(worker);
<<<<<<< HEAD
            System.out.println(workerRepo.findByUsername(worker.getUsername()));
            return "Registered !!";
=======

            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken();
            verificationToken.setToken(token);
            verificationToken.setUserUsername(worker.getUsername());
            verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));
            tokenRepo.save(verificationToken);

            CompletableFuture.runAsync(() -> {
                emailService.sendVerificationMail(worker.getEmail(), token);
            });
            return "We Send verification link to your email verify it";
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
        }
        else {
            return "Error to saving in User";
        }
    }

}
