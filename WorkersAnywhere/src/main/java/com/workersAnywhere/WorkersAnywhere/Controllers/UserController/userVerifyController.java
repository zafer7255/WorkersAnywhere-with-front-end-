package com.workersAnywhere.WorkersAnywhere.Controllers.UserController;

import com.workersAnywhere.WorkersAnywhere.Services.EmailService.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userVerifyController {

    @Autowired
    VerificationService verificationService;
    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token) {
        System.out.println("Verifying...");
        String result = verificationService.VerifyToken(token);
        return ResponseEntity.ok(result);
    }
}
