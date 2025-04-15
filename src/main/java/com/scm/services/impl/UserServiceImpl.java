package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        //generating a unique user id before saving each user

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // encoding password
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User currentUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        currentUser.setAbout(user.getAbout());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setProfilePic(user.getProfilePic());
        currentUser.setEnabled(user.isEnabled());
        currentUser.setEmailVerified(user.isEmailVerified());
        currentUser.setPhoneVerified(user.isPhoneVerified());
        currentUser.setProvider(user.getProvider());
        currentUser.setProviderUserId(user.getProviderUserId());
        User updatedUser = userRepository.save(currentUser);
        return Optional.ofNullable(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        userRepository.delete(currentUser);
    }

    @Override
    public boolean doesUserExist(String userId) {
        User currentUser = userRepository.findById(userId).orElse(null);
        return currentUser!=null ? true : false;
    }

    @Override
    public boolean doesUserExistByEmail(String email) {
        User currentUser = userRepository.findByEmail(email).orElse(null);
        return currentUser != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    

}
