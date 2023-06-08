package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(method = RequestMethod.GET, value="/users")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> user = userService.getAllUsers();
            if (user.size() <= 0)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Result not found");
            }
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            // Handle the exception and return an appropriate response
            // You can customize the error message based on the exception type
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ArrayList<>());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        try{
            User user = userService.getUser(id);
            if (user == null)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id doesn't exists");
            }
            return ResponseEntity.ok(user);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new User());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Added");
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in adding a new user");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in adding a new user");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        try {
            userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Saved");
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in updating user");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in updating user");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        try{
            //Check for getResultId
            ResponseEntity<User> response= getUser(id);
            HttpStatus status = (HttpStatus) response.getStatusCode();
            if(status == HttpStatus.OK){
                userService.deleteUser(id);
                return ResponseEntity.status(HttpStatus.OK).body("Result Deleted");
            }
            else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No result found");
            }
        }
        catch (DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be deleted as user exists in Result");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting result");
        }
    }
}
