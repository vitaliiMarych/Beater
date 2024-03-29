package com.example.biter.Service;

import com.example.biter.Domain.Role;
import com.example.biter.Domain.User;
import com.example.biter.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addUser(User user){
        User foundUser = userRepo.findByUsername(user.getUsername());

        if (foundUser != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user){
        if (!user.getEmail().isEmpty()){
            String message = String.format("Hello, %s!\n" +
                            "Welcome to Beater. To activate mail, follow the link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode());

            mailSenderService.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null)
            return false;

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(Long user) {
        return userRepo.findById(user).orElse(null);
    }


    public void saveUser(Long userId, String username, Map<String, String> form) {
        User findedUser = findById(userId);

        findedUser.setUsername(username);

        Set<String> newRoles = new HashSet<>();

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        for (String role : form.keySet()){
            if (roles.contains(role) && !role.equals(Role.USER.name()))
                newRoles.add(role);

        }

        findedUser.getRoles().clear();
        findedUser.getRoles().add(Role.USER);
        for (String role : newRoles){
            findedUser.getRoles().add(Role.valueOf(role));
        }

        userRepo.save(findedUser);
    }

    public void saveProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = !userEmail.equals(email);

        if (isEmailChanged){
            user.setEmail(email);

            if (!email.isEmpty()){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepo.save(user);

        if (isEmailChanged)
            sendMessage(user);
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);

        userRepo.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);


        userRepo.save(user);
    }
}
