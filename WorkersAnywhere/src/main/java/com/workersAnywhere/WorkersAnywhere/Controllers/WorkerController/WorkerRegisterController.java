package com.workersAnywhere.WorkersAnywhere.Controllers.WorkerController;

import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.Services.WorkerServices.RegisterWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class WorkerRegisterController {

    @Autowired
    private RegisterWorkerService workerService;

    @PostMapping("/worker")
    public ResponseEntity<?> registerWorker(@RequestBody Worker worker)
    {
      String result = workerService.RegisterWorker(worker);
<<<<<<< HEAD
      if (result == "Username already exist try new one")
          return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
      else if (result == "Registered !!")
=======
      if (result.equals("Username already exist try new one"))
          return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
      else if (result.equals("We Send verification link to your email verify it"))
>>>>>>> 7eb755e (Updated WorkersAnywhere repo)
          return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
      else
          return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
