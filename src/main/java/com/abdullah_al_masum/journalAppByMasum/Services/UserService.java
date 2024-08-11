package com.abdullah_al_masum.journalAppByMasum.Services;

import com.abdullah_al_masum.journalAppByMasum.Entity.User;
import com.abdullah_al_masum.journalAppByMasum.Repository.IUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    
    @Autowired
    private IUserRepository iUserRepository;
    
    public User create(User user) {
        return iUserRepository.save(user);
    }
    
    public List<User> getAllUsers(){
        return iUserRepository.findAll();
    }
    
    public Optional<User> getUserById(ObjectId id){
        return iUserRepository.findById(id);
    }
    
    public void deleteUserById(ObjectId id){
        iUserRepository.deleteById(id);
    }
    
    public User findByUserName(String username){
        return iUserRepository.findByUserName(username);
    }
    
}
