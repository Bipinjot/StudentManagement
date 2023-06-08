package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
//    private List<User> users= new ArrayList<>(Arrays.asList(
//            new User(1, "Bipin", "Hara", "bh@gmail.com", "1997-05-08"),
//                new User(1, "Harman", "Hara","hh@gmail.com", "1997-05-08"),
//                new User(3, "Omkar", "Tingre","oy@gmail.com", "1997-05-08"),
//                new User(4, "Subham", "Biswajit","sb@gmail.com", "1997-05-08")
//                ));
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); // Return the user or null if not found
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
