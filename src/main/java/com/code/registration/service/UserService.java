package com.code.registration.service;

import com.code.registration.model.User;
import com.code.registration.repository.UserRepository;
import org.attoparser.prettyhtml.PrettyHtmlMarkupHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String email, String password){
        var user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }
    public User authenticate(String username, String password){
        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(()-> new IllegalArgumentException("Wrong Username or password"));
    }

}
