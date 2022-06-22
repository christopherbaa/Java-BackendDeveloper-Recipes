package de.nutposit.javabackenddeveloper_recipes.service;

import de.nutposit.javabackenddeveloper_recipes.model.User;
import de.nutposit.javabackenddeveloper_recipes.model.UserDetailsImpl;
import de.nutposit.javabackenddeveloper_recipes.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(User user) {
        if(!userRepository.existsByEmail(user.getEmail())) {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            encodePassword(newUser, user);
            userRepository.save(newUser);
            return true;
        } else {
            return false;
        }
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void encodePassword(User newUser, User user) {
        newUser.setPassword(passwordEncoder().encode(user.getPassword()));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!this.userRepository.existsByEmail(username)) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(this.userRepository.findByEmail(username));
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
