package com.app.service;

import com.app.dto.RegisterRequestDTO;
import com.app.entity.AppUser;
import com.app.entity.Role;
import com.app.exception.UsernameNotFoundException;

import com.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     AppUser user = repo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not Found"));

     return new User(
             user.getEmail(),
             user.getPassword(),
             List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
     );


    }

    public String createUser(RegisterRequestDTO user) throws IllegalAccessException {

        if (repo.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalAccessException("User is already present");
        }

        AppUser user1 = new AppUser();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(Role.CASHIER);

        repo.save(user1);
        return "User Registered";


    }


    public List<AppUser> loadUsers(){
       return repo.findAll();
    }
}
