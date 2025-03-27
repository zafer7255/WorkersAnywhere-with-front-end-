package com.workersAnywhere.WorkersAnywhere.Services.CustomerService;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Users;
<<<<<<< HEAD
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.CustomerRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
=======
import com.workersAnywhere.WorkersAnywhere.Models.VerificationToken;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.CustomerRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.VerificationTokenRepo;
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
public class RegisterCustomerService {

    private final String role = "CUSTOMER";
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UserService userService;
<<<<<<< HEAD

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String RegisterCustomer(Customer customer) {

=======
    @Autowired
    VerificationTokenRepo tokenRepo;
    @Autowired
    UsersEmailService emailService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String RegisterCustomer(Customer customer) {
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
        for (Users u : usersRepo.findAll())
        {
            if (u.getUsername().equals(customer.getUsername()))
            {
                return "Username already exist try new one";
            }
        }
        customer.setPassword(encoder.encode(customer.getPassword()));
        if (userService.RegisterUser(customer.getUsername(),customer.getPassword(),role))
        {
            customerRepo.save(customer);
<<<<<<< HEAD
            return "Registered !!";
=======

            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken();
            verificationToken.setToken(token);
            verificationToken.setUserUsername(customer.getUsername());
            verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));
            tokenRepo.save(verificationToken);

            CompletableFuture.runAsync(() -> {
                emailService.sendVerificationMail(customer.getEmail(), token);
            });
            return "We Send verification link to your email verify it";
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
        }
        else
        {
            return "Error to saving in User";
        }
    }
}
