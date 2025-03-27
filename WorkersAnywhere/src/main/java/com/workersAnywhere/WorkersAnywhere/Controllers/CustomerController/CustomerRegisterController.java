package com.workersAnywhere.WorkersAnywhere.Controllers.CustomerController;


import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Services.CustomerService.RegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)

@RestController
@RequestMapping("/register")
public class CustomerRegisterController {

    @Autowired
    RegisterCustomerService registerCustomerService;

<<<<<<< HEAD
    @PostMapping("customer")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer)
    {
        String result = registerCustomerService.RegisterCustomer(customer);
        if (result == "Username already exist try new one")
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        else if (result == "Registered !!")
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
=======
    @PostMapping("/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer)
    {
        String result = registerCustomerService.RegisterCustomer(customer);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);

        if (result.equals("Username already exist try new one"))
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        else if (result.equals("We Send verification link to your email verify it"))
            return new ResponseEntity<>(response,HttpStatus.OK);
        else
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
    }

}
