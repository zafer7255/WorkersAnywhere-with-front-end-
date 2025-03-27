package com.workersAnywhere.WorkersAnywhere.Controllers;

import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.Services.UserServices.JwtService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsersRepo repo;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Users user , Request request)
    {
        user.setRole(user.getRole().toUpperCase());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
<<<<<<< HEAD
        if (authentication.isAuthenticated()) {

            String role = repo.findByUsername(user.getUsername()).getRole();
            if (role.equals(user.getRole())) {
=======

        if (authentication.isAuthenticated()) {
            String role = repo.findByUsername(user.getUsername()).getRole();
            boolean verified = repo.findByUsername(user.getUsername()).isVerified();
            if (role.equals(user.getRole()) && verified) {
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
                String token = jwtService.generateToken(user.getUsername(), user.getRole());
                return new ResponseEntity(token, HttpStatus.ACCEPTED);
            }
            else
                return new ResponseEntity("Your profile is not matched change the role",HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity("Login Failed" , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
