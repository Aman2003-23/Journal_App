package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//contains business logic
@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //there arre multiple password encoders one of its implementation is BCryptPassordEncoder
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private  static final Logger logger= LoggerFactory.getLogger(UserService.class);

    public boolean saveNewUser(User user) {
        try{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return true;
    }catch(Exception e){
        logger.error("error occurred for {}: ",user.getUsername(),e);
        return false;
        }
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserByName(ObjectId id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);


    }

    public void saveNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }
}
