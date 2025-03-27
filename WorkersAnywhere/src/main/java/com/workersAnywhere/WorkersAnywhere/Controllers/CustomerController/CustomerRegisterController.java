package com.workersAnywhere.WorkersAnywhere.Controllers.CustomerController;


import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Services.CustomerService.RegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class CustomerRegisterController {

    @Autowired
    RegisterCustomerService registerCustomerService;

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
    }

}
