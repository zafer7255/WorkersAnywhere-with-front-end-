package com.workersAnywhere.WorkersAnywhere.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    private String username;
    private String password;
    private String name;
<<<<<<< HEAD
    private String Email;
=======
    private String email;
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
    private String address;
    private int pinCode;
    private String phoneNo;
    private String state;
<<<<<<< HEAD
=======
    private boolean verified = false;
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
}
