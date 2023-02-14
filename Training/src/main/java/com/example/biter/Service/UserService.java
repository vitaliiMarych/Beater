package com.example.biter.Service;

import com.example.biter.Domain.Role;
import com.example.biter.Domain.User;
import com.example.biter.InfoMessages.InputInfoMessages;
import com.example.biter.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public int addUser(User user){
        User foundUser = userRepo.findByUsername(user.getUsername());

        if (foundUser != null){
            return InputInfoMessages.getExistUserCode();
        }

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return InputInfoMessages.getBadInputCode();
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if (!user.getEmail().isEmpty()){
            String message = String.format("Привіт, %s!\n" +
                    "Ласкаво просимо на Beater. Щоб активувати пошту перейдіть по посиланню: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode());

            mailSenderService.send(user.getEmail(), "Код активації пошти", message);
        }

        return InputInfoMessages.getGoodInputCode();
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null)
            return false;

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }
}
